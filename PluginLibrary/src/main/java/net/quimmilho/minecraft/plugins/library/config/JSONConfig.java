package net.quimmilho.minecraft.plugins.library.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.quimmilho.minecraft.plugins.library.configurations.Configuration;

import java.io.IOException;

/**
 * A class that saves configurations to JSON
 * @param <ConfigDataType> The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
 * class of the respective configuration data to save
 */
public non-sealed class JSONConfig <ConfigDataType extends Configuration> extends
        Config<ConfigDataType> {

    // CONSTRUCTOR

    /**
     * Constructor for a JSON Config
     * @param dirPath Directory inside the {@code CONFIG_PATH} for this config
     * @param name Name of this config
     * @param configDataClass The specific type of
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * this class will work with
     */
    JSONConfig(String dirPath, String name, Class<ConfigDataType> configDataClass) {
        super(dirPath, name, configDataClass);
    }

    // OVERRIDES
    /**
     * Saves the configuration in JSON on the respective file
     * @param config The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} data
     * @throws IOException If the configuration can't be saved
     */
    @Override
    public void save(ConfigDataType config) throws IOException {
        if (!exists()) {
            createFile();
        }
        ObjectMapper mapper = new ObjectMapper(new JsonFactory()).enable(SerializationFeature.INDENT_OUTPUT)
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writeValue(file, config);
    }

    /**
     * Loads the configuration from the JSON file
     * @return An object of the respective
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * @throws IOException If the configuration can't be loaded
     */
    @Override
    public ConfigDataType load() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory()).findAndRegisterModules();
        return mapper.readValue(file, getConfigDataClass());
    }

}
