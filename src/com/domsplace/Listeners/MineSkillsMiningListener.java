package com.domsplace.Listeners;

import com.domsplace.DataManagers.MineSkillsSkillsManager;
import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class MineSkillsMiningListener extends MineSkillsListenerBase {
    
    @EventHandler(ignoreCancelled=true)
    public void onBlockBreak(BlockBreakEvent e) {
        int id = e.getBlock().getTypeId();
        
        if(!MineSkillsSkillsManager.yml.contains("Mining." + id)) {
            return;
        }
        
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(e.getPlayer().getName()));
        player.getSkillLevel(MineSkillsSkill.Mining).addXP(getXP(MineSkillsSkill.Mining, "" + id), player, MineSkillsSkill.Mining);
    }
    
}
