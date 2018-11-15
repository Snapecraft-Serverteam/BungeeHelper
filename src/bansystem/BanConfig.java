package bansystem;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BanConfig {
    public static Configuration BanConfig;
    public static File BanFile;

    public static void setDefaultConfig()
    {
        if (BanConfig.get("BannedPlayers") == null)
        {
            BanConfig.set("BannedPlayers", null);
            try
            {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(BanConfig, BanFile);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void saveBanFile()
    {
        try
        {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(
                    BanConfig, BanFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
