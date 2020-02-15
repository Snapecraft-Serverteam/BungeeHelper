package commands;

import main.BungeeHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HelpCMD extends Command {

    public HelpCMD() {
        super("helpme");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        if(args.length > 0) {
            StringBuilder msg = new StringBuilder();
            for(int i = 0; i < args.length ; i++) {
                    msg.append(" ").append(args[i]);

            }
            for(ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()) {
                if(pp.hasPermission("main.BungeeHelper.HelpPlayer")) {
                    pp.sendMessage(BungeeHelper.prefix + "Der Spieler §6" + p.getName() + "§r braucht Hilfe.");
                    pp.sendMessage(BungeeHelper.prefix + "Nachricht:§a " + msg);
                    TextComponent message1 = new TextComponent("[§aAnsehen§r]");
                    message1.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/spectate " + p.getName()));
                    pp.sendMessage(message1);
                }
            }
        } else {
            p.sendMessage(BungeeHelper.prefix + "§cDu musst einen Grund angeben: /help <Grund>");
        }


    }
}
