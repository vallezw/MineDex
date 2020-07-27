package me.vallezw.MineDexPlugins.listeners;

import me.vallezw.MineDexPlugins.Main;
import me.vallezw.MineDexPlugins.mysql.DBConnection;
import me.vallezw.MineDexPlugins.utils.CheckPos;
import me.vallezw.MineDexPlugins.utils.Position;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class BlockEventListener implements Listener {

    private Main plugin;


    public BlockEventListener(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(!p.isOp()) {
            Location l = e.getBlock().getLocation();
            e.setCancelled(true);
            Position blockPos = new Position(l.getX(), l.getY(), l.getZ());

            try {
                if (!CheckPos.checkSquare(blockPos, p.getDisplayName())) {
                    e.setCancelled(true);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }
}
