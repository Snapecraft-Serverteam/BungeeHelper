package bansystem;

import api.BanAPI;
import api.OfflineUniqueID;
import main.BungeeHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class UnbanCMD extends Command {

    public UnbanCMD() {
        super("unban");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission("main.BungeeHelper.Unban")) {
            if(args.length == 1) {
                if(BanAPI.isBanned(UUID.fromString(OfflineUniqueID.getOfflineUUID(args[0])))) {
                    BanAPI.unbanPlayer(UUID.fromString(OfflineUniqueID.getOfflineUUID(args[0])));
                    sender.sendMessage(BungeeHelper.prefix + "§aSpieler wurde entbannt.");
                } else {
                    sender.sendMessage(BungeeHelper.prefix + "§cSpieler §6" + args[0] + "§c ist nicht gebannt!");
                }
            } else {
                sender.sendMessage(BungeeHelper.prefix + "§cBenutzung: /unban <Spieler>");
            }
        } else {
            sender.sendMessage(BungeeHelper.noperm);
        }


    }


}
