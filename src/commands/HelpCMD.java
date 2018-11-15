package commands;

import main.BungeeHelper;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HelpCMD extends Command {

    public HelpCMD() {
        super("help");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        if(args.length > 0) {
            String msg = "";
            for(int i = 0; i < args.length ; i++) {
                    msg = msg + " " + args[i];

            }
            for(ProxiedPlayer pp : BungeeCord.getInstance().getPlayers()) {
                if(pp.hasPermission("BungeeHelper.HelpPlayer")) {
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
