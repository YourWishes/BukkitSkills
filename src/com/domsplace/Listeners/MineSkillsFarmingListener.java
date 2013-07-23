package com.domsplace.Listeners;

import com.domsplace.DataManagers.MineSkillsSkillsManager;
import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class MineSkillsFarmingListener extends MineSkillsListenerBase {
    @EventHandler(ignoreCancelled=true)
    public void onBlockBreak(BlockBreakEvent e) {
        int id = e.getBlock().getTypeId();
        
        if(!MineSkillsSkillsManager.yml.contains("Farming." + id) && !MineSkillsSkillsManager.yml.contains("Farming." + id + ":" + e.getBlock().getData())) {
            return;
        }
        
        double amount = 0.0d;
        
        if(MineSkillsSkillsManager.yml.contains("Farming." + id)) {
            amount = getXP(MineSkillsSkill.Farming, "" + id);
        }
        
        if(MineSkillsSkillsManager.yml.contains("Farming." + id + ":" + e.getBlock().getData())) {
            amount = getXP(MineSkillsSkill.Farming, "" + id + ":" + e.getBlock().getData());
        }
        
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(e.getPlayer().getName()));
        player.getSkillLevel(MineSkillsSkill.Farming).addXP(amount, player, MineSkillsSkill.Farming);
    }
}
