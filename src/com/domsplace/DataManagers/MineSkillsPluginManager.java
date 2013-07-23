package com.domsplace.DataManagers;

import com.domsplace.Utils.MineSkillsUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

public class MineSkillsPluginManager {
    public static YamlConfiguration PluginYML;
    
    public static void LoadPlugin() {
        try {
            InputStream pluginStream = MineSkillsUtils.plugin.getResource("plugin.yml");
            PluginYML = YamlConfiguration.loadConfiguration(pluginStream);
            pluginStream.close();
        } catch(Exception ex) {
            MineSkillsUtils.Error("Failed to load Plugin.YML", ex);
            return;
        }
    }
    
    public static String getPluginName() {
        return PluginYML.getString("name");
    }
    
    public static String getPluginVersion() {
        return PluginYML.getString("version");
    }
    
    public static List<String> getCommands() {
        List<String> commands = new ArrayList<String>();
        
        for(String s : ((MemorySection) PluginYML.get("commands")).getKeys(false)) {
            commands.add(s);
        }
        
        return commands;
    }
    
    public static List<Command> getCmds() {
        List<Command> commands = new ArrayList<Command>();
        
        for(String s : getCommands()) {
            Command c = MineSkillsUtils.plugin.getCommand(s);
            
            if(c == null) {
                continue;
            }
            
            commands.add(c);
        }
        
        return commands;
    }
}
