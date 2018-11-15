package Listener;

import API.BanAPI;
import main.BungeeHelper;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.api.plugin.Listener;
import ranks.DisplayNameRank;

import java.util.concurrent.TimeUnit;

public class LoginListener implements Listener {
    /*
    @EventHandler
    public void on(LoginEvent e)
    {
        //DisplayNameRank.setDisplayName(e.getPlayer());
        String currentPlayers = BungeeCord.getInstance().getOnlineCount() + "";

        //String currentServer = "Lobby";

        String Header = "§lSnape§6Craft §8⨉ §7Dein Minecraft Minigame Netzwerk §b➟§r" + currentPlayers +"/1000"/*\n§b‣ §7Current Server: §b" + currentServer + "\n";

        String Footer = "\n§l§bDiscord: snapecraft.ddns.net/discord\n" +
                        "§l§7Website: snapecraft.ddns.net\n§r" +
                        "§8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯\n" +
                        "§l§9Wir suchen Teammitglieder!§r\n" +
                        "➠§lDeveloper §r ➠§lSupporter";

        try
        {
            for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers())
            {
                p.setTabHeader(new TextComponent(Header), new TextComponent(Footer));
            }
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
    }


    @EventHandler
    public void on(ServerDisconnectEvent e)
    {
        String Header = "§lSnape§6Craft §8⨉ §7Dein Minecraft Minigame Netzwerk §b➟§r" + BungeeCord.getInstance().getOnlineCount() +"/1000"/*\n§b‣ §7Current Server: §b" + currentServer + "\n";

        String Footer = "\n§l§bDiscord: snapecraft.ddns.net/discord\n" +
                "§l§7Website: snapecraft.ddns.net\n§r" +
                "§8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯\n" +
                "§l§9Wir suchen Teammitglieder!§r\n" +
                "➠§lDeveloper §r ➠§lSupporter";
        try
        {
            for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers())
            {
                p.setTabHeader(new TextComponent(Header), new TextComponent(Footer));
            }
        }
        catch (Exception localException) {}
    }

    @EventHandler
    public void on(ServerSwitchEvent e)
    {

        String currentPlayers = BungeeCord.getInstance().getOnlineCount() + "";
        String currentServer = BungeeCord.getInstance().getPlayer(e.getPlayer().getUniqueId()).getServer().getInfo().getName();
        String Header = "§lSnape§6Craft >> §7Dein Minecraft Minigame Netzwerk §a➜§r " + currentPlayers + "/1000\n§a‣§7Current Server: §b" + currentServer + "\n\n";

        String Footer = "\n§l§bDiscord: snapecraft.ddns.net/discord\n" +
                "§l§7Website: snapecraft.ddns.net\n§r" +
                "§8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯\n" +
                "§l§9Wir suchen Teammitglieder!§r\n" +
                "➠§lDeveloper §r ➠§lSupporter";
        try
        {
            BungeeCord.getInstance().getScheduler().schedule(BungeeHelper.plugin, new Runnable()
            {
                public void run()
                {
                    for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers())
                    {
                       p.setTabHeader(new TextComponent(Header), new TextComponent(Footer));
                    }
                }
            }, 15L, TimeUnit.MILLISECONDS);
        }
        catch (Exception localException) {
            e.getPlayer().sendMessage(localException.getMessage());
        }
    }
*/
    @EventHandler
    public void onLogin(LoginEvent e) {

        if(BanAPI.isBanned(e.getConnection().getName())) {

            //Ist der Spieler NICHT permanent gebannt?
            if(BanAPI.getBannedUntil(e.getConnection().getName()) != -1) {

                //Wenn NEIN


                //Ist der Bann des Spielers bereits abgelaufen?
                if(BanAPI.getBannedUntil(e.getConnection().getName()) < System.currentTimeMillis()) {
                    BanAPI.unbanPlayer(e.getConnection().getName());
                } else {
                    e.setCancelled(true);
                    e.setCancelReason("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(e.getConnection().getName()) + "  §7#" + BanAPI.getBanID(e.getConnection().getName()) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(e.getConnection().getName()) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §esnapecraft.ddns.net \n§8------------------------------");
                }

            //Spieler ist permanent gebannt:
            } else {
                e.setCancelled(true);
                e.setCancelReason("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(e.getConnection().getName()) + "  §7#" +  BanAPI.getBanID(e.getConnection().getName()) +" \n \n §7Verbleibende Zeit: §c  \nPERMANENT\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §esnapecraft.ddns.net \n§8------------------------------");

            }

        }
    }
}
