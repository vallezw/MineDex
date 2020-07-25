package me.vallezw.MineDexPlugins.commands.coindex;

import me.vallezw.MineDexPlugins.Main;
import me.vallezw.MineDexPlugins.mysql.DBConnection;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CoinDex implements CommandExecutor {

    private Main plugin;

    public CoinDex(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("coindex").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("coindex")){
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only Players are able to send this command");
                return true;
            }
            Player player = (Player) sender;
            try {
                int coins = DBConnection.getCoin(player.getDisplayName());
                player.sendMessage("You currently have " + ChatColor.DARK_AQUA + coins + " coindex");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
