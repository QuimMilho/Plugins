package net.quimmilho.minecraft.plugins.library.connection.packets;

import net.quimmilho.minecraft.plugins.library.connection.IPacketsProcessor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Abstract class that represents a packet
 */
public sealed abstract class Packet permits ServerOpenPacket {

    /**
     * The socket where the packet will be sent or was recieved
     */
    protected final Socket socket;
    /**
     * The type of the packet
     */
    protected final PacketType packetType;

    /**
     * Basic packet constructor with the necessary information
     * @param socket The socket where this packet is going to be sent or has been recieved
     * @param packetType The packet type
     */
    protected Packet(Socket socket, PacketType packetType) {
        this.socket = socket;
        this.packetType = packetType;
    }

    /**
     * Sends the packet to the respective {@link Socket}
     * @throws IOException If the packet cannot be sent
     */
    public abstract void send() throws IOException;

    /**
     * Reads the information of a recieving packet
     * @param stream The {@link DataInputStream} of the respective {@link Socket}
     * @throws IOException If the packet can't be read
     */
    protected abstract void read(DataInputStream stream) throws IOException;

    /**
     * Processes the information of the respective packet using a plugin based aproach. This allows
     * for a generalized packet processing function across all diferent projects. Each packet type
     * has its own function for process.
     * @param packetsProcessor The instance of a {@link IPacketsProcessor} for the respective plugin
     */
    public abstract void process(IPacketsProcessor packetsProcessor);

    /**
     * Reads the recieving packet type and discovers the packet type. After that reads the packet and returns the information
     * @param socket The socket where it's reading the information from
     * @return The packet created with all the information
     * @throws IOException If some information cannot be read
     * @throws IllegalPacketType If the type of the packet is incompatible
     */
    public static Packet getPacketFromSocket(Socket socket) throws IOException, IllegalPacketType {
        DataInputStream stream = new DataInputStream(socket.getInputStream());
        int type = stream.readInt();
        if (type < 0 || type > PacketType.values().length) {
            throw new IllegalPacketType(type);
        }

        PacketType packetType = PacketType.values()[type];
        Packet p = switch (packetType) {
            case SERVER_OPEN -> new ServerOpenPacket(socket);
            case SERVER_CLOSE -> null;
            case SERVER_STATUS -> null;
            case PLAYER_COUNT -> null;

            case ASK_SERVERS -> null;
            case ASK_LOBBIES -> null;
            case MOVE_PLAYER -> null;

            case OPEN_SERVER -> null;
            case CLOSE_SERVER -> null;
        };

        p.read(stream);
        return p;
    }

}
