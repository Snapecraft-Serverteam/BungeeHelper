package main;

import API.BanAPI;
import API.OfflineUniqueID;
import Listener.ChatListener;
import Listener.LoginListener;
import bansystem.BanCMDs;
import bansystem.unbanByUIDCMD;
import bansystem.unbanCMD;
import commands.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.UUID;

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

        getLogger().info(BanAPI.isBanned(UUID.fromString(OfflineUniqueID.getOfflineUUID("FrecherBruder"))).toString());
        getLogger().info(OfflineUniqueID.getOfflineUUID("FrecherBruder"));
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
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BanCMDs());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new unbanByUIDCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new unbanCMD());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BroadcastCMD());
        //EVENTS
        ProxyServer.getInstance().getPluginManager().registerListener(this, new LoginListener());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new ChatListener());
    }

}
