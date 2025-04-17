package net.quimmilho.minecraft.plugins.library.config;

/**
 * When there isn't a {@link net.quimmilho.minecraft.plugins.library.config.Config} with that name created yet
 */
public class ConfigNotFound extends Exception {

    // CONSTRUCTOR

    /**
     * Constructor for the Exception
     * @param configName The name of the config that doesn't exist
     */
    ConfigNotFound(String configName) {
        super("Config not found: " + configName);
    }

}
