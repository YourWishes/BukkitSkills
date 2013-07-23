package com.domsplace.DataManagers;

import com.domsplace.MineSkillsBase;
import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import com.domsplace.Utils.MineSkillsUtils;
import java.io.File;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MineSkillsPlayerDataManager extends MineSkillsBase {
    
    public static File playersDirectory;
    
    public static boolean setupFiles() {
        try {
            playersDirectory = new File(getPlugin().getDataFolder(), "players");
            
            if(!playersDirectory.exists()) {
                playersDirectory.mkdir();
            }
            
            LoadAllPlayers();
            SaveAllPlayers();
            
            return true;
        } catch(Exception ex) {
            MineSkillsUtils.Error("Failed to setup players config.", ex);
            return false;
        }
    }
    
    public static void SaveAllPlayers() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            SavePlayer(p);
        }
    }
    
    public static void SavePlayers(List<OfflinePlayer> players) {
        for(OfflinePlayer player : players) {
            SavePlayer(player);
        }
    }
    
    public static void SavePlayer(OfflinePlayer player) {
        try {
            File playerFile = new File(playersDirectory, player.getName() + ".yml");
            playerFile.delete();
            
            if(!playerFile.exists()) {
                playerFile.createNewFile();
            }
            
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(playerFile);
            
            for(MineSkillsSkill skill : MineSkillsSkill.skills) {
                yml.set(skill.getType(), MineSkillsPlayer.getPlayer(player).getSkillLevel(skill).getXP());
            }
            
            yml.save(playerFile);
        } catch(Exception ex) {
            MineSkillsUtils.Error("Failed to save player " + player.getName(), ex);
            return;
        }
    }
    
    public static void LoadAllPlayers() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            LoadPlayer(p);
        }
    }
    
    public static void LoadPlayer(OfflinePlayer player) {
        File playerFile = new File(playersDirectory, player.getName() + ".yml");
        if(!playerFile.exists()) {
            return;
        }
        
        YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        MineSkillsPlayer pl = MineSkillsPlayer.getPlayer(player);
        
        for(MineSkillsSkill skill : MineSkillsSkill.skills) {
            pl.getSkillLevel(skill).setXP(playerConfig.getDouble(skill.getType()));
        }
    }
}
