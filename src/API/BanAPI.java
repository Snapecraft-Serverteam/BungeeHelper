package API;

import bansystem.BanConfig;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.concurrent.TimeUnit;

public class BanAPI {

    public static boolean doesExist(String p)
    {
        return BanConfig.BanConfig.get("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p)) != null;
    }

    public static void banPlayer(String p, String reason, String executor) {

        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".name", p);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".reason", reason);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".bannedUntil", -1L);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".bannedBy", executor);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".banID", getHighestBanID() + 1);
        setHighestBanID(getHighestBanID() + 1);
        BanConfig.saveBanFile();
        BungeeCord.getInstance().getPlayer(p).disconnect("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + getReason(p) + "  §7#" + getBanID(p) +" \n \n §7Verbleibende Zeit: §c  \nPERMANENT\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §esnapecraft.ddns.net \n§8------------------------------");

    }

    public static void banPlayer(String p, String reason, String executor, Long bannedUntil) {

        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".name", p);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".reason", reason);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".bannedUntil", System.currentTimeMillis() + bannedUntil);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".bannedBy", executor);
        BanConfig.BanConfig.set("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".banID", getHighestBanID() + 1);
        setHighestBanID(getHighestBanID() + 1);
        BanConfig.saveBanFile();
        BungeeCord.getInstance().getPlayer(p).disconnect("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + getReason(p) + "  §7#" + getBanID(p) + " \n \n §7Verbleibende Zeit: §c  \n" + getRemainingBanTime(p) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §esnapecraft.ddns.net \n§8------------------------------");

    }
    @Deprecated
    public static void unbanPlayer(String p) {
        BanConfig.BanConfig.set("BannedPlayers." +  OfflineUniqueID.getOfflineUUID(p), null);
        BanConfig.saveBanFile();
    }

    public static void unbanPlayerByUID(String p) {
        BanConfig.BanConfig.set("BannedPlayers." +  p, null);
        BanConfig.saveBanFile();
    }

    @Deprecated
    public static Boolean isBanned(String p) {
        if(BanConfig.BanConfig.getString("BannedPlayers." + OfflineUniqueID.getOfflineUUID(p) + ".reason") != null) {
            return true;
        }
        else{
            return false;
        }
    }

    public static Boolean isBannedByUID(String p) {
        if(BanConfig.BanConfig.getString("BannedPlayers." + p + ".reason") != null) {
            return true;
        }
        else{
            return false;
        }
    }
    @Deprecated
    public static String getReason(String p) {
       return BanConfig.BanConfig.getString("BannedPlayers." +  OfflineUniqueID.getOfflineUUID(p) + ".reason");
    }

    public static String getReasonByUID(String p) {
        return BanConfig.BanConfig.getString("BannedPlayers." +  p + ".reason");
    }
    @Deprecated
    public static String getExecutor(String p) {
        return BanConfig.BanConfig.getString("BannedPlayers." +  OfflineUniqueID.getOfflineUUID(p) + ".bannedBy");
    }

    public static String getExecutorByUID(String p) {
        return BanConfig.BanConfig.getString("BannedPlayers." +  p + ".bannedBy");
    }
    @Deprecated
    public static Long getBannedUntil(String p) {
        return BanConfig.BanConfig.getLong("BannedPlayers." +  OfflineUniqueID.getOfflineUUID(p) + ".bannedUntil");
    }

    public static Long getBannedUntilByUID(String p) {
        return BanConfig.BanConfig.getLong("BannedPlayers." +  p + ".bannedUntil");
    }
    @Deprecated
    public static Integer getBanID(String p) {

        return BanConfig.BanConfig.getInt("BannedPlayers." +  OfflineUniqueID.getOfflineUUID(p) + ".banID");

    }
    public static Integer getBanIDByUID(String p) {

        return BanConfig.BanConfig.getInt("BannedPlayers." +  p + ".banID");

    }
    public static void setHighestBanID(Integer i) {
        BanConfig.BanConfig.set("Settings.DoNotChange.currentBanID", i);
        BanConfig.saveBanFile();
    }

    public static Integer getHighestBanID() {
        return BanConfig.BanConfig.getInt("Settings.DoNotChange.currentBanID");
    }


    @Deprecated
    public static String getRemainingBanTime(String p) {
        long timeInMilliSeconds = getBannedUntil(p) - System.currentTimeMillis();
        long seconds = timeInMilliSeconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + " Tage, " + hours % 24 + " Stunden";
        return time;
    }

    public static String getRemainingBanTimeByUID(String p) {
        long timeInMilliSeconds = getBannedUntilByUID(p) - System.currentTimeMillis() ;
        long seconds = timeInMilliSeconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + " Tage, " + hours % 24 + " Stunden";
        return time;
    }

    public static String getPlayerNameByUID(String uid) {
        return BanConfig.BanConfig.getString("BannedPlayers." + uid + ".name");
    }


}
