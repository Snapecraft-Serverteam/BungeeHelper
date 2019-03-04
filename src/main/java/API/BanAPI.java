package API;

import MySQL.SqlApi;
import net.md_5.bungee.api.ProxyServer;

import java.util.UUID;

public class BanAPI {

    public static void banPlayer(UUID uuid, UUID executor, String reason) {
         SqlApi.executeSQL("INSERT into bannedplayers (uuid, bannedBy, bannedSince, bannedUntil, banReason, lastname, lastExecName) VALUES ('" + uuid.toString() + "', '" + executor.toString() + "', '" + System.currentTimeMillis() + "', '-1', '" + reason + "', '" + ProxyServer.getInstance().getPlayer(uuid).getName() + "', '" + ProxyServer.getInstance().getPlayer(executor).getName() + "')");
    }

    public static void banPlayer(UUID uuid, UUID executor, String reason, Long banDuration) {

        SqlApi.executeSQL("INSERT into bannedplayers (uuid, bannedBy, bannedSince, bannedUntil, banReason, lastname, lastExecName) VALUES ('" + uuid.toString() + "', '" + executor.toString() + "', '" + System.currentTimeMillis() + "', " + (System.currentTimeMillis() + banDuration) + ", '" + reason + "', '" + ProxyServer.getInstance().getPlayer(uuid).getName() + "', '" + ProxyServer.getInstance().getPlayer(executor).getName() + "')");


    }

    public static void unbanPlayer(UUID p) {
          SqlApi.executeSQL("delete from bannedplayers\n" +
                  "where uuid = '" + p.toString() + "'\n" +
                  "limit 1;");

    }

    public static Boolean isBanned(UUID p) {
        return SqlApi.executeQuery("SELECT EXISTS(SELECT 1 FROM bannedplayers WHERE uuid = '" + p.toString() + "')").equals("1");
    }

    public static String getReason(UUID p) {
        return SqlApi.executeQuery("SELECT\n" +
                "  banReason\n" +
                "FROM bannedplayers\n" +
                "where uuid='" + p.toString() + "';");
    }

    public static String getExecutor(UUID p) {
        return SqlApi.executeQuery("SELECT\n" +
                "  bannedBy\n" +
                "FROM bannedplayers\n" +
                "where uuid='" + p.toString() + "';");
    }

    public static Long getBannedUntil(UUID p) {
        return Long.parseLong(SqlApi.executeQuery("SELECT\n" +
                "  bannedUntil\n" +
                "FROM bannedplayers\n" +
                "where uuid='" + p.toString() + "';"));
    }

    public static Integer getBanID(UUID p) {
        return Integer.parseInt(SqlApi.executeQuery("SELECT\n" +
                "  id\n" +
                "FROM bannedplayers\n" +
                "where uuid='" + p.toString() + "';"));
    }

    public static String getRemainingBanTime(UUID p) {
        long timeInMilliSeconds = getBannedUntil(p) - System.currentTimeMillis();
        long seconds = timeInMilliSeconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + " Tage, " + hours % 24 + " Stunden";
        return time;
    }

    public static UUID getUuidFromId(Integer id) {
        return UUID.fromString(SqlApi.executeQuery("SELECT\n" +
                "  uuid\n" +
                "FROM bannedplayers\n" +
                "where id='" + id.toString() + "';"));
    }

    public static String getLastName(UUID p) {
        return SqlApi.executeQuery("SELECT\n" +
                "  lastname\n" +
                "FROM bannedplayers\n" +
                "where uuid='" + p.toString() + "';");
    }
    public static String getLastExecName(UUID p) {
        return SqlApi.executeQuery("SELECT\n" +
                "  lastExecName\n" +
                "FROM bannedplayers\n" +
                "where uuid='" + p.toString() + "';");
    }
}
