package net.quimmilho.minecraft.plugins.library;

import net.quimmilho.minecraft.plugins.library.config.ConfigManager;
import net.quimmilho.minecraft.plugins.library.config.ConfigNotFound;
import net.quimmilho.minecraft.plugins.library.config.ConfigurationParsingException;
import net.quimmilho.minecraft.plugins.library.configurations.ServerConfiguration;

import java.io.IOException;

/**
 * Just a useless Main class
 */
public class Main {

    /**
     * Just a private constructor
     */
    private Main() {}

    /**
     * Main class for tests
     * @param args Program arguments
     */
    public static void main(String[] args) {
        ConfigManager cm = new ConfigManager();
        cm.createYAMLConfig("velocity", "server", new ServerConfiguration());
        try {
            ServerConfiguration sc;
            cm.load("server");
            if ((sc = cm.get("server", ServerConfiguration.class)) != null) {
                System.out.println(sc.getHost() + ":" + sc.getPort());
                sc.setHost("192.168.1.76");
                sc.setPort(8080);
                cm.set("server", sc);
                cm.save("server");
            }
        } catch (ConfigNotFound | ConfigurationParsingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
