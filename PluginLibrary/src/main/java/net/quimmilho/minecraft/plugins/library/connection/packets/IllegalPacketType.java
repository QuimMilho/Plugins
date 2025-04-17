package net.quimmilho.minecraft.plugins.library.connection.packets;

/**
 * Exception for when reading an invalid type of {@link Packet}
 */
public class IllegalPacketType extends Exception {

    /**
     * Basic constructor for when the error is with an index
     * @param index The index of the {@link PacketType}
     */
    public IllegalPacketType(int index) {
        super("Illegal packet type: " + index);
    }

}
