package com.domsplace.Utils;

import com.domsplace.DataManagers.MineSkillsConfigManager;
import com.domsplace.MineSkillsBase;
import com.domsplace.MineSkillsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MineSkillsUtils extends MineSkillsBase {
    public static MineSkillsPlugin plugin;
    
    public static EntityType[] hostileTypes = {
        EntityType.BLAZE,
        EntityType.CAVE_SPIDER,
        EntityType.CREEPER,
        EntityType.ENDERMAN,
        EntityType.ENDER_DRAGON,
        EntityType.GHAST,
        EntityType.GIANT,
        EntityType.MAGMA_CUBE,
        EntityType.PIG_ZOMBIE,
        EntityType.SILVERFISH,
        EntityType.SKELETON,
        EntityType.SLIME,
        EntityType.SPIDER,
        EntityType.WITHER,
        EntityType.WITCH,
        EntityType.WITHER_SKULL,
        EntityType.ZOMBIE
    };
    
    public static void Error(String Error, Exception cause) {
        msgConsole(ChatColor.DARK_RED + "Error! Cause: " + ChatColor.WHITE + Error);
        
        if(!MineSkillsConfigManager.config.getBoolean("debug")) {
            return;
        }
        
        if(cause != null) {
            cause.printStackTrace();
        }
    }
    
    public static void broadcastWithPermission(String permission, String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!p.hasPermission(permission)) {
                continue;
            }
            msgPlayer(p, message);
        }
        msgConsole(message);
    }
    
    public static void msgConsole(String message) {
        msgPlayer(Bukkit.getConsoleSender(), message);
    }
    
    public static void msgPlayer(CommandSender sender, String message) {
        sender.sendMessage(message);
    }
    
    public static boolean isHostile(EntityType type) {
        for(EntityType t : hostileTypes) {
            if(t.equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMineSkillsWorld(World world) {
        for(World w : MineSkillsWorlds) {
            if(!w.equals(world)) {
                continue;
            }
            return true;
        }
        return false;
    }
}
