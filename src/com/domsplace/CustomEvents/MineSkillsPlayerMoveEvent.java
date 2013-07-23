package com.domsplace.CustomEvents;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MineSkillsPlayerMoveEvent extends MineSkillsCustomEventBase {
    private Location oldLocation;
    private Location toLocation;
    private Player player;
    
    private long lastMoveTime;
    
    public MineSkillsPlayerMoveEvent(Location oldLocation, Location toLocation, Player player, long lastMoveTime) {
        this.oldLocation = oldLocation;
        this.toLocation = toLocation;
        this.player = player;
        this.lastMoveTime = lastMoveTime;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public Location getFrom() {
        return this.oldLocation;
    }
    
    public Location getTo() {
        return this.toLocation;
    }
    
    public long getLastMoveTime() {
        return this.lastMoveTime;
    }
}
