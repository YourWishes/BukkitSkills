package com.domsplace.Objects;

import com.domsplace.CustomEvents.MineSkillsLevelUpEvent;
import com.domsplace.CustomEvents.MineSkillsOverallLevelUpEvent;
import com.domsplace.CustomEvents.MineSkillsXPChangeEvent;
import com.domsplace.MineSkillsBase;
import com.domsplace.Utils.MineSkillsUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MineSkillsSkillLevels extends MineSkillsBase {
    public double DEFAULT_LEVEL = 1.0d;
    
    public double levellingCurve = 60d;
    private double level = 0;
    
    public MineSkillsSkillLevels() {
    }
    
    public double getXP() {
        return this.level;
    }
    
    public MineSkillsSkillLevels setXP(double level) {
        this.level = level;
        return this;
    }
    
    public MineSkillsSkillLevels addXP(double amount, MineSkillsPlayer player, MineSkillsSkill skill) {
        
        if(player.getPlayer().isOnline()) {
            Player p = player.getPlayer().getPlayer();
            if(!MineSkillsUtils.isMineSkillsWorld(p.getWorld())) {
                return this;
            }
        }
        
        if(amount == 0d) {
            return this;
        }
        
        //Fire XP Event
        MineSkillsXPChangeEvent xpEvent = new MineSkillsXPChangeEvent(player, skill, this.getXP(), this.getXP() + amount);
        Bukkit.getPluginManager().callEvent(xpEvent);
        
        //Fire Level Event (IF needed)
        double oldLevel = this.getLevel();
        double oldOverallLevel = player.getOverallLevel();
        
        if(xpEvent.isCancelled()) {
            return this;
        }
        
        //TRY set XP
        this.setXP(this.getXP() + amount);
        double newLevel = this.getLevel();
        this.setXP(this.getXP() - amount);
        
        if(oldLevel != newLevel) {
            MineSkillsLevelUpEvent levelEvent = new MineSkillsLevelUpEvent(player, skill, oldLevel, newLevel);
            Bukkit.getPluginManager().callEvent(levelEvent);
            
            if(levelEvent.isCancelled()) {
                return this;
            }
        }
        
        //Fire Overall Event (IF Needed)
        this.setXP(this.getXP() + amount);
        double newOverallLevel = player.getOverallLevel();
        this.setXP(this.getXP() - amount);
        
        if(oldOverallLevel != newOverallLevel) {
            MineSkillsOverallLevelUpEvent levelEvent = new MineSkillsOverallLevelUpEvent(player, oldOverallLevel, newOverallLevel);
            Bukkit.getPluginManager().callEvent(levelEvent);
            
            if(levelEvent.isCancelled()) {
                return this;
            }
        }
        
        //FINALLY set XP
        this.setXP(this.getXP() + amount);
        return this;
    }
    
    public double getLevel() {
        double l = DEFAULT_LEVEL;
        double xp = this.getXP();
        
        while(xp > 0) {
            xp -= (l-DEFAULT_LEVEL+1.0d) * this.levellingCurve;
            
            if(xp >= 0) {
                l++;
            }
        }
        
        return l;
    }
    
    public double getXPToNextLevel() {
        return getXPToLevel(this.getLevel() + 1, this.levellingCurve);
    }
    
    public static double getXPToLevel(double level, double curve) {
        
        double l = level;
        double xp = 0.0d;
        while(l > 0) {
            l--;
            xp += l * curve;
        }
        
        return xp;
    }
}
