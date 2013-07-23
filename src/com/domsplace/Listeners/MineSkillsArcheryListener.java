package com.domsplace.Listeners;

import com.domsplace.CustomEvents.MineSkillsBowHitEvent;
import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import com.domsplace.Utils.MineSkillsUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class MineSkillsArcheryListener extends MineSkillsListenerBase {
    @EventHandler
    public void onBowHit(MineSkillsBowHitEvent e) {
        if(e.isCancelled()) {
            return;
        }
        
        if(!e.getShooter().getType().equals(EntityType.PLAYER)) {
            return;
        }
        
        Player p = (Player) e.getShooter();
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(p.getName()));
        
        if(MineSkillsUtils.isHostile(e.getDamaged().getType())) {
            player.getSkillLevel(MineSkillsSkill.Archery).addXP(getXP(MineSkillsSkill.Archery, "HitMonster"), player, MineSkillsSkill.Archery);
            return;
        }
        
        if(e.getDamaged().getType().equals(EntityType.PLAYER)) {
            player.getSkillLevel(MineSkillsSkill.Archery).addXP(getXP(MineSkillsSkill.Archery, "HitPlayer"), player, MineSkillsSkill.Archery);
            return;
        }
        
        player.getSkillLevel(MineSkillsSkill.Archery).addXP(getXP(MineSkillsSkill.Archery, "HitAnimal"), player, MineSkillsSkill.Archery);
    }
}
