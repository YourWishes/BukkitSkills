package com.domsplace.DataManagers;

import com.domsplace.MineSkillsBase;
import com.domsplace.Objects.MineSkillsSkill;
import com.domsplace.Utils.MineSkillsUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

public class MineSkillsSkillsManager extends MineSkillsBase {
    
    public static File f;
    public static YamlConfiguration yml;
    
    public static boolean LoadSkills() {
        try {
            
            f = new File(getPlugin().getDataFolder(), "skills.yml");
            if(!f.exists()) {
                f.createNewFile();
            }
            
            yml = YamlConfiguration.loadConfiguration(f);
            
            /*** Load in Defaults ***/
            
            //Store Default Skill Names
            for(MineSkillsSkill s : MineSkillsSkill.skills) {
                String name = s.getType();
                String oldName = s.getName();
                dV(name + ".name", oldName);
                dV(name + ".defaultLevel", 1.0d);
            }
            
            //Store Default XP Values
            dV("Adventuring.MoveXP", 1);
            
            dV("Archery.HitAnimal", 1);
            dV("Archery.HitMonster", 1);
            dV("Archery.HitPlayer", 2);
            
            dV("Mining.1", 1);
            dV("Mining.14", 4);
            dV("Mining.15", 3);
            dV("Mining.16", 2);
            dV("Mining.21", 2);
            dV("Mining.56", 7);
            dV("Mining.73", 4);
            dV("Mining.74", 4);
            dV("Mining.129", 5);
            
            dV("Woodcutting.17", 4);
            dV("Woodcutting.106", 1);
            dV("Woodcutting.18", 1);
            
            dV("Cooking.354", 10);
            dV("Cooking.92", 10);
            dV("Cooking.297", 3);
            dV("Cooking.364", 2);
            dV("Cooking.366", 2);
            dV("Cooking.350", 2);
            dV("Cooking.319", 2);
            dV("Cooking.363", 1);
            dV("Cooking.365", 1);
            dV("Cooking.349", 1);
            dV("Cooking.392", 2);
            dV("Cooking.391", 3);
            dV("Cooking.360", 5);
            dV("Cooking.393", 3);
            dV("Cooking.357", 1);
            dV("Cooking.396", 4);
            dV("Cooking.322", 5);
            dV("Cooking.260", 5);
            dV("Cooking.400", 5);
            dV("Cooking.353", 1);
            dV("Cooking.282", 3);
            dV("Cooking.320", 3);
            
            dV("Fishing.FishingXP", 3);
            
            dV("Farming.142:7", 2);
            dV("Farming.115:3", 3);
            dV("Farming.141:7", 3);
            dV("Farming.59:7", 1);
            dV("Farming.83", 2);
            dV("Farming.127", 4);
            dV("Farming.86", 5);
            dV("Farming.103", 5);
            dV("Farming.81", 1);
            
            //SMITHING (Too lazy to re-add)
            Map<Material, Integer> smithingOres = new HashMap<Material, Integer>();
            smithingOres.put(Material.DIAMOND, 5);
            smithingOres.put(Material.GOLD_INGOT, 2);
            smithingOres.put(Material.IRON_INGOT, 2);
            smithingOres.put(Material.COAL, 1);
            smithingOres.put(Material.EMERALD, 5);
            smithingOres.put(Material.GLASS, 1);
            smithingOres.put(Material.STONE, 1);
            smithingOres.put(Material.CLAY_BRICK, 1);
            smithingOres.put(Material.NETHER_BRICK_ITEM, 1);
            smithingOres.put(Material.REDSTONE, 1);
            smithingOres.put(Material.QUARTZ, 1);
            smithingOres.put(Material.DIAMOND_AXE, 20);
            smithingOres.put(Material.DIAMOND_BOOTS, 15);
            smithingOres.put(Material.DIAMOND_CHESTPLATE, 30);
            smithingOres.put(Material.DIAMOND_HELMET, 15);
            smithingOres.put(Material.DIAMOND_HOE, 12);
            smithingOres.put(Material.DIAMOND_LEGGINGS, 25);
            smithingOres.put(Material.DIAMOND_PICKAXE, 20);
            smithingOres.put(Material.DIAMOND_SPADE, 10);
            smithingOres.put(Material.DIAMOND_SWORD, 12);
            smithingOres.put(Material.GOLD_AXE, 10);
            smithingOres.put(Material.GOLD_BOOTS, 7);
            smithingOres.put(Material.GOLD_CHESTPLATE, 15);
            smithingOres.put(Material.GOLD_HELMET, 7);
            smithingOres.put(Material.GOLD_HOE, 6);
            smithingOres.put(Material.GOLD_LEGGINGS, 12);
            smithingOres.put(Material.GOLD_PICKAXE, 10);
            smithingOres.put(Material.GOLD_SPADE, 5);
            smithingOres.put(Material.GOLD_SWORD, 6);
            smithingOres.put(Material.IRON_AXE, 10);
            smithingOres.put(Material.IRON_BOOTS, 7);
            smithingOres.put(Material.IRON_CHESTPLATE, 15);
            smithingOres.put(Material.IRON_HELMET, 7);
            smithingOres.put(Material.IRON_HOE, 6);
            smithingOres.put(Material.IRON_LEGGINGS, 12);
            smithingOres.put(Material.IRON_PICKAXE, 10);
            smithingOres.put(Material.IRON_SPADE, 5);
            smithingOres.put(Material.IRON_SWORD, 6);
            smithingOres.put(Material.STONE_AXE, 5);
            smithingOres.put(Material.CHAINMAIL_BOOTS, 4);
            smithingOres.put(Material.CHAINMAIL_CHESTPLATE, 7);
            smithingOres.put(Material.CHAINMAIL_HELMET, 4);
            smithingOres.put(Material.STONE_HOE, 3);
            smithingOres.put(Material.CHAINMAIL_LEGGINGS, 6);
            smithingOres.put(Material.STONE_PICKAXE, 5);
            smithingOres.put(Material.STONE_SPADE, 2);
            smithingOres.put(Material.STONE_SWORD, 3);
            smithingOres.put(Material.LEATHER_BOOTS, 2);
            smithingOres.put(Material.LEATHER_CHESTPLATE, 4);
            smithingOres.put(Material.LEATHER_HELMET, 2);
            smithingOres.put(Material.LEATHER_LEGGINGS, 3);
            
            for(Material m : smithingOres.keySet()) {
                int amount = smithingOres.get(m);
                dV("Smithing." + m.getId(), amount);
            }
            
            dV("Strength.PerHP", 1);
            
            dV("Defence.PerHP", 1);
            
            dV("Attack.PerHP", 1);
            
            dV("Enchanting.Multiplier", 1);
            
            /*** Update Data ***/
            for(MineSkillsSkill s : MineSkillsSkill.skills) {
                String name = s.getType();                
                s.setName(yml.getString(name + ".name"));
                s.DEFAULT_LEVEL = yml.getDouble(name + ".defaultLevel");
            }
            
            /*** Save Data ***/
            yml.save(f);
            return true;
        } catch(Exception ex) {
            MineSkillsUtils.Error("Failed to Load Skills Defaults.", ex);
            return false;
        }
    }
    
    //Create Default Value
    public static void dV(String key, Object obj) {
        if(yml.contains(key)) {
            return;
        }
        
        yml.set(key, obj);
    }
}
