package com.domsplace.Commands;

import com.domsplace.DataManagers.MineSkillsConfigManager;
import com.domsplace.DataManagers.MineSkillsPlayerDataManager;
import com.domsplace.DataManagers.MineSkillsPluginManager;
import com.domsplace.DataManagers.MineSkillsSkillsManager;
import static com.domsplace.MineSkillsBase.ChatDefault;
import static com.domsplace.MineSkillsBase.ChatError;
import static com.domsplace.MineSkillsBase.ChatImportant;
import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import com.domsplace.Utils.MineSkillsUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MineSkillsCommand extends MineSkillsCommandBase {
    
    public MineSkillsCommand() {
        super("BukkitSkills");
    }
    
    @Override
    public boolean gotCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length < 1) {
            String[] messages = {
                ChatImportant + "                        " + MineSkillsPluginManager.getPluginName() + " v" + MineSkillsPluginManager.getPluginVersion(),
                ChatDefault + "----" + ChatImportant + "Programmed by " + ChatDefault + " Dominic Masters",
                ChatDefault + "----" + ChatImportant + "Tested by " + ChatDefault + "Jordan Atkins"
            };
            
            sender.sendMessage(messages);
            return true;
        }
        
        if(args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(ChatDefault + "Reloading Config...");

            if(!MineSkillsConfigManager.loadConfig()) {
                sender.sendMessage(ChatError + "Failed to reload Config! Check Console for more information.");
                return true;
            }
            
            if(!MineSkillsSkillsManager.LoadSkills()) {
                sender.sendMessage(ChatError + "Failed to reload Skills! Check Console for more information.");
                return true;
            }
            
            MineSkillsPlayerDataManager.LoadAllPlayers();

            sender.sendMessage(ChatImportant + "Reloaded Config!");
            return true;
        }
        
        if(args[0].equalsIgnoreCase("save")) {
            sender.sendMessage(ChatDefault + "Saving Data...");
            MineSkillsPlayerDataManager.SaveAllPlayers();
            sender.sendMessage(ChatImportant + "Saved Data.");
            return true;
        }
        
        if(args[0].equalsIgnoreCase("xp")) {
            //Try Add XP
            if(args.length < 2) {
                sender.sendMessage(ChatError + "Invalid Sub command");
                return false;
            }
            
            if(args.length < 3) {
                sender.sendMessage(ChatError + "Please enter a player name.");
                return false;
            }
            
            if(args.length < 4) {
                sender.sendMessage(ChatError + "Please enter a skill.");
                return false;
            }
            
            if(args.length < 5) {
                sender.sendMessage(ChatError + "Please enter an amount.");
                return false;
            }
            
            if(!isDouble(args[4])) {
                sender.sendMessage(ChatError + "Please enter a valid number");
                return false;
            }
            
            String sc = args[1];
            OfflinePlayer player = MineSkillsUtils.getOfflinePlayer(args[2], sender);
            double amount = cDouble(args[4]);
            MineSkillsSkill skill = MineSkillsSkill.getSkill(args[3]);
            
            if(skill == null) {
                sender.sendMessage(ChatError + args[3] + " isn't a valid skill name.");
                return false;
            }
            
            if(player == null) {
                sender.sendMessage(ChatError + args[2] + " has never played before.");
                return false;
            }
            
            if(sc.equalsIgnoreCase("set")) {
                MineSkillsPlayer.getPlayer(player).getSkillLevel(skill).setXP(amount);
                sender.sendMessage(
                    ChatDefault + "Set " + 
                    ChatImportant + player.getName() + "'s" +
                    ChatDefault + " xp in " +
                    ChatImportant + skill.getName() +
                    ChatDefault + " to " +
                    ChatImportant + amount
                );
                
                return true;
            }
            
            if(sc.equalsIgnoreCase("add")) {
                MineSkillsPlayer.getPlayer(player).getSkillLevel(skill).addXP(amount, MineSkillsPlayer.getPlayer(player), skill);
                sender.sendMessage(
                    ChatDefault + "Added XP to " + 
                    ChatImportant + player.getName() + "'s" +
                    ChatDefault + " skill, " +
                    ChatImportant + skill.getName() +
                    ChatDefault + " with " +
                    ChatImportant + amount
                );
                
                return true;
            }
        }
        
        sender.sendMessage(ChatError + "Invalid argument.");
        return false;
    }
}
