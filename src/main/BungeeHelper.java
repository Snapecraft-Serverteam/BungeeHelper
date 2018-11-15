package main;

import Listener.ChatListener;
import Listener.LoginListener;
import bansystem.BanCMDs;
import bansystem.BanConfig;
import bansystem.unbanByUIDCMD;
import bansystem.unbanCMD;
import commands.*;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BungeeHelper extends Plugin {

    public static String prefix = "§7[§3System§7]§5 > §r";
    public static String noperm = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
    public static BungeeHelper plugin;

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        getLogger().info(ChatColor.BLUE +                "+-                         -+");
        getLogger().info(ChatColor.YELLOW +              "+-   BungeeHelper v.1.0    -+");
        getLogger().info(ChatColor.LIGHT_PURPLE +        "+-        by Mayus         -+");
        getLogger().info(ChatColor.BLUE +                "+-                         -+");
        getLogger().info(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        init();
        createFolders();
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        getLogger().info(ChatColor.BLUE +                "+-                         -+");
        getLogger().info(ChatColor.YELLOW +              "+-   BungeeHelper v.1.0    -+");
        getLogger().info(ChatColor.LIGHT_PURPLE +        "+-        by Mayus         -+");
        getLogger().info(ChatColor.BLUE +                "+-                         -+");
        getLogger().info(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public void init() {



        //BanConfig.setDefaultConfig();

        if(BanConfig.BanFile != null) {
            if(BanConfig.BanFile.length() == 0) {
                BanConfig.BanConfig.set("Settings.DoNotChange.currentBanID", 1);
            }
        }

        //COMMANDS
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MsgCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HelpCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new SpectateCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BanCMDs());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new unbanByUIDCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new unbanCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BroadcastCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new StaffChatCMD());
        //EVENTS
        BungeeCord.getInstance().getPluginManager().registerListener(this, new LoginListener());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new ChatListener());
    }

    public void createFolders()
    {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        BanConfig.BanFile = new File(getDataFolder().getPath(), "bans.yml");
        if (!BanConfig.BanFile.exists()) {
            try
            {
                BanConfig.BanFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BanConfig.BanConfig =
                    ConfigurationProvider.getProvider(YamlConfiguration.class).load(BanConfig.BanFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

