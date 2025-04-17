package net.quimmilho.minecraft.plugins.library.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.quimmilho.minecraft.plugins.library.configurations.Configuration;

import java.io.IOException;

/**
 * A class that saves configurations to YAML
 * @param <ConfigDataType> The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
 * class of the respective configuration data to save
 */
public non-sealed class YAMLConfig<ConfigDataType extends Configuration> extends
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
    YAMLConfig(String dirPath, String name, Class<ConfigDataType> configDataClass) {
        super(dirPath, name, configDataClass);
    }

    // OVERRIDES

    /**
     * Saves the configuration in YAML on the respective file
     * @param config The {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration} data
     * @throws IOException If the configuration can't be saved
     */
    @Override
    public void save(ConfigDataType config) throws IOException {
        if (!exists()) {
            createFile();
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).enable(SerializationFeature.INDENT_OUTPUT)
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writeValue(file, config);
    }

    /**
     * Loads the configuration from the YAML file
     * @return An object of the respective
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * @throws IOException If the configuration can't be loaded
     */
    @Override
    public ConfigDataType load() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).findAndRegisterModules();
        return mapper.readValue(file, getConfigDataClass());
    }

}
