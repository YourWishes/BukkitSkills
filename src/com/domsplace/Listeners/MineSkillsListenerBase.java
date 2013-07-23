package com.domsplace.Listeners;

import com.domsplace.MineSkillsBase;
import com.domsplace.Objects.MineSkillsPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class MineSkillsListenerBase extends MineSkillsBase implements Listener {
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        MineSkillsPlayer.getPlayer(e.getPlayer());
    }
}
