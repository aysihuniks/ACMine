package nesoi.network;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ACMine extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        this.getCommand("acmine").setExecutor(new Command(this));
    }

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix", "Unkown") +  " " + message);
    }
 }
