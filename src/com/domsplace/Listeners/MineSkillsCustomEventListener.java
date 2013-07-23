package com.domsplace.Listeners;

import com.domsplace.CustomEvents.MineSkillsBowHitEvent;
import com.domsplace.CustomEvents.MineSkillsCraftItemEvent;
import com.domsplace.CustomEvents.MineSkillsPlayerMoveEvent;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class MineSkillsCustomEventListener extends MineSkillsListenerBase {
    
    public static HashMap<Player, Location> moveRadius = new HashMap<Player, Location>();
    public static HashMap<Player, Long> moveTime = new HashMap<Player, Long>();
    
    @EventHandler(ignoreCancelled=true)
    public void MineSkillsPlayerMoveEventListener(PlayerMoveEvent e) {
        Location oldLocation;
        Location loc = e.getTo();
        
        int radius = 2;
        
        try {
            oldLocation = moveRadius.get(e.getPlayer());
            
            int lX = (int) loc.getX();
            int minX = (int) (oldLocation.getX() - radius);
            int maxX = (int) (oldLocation.getX() + radius);
            
            int lY = (int) loc.getY();
            int minY = (int) (oldLocation.getY() - radius);
            int maxY = (int) (oldLocation.getY() + radius);
            
            int lZ = (int) loc.getZ();
            int minZ = (int) (oldLocation.getZ() - radius);
            int maxZ = (int) (oldLocation.getZ() + radius);
            
            boolean x = (lX >= maxX) || (lX <= minX);
            boolean y = (lY >= maxY) || (lY <= minY);
            boolean z = (lZ >= maxZ) || (lZ <= minZ);
            
            if(x || y || z) {
                MineSkillsPlayerMoveEvent event = new MineSkillsPlayerMoveEvent(oldLocation, loc, e.getPlayer(), moveTime.get(e.getPlayer()));
                Bukkit.getPluginManager().callEvent(event);
                
                if(event.isCancelled()) {
                    e.setTo(oldLocation);
                    return;
                }
                
                moveRadius.put(e.getPlayer(), e.getTo());
                moveTime.put(e.getPlayer(), getNow());
            }
        } catch(Exception ex) {
            moveRadius.put(e.getPlayer(), e.getTo());
            moveTime.put(e.getPlayer(), getNow());
        }
    }
    
    
    @EventHandler(ignoreCancelled=true)
    public void onBowDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity() == null) {
            return;
        }
        
        if(e.getDamager() == null) {
            return;
        }
        
        if(e.getDamager().getType() != EntityType.ARROW) {
            return;
        }
        
        Entity hit = e.getEntity();
        Arrow arrow = (Arrow) e.getDamager();
        
        if(arrow.getShooter() == null) {
            return;
        }
        
        Entity killer = arrow.getShooter();
        
        MineSkillsBowHitEvent event = new MineSkillsBowHitEvent(hit, arrow, killer);
        Bukkit.getPluginManager().callEvent(event);
        
        if(event.isCancelled()) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler(ignoreCancelled=true)
    private void onCraftItem(CraftItemEvent e) {
        Player player = Bukkit.getPlayerExact(e.getWhoClicked().getName());
        MineSkillsCraftItemEvent event = new MineSkillsCraftItemEvent(player, e.getRecipe(), e.getView(), e.getSlotType(), e.getSlot(), e.isRightClick(), e.isShiftClick());
        
        /*** ENSURE EVENT IS VALID ***/
        
        if(event.getPlayer() == null) {
            return;
        }
        
        ItemStack[] craftedItems = event.getCraftedItems();
        if(craftedItems == null) {
            return;
        }
        if(craftedItems.length < 1) {
            return;
        }
        
        Material itemType = craftedItems[0].getType();
        if(e.getCursor() != null && e.getCursor().getType() != null  && e.getCursor().getType() != itemType && e.getCursor().getType() != Material.AIR) {
            return;
        }
        
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled()) {
            e.setCancelled(true);
        }
    }
}
