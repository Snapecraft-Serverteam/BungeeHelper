package commands;


import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
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
            if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

                ProxiedPlayer rec = ProxyServer.getInstance().getPlayer(args[0]);

                StringBuilder msg = new StringBuilder();
                for(int i = 1; i < args.length ; i++) {
                    if(i > 1) {
                        msg.append(" ").append(args[i]);
                    } else {
                        msg = new StringBuilder(args[i]);
                    }
                }

                p.sendMessage("[§aDu§r] §5> §r[§6" + rec.getDisplayName() + "§r]: " + msg);
                rec.sendMessage("[§6" + p.getName() + "§r] §5> §r[§aDu§r]: " + msg);
            }
        }
    }
}
