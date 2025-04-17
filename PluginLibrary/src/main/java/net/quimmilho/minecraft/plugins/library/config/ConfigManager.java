package net.quimmilho.minecraft.plugins.library.config;

import net.quimmilho.minecraft.plugins.library.configurations.Configuration;

import java.io.IOException;
import java.util.HashMap;

/**
 * The class that stores and controls all configurations
 */
public class ConfigManager {

    // DATA

    /**
     * All the {@link net.quimmilho.minecraft.plugins.library.config.Config}
     * objects are stored with the respective name
     */
    private final HashMap<String, Config<?>> configs;
    /**
     * All the {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * objects are stored with the respective name
     */
    private final HashMap<String, Configuration> configsData;

    // CONSTRUCTOR

    /**
     * Basic constructor
     */
    public ConfigManager() {
        this.configs = new HashMap<>();
        this.configsData = new HashMap<>();
    }

    // FILE MANAGEMENT

    /**
     * Creates a new YAML Config
     * @param dirPath The directory inside {@code Config.CONFIG_PATH} where the file will be stored
     * @param name The name of the config
     * @param configData The data class instance that will be stored
     * @param <T> The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * data class that will be stored in the configuration
     */
    public <T extends Configuration> void createYAMLConfig(String dirPath, String name,
                   T configData) {
        Config<? extends Configuration> config = new YAMLConfig<>(dirPath, name, configData.getClass());
        configs.put(name, config);
        configsData.put(name, configData);
    }

    /**
     * Creates a new JSON Config
     * @param dirPath The directory inside {@code Config.CONFIG_PATH} where the file will be stored
     * @param name The name of the config
     * @param configData The data class instance that will be stored
     * @param <T> The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * data class that will be stored in the configuration
     */
    public <T extends Configuration> void createJSONConfig(String dirPath, String name,
                   T configData) {
        JSONConfig<? extends Configuration> config = new JSONConfig<>(dirPath, name, configData.getClass());
        configs.put(name, config);
        configsData.put(name, configData);
    }

    /**
     * Checks if the configuration file of the config with respective {@code name} exists
     * @param name The name of the config
     * @return If the file exists
     */
    public boolean fileExists(String name) {
        Config c = this.configs.get(name);
        if (c == null) {
            return false;
        }
        return c.exists();
    }

    /**
     * Creates a new configuration file for the config with the respective {@code name}
     * @param name The name of the config
     * @throws IOException If the configuration file cannot be created
     * @throws ConfigNotFound If the config does not exist
     */
    public void createFile(String name) throws IOException, ConfigNotFound {
        Config c = this.configs.get(name);
        if (c == null) {
            throw new ConfigNotFound(name);
        }
        c.createFile();
    }

    /**
     * Saves the config with the respective {@code name} to the file
     * @param name The name of the config to be saved
     * @throws ConfigNotFound If the config doesn't exist
     * @throws IOException If the file can't be saved
     */
    public void save(String name) throws ConfigNotFound, IOException {
        if (!this.configs.containsKey(name)) {
            throw new ConfigNotFound(name);
        }

        Configuration config = this.configsData.get(name);
        Config configData = this.configs.get(name);

        configData.save(config);
    }

    /**
     * Loads the config with the respective {@code name} from the file
     * @param name The name of the config to be loaded
     * @throws IOException If the file can't be read
     * @throws ConfigNotFound If the config doesn't exist
     */
    public void load(String name) throws IOException, ConfigNotFound {
        if (!this.configs.containsKey(name)) {
            throw new ConfigNotFound(name);
        }

        Config<?> configData = this.configs.get(name);

        configsData.put(name, configData.load());
    }

    // DATA MANAGEMENT

    /**
     * Checks if the configuration with {@code name} exists
     * @param name The name of the configuration
     * @return If the config exists
     */
    public boolean exists(String name) {
        return this.configs.containsKey(name);
    }

    /**
     * Sets the information in the hashmap to the one that is passed on the arguments
     * @param name The name of the configuration
     * @param config The configuration data to be replaced
     * @throws ConfigNotFound If the config doesn't exist
     * @throws ConfigurationParsingException If both
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} don't match
     */
    public void set(String name, Configuration config) throws ConfigNotFound,
            ConfigurationParsingException {
        if (!this.configs.containsKey(name)) {
            throw new ConfigNotFound(name);
        }
        Config<?> configData = this.configs.get(name);
        if (!config.getConfigurationClass().isAssignableFrom(configData.getConfigDataClass())) {
            throw new ConfigurationParsingException(config.getConfigurationClass(),
                    configData.getConfigDataClass());
        }
        configsData.put(name, config);
    }

    /**
     * Gets a clone of the configuration with the respective {@code name}
     * @param name The name of the configuration
     * @param clazz The information about the
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} type
     * @return The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} of the respectve type
     * @param <T> The respective {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} class type
     * @throws ConfigNotFound If the config doesn't exist
     * @throws ConfigurationParsingException If both
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} don't match
     */
    public <T extends Configuration> T get(String name, Class<T> clazz) throws ConfigNotFound,
            ConfigurationParsingException {
        if (!this.configsData.containsKey(name)) {
            throw new ConfigNotFound(name);
        }
        Configuration a = this.configsData.get(name);
        if (clazz.isInstance(a)) {
            return clazz.cast(a.clone());
        } else {
            throw new ConfigurationParsingException(clazz, a.getConfigurationClass());
        }
    }

}
