package me.vallezw.MineDexPlugins;

import me.vallezw.MineDexPlugins.commands.coindex.CoinDex;
import me.vallezw.MineDexPlugins.commands.coindex.Pay;
import me.vallezw.MineDexPlugins.listeners.JoinListener;
import me.vallezw.MineDexPlugins.mysql.DBConnection;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        getLogger().info("MineDex has been enabled.");
        try {
            DBConnection.createtable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new CoinDex(this);
        new Pay(this);
        new JoinListener(this);
    }
}
