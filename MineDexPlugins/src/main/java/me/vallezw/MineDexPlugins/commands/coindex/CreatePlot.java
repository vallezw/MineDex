package me.vallezw.MineDexPlugins.commands.coindex;

import me.vallezw.MineDexPlugins.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreatePlot implements CommandExecutor {
    private Main plugin;

    public CreatePlot(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("createPlot").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("coindex")){

        }
        return false;
    }
}
