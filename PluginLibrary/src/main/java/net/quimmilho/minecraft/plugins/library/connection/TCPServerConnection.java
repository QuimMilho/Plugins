package net.quimmilho.minecraft.plugins.library.connection;

import net.quimmilho.minecraft.plugins.library.connection.packets.IllegalPacketType;
import net.quimmilho.minecraft.plugins.library.connection.packets.Packet;

import java.io.IOException;
import java.net.Socket;

/**
 * Class of the server connections to each client
 */
public class TCPServerConnection extends Thread implements Runnable {

    /**
     * The connection socket (server side)
     */
    private final Socket socket;
    /**
     * The interface responsible for the processing of the data
     */
    private final IPacketsProcessor packetsProcessor;

    /**
     * The constructor for the connection
     * @param socket The connection socket
     * @param packetsProcessor The interface responsible for the processing of the data
     */
    TCPServerConnection(Socket socket, IPacketsProcessor packetsProcessor) {
        this.socket = socket;
        this.packetsProcessor = packetsProcessor;
    }

    /**
     * The thread function responsible for the acceptance of packets
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Packet p = Packet.getPacketFromSocket(socket);
                p.process(packetsProcessor);
            }
        } catch (IOException | IllegalPacketType e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the server side connection socket
     * @return The connection socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Closes the connection socket
     * @throws IOException If the connection cannot be closed
     */
    public void close() throws IOException {
        socket.close();
    }

}
