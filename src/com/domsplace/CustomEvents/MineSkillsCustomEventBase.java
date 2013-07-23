package com.domsplace.CustomEvents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MineSkillsCustomEventBase extends Event {
    
    private static final HandlerList handlers = new HandlerList();
    
    private boolean isCancelled = false;
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public boolean isCancelled() {
        return this.isCancelled;
    }
    
    public void setCancelled(boolean result) {
        this.isCancelled = result;
    }
}
