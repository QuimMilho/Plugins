package net.quimmilho.minecraft.velocityPlugin;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import net.quimmilho.minecraft.plugins.library.config.ConfigManager;
import org.slf4j.Logger;

@Plugin(id = "velocityplugin", name = "VelocityPlugin", version = BuildConstants.VERSION, description = "Velocity Main Plugin", url = "quimmilho.net",
        authors = {"Joaquim Milheiro"})
public class VelocityPlugin {

    @Inject
    private Logger logger;

    private static ConfigManager configManager;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        loadConfig();

        
    }

    private void loadConfig() {
        configManager = new ConfigManager();

    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

}
