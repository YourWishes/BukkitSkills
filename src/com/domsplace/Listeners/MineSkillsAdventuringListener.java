package com.domsplace.Listeners;

import com.domsplace.CustomEvents.MineSkillsPlayerMoveEvent;
import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

public class MineSkillsAdventuringListener extends MineSkillsListenerBase {
    
    @EventHandler
    public void onPlayerMove(MineSkillsPlayerMoveEvent e) {
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(e.getPlayer().getName()));
        player.getSkillLevel(MineSkillsSkill.Adventuring).addXP(getXP(MineSkillsSkill.Adventuring, "MoveXP"), player, MineSkillsSkill.Adventuring);
    }
}
