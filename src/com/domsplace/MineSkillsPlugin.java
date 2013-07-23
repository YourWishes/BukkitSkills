package com.domsplace;

import com.domsplace.Commands.*;
import com.domsplace.Utils.*;
import com.domsplace.DataManagers.*;
import com.domsplace.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MineSkillsPlugin extends JavaPlugin {
    public static PluginManager pluginManager;
    public static boolean isEnabled = false;
    
    //Define Commands
    public static final MineSkillsCommand commandMineSkills = new MineSkillsCommand();
    public static final SkillStatsCommand commandSkillStats = new SkillStatsCommand();
    
    //Define Listeners
    public static MineSkillsArcheryListener ArcheryListener;
    public static MineSkillsAdventuringListener AdventuringListener;
    public static MineSkillsMiningListener MiningListener;
    public static MineSkillsWoodcuttingListener WoodcuttingListener;
    public static MineSkillsCookingListener CookingListener;
    public static MineSkillsFishingListener FishingListener;
    public static MineSkillsFarmingListener FarmingListener;
    public static MineSkillsSmithingListener SmithingListener;
    public static MineSkillsStrengthListener StrengthListener;
    public static MineSkillsDefenceListener DefenceListener;
    public static MineSkillsAttackListener AttackListener;
    public static MineSkillsEnchantingListener EnchantingListener;
    
    public static MineSkillsCustomEventListener CustomListener;
    public static MineSkillsGlobalListener GlobalListener;
    
    @Override
    public void onEnable() {
        pluginManager = Bukkit.getPluginManager();
        MineSkillsUtils.plugin = getMineSkillsPlugin();
        
        //Load Data
        MineSkillsPluginManager.LoadPlugin();
        
        if(!MineSkillsConfigManager.loadConfig()) {
            disable();
            MineSkillsUtils.Error("Failed to load config, check console for errors.", null);
            return;
        }
        
        if(!MineSkillsSkillsManager.LoadSkills()) {
            disable();
            MineSkillsUtils.Error("Failed to load Skills YML.", null);
            return;
        }
        
        if(!MineSkillsPlayerDataManager.setupFiles()) {
            disable();
            MineSkillsUtils.Error("Failed to load Players.", null);
            return;
        }
        
        //Load Listeners
        ArcheryListener = new MineSkillsArcheryListener();
        AdventuringListener = new MineSkillsAdventuringListener();
        MiningListener = new MineSkillsMiningListener();
        WoodcuttingListener = new MineSkillsWoodcuttingListener();
        CookingListener = new MineSkillsCookingListener();
        FishingListener = new MineSkillsFishingListener();
        FarmingListener = new MineSkillsFarmingListener();
        SmithingListener = new MineSkillsSmithingListener();
        StrengthListener = new MineSkillsStrengthListener();
        DefenceListener = new MineSkillsDefenceListener();
        AttackListener = new MineSkillsAttackListener();
        EnchantingListener = new MineSkillsEnchantingListener();
        
        CustomListener = new MineSkillsCustomEventListener();
        GlobalListener = new MineSkillsGlobalListener();
        
        //Register Commands
        for(String s : MineSkillsPluginManager.getCommands()) {
            getCommand(s).setExecutor(MineSkillsCommandBase.commands.get(s.toLowerCase()));
            getCommand(s).setPermissionMessage(MineSkillsBase.ChatError + MineSkillsPluginManager.PluginYML.getString("permissionMessage"));
        }
        
        //Register Listeners
        pluginManager.registerEvents(ArcheryListener, this);
        pluginManager.registerEvents(AdventuringListener, this);
        pluginManager.registerEvents(MiningListener, this);
        pluginManager.registerEvents(WoodcuttingListener, this);
        pluginManager.registerEvents(CookingListener, this);
        pluginManager.registerEvents(FishingListener, this);
        pluginManager.registerEvents(FarmingListener, this);
        pluginManager.registerEvents(SmithingListener, this);
        pluginManager.registerEvents(StrengthListener, this);
        pluginManager.registerEvents(DefenceListener, this);
        pluginManager.registerEvents(AttackListener, this);
        pluginManager.registerEvents(EnchantingListener, this);
        
        pluginManager.registerEvents(CustomListener, this);
        pluginManager.registerEvents(GlobalListener, this);
        
        isEnabled = true;
        MineSkillsUtils.broadcastWithPermission(
            "BukkitSkills.admin", 
            "§dLoaded " + MineSkillsPluginManager.getPluginName() + " version " + MineSkillsPluginManager.getPluginVersion() + "."
        );
    }
    
    @Override
    public void onDisable() {
        if(!isEnabled) {
            MineSkillsUtils.Error("Failed to enable!", null);
            return;
        }
        
        MineSkillsPlayerDataManager.SaveAllPlayers();
        GlobalListener.saveConfigManager.cancel();
    }
    
    public void disable() {
        pluginManager.disablePlugin(this);
    }
    
    //Self Referencing
    public static com.domsplace.MineSkillsPlugin getMineSkillsPlugin() {
        try {
            Plugin p = Bukkit.getPluginManager().getPlugin("BukkitSkills");
            if(p == null || !(p instanceof com.domsplace.MineSkillsPlugin)) {
                return null;
            }
            
            return (com.domsplace.MineSkillsPlugin) p;
        } catch(NoClassDefFoundError e) {
            return null;
        }
    }
}
