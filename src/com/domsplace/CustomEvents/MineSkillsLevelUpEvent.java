package com.domsplace.CustomEvents;

import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;

public class MineSkillsLevelUpEvent extends MineSkillsCustomEventBase {
    
    private MineSkillsPlayer player;
    private MineSkillsSkill skill;
    private double oldLevel;
    private double newLevel;
    
    public MineSkillsLevelUpEvent(MineSkillsPlayer player, MineSkillsSkill skill, double oldLevel, double newLevel) {
        this.player = player;
        this.skill = skill;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }
    
    public MineSkillsPlayer getPlayer() {
        return this.player;
    }
    
    public MineSkillsSkill getSkill() {
        return this.skill;
    }
    
    public double getFromLevel() {
        return this.oldLevel;
    }
    
    public double getToLevel() {
        return this.newLevel;
    }
    
}
