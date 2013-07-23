package com.domsplace.Listeners;

import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class MineSkillsDefenceListener extends MineSkillsListenerBase {
    @EventHandler(ignoreCancelled=true)
    public void onPlayerDamage(EntityDamageEvent e) {
        if(e.getEntity() == null) {
            return;
        }
        
        if(!e.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }
        
        Player player = (Player) e.getEntity();
        
        if(!player.isBlocking()) {
            return;
        }
        
        MineSkillsPlayer plyr = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(player.getName()));
        plyr.getSkillLevel(MineSkillsSkill.Defence).addXP(getXP(MineSkillsSkill.Defence, "PerHP") * e.getDamage(), plyr, MineSkillsSkill.Defence);
    }
}
