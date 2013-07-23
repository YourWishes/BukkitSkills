package com.domsplace.Commands;

import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import com.domsplace.Objects.MineSkillsSkillLevels;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillStatsCommand extends MineSkillsCommandBase {
    
    public SkillStatsCommand() {
        super("SkillStats");
    }
    
    @Override
    public boolean gotCommand(CommandSender sender, Command cmd, String label, String[] args) {
        OfflinePlayer targetPlayer = null;
        MineSkillsSkill skill = null;
        
        if(args.length >= 1) {
            targetPlayer = getOfflinePlayer(args[0], sender);
            if(targetPlayer == null) {
                //Try to get Skill
                skill = MineSkillsSkill.getSkill(args[0]);
                
                if(skill == null) {
                    sender.sendMessage(ChatError + args[0] + " isn't a player/skill.");
                    return true;
                }
            }
        }
        
        if(targetPlayer == null && skill == null && !(sender instanceof Player)) {
            sender.sendMessage(ChatError + "Only players can lookup their own stats.");
            return true;
        }
        
        if(targetPlayer == null) {
            targetPlayer = Bukkit.getOfflinePlayer(sender.getName());
        }
        
        if(args.length >= 2) {
            //Try to get Skill
            skill = MineSkillsSkill.getSkill(args[1]);

            if(skill == null) {
                sender.sendMessage(ChatError + args[1] + " isn't a skill.");
                return true;
            }
        }
        
        if(skill == null) {
            //Send ALL Stats
            
            sender.sendMessage(
                ChatImportant + "Skill levels for " + 
                ChatDefault + targetPlayer.getName() + 
                ChatImportant + ", Overall level: " + 
                ChatDefault + MineSkillsPlayer.getPlayer(targetPlayer).getOverallLevel()
            );
            
            for(MineSkillsSkill sk : MineSkillsSkill.skills) {
                sender.sendMessage(
                    ChatImportant + "Skill: " + ChatDefault + sk.getName()
                    + " §7: " + ChatImportant + "Level: " + ChatDefault + MineSkillsPlayer.getPlayer(targetPlayer).getSkillLevel(sk).getLevel()
                );
            }
            
            return true;
        }
        
        MineSkillsSkillLevels xp = MineSkillsPlayer.getPlayer(targetPlayer).getSkillLevel(skill);
        
        sender.sendMessage(ChatImportant + "Skill: " + ChatDefault + skill.getName() + ChatImportant + " for " + ChatDefault + targetPlayer.getName());
        sender.sendMessage(ChatImportant + "Level: " + ChatDefault + xp.getLevel());
        sender.sendMessage(ChatImportant + "XP: " + ChatDefault + xp.getXP() + "/" + xp.getXPToNextLevel());
        
        return true;
    }
}
