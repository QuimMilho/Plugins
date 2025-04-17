package net.quimmilho.minecraft.plugins.library.connection;

import net.quimmilho.minecraft.plugins.library.connection.packets.ServerOpenPacket;

public interface IPacketsProcessor {

    /**
     * Executes the function when recieves a {@link ServerOpenPacket}
     * @param packet The respective packet
     */
    void serverOpen(ServerOpenPacket packet);
    void serverClose();
    void serverStatus();
    void playerCount();

    void askServers();
    void askLobbies();
    void movePlayer();

    void closeServer();
    void openServer();

}
