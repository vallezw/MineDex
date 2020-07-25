package me.vallezw.MineDexPlugins.commands;

import me.vallezw.MineDexPlugins.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinDex implements CommandExecutor {

    private Main plugin;

    public CoinDex(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("coindex").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("coindex")){
            Player player = (Player) sender;
            player.sendMessage("HEyoo gj!");
            return true;
        }
        return false;
    }
}
