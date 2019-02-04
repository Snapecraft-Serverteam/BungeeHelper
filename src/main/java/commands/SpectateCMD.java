package commands;

import main.BungeeHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SpectateCMD extends Command {

    public SpectateCMD() {
        super("spectate");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;

        if(p.hasPermission("main.BungeeHelper.Spectate")) {
            if(args.length == 1) {
                if(ProxyServer.getInstance().getPlayer(args[0]) != null) {
                    p.connect(ProxyServer.getInstance().getPlayer(args[0]).getServer().getInfo());
                } else {
                    p.sendMessage(BungeeHelper.prefix + "§cSpieler existiert nicht.");
                }
            } else {
                p.sendMessage(BungeeHelper.prefix + "§cBenutzung: /spectate <Spieler>");
            }
        } else {
            p.sendMessage(BungeeHelper.noperm);
        }


    }

}
