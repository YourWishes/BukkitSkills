package com.domsplace.Listeners;

import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class MineSkillsEnchantingListener extends MineSkillsListenerBase {
    @EventHandler(ignoreCancelled=true)
    public void onEnchant(EnchantItemEvent e) {
        if(e.getEnchanter() == null) {
            return;
        }
        
        Player player = e.getEnchanter();
        MineSkillsPlayer plyr = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(player.getName()));
        
        double amount = getXP(MineSkillsSkill.Enchanting, "Multiplier") * e.getExpLevelCost();
        plyr.getSkillLevel(MineSkillsSkill.Enchanting).addXP(amount, plyr, MineSkillsSkill.Enchanting);
        
        return;
    }
}
