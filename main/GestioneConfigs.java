package it.toninosas.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GestioneConfigs {

    public static File zonaFile, playerDataFile, pluginFile, permissionFile, protezioneFile;
    public static FileConfiguration zonaConfiguration,
            playerDataConfiguration, pluginConfiguration, permissionConfiguration, protezioneConfiguration;

    //Finds or generates the custom config file
    public static void setup(){

        zonaFile = new File(Bukkit.getServer().getPluginManager().getPlugin("ProvaPlugin").getDataFolder(),
                "zona.yml");
        playerDataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("ProvaPlugin").getDataFolder(),
                "playerData.yml");
        pluginFile = new File(Bukkit.getServer().getPluginManager().getPlugin("ProvaPlugin").getDataFolder(),
                "plugin_config.yml");
        permissionFile = new File(Bukkit.getServer().getPluginManager().getPlugin("ProvaPlugin").getDataFolder(),
                "permission.yml");
        protezioneFile = new File(Bukkit.getServer().getPluginManager().getPlugin("ProvaPlugin").getDataFolder(),
                "protezione.yml");

        CheckIfExist(zonaFile);
        CheckIfExist(playerDataFile);
        CheckIfExist(pluginFile);
        CheckIfExist(permissionFile);
        CheckIfExist(protezioneFile);

        zonaConfiguration = YamlConfiguration.loadConfiguration(zonaFile);
        playerDataConfiguration = YamlConfiguration.loadConfiguration(playerDataFile);
        pluginConfiguration = YamlConfiguration.loadConfiguration(pluginFile);
        permissionConfiguration = YamlConfiguration.loadConfiguration(permissionFile);
        protezioneConfiguration = YamlConfiguration.loadConfiguration(protezioneFile);
    }


    public static void save(){

        try{
            zonaConfiguration.save(zonaFile);
            playerDataConfiguration.save(playerDataFile);
            pluginConfiguration.save(pluginFile);
            permissionConfiguration.save(permissionFile);
            protezioneConfiguration.save(protezioneFile);

        }catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }

    public static void reload(){
        zonaConfiguration = YamlConfiguration.loadConfiguration(zonaFile);
        playerDataConfiguration = YamlConfiguration.loadConfiguration(playerDataFile);
        pluginConfiguration = YamlConfiguration.loadConfiguration(pluginFile);
        permissionConfiguration = YamlConfiguration.loadConfiguration(permissionFile);
        protezioneConfiguration = YamlConfiguration.loadConfiguration(protezioneFile);
    }

    public static void CheckIfExist(File file){
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                //eee
            }
        }
    }

}