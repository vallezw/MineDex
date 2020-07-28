package me.vallezw.MineDexPlugins.commands.coindex;

import me.vallezw.MineDexPlugins.Main;
import me.vallezw.MineDexPlugins.mysql.DBConnection;
import me.vallezw.MineDexPlugins.utils.Position;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

public class CreatePlot implements CommandExecutor {
    private Main plugin;

    public CreatePlot(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("createPlot").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("createPlot")){
            if (args.length > 7 || args.length < 7) {
                sender.sendMessage(ChatColor.YELLOW + "/createPlot" + ChatColor.GRAY + " <username> <x-Coordinate> <y-Coordinate <z-Coordinate>  <x-Coordinate> <y-Coordinate <z-Coordinate>");
            }
            String username = args[0];
            Position pos1 = new Position(Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3]));
            Position pos2 = new Position(Double.valueOf(args[4]), Double.valueOf(args[5]), Double.valueOf(args[6]));
            try {
                int id1 = DBConnection.addPosition(pos1, username);
                int id2 = DBConnection.addPosition(pos2, username);
                DBConnection.writeIds(id1, id2, username);
                sender.sendMessage("The plot was created");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
