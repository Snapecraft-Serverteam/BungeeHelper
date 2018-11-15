package Listener;

import commands.StaffChatCMD;
import de.dytanic.cloudnet.api.player.PermissionProvider;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {


    @EventHandler
    public void onChat(ChatEvent e) {

        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        String msg = e.getMessage();
        if(StaffChatCMD.StaffChat.contains(p)) {
            e.setCancelled(true);
            for(ProxiedPlayer staffMember : StaffChatCMD.StaffChat) {
                staffMember.sendMessage("§7[§4Staff§dChat§7] §6" + p.getName() + " §5 > §r" + msg);
            }
        }

        else if(msg.startsWith("*")) {
            if(p.hasPermission("BungeeHelper.StaffChat")) {
                e.setCancelled(true);
                for(ProxiedPlayer staffMember : StaffChatCMD.StaffChat) {
                    staffMember.sendMessage("§7[§4Staff§dChat§7] §6" + p.getName() + " §5 > §r" + msg.substring(1));
                }
            }
        }
        else if(msg.startsWith("@")) {
            if(msg.contains(" ")){
                String probablyPlayer = msg.substring(0, msg.indexOf(" "));
                if(BungeeCord.getInstance().getPlayer(probablyPlayer) != null) {
                    ProxiedPlayer rec = BungeeCord.getInstance().getPlayer(probablyPlayer);
                    e.setCancelled(true);
                    p.sendMessage("[§aDu§r] §5> §r[§6" + rec.getDisplayName() + "§r]: " + msg.substring(1));
                    rec.sendMessage("[§6" + p.getDisplayName() + "§r] §5> §r[§aDu§r]: " + msg.substring(1));

                    PermissionProvider.isInGroup("Inhaber", (OfflinePlayer) Bukkit.getPlayer("Horrrst"));
                }
            }
        }
    }

}
