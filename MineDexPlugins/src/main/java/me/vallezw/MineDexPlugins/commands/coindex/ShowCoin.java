package me.vallezw.MineDexPlugins.commands.coindex;

import me.vallezw.MineDexPlugins.Main;
import me.vallezw.MineDexPlugins.mysql.DBConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class ShowCoin implements CommandExecutor {
    private Main plugin;

    public ShowCoin(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("showcoindex").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("showcoindex")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only Players are able to send this command");
                return true;
            }
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length != 1) {
                    player.sendMessage(ChatColor.YELLOW + "/showcoin " + ChatColor.GRAY + "<playername>");
                    return true;
                }
                Player newplayer = Bukkit.getPlayer(args[0]);
                if (newplayer == null) {
                    player.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.RED + " is not online or doesnt exist");
                    return true;
                }
                try {
                    int coins = DBConnection.getCoin(newplayer.getDisplayName());
                    player.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.WHITE + " has currently " + ChatColor.DARK_AQUA + String.valueOf(coins) + " coindex");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            }
            Bukkit.broadcastMessage("Only Operators are able to call this command");
            return true;
        }
        return false;
    }
}
