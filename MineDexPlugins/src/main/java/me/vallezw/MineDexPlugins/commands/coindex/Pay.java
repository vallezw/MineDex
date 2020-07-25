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
import java.util.Arrays;

public class Pay implements CommandExecutor {
    private Main plugin;

    public Pay(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("pay").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("pay")){
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only Players are able to send this command");
                return true;
            }
            Player player = (Player) sender;
            int arglength = args.length;
            if (arglength != 2){
                player.sendMessage(ChatColor.YELLOW + "/pay" + ChatColor.GRAY + " <player> <amount>");
                return true;
            }
            int amount = Integer.valueOf(args[1]);
            DBConnection connection = new DBConnection();
            try {
                int senderBalance = connection.getCoin(player.getDisplayName());
                if (senderBalance < amount){
                    player.sendMessage("You have not enough " + ChatColor.RED + "coindex");
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection.payUser(player.getDisplayName(), args[0], amount);
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
