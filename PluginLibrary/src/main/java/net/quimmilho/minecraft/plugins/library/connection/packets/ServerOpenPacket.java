package net.quimmilho.minecraft.plugins.library.connection.packets;

import net.quimmilho.minecraft.plugins.library.connection.IPacketsProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Packet for when a server is open
 */
public non-sealed class ServerOpenPacket extends Packet {

    /**
     * The minecraft server name
     */
    private String serverName;

    /**
     * The minecraft server type
     */
    private String serverType;

    /**
     * The minecraft server hostname
     */
    private String hostName;

    /**
     * The minecraft server port
     */
    private int port;

    /**
     * Basic constructor for the packet
     * @param socket The current socket
     */
    ServerOpenPacket(Socket socket) {
        super(socket, PacketType.SERVER_OPEN);
    }

    /**
     * Constructor for creating a new ServerOpenPacket ready to be sent
     * @param s The current socket
     * @param serverType The minecraft server type
     * @param serverName The minecraft server name
     * @param hostName The minecraft server hostname
     * @param port The minecraft server port
     */
    public ServerOpenPacket(Socket s, String serverType, String serverName, String hostName, int port) {
        super(s, PacketType.SERVER_OPEN);
        this.serverName = serverName;
        this.serverType = serverType;
        this.hostName = hostName;
        this.port = port;
    }

    /**
     * Sends the information in the Packet to the socket
     * @throws IOException If the data cannot be sent
     */
    @Override
    public void send() throws IOException {
        DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        stream.writeInt(packetType.ordinal());
        stream.writeUTF(serverType);
        stream.writeUTF(serverName);
        stream.writeUTF(hostName);
        stream.writeInt(port);
    }

    /**
     * Reads the information from the socket
     * @param stream The {@link DataInputStream} of the respective {@link Socket}
     * @throws IOException If the data cannot be read
     */
    @Override
    protected void read(DataInputStream stream) throws IOException {
        serverType = stream.readUTF();
        serverName = stream.readUTF();
        hostName = stream.readUTF();
        port = stream.readInt();
    }

    /**
     * Processes the data using the {@code serverOpen} function of the interface {@link IPacketsProcessor}
     * @param packetsProcessor The instance of a {@link IPacketsProcessor} for the respective plugin
     */
    @Override
    public void process(IPacketsProcessor packetsProcessor) {
        packetsProcessor.serverOpen(this);
    }

    /**
     * Gets the server name
     * @return The server name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Gets the server type
     * @return The server type
     */
    public String getServerType() {
        return serverType;
    }

    /**
     * Gets the server hostname
     * @return The server hostname
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Gets the server port
     * @return The server port
     */
    public int getPort() {
        return port;
    }

}
