package com.domsplace.Listeners;

import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

public class MineSkillsFishingListener extends MineSkillsListenerBase {
    @EventHandler(ignoreCancelled=true)
    public void onFish(PlayerFishEvent e) {
        if(e.getCaught() == null) {
            return;
        }
        
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(e.getPlayer().getName()));
        player.getSkillLevel(MineSkillsSkill.Fishing).addXP(getXP(MineSkillsSkill.Fishing, "FishingXP"), player, MineSkillsSkill.Fishing);
    }
}
