package com.domsplace.Listeners;

import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MineSkillsAttackListener extends MineSkillsListenerBase {
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e) {
        if(e.getDamager() == null) {
            return;
        }
        
        if(e.getDamager().getType() == null) {
            return;
        }
        
        if(!e.getDamager().getType().equals(EntityType.PLAYER)) {
            return;
        }
        
        Player damager = (Player) e.getDamager();
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(damager.getName()));
        
        double amount = getXP(MineSkillsSkill.Attack, "PerHP") * e.getDamage();
        player.getSkillLevel(MineSkillsSkill.Attack).addXP(amount, player, MineSkillsSkill.Attack);
    }
}
