package net.quimmilho.minecraft.plugins.library.configurations;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Configuration data for the connections to the server
 */
public class ServerConfiguration extends Configuration {

    // DATA

    /**
     * Server host (Client and Server side)
     */
    private String host;
    /**
     * Server port (Client and Server side)
     */
    private int port;

    // CONSTRUCTOR

    /**
     * Basic constructor for the configuration
     */
    public ServerConfiguration() {}

    // OVERRIDES

    /**
     * Gets the specific class
     * @return Class object reference for the child class
     */
    @Override
    @JsonIgnore
    public Class<? extends Configuration> getConfigurationClass() {
        return ServerConfiguration.class;
    }

    /**
     * Allows the clonning of this object
     * @return A new object of this class with the same data
     */
    @Override
    public ServerConfiguration clone() {
        ServerConfiguration config = new ServerConfiguration();
        config.setHost(host);
        config.setPort(port);
        return config;
    }

    /**
     * Sets the {@link ServerConfiguration} default values
     */
    @Override
    public void setDefaults() {
        host = "localhost";
        port = 8080;
    }

    // GETTERS AND SETTERS

    /**
     * Gets the server port
     * @return The server port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the server port
     * @param port The new server port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Gets the server hostname
     * @return The server hostname
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the server hostname
     * @param host The new server hostname
     */
    public void setHost(String host) {
        this.host = host;
    }

}
