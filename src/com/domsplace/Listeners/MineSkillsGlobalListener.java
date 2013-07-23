package com.domsplace.Listeners;

import com.domsplace.CustomEvents.MineSkillsLevelUpEvent;
import com.domsplace.CustomEvents.MineSkillsOverallLevelUpEvent;
import com.domsplace.DataManagers.MineSkillsConfigManager;
import com.domsplace.DataManagers.MineSkillsPlayerDataManager;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class MineSkillsGlobalListener extends MineSkillsListenerBase {
    
    public BukkitTask saveConfigManager;
    public MineSkillsGlobalListener() {
        super();
        
        saveConfigManager = Bukkit.getServer().getScheduler().runTaskTimer(getPlugin(), new Runnable() {
            @Override
            public void run() {
                MineSkillsPlayerDataManager.SaveAllPlayers();
            }
        }, 60L, 20L);
    }
    
    @EventHandler
    public void showLevelUpEffect(MineSkillsLevelUpEvent e) {
        if(e.isCancelled()) {
            return;
        }
        
        OfflinePlayer player = e.getPlayer().getPlayer();
        if(!player.isOnline()) {
            return;
        }
        
        Player pl = player.getPlayer();
        
        if(MineSkillsConfigManager.config.getBoolean("notify.levelup")) {
            pl.sendMessage(ChatDefault + "You levelled up to " + ChatImportant + e.getToLevel() + ChatDefault + " in " + ChatImportant + e.getSkill().getName());
        }
        
        if(MineSkillsConfigManager.config.getBoolean("nearbyNotify.levelup")) {
            List<Player> players = getNearbyPlayers(pl);
            
            for(Player plyr : players) {
                plyr.sendMessage(ChatImportant + pl.getName() + ChatDefault + " levelled up to " + ChatImportant + e.getToLevel() + ChatDefault + " in " + ChatImportant + e.getSkill().getName());
            }
        }
        
        if(MineSkillsConfigManager.config.getBoolean("effects.levelup")) {
            //Make sure player doesn't have Night Vision
            
            if(pl.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                return;
            }
            
            PotionEffect notify = new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 1, true);
            
            pl.addPotionEffect(notify);
        }
    }
    
    @EventHandler
    public void showOverallLevelUp(MineSkillsOverallLevelUpEvent e) {
        if(e.isCancelled()) {
            return;
        }
        
        OfflinePlayer player = e.getPlayer().getPlayer();
        if(!player.isOnline()) {
            return;
        }
        
        Player pl = player.getPlayer();
        
        if(MineSkillsConfigManager.config.getBoolean("notify.overallup")) {
        }
        
        if(MineSkillsConfigManager.config.getBoolean("nearbyNotify.overallup")) {
            List<Player> players = getNearbyPlayers(pl);
            
            for(Player plyr : players) {
                plyr.sendMessage(ChatImportant + pl.getName() + ChatDefault + " achieved level " + ChatImportant + e.getToLevel() + ChatDefault + "!");
            }
        }
        
        if(MineSkillsConfigManager.config.getBoolean("effects.overallup")) {
            //Make sure player doesn't have Night Vision
            
            if(pl.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                return;
            }
            
            PotionEffect notify = new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 1, true);
            
            pl.addPotionEffect(notify);
            pl.getWorld().playEffect(pl.getLocation(), Effect.ENDER_SIGNAL, null);
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        MineSkillsPlayerDataManager.LoadPlayer(e.getPlayer());
    }
    
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        onPlayerGone(e.getPlayer());
    }
    
    @EventHandler
    public void onPlayerKicked(PlayerKickEvent e) {
        onPlayerGone(e.getPlayer());
    }
    
    public void onPlayerGone(Player p) {
        MineSkillsPlayerDataManager.SaveAllPlayers();
    }
}
