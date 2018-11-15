package bansystem;

import API.BanAPI;
import main.BungeeHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class unbanByUIDCMD extends Command {

    public unbanByUIDCMD() {
        super("unbanuid");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission("BungeeHelper.Unban")) {
            if(args.length == 1) {
                BanAPI.unbanPlayerByUID(args[0]);
                sender.sendMessage(BungeeHelper.prefix + "§aSpieler entbannt.");
            } else {
                sender.sendMessage(BungeeHelper.prefix + "§cBenutzung: /unbanuid <UUID>");
            }
        } else {
            sender.sendMessage(BungeeHelper.noperm);
        }

    }


}
