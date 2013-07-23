package com.domsplace.Commands;

import com.domsplace.DataManagers.MineSkillsPluginManager;
import com.domsplace.MineSkillsBase;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineSkillsCommandBase extends MineSkillsBase implements CommandExecutor {
    
    public static final HashMap<String, MineSkillsCommandBase> commands = new HashMap<String, MineSkillsCommandBase>();
    
    public String command;
    
    public MineSkillsCommandBase(String commandName) {
        commands.put(commandName.toLowerCase(), this);
        command = commandName.toLowerCase();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase(command)) {
            if(!gotCommand(sender, cmd, label, args)) {
                sender.sendMessage(getCommandInfo(command, label));
                return true;
            }
            
            return true;
        }
        return false;
    }
    
    public boolean gotCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }
    
    public static String[] getCommandInfo(String command, String label) {
        String commandName = MineSkillsPluginManager.PluginYML.getString("commands." + command.toLowerCase() + ".description");
        String commandUsage = MineSkillsPluginManager.PluginYML.getString("commands." + command.toLowerCase() + ".usage").replaceAll("<command>", label);
        
        String[] commands = {
            ChatDefault + commandName,
            ChatImportant + commandUsage
        };
        
        return commands;
    }
    
    public static boolean canSee(OfflinePlayer player, CommandSender sender) {
        if(!(sender instanceof Player)) {
            return true;
        }
        
        if(!player.isOnline()) {
            return true;
        }
        
        Player p = (Player) sender;
        Player p2 = player.getPlayer();
        
        if(!p.canSee(p2)) {
            return false;
        }
        return true;
    }
    
    public static OfflinePlayer getOfflinePlayer(String argument, CommandSender sender) {
        OfflinePlayer p = Bukkit.getPlayer(argument);
        if(p == null) {
            p = Bukkit.getOfflinePlayer(argument);
            if(!p.hasPlayedBefore()) {
                return null;
            }
            
            return p;
        }
        
        if(!canSee(p, sender)) {
            p = Bukkit.getPlayerExact(argument);
        }
        
        return p;
    }
}
