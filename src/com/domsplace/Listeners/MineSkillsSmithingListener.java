package com.domsplace.Listeners;

import com.domsplace.CustomEvents.MineSkillsCraftItemEvent;
import com.domsplace.DataManagers.MineSkillsSkillsManager;
import static com.domsplace.MineSkillsBase.getXP;
import com.domsplace.Objects.MineSkillsPlayer;
import com.domsplace.Objects.MineSkillsSkill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.inventory.ItemStack;

public class MineSkillsSmithingListener extends MineSkillsListenerBase {
    @EventHandler
    public void onCraftItem(MineSkillsCraftItemEvent e) {
        if(e.isCancelled()) {
            return;
        }
        
        Material itemType = e.getCraftedItems()[0].getType();
        if(!MineSkillsSkillsManager.yml.contains("Smithing." + itemType.getId())) {
            return;
        }
        
        
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(e.getPlayer().getName()));
        
        double amount = getXP(MineSkillsSkill.Smithing, "" + itemType.getId());
        double famount = 0.0d;
        
        for(ItemStack is : e.getCraftedItems()) {
            famount += is.getAmount() * amount;
        }
        
        player.getSkillLevel(MineSkillsSkill.Smithing).addXP(famount, player, MineSkillsSkill.Smithing);
    }
    
    @EventHandler(ignoreCancelled=true)
    public void onFurnaceExtract(FurnaceExtractEvent e) {
        if(e.getItemType() == Material.AIR) {
            return;
        }
        
        Material itemType = e.getItemType();
        if(!MineSkillsSkillsManager.yml.contains("Smithing." + itemType.getId())) {
            return;
        }
        
        MineSkillsPlayer player = MineSkillsPlayer.getPlayer(Bukkit.getOfflinePlayer(e.getPlayer().getName()));
        double amount = getXP(MineSkillsSkill.Smithing, "" + itemType.getId());
        double famount = amount * e.getItemAmount();
        
        player.getSkillLevel(MineSkillsSkill.Smithing).addXP(famount, player, MineSkillsSkill.Smithing);
    }
}
