package com.domsplace.Objects;

import com.domsplace.MineSkillsBase;
import java.util.ArrayList;
import java.util.List;

public class MineSkillsSkill extends MineSkillsBase {
    
    /*** List ***/
    public static final List<MineSkillsSkill> skills = new ArrayList<MineSkillsSkill>();
    
    /*** Types ***/
    public static final MineSkillsSkill Archery = new MineSkillsSkill("Archery");
    public static final MineSkillsSkill Mining = new MineSkillsSkill("Mining");
    public static final MineSkillsSkill Adventuring = new MineSkillsSkill("Adventuring");
    public static final MineSkillsSkill Woodcutting = new MineSkillsSkill("Woodcutting");
    public static final MineSkillsSkill Cooking = new MineSkillsSkill("Cooking");
    public static final MineSkillsSkill Fishing = new MineSkillsSkill("Fishing");
    public static final MineSkillsSkill Farming = new MineSkillsSkill("Farming");
    public static final MineSkillsSkill Smithing = new MineSkillsSkill("Smithing");
    public static final MineSkillsSkill Strength = new MineSkillsSkill("Strength");
    public static final MineSkillsSkill Defence = new MineSkillsSkill("Defence");
    public static final MineSkillsSkill Attack = new MineSkillsSkill("Attack");
    public static final MineSkillsSkill Enchanting = new MineSkillsSkill("Enchanting");
    
    /*** Static Methods ***/
    public static MineSkillsSkill getSkill(String string) {
        for(MineSkillsSkill s : skills) {
            if(s.getName().toLowerCase().startsWith(string.toLowerCase())) {
                return s;
            }
            
            if(s.getType().toLowerCase().startsWith(string.toLowerCase())) {
                return s;
            }
        }
        
        return null;
    }
    
    /*** Instance ***/
    private String name;
    private String type;
    public double DEFAULT_LEVEL;
    
    public MineSkillsSkill(String name) {
        this.name = name;
        this.type = name;
        
        skills.add(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getType() {
        return this.type;
    }
    
    public MineSkillsSkill setName(String name) {
        this.name = name;
        return this;
    }
}
