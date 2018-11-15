package commands;

import main.BungeeHelper;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BroadcastCMD extends Command {

    public BroadcastCMD() {
        super("bc");
    }


    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        if(p.hasPermission("BungeeHelper.Broadcast")) {
            String msg = "";
            for(int i = 0; i < args.length ; i++) {
                    msg = msg + " " + args[i];
            }


            for(ProxiedPlayer pl : BungeeCord.getInstance().getPlayers()) {
                pl.sendMessage("§l§bBROADCAST | " + p.getName() +": §r§l" + msg);
            }
        } else {
            p.sendMessage(BungeeHelper.noperm);
        }

    }
}