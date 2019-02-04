package ranks;

import de.dytanic.cloudnet.api.player.PermissionProvider;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class DisplayNameRank {


    //Invoked in LoginListener
    public static void setDisplayName(ProxiedPlayer p) {
        if(PermissionProvider.isInGroup("Inhaber", (OfflinePlayer) p)) {
            p.setDisplayName(RankPrefix.getRankPrefix("Inhaber") + p.getName());
        } else if(PermissionProvider.isInGroup("Admin", (OfflinePlayer) p)) {
            p.setDisplayName(RankPrefix.getRankPrefix("Admin") + p.getName());
        } else if(PermissionProvider.isInGroup("Moderator", (OfflinePlayer) p)) {
            p.setDisplayName(RankPrefix.getRankPrefix("Moderator") + p.getName());
        } else if(PermissionProvider.isInGroup("Supporter", (OfflinePlayer) p)) {
            p.setDisplayName(RankPrefix.getRankPrefix("Supporter") + p.getName());
        } else if(PermissionProvider.isInGroup("Builder", (OfflinePlayer) p)) {
            p.setDisplayName(RankPrefix.getRankPrefix("Builder") + p.getName());
        } else if(PermissionProvider.isInGroup("Premium", (OfflinePlayer) p)) {
            p.setDisplayName(RankPrefix.getRankPrefix("Premium") + p.getName());
        } else if(PermissionProvider.isInGroup("Spieler", (OfflinePlayer) p)) {
            p.setDisplayName(RankPrefix.getRankPrefix("Spieler") + p.getName());
        }

    }

}
