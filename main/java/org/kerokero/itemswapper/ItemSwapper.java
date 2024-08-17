package org.kerokero.itemswapper;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemSwapper extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginCommand swapCommand = this.getCommand("swap");
        if (swapCommand != null) {
            swapCommand.setExecutor(new ItemSwapCommand());
            getLogger().info("ItemSwap has been enabled!");
        } else {
            getLogger().severe("The swap command is not defined in plugin.yml or failed to load.");
            // Consider disabling the plugin if the command is critical
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
