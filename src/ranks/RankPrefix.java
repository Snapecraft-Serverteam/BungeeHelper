package ranks;

public class RankPrefix {


    public static String getRankPrefix(String group) {

        if(group.equalsIgnoreCase("Inhaber")) {
            return "§4Inhaber | ";
        } else if(group.equalsIgnoreCase("Admin")) {
            return "§cAdmin | ";
        } else if(group.equalsIgnoreCase("Moderator")) {
            return "§9Moderator | ";
        } else if(group.equalsIgnoreCase("Supporter")) {
            return "§3Supporter | ";
        } else if(group.equalsIgnoreCase("Builder")) {
            return "§2Builder | ";
        } else if(group.equalsIgnoreCase("Premium")) {
            return "§6Premium | ";
        } else if(group.equalsIgnoreCase("Spieler")) {
            return "§7Spieler | ";
        }

        return "";

    }
}
