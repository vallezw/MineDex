package me.vallezw.MineDexPlugins.listeners;

import me.vallezw.MineDexPlugins.Main;
import me.vallezw.MineDexPlugins.mysql.DBConnection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerHarvestBlockEventListener implements Listener {

    private Main plugin;


    public PlayerHarvestBlockEventListener(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLog(){
        Bukkit.broadcastMessage("Player harvested BLock");
    }
}
