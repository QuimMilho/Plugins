package net.quimmilho.minecraft.plugins.library.connection;

import net.quimmilho.minecraft.plugins.library.configurations.ServerConfiguration;
import net.quimmilho.minecraft.plugins.library.connection.packets.IllegalPacketType;
import net.quimmilho.minecraft.plugins.library.connection.packets.Packet;
import net.quimmilho.minecraft.plugins.library.connection.packets.ServerOpenPacket;
import net.quimmilho.minecraft.plugins.library.data.Pair;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for the creation of a TCP server
 */
public class TCPServer extends Thread implements Runnable {

    /**
     * The server socket
     */
    private final ServerSocket server;
    /**
     * The interface responsible for the processing of the data
     */
    private final IPacketsProcessor packetsProcessor;

    /**
     * The list of connections that exist, stored with a {@link Pair} where the first element is
     * the server type and the second element is the server name.
     */
    private final HashMap<Pair<String, String>, TCPServerConnection> connections;

    /**
     * The constructor of the TCP Server class
     * @param config The server config
     * @param packetsProcessor The interface responsible for the processing of the data
     * @throws IOException If the server cannot be open
     */
    public TCPServer(ServerConfiguration config, IPacketsProcessor packetsProcessor) throws IOException {
        connections = new HashMap<>();
        this.packetsProcessor = packetsProcessor;

        InetAddress address = InetAddress.getByName(config.getHost());
        server = new ServerSocket(config.getPort(), 10, address);

        System.out.println("Server started on " + config.getHost() + ":" + config.getPort());
    }

    /**
     * The thread function that waits for connections and opens the {@link TCPServerConnection} class where the data
     * is processed
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket s = server.accept();
                Packet p = Packet.getPacketFromSocket(s);

                if (p instanceof ServerOpenPacket sop) {
                    TCPServerConnection connection = new TCPServerConnection(s, packetsProcessor);
                    connections.put(new Pair<>(sop.getServerType(), sop.getServerName()), connection);
                    connection.start();
                }
            } catch (IOException | IllegalPacketType e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Gets the socket of the server with the respective {@code name}
     * @param name The name of the minecraft server
     * @return The socket connection
     */
    public Socket getSocket(String name) {
        for (Pair<String, String> pair : connections.keySet()) {
            if (pair.getSecond().equals(name)) {
                return connections.get(pair).getSocket();
            }
        }
        return null;
    }

    /**
     * Removes and closes the connection from the respective server
     * @param name The minecraft server name
     * @return If the connection was found or not
     */
    public boolean removeConnection(String name) throws IOException {
        for (Pair<String, String> pair : connections.keySet()) {
            if (pair.getSecond().equals(name)) {
                TCPServerConnection c = connections.get(pair);
                c.close();
                connections.remove(pair);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the names of the minecraft servers
     * @return The list of names of the minecraft servers
     */
    public List<String> getServerNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Pair<String, String> pair : connections.keySet()) {
            names.add(pair.getSecond());
        }
        return names;
    }

}
