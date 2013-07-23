package com.domsplace.CustomEvents;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class MineSkillsBowHitEvent extends MineSkillsCustomEventBase {
    
    private Entity damaged;
    private Arrow arrow;
    private Entity shooter;
    
    public MineSkillsBowHitEvent(Entity target, Arrow arrow, Entity shooter) {
       this.damaged = target;
       this.arrow = arrow;
       this.shooter = shooter;
    }
    
    public Entity getDamaged() {
        return this.damaged;
    }
    
    public Arrow getArrow() {
        return this.arrow;
    }
    
    public Entity getShooter() {
        return this.shooter;
    }
}
