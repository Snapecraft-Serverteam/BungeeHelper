package main;

import bansystem.WarnCMD;
import listener.ChatListener;
import listener.LoginListener;
import rest.PlayerListRequestHandler;
import rest.PlayerOnlineRequestHandler;
import bansystem.BanCMD;
import bansystem.UnbanByUIDCMD;
import bansystem.UnbanCMD;
import com.sun.net.httpserver.HttpServer;
import commands.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.InetSocketAddress;

public class BungeeHelper extends Plugin {

    public static BungeeHelper instance = new BungeeHelper();
    public static BungeeHelper getInstance() { return instance; }

    public static String prefix = "§7[§3System§7]§5 > §r";
    public static String noperm = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
    public static BungeeHelper plugin;

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


        //COMMANDS
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MsgCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HelpCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new SpectateCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BanCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new UnbanByUIDCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new UnbanCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new WarnCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BroadcastCMD());
        //EVENTS
        ProxyServer.getInstance().getPluginManager().registerListener(this, new LoginListener());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new ChatListener());


        //Starting the rest api webserver
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

