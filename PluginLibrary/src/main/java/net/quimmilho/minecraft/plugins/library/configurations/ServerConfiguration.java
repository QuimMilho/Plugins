package net.quimmilho.minecraft.plugins.library.configurations;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Configuration data for the connections to the server
 */
public class ServerConfiguration extends Configuration {

    // DATA

    /**
     * The name of the current minecraft server (mainly used by the proxy)
     */
    private String serverName;
    /**
     * The server type (minigame or lobby name)
     */
    private String serverType;

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
        serverName = "server-1";
        serverType = "server";
    }

    // GETTERS AND SETTERS

    /**
     * Gets the server type (minigame or lobby name)
     * @return The server type
     */
    public String getServerType() {
        return serverType;
    }

    /**
     * Sets the server type (minigame or lobby name)
     * @param serverType The new server type
     */
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    /**
     * Gets the server name
     * @return The server name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the server name
     * @param serverName The new server name
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

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
