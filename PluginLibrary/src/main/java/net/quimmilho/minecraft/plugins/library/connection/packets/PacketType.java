package net.quimmilho.minecraft.plugins.library.connection.packets;

/**
 * Enum for the types of packets
 */
public enum PacketType {

    // SINGLE SERVER RELATED
    /**
     * When a server is open
     */
    SERVER_OPEN,
    /**
     * When a server is closed
     */
    SERVER_CLOSE,
    /**
     * When sending / recieving a server status update
     */
    SERVER_STATUS,
    /**
     * When sending / recieving a player count update
     */
    PLAYER_COUNT,
    // PROXY RELATED
    /**
     * When asking for the list of servers
     */
    ASK_SERVERS,
    /**
     * When asking for a player to be moved
     */
    MOVE_PLAYER,
    /**
     * When asking for a list of servers of a certain type
     */
    ASK_LOBBIES,
    // CONTROL
    /**
     * When asking to force close a server
     */
    CLOSE_SERVER,
    /**
     * When asking to force open a server
     */
    OPEN_SERVER;

    /**
     * Simple constructor
     */
    PacketType() {}

}
