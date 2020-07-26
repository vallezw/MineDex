package me.vallezw.MineDexPlugins.listeners;

import me.vallezw.MineDexPlugins.Main;
import me.vallezw.MineDexPlugins.mysql.DBConnection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.SQLException;

public class JoinListener implements Listener {
    private Main plugin;

    public JoinListener(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        PermissionAttachment attachment = p.addAttachment(plugin);
        attachment.setPermission("useplot", true);
        if (! p.hasPlayedBefore()){
            try {
                DBConnection.addUser(p.getDisplayName());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }

    }
}
