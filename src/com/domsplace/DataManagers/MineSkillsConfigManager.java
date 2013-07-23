package com.domsplace.DataManagers;

import com.domsplace.MineSkillsBase;
import com.domsplace.Utils.MineSkillsUtils;
import java.io.File;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class MineSkillsConfigManager extends MineSkillsBase {
    
    public static YamlConfiguration config;
    
    public static boolean loadConfig() {
        try {
            File ymlFile = new File(getPlugin().getDataFolder(), "config.yml");
            config = YamlConfiguration.loadConfiguration(ymlFile);
            
            if(!getPlugin().getDataFolder().exists()) {
                getPlugin().getDataFolder().mkdir();
            }
            
            if(!ymlFile.exists()) {
                ymlFile.createNewFile();
            }
            
            //Store Worlds into Config
            ArrayList<String> worlds = new ArrayList<String>();
            for(World w : Bukkit.getWorlds()) {
                worlds.add(w.getName());
            }
            dV("worlds", worlds);
            
            //Set Default Values
            dV("colors.default", "&7");
            dV("colors.important", "&9");
            dV("colors.error", "&c");
            
            dV("nearbyNotify.radius", 25);
            
            dV("nearbyNotify.levelup", true);
            dV("nearbyNotify.overallup", true);
            
            dV("notify.levelup", true);
            dV("notify.overallup", false);
            
            dV("effects.levelup", true);
            dV("effects.overallup", true);
            
            //Store Values
            MineSkillsBase.ChatDefault = Colorise(gS("colors.default"));
            MineSkillsBase.ChatImportant = Colorise(gS("colors.important"));
            MineSkillsBase.ChatError = Colorise(gS("colors.error"));
            
            MineSkillsBase.nearbyRadius = config.getDouble("nearbyNotify.radius");
            
            //Update Vales
            MineSkillsBase.MineSkillsWorlds = new ArrayList<World>();
            for(String s : config.getStringList("worlds")) {
                World w = Bukkit.getWorld(s);
                if(w == null) {
                    MineSkillsUtils.Error("Failed to load world " + s + ".", null);
                    continue;
                }
                MineSkillsBase.MineSkillsWorlds.add(w);
            }
            
            //Finally Save
            config.save(ymlFile);
            return true;
        } catch(Exception ex) {
            MineSkillsUtils.Error("Failed to load config.", ex);
            return false;
        }
    }
    
    //Set a Default Value if it's not set
    public static void dV(String path, Object value) {
        if(config.contains(path)) {
            return;
        }
        
        config.set(path, value);
    }
    
    public static String gS(String path) throws Exception {
        if(!config.contains(path)) {
            throw new Exception();
        }
        
        return config.getString(path);
    }
}
