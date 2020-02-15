package bansystem;

import api.BanAPI;
import main.BungeeHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class UnbanByUIDCMD extends Command {

    public UnbanByUIDCMD() {
        super("unbanuid");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission("main.BungeeHelper.Unban")) {
            if(args.length == 1) {
                if(BanAPI.isBanned(UUID.fromString(args[0]))) {
                    BanAPI.unbanPlayer(UUID.fromString(args[0]));
                    sender.sendMessage(BungeeHelper.prefix + "§aSpieler entbannt.");
                } else {
                    sender.sendMessage(BungeeHelper.prefix + "§cSpieler ist nicht gebannt!");
                }
            } else {
                sender.sendMessage(BungeeHelper.prefix + "§cBenutzung: /unbanuid <UUID>");
            }
        } else {
            sender.sendMessage(BungeeHelper.noperm);
        }

    }


}
