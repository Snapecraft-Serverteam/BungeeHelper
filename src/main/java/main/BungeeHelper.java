package main;

import Listener.ChatListener;
import Listener.LoginListener;
import REST.PlayerListRequestHandler;
import REST.PlayerOnlineRequestHandler;
import bansystem.BanCMDs;
import bansystem.unbanByUIDCMD;
import bansystem.unbanCMD;
import com.sun.net.httpserver.HttpServer;
import commands.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class BungeeHelper extends Plugin {

    public static BungeeHelper instance = new BungeeHelper();
    public static BungeeHelper getInstance() { return instance; }

    public static String prefix = "§7[§3System§7]§5 > §r";
    public static String noperm = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
    public static BungeeHelper plugin;
    public static Configuration configuration;

    @Override
    public void onEnable() {
        logInfo();
        init();
    }

    private void logInfo() {
        getLogger().info(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        getLogger().info(ChatColor.BLUE +                "+-                         -+");
        getLogger().info(ChatColor.YELLOW +              "+-   main.BungeeHelper v.1.0    -+");
        getLogger().info(ChatColor.LIGHT_PURPLE +        "+-        by Mayus         -+");
        getLogger().info(ChatColor.BLUE +                "+-                         -+");
        getLogger().info(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    @Override
    public void onDisable() {
        logInfo();
    }

    public void init() {

        initConfig();

        //COMMANDS
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MsgCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HelpCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new SpectateCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BanCMDs());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new unbanByUIDCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new unbanCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BroadcastCMD());
        //EVENTS
        ProxyServer.getInstance().getPluginManager().registerListener(this, new LoginListener());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new ChatListener());


        //Starting the REST API webserver
        if(configuration.getBoolean("restapi.enabled")) {
            HttpServer server = null;
            try {
                server = HttpServer.create(new InetSocketAddress(8000), 0);
                server.createContext("/isplayeronline", new PlayerOnlineRequestHandler());
                server.createContext("/playerlist", new PlayerListRequestHandler());
                server.setExecutor(null); // creates a default executor
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void initConfig() {

        // Use config template if no config is present
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void reloadConfig() {
        File file = new File(BungeeHelper.getInstance().getDataFolder(), "config.yml");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(BungeeHelper.getInstance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

