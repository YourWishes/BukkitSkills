package com.domsplace.Objects;

import com.domsplace.MineSkillsBase;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.OfflinePlayer;

public class MineSkillsPlayer extends MineSkillsBase {
    
    public static final HashMap<OfflinePlayer, MineSkillsPlayer> Players = new HashMap<OfflinePlayer, MineSkillsPlayer>();
    
    public static void RegisterPlayer(OfflinePlayer player) {
        if(Players.containsKey(player)) {
            return;
        }
        
        MineSkillsPlayer registrate = new MineSkillsPlayer(player);
        Players.put(player, registrate);
    }
    
    public static MineSkillsPlayer getPlayer(OfflinePlayer player) {
        if(!Players.containsKey(player)) {
            RegisterPlayer(player);
        }
        
        return Players.get(player);
    }
    
    OfflinePlayer player;
    Map<MineSkillsSkill, MineSkillsSkillLevels> levels;
    
    public MineSkillsPlayer(OfflinePlayer player) {
        this.player = player;
        this.levels = new HashMap<MineSkillsSkill, MineSkillsSkillLevels>();
        
        /*** Register Default Skills ***/
    }
    
    public Map<MineSkillsSkill, MineSkillsSkillLevels> getSkillLevels() {
        return this.levels;
    }
    
    public MineSkillsSkillLevels getSkillLevel(MineSkillsSkill skill) {
        checkSkillExists(skill);
        
        return this.getSkillLevels().get(skill);
    }
    
    public MineSkillsSkillLevels setSkillLevel(MineSkillsSkillLevels levels, MineSkillsSkill skill) {
        this.getSkillLevels().put(skill, levels);
        return levels;
    }
    
    public OfflinePlayer getPlayer() {
        return this.player;
    }
    
    public MineSkillsPlayer setPlayer(OfflinePlayer player) {
        this.player = player;
        return this;
    }
    
    private void checkSkillExists(MineSkillsSkill skill) {
        if(this.getSkillLevels().containsKey(skill)) {
            return;
        }
        
        MineSkillsSkillLevels mssl = new MineSkillsSkillLevels();
        this.getSkillLevels().put(skill, mssl);
        mssl.DEFAULT_LEVEL = skill.DEFAULT_LEVEL;
    }
    
    private void checkAllSkills() {
        for(MineSkillsSkill skill : MineSkillsSkill.skills) {
            checkSkillExists(skill);
        }
    }
    
    public double getOverallLevel() {
        checkAllSkills();
        try {
            double level = 0.0;

            double t = 0.0d;

            for(MineSkillsSkillLevels l : this.getSkillLevels().values()) {
                level += l.getLevel();
                t += 1.0d;
            }
            
            //debug(level + "/" + t + "=" + (level/t));

            level = Math.floor(level / t);
            return level;
        } catch(Exception ex) {
            return 1.0d;
        }
    }
}
