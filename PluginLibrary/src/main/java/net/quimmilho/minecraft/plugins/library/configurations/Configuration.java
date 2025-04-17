package net.quimmilho.minecraft.plugins.library.configurations;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Abstract class for all the configurations of the plugins
 */
public abstract class Configuration {

    // CONSTRUCTOR

    /**
     * Basic constructor for the configuration
     */
    protected Configuration() {}

    // ABSTRACT

    /**
     * Gets the specific child class of this object
     * @return Class object reference for the child class
     */
    @JsonIgnore
    public abstract Class<? extends Configuration> getConfigurationClass();

    /**
     * Allows the clonning of this object
     * @return A new object of this class with the same data
     */
    public abstract Configuration clone();

}
