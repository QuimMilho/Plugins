package net.quimmilho.minecraft.plugins.library.connection;

import net.quimmilho.minecraft.plugins.library.configurations.ServerConfiguration;
import net.quimmilho.minecraft.plugins.library.connection.packets.IllegalPacketType;
import net.quimmilho.minecraft.plugins.library.connection.packets.Packet;
import net.quimmilho.minecraft.plugins.library.connection.packets.ServerOpenPacket;

import java.io.IOException;
import java.net.Socket;

/**
 * Class for the creation of the client connection to the TCP server
 */
public class TCPConnection extends Thread implements Runnable {

    /**
     * The socket that stores the connection to the server
     */
    private final Socket socket;
    /**
     * The interface responsible for the processing of the data
     */
    private final IPacketsProcessor packetsProcessor;

    /**
     * The configuration of the server
     */
    private final ServerConfiguration serverConfig;
    /**
     * The TCP server connection hostname
     */
    private final String serverHost;
    /**
     * The TCP server connection port
     */
    private final int serverPort;

    /**
     * Constructor of the class
     * @param config The server configuration
     * @param packetsProcessor The interface responsible for the processing of the data
     * @param serverHost The minecraft server hostname
     * @param serverPort The minecraft server port
     * @throws IOException If the socket cannot be created
     */
    public TCPConnection(ServerConfiguration config, IPacketsProcessor packetsProcessor,
                 String serverHost, int serverPort) throws IOException {
        this.socket = new Socket(config.getHost(), config.getPort());
        this.packetsProcessor = packetsProcessor;
        this.serverConfig = config;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    /**
     * The thread function that recieves the data from the socket. It first sends the minecraft server information
     * and then waits for packets coming from the server
     */
    @Override
    public void run() {
        ServerOpenPacket packet = new ServerOpenPacket(socket, serverConfig.getServerType(), serverConfig.getServerName(),
                serverHost, serverPort);
        try {
            packet.send();

            while (!Thread.currentThread().isInterrupted()) {
                Packet p = Packet.getPacketFromSocket(socket);
                p.process(packetsProcessor);
            }
        } catch (IOException | IllegalPacketType e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the connection socket to the server
     * @return The connection socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Closes the connection socket
     * @throws IOException If the socket cannot be closed
     */
    public void close() throws IOException {
        socket.close();
    }

}
