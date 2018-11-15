package API;

import com.google.common.base.Charsets;

import java.util.UUID;

public class OfflineUniqueID {

    public static String getOfflineUUID(String p) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + p).getBytes(Charsets.UTF_8)).toString();
    }
}
