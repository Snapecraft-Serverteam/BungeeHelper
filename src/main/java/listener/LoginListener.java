package listener;

import api.BanAPI;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {
    @EventHandler
    public void onLogin(LoginEvent e) {
        if(BanAPI.isBanned(e.getConnection().getUniqueId())) {
            //Ist der Spieler NICHT permanent gebannt?
            if(BanAPI.getBannedUntil(e.getConnection().getUniqueId()) != -1) {
                //Wenn NEIN
                //Ist der Bann des Spielers bereits abgelaufen?
                if(BanAPI.getBannedUntil(e.getConnection().getUniqueId()) < System.currentTimeMillis()) {
                    //Datenbank aufräumen
                    BanAPI.unbanPlayer(e.getConnection().getUniqueId());
                } else {
                    e.setCancelled(true);
                    e.setCancelReason("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(e.getConnection().getUniqueId()) + "  §7#" + BanAPI.getBanID(e.getConnection().getUniqueId()) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(e.getConnection().getUniqueId()) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------");
                }
            //Spieler ist permanent gebannt:
            } else {
                e.setCancelled(true);
                e.setCancelReason("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(e.getConnection().getUniqueId()) + "  §7#" +  BanAPI.getBanID(e.getConnection().getUniqueId()) +" \n \n §7Verbleibende Zeit: §c  \nPERMANENT\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------");
            }
        }
    }
}
