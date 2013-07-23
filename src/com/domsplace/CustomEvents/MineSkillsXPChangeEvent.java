package com.domsplace.CustomEvents;

import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;

public class MineSkillsXPChangeEvent extends MineSkillsCustomEventBase {
    
    private MineSkillsPlayer player;
    private MineSkillsSkill skill;
    private double oldXP;
    private double newXP;
    
    public MineSkillsXPChangeEvent(MineSkillsPlayer player, MineSkillsSkill skill, double oldXP, double newXP) {
        this.player = player;
        this.skill = skill;
        
        this.oldXP = oldXP;
        this.newXP = newXP;
    }
    
    public MineSkillsPlayer getPlayer() {
        return this.player;
    }
    
    public MineSkillsSkill getSkill() {
        return this.skill;
    }
    
    public double getFromXP() {
        return this.oldXP;
    }
    
    public double getToXP() {
        return this.newXP;
    }
    
    public MineSkillsXPChangeEvent setFromXP(double oldXP) {
        this.oldXP = oldXP;
        return this;
    }
    
    public MineSkillsXPChangeEvent setToXP(double newXP) {
        this.newXP = newXP;
        return this;
    }
    
    public double getXPGained() {
        return this.getToXP() - this.getFromXP();
    }
}
