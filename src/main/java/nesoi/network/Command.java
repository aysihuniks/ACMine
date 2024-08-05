package nesoi.network;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    private final ACMine plugin;

    public Command(ACMine plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("acmine.reload")) {
                    reloadConfig();
                    player.sendMessage(plugin.colorize("&aConfiguration files successfully reloaded."));
                } else {
                    player.sendMessage(plugin.colorize("&cYou don't have the permission to do that."));
                }
            } else {
                reloadConfig();
                sender.sendMessage(plugin.colorize("&aConfiguration files successfully reloaded."));
            }
            return true;
        } else {
            sender.sendMessage(plugin.colorize("&cUnknown command."));
        }
        return true;
    }

    private void reloadConfig() {
        plugin.reloadConfig();
    }
}
