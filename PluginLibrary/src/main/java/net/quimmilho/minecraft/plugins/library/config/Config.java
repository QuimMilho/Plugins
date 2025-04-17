package net.quimmilho.minecraft.plugins.library.config;

import net.quimmilho.minecraft.plugins.library.configurations.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An abstract class that is responsable for the main methods of plugins configurations
 * @param <ConfigDataType> The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
 * class of the respective configuration data to save
 */
public abstract sealed class Config <ConfigDataType extends Configuration> permits JSONConfig,
        YAMLConfig {

    // STATIC DATA

    /**
     * Full path for the plugin config directory
     */
    public static final String CONFIG_PATH;

    static {
        String p = Config.class.getProtectionDomain().getCodeSource().getLocation()
                .getPath();
        String[] dividedPath = p.split("[/\\\\]");
        StringBuilder path = new StringBuilder();
        System.out.println(dividedPath.length);
        for (int i = 0; i < dividedPath.length - 1; i++) {
            if (dividedPath[i].isEmpty()) {
                continue;
            }
            path.append(dividedPath[i]).append("/");
        }
        path.append("config").append("/");
        CONFIG_PATH = path.toString();
    }

    // DATA

    /**
     * Path for the directory inside the {@code CONFIG_PATH}
     */
    private final String dirPath;
    /**
     * {@code dirPath} + config file name
     */
    private final String fullPath;
    /**
     * Name of the configuration (without the .yml extension)
     */
    private final String name;
    /**
     * Reference to the object file in the {@code CONFIG_PATH + fullPath} location
     */
    protected final File file;

    /**
     * The specific type of {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * this class will work with
     */
    private final Class<ConfigDataType> configDataClass;

    // CONSTRUCTOR

    /**
     * Constructor for the config class
     * @param dirPath Directory inside the {@code CONFIG_PATH} for this config
     * @param name Name of this config
     * @param configDataClass The specific type of
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * this class will work with
     */
    protected Config(String dirPath, String name, Class<ConfigDataType> configDataClass) {
        this.dirPath = dirPath;
        this.name = name;
        this.fullPath = dirPath + File.separator + name + ".yml";
        this.file = new File(CONFIG_PATH, this.fullPath);
        this.configDataClass = configDataClass;
    }

    // FILE LOGIC

    /**
     * Creates the file for this config
     * @throws IOException Cannot create directories or files
     */
    public void createFile() throws IOException {
        Path path = Paths.get(CONFIG_PATH, this.dirPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        if (!Files.isDirectory(path)) {
            throw new IOException(this.dirPath + "is not a directory");
        }
        path = Paths.get(CONFIG_PATH, this.fullPath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    /**
     * Checks if the config file doesn't exist
     * @return Returns true if the file doesn't exist
     */
    public boolean notExists() {
        return !file.exists();
    }

    /**
     * Saves the configuration on the respective file
     * @param config The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} data
     * @throws IOException If the configuration can't be saved
     */
    public abstract void save(ConfigDataType config) throws IOException;

    /**
     * Loads the configuration from the file
     * @return An object of the respective
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * @throws IOException If the configuration can't be loaded
     */
    public abstract ConfigDataType load() throws IOException;

    // GETTERS

    /**
     * Gets the name of the config
     * @return The name of the configuration (without the .yml extension)
     */
    public String name() {
        return name;
    }

    /**
     * Gets the subdirectory path
     * @return The path for the directory inside the {@code CONFIG_PATH}
     */
    public String dirPath() {
        return dirPath;
    }

    /**
     * Gets the full config file path
     * @return {@code dirPath} + config file name
     */
    public String fullPath() {
        return fullPath;
    }

    /**
     * Gets the config class type
     * @return The specific type of {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * this class will work with
     */
    public Class<ConfigDataType> getConfigDataClass() {
        return configDataClass;
    }
}
