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
            if (arglength != 2){ // Unvalid Arguments
                player.sendMessage(ChatColor.YELLOW + "/pay" + ChatColor.GRAY + " <player> <amount>");
                return true;
            }

            int amount = Integer.valueOf(args[1]);
            Player receiver = Bukkit.getPlayer(args[0]);

            if(receiver == null){ // Receiver isnt found or doesnt exist.
                player.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.RED + " is not online or doesnt exist");
                return true;
            }
            else if (receiver == player){ // Receiver is the Sender
                player.sendMessage(ChatColor.RED + "You cant pay yourself");
                return true;
            }
            DBConnection connection = new DBConnection();
            try {
                int senderBalance = connection.getCoin(player.getDisplayName());
                if (senderBalance < amount){ // Not enough Balance
                    player.sendMessage(ChatColor.RED  + "You dont have enough coindex." + ChatColor.GRAY + " You currently have " + ChatColor.DARK_AQUA + senderBalance + " coindex");
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try { // Paying in the database
                int newSenderBalance = connection.getCoin(player.getDisplayName()) - amount;
                int newReceiverBalance = connection.getCoin(receiver.getDisplayName()) + amount;
                connection.payUser(player.getDisplayName(), args[0], amount);
                player.sendMessage(ChatColor.GRAY + "You payed " + ChatColor.YELLOW + receiver.getDisplayName() + " "+ ChatColor.DARK_AQUA + String.valueOf(amount) + " coindex." + ChatColor.GRAY + " Now you have " + ChatColor.DARK_AQUA + newSenderBalance + " coindex");
                receiver.sendMessage(ChatColor.YELLOW + player.getDisplayName() +ChatColor.GRAY +  " payed you " + ChatColor.DARK_AQUA +  String.valueOf(amount) + " coindex." + ChatColor.GRAY  + " Now you have " + ChatColor.DARK_AQUA + newReceiverBalance + " coindex");
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
