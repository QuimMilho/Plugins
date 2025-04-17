package net.quimmilho.minecraft.plugins.library.config;

import net.quimmilho.minecraft.plugins.library.configurations.Configuration;

/**
 * An exception for when the types of {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
 * are different
 */
public class ConfigurationParsingException extends Exception {

    // CONSTRUCTOR

    /**
     * Constructor for the Exception
     * @param classNameA Original type of the
     * {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     * @param classNameB Wrong type being used of
     *                   {@link net.quimmilho.minecraft.plugins.library.configurations.Configuration}
     */
    ConfigurationParsingException(Class<? extends Configuration> classNameA,
                Class<? extends Configuration> classNameB) {
        super("Configuration type " + classNameA.getName() + " is not assignable from " + classNameB.getName());
    }

}
