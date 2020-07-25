package me.vallezw.MineDexPlugins;

import me.vallezw.MineDexPlugins.commands.CoinDex;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        getLogger().info("MineDex has been enabled.");
        new CoinDex(this);
    }
}
