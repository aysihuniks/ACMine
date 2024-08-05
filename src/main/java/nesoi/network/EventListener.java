package nesoi.network;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.util.Set;

public class EventListener implements Listener {

    private final ACMine plugin;

    public EventListener(ACMine plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material brokenBlock = block.getType();
        byte blockData = block.getData();

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection blocksSection = config.getConfigurationSection("blocks");

        if (blocksSection != null) {
            Set<String> blockKeys = blocksSection.getKeys(false);

            for (String key : blockKeys) {
                ConfigurationSection blockConfig = blocksSection.getConfigurationSection(key);

                int configBlockId = blockConfig.getInt("block_id");
                byte configBlockData = (byte) blockConfig.getInt("block_data");
                int chance = blockConfig.getInt("chance");
                String command = blockConfig.getString("command");

                Material configuredBlock = getMaterialFromId(configBlockId);

                if (configuredBlock != null && brokenBlock == configuredBlock && blockData == configBlockData) {
                    if (Math.random() * 100 < chance) {
                        String finalCommand = command.replace("%player%", player.getName());
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), finalCommand);
                    }
                }
            }
        }
    }

    private Material getMaterialFromId(int id) {
        for (Material material : Material.values()) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }
}
