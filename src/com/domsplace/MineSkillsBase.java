package com.domsplace;

import com.domsplace.DataManagers.MineSkillsSkillsManager;
import com.domsplace.Objects.MineSkillsSkill;
import com.domsplace.Utils.MineSkillsUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MineSkillsBase {
    
    public static String ChatDefault = ChatColor.GRAY + "";
    public static String ChatImportant = ChatColor.BLUE + "";
    public static String ChatError = ChatColor.RED + "";
    
    public static double nearbyRadius = 5.0d;
    
    public static List<World> MineSkillsWorlds;
    
    public static MineSkillsPlugin getPlugin() {
        return MineSkillsUtils.plugin;
    }
    
    public static String Colorise(String string) {
        
        String[] normvalues = { "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f", "&l", "&m", "&n", "&k", "&r", "&o" };
        String[] coloredvalues = { "§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§a", "§b", "§c", "§d", "§e", "§f", "§l", "§m", "§n", "§k", "§r", "§o" };
        
        for(int i = 0; i < normvalues.length; i++) {
            string = string.replaceAll(normvalues[i], coloredvalues[i]);
        }
        
        return string;
    }
    
    public static long getNow() {
        Date now = new Date();
        return now.getTime();
    }
    
    public static double getXP(MineSkillsSkill skill, String xpType) {
        return MineSkillsSkillsManager.yml.getDouble(skill.getType() + "." + xpType);
    }
    
    public static void debug(Object obj) {
        MineSkillsUtils.broadcastWithPermission("BukkitSkills.*", "§dDEBUG: §b" + obj.toString());
    }
    
    public static List<Player> getNearbyPlayers(Player player) {
        return getNearbyPlayers(player, false);
    }
    
    public static List<Player> getNearbyPlayers(Player player, boolean includeSelf) {
        List<Entity> ents = player.getNearbyEntities(nearbyRadius, nearbyRadius, nearbyRadius);
        
        List<Player> players = new ArrayList<Player>();
        
        for(Entity ent : ents) {
            if(!ent.getType().equals(EntityType.PLAYER)) {
                continue;
            }
            
            if(!includeSelf && ent.equals(player)) {
                continue;
            }
            
            players.add((Player) ent);
        }
        
        return players;
    }
    
    public static boolean CanSee(CommandSender sender, OfflinePlayer target) {
        if(!(sender instanceof Player)) {
            return true;
        }
        
        if(!target.isOnline()) {
            return true;
        }
        
        Player p = (Player) sender;
        Player t = target.getPlayer();
        
        if(!p.canSee(t)) {
            return false;
        }
        return true;
    }
    
    public static OfflinePlayer getOfflinePlayer(String arg, CommandSender sender) {
        OfflinePlayer p = Bukkit.getPlayer(arg);
        if(p == null) {
            p = Bukkit.getOfflinePlayer(arg);
            
            if(!p.hasPlayedBefore()) {
                return null;
            }
            
            return p;
        }
        
        if(!CanSee(sender, p)) {
            p = Bukkit.getPlayerExact(arg);
            if(p == null) {
                return null;
            }
            
            return p;
        }
        
        return p;
    }
    
    public static boolean isInt(String integer) {
        try {
            int x = Integer.parseInt(integer);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }
    
    public static int cInt(String c) {
        try {
            return Integer.parseInt(c);
        } catch(NumberFormatException ex) {
            return -1;
        }
    }
    
    public static boolean isDouble(String integer) {
        try {
            double x = Double.parseDouble(integer);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }
    
    public static double cDouble(String c) {
        try {
            return Double.parseDouble(c);
        } catch(NumberFormatException ex) {
            return -1d;
        }
    }
}
