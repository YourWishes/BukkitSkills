package com.domsplace.CustomEvents;

import com.domsplace.Objects.MineSkillsPlayer;

public class MineSkillsOverallLevelUpEvent extends MineSkillsCustomEventBase {
    
    private MineSkillsPlayer player;
    private double oldLevel;
    private double newLevel;
    
    public MineSkillsOverallLevelUpEvent(MineSkillsPlayer player, double oldLevel, double newLevel) {
        this.player = player;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }
    
    public MineSkillsPlayer getPlayer() {
        return this.player;
    }
    
    public double getFromLevel() {
        return this.oldLevel;
    }
    
    public double getToLevel() {
        return this.newLevel;
    }
}
