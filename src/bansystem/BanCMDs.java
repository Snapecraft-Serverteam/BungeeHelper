package bansystem;

import API.BanAPI;
import main.BungeeHelper;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class BanCMDs extends Command {

    public BanCMDs() {
        super("ban");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {


        if (sender.hasPermission("BungeeHelper.Ban")) {
            if(args.length == 0) {
                sender.sendMessage(BungeeHelper.prefix + "--------- Ban Gründe ---------");
                sender.sendMessage("§4  1 §b§ §cHacking");
                sender.sendMessage("§4  2 §b§ §cBeleidigung");
                sender.sendMessage("§4  3 §b§ §cWerbung");
                sender.sendMessage("§4  4 §b§ §cTrolling");
                sender.sendMessage("§4  5 §b§ §cBugusing");
                sender.sendMessage("§4  6 §b§ §cSupKick");
                sender.sendMessage("§4  7 §b§ §cVerhalten");
                sender.sendMessage("§4  8 §b§ §cDrohung");
                sender.sendMessage("§4  9 §b§ §cTeaming");
                sender.sendMessage("§4 10 §b§ §cSpawntrapping");
                sender.sendMessage("§4 11 §b§ §cSkin");
                sender.sendMessage("§4 12 §b§ §cUsername");
                sender.sendMessage("§4 13 §b§ §cSpamming");
                sender.sendMessage("§4 14 §b§ §cExtrem");
                sender.sendMessage("Ban checken mit: /ban #Nummer");
            }
            else if (args.length == 1) {
                if (args[0].startsWith("#")) {

                    for (String uid : BanConfig.BanConfig.getSection("BannedPlayers").getKeys()) {
                        //sender.sendMessage(pl);
                        if (BanAPI.getBanIDByUID(uid) == Integer.parseInt(args[0].substring(1))) {

                            if(BanAPI.isBannedByUID(uid)) {
                                sender.sendMessage(BungeeHelper.prefix + "--------- Ban Info ---------");
                                sender.sendMessage(BungeeHelper.prefix + "Spieler:  §6" + BanAPI.getPlayerNameByUID(uid));
                                sender.sendMessage(BungeeHelper.prefix + "Grund:  §6" + BanAPI.getReasonByUID(uid));
                                sender.sendMessage(BungeeHelper.prefix + "Gebannt von:  §6" + BanAPI.getExecutorByUID(uid));
                                if (BanAPI.getBannedUntilByUID(uid) == -1) {
                                    sender.sendMessage(BungeeHelper.prefix + "Gebannt bis:  §cPERMANENT");
                                } else {
                                    sender.sendMessage(BungeeHelper.prefix + "Gebannt bis:  §6" + BanAPI.getRemainingBanTimeByUID(uid));
                                }
                                TextComponent message1 = new TextComponent(BungeeHelper.prefix + "[§aEntbannen§r]");
                                message1.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/unbanUID " + uid));
                                sender.sendMessage(message1);

                                break;
                            }
                        }
                    }
                    return;
                } else if(args[0].startsWith("@")) {

                    for (String uid : BanConfig.BanConfig.getSection("BannedPlayers").getKeys()) {
                        //sender.sendMessage(pl);
                        if (BanAPI.getPlayerNameByUID(uid).equalsIgnoreCase(args[0].substring(1))) {

                            if(BanAPI.isBannedByUID(uid)) {
                                sender.sendMessage(BungeeHelper.prefix + "--------- Ban Info ---------");
                                sender.sendMessage(BungeeHelper.prefix + "Spieler:  §6" + BanAPI.getPlayerNameByUID(uid));
                                sender.sendMessage(BungeeHelper.prefix + "Grund:  §6" + BanAPI.getReasonByUID(uid));
                                sender.sendMessage(BungeeHelper.prefix + "Gebannt von:  §6" + BanAPI.getExecutorByUID(uid));
                                if (BanAPI.getBannedUntilByUID(uid) == -1) {
                                    sender.sendMessage(BungeeHelper.prefix + "Gebannt bis:  §cPERMANENT");
                                } else {
                                    sender.sendMessage(BungeeHelper.prefix + "Gebannt bis:  §6" + BanAPI.getRemainingBanTimeByUID(uid));
                                }
                                TextComponent message1 = new TextComponent(BungeeHelper.prefix + "[§aEntbannen§r]");
                                message1.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/unbanUID " + uid));
                                sender.sendMessage(message1);

                                break;
                            }
                        }
                    }

                } else {
                    sender.sendMessage(BungeeHelper.prefix + "§cFalsche Argumente. Hier ein paar Beispiele:");
                    sender.sendMessage(BungeeHelper.prefix + "§c/ban | Hilfe & Gründe anzeigen");
                    sender.sendMessage(BungeeHelper.prefix + "§c/ban #12345 | Infos zum Ban der Nummer 12345 anzeigen");
                    sender.sendMessage(BungeeHelper.prefix + "§c/ban FrecherBruder 14 | Den Spieler 'FrecherBruder' bannen mit Grund 'Extrem'");
                }
            } else if(args.length == 2) {

                Integer banID = Integer.parseInt(args[1]);
                String playerToBan = args[0];
                if(banID == 1) {
                    BanAPI.banPlayer(playerToBan, "Hacking", sender.getName(), TimeUnit.DAYS.toMillis(30));
                } else if(banID == 2) {
                    BanAPI.banPlayer(playerToBan, "Beleidigung", sender.getName(), TimeUnit.DAYS.toMillis(10));
                } else if(banID == 3) {
                    BanAPI.banPlayer(playerToBan, "Werbung", sender.getName(), TimeUnit.DAYS.toMillis(10));
                } else if(banID == 4) {
                    BanAPI.banPlayer(playerToBan, "Trolling", sender.getName(), TimeUnit.DAYS.toMillis(8));
                } else if(banID == 5) {
                    BanAPI.banPlayer(playerToBan, "Bugusing", sender.getName(), TimeUnit.DAYS.toMillis(5));
                } else if(banID == 6) {
                    BanAPI.banPlayer(playerToBan, "SupKick", sender.getName(), TimeUnit.DAYS.toMillis(2));
                } else if(banID == 7) {
                    BanAPI.banPlayer(playerToBan, "Verhalten", sender.getName(), TimeUnit.DAYS.toMillis(5));
                } else if(banID == 8) {
                    BanAPI.banPlayer(playerToBan, "Drohung", sender.getName(), TimeUnit.DAYS.toMillis(10));
                } else if(banID == 9) {
                    BanAPI.banPlayer(playerToBan, "Teaming", sender.getName(), TimeUnit.DAYS.toMillis(10));
                } else if(banID == 10) {
                    BanAPI.banPlayer(playerToBan, "Spawntrapping", sender.getName(), TimeUnit.DAYS.toMillis(8));
                } else if(banID == 11) {
                    BanAPI.banPlayer(playerToBan, "Skin", sender.getName(), TimeUnit.DAYS.toMillis(5));
                } else if(banID == 12) {
                    BanAPI.banPlayer(playerToBan, "Username", sender.getName(), TimeUnit.DAYS.toMillis(5));
                } else if(banID == 13) {
                    BanAPI.banPlayer(playerToBan, "Spamming", sender.getName(), TimeUnit.DAYS.toMillis(5));
                } else if(banID == 14) {
                    BanAPI.banPlayer(playerToBan, "Extrem", sender.getName());
                } else {
                    sender.sendMessage(BungeeHelper.prefix + "§cUngültige ReasonID. Nutze /ban um die Gründe zu sehen.");
                }
            }
        } else {
            sender.sendMessage(BungeeHelper.noperm);
        }


    }
}
