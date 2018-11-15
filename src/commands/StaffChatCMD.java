package commands;

import main.BungeeHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class StaffChatCMD extends Command {

    public static List<ProxiedPlayer> StaffChat;

    public StaffChatCMD() {
        super("sc");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if(sender.hasPermission("BungeeHelper.StaffChat")) {
            if(!StaffChat.contains(p)) {
                StaffChat.add(p);
                sender.sendMessage(BungeeHelper.prefix + "§6StaffChat §aaktiviert!");
            } else {
                StaffChat.remove(p);
                sender.sendMessage(BungeeHelper.prefix + "§6StaffChat §cdektiviert!");
            }
        } else {
            sender.sendMessage(BungeeHelper.noperm);
        }
    }



}
