package commands;

import main.BungeeHelper;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MsgCMD extends Command {

    public MsgCMD() {
        super("msg");
    }


    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;

        if(args.length > 1) {
            if(BungeeCord.getInstance().getPlayer(args[0]) != null) {

                ProxiedPlayer rec = BungeeCord.getInstance().getPlayer(args[0]);

                String msg = "";
                for(int i = 1; i < args.length ; i++) {
                    if(i > 1) {
                        msg = msg + " " + args[i];
                    } else {
                        msg = args[i];
                    }
                }

                p.sendMessage("[§aDu§r] §5> §r[§6" + rec.getDisplayName() + "§r]: " + msg);
                rec.sendMessage("[§6" + p.getName() + "§r] §5> §r[§aDu§r]: " + msg);

            }

        }
    }


}
