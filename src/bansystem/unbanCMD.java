package bansystem;

import API.BanAPI;
import main.BungeeHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class unbanCMD extends Command {

    public unbanCMD() {
        super("unban");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission("BungeeHelper.Unban")) {
            if(args.length == 1) {
                if(BanAPI.isBanned(args[0])) {
                    BanAPI.unbanPlayer(args[0]);
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
