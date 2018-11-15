package commands;

import main.BungeeHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCMD extends Command {

    public PingCMD() {
        super("ping");
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        commandSender.sendMessage(BungeeHelper.prefix + "Dein derzeitiger Ping ist: §6" + p.getPing());
    }

}
