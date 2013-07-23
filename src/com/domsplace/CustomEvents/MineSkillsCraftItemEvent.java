package com.domsplace.CustomEvents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class MineSkillsCraftItemEvent extends MineSkillsCustomEventBase {
    
    private Player player;
    private Recipe recipe;
    private InventoryView view;
    private InventoryType.SlotType slotType;
    private int slot;
    private boolean rightClick;
    private boolean shiftClick;
    
    public MineSkillsCraftItemEvent(Player player, Recipe recipe, InventoryView what, InventoryType.SlotType type, int slot, boolean right, boolean shift) {
        this.player = player;
        this.recipe = recipe;
        this.view = what;
        this.slotType = type;
        this.slot = slot;
        this.rightClick = right;
        this.shiftClick = shift;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public boolean isShiftClick() {
        return this.shiftClick;
    }
    
    public boolean isRightClick() {
        return this.rightClick;
    }
    
    public ItemStack getCurrentItem() {
        return view.getItem(slot);
    }
    
    public InventoryView getView() {
        return this.view;
    }
    
    public CraftingInventory getInventory() {
        return (CraftingInventory) this.getView().getTopInventory();
    }
    
    public boolean isValidRecipe() {
        if(this.getCurrentItem() == null || this.getCurrentItem().getType() == null || this.getCurrentItem().getType() == Material.AIR) {
            return false;
        }
        return true;
    }
    
    public ItemStack[] getCraftedItem() {
        ItemStack[] is = new ItemStack[1];
        is[0] = this.getCurrentItem();
        return is;
    }
    
    public ItemStack[] getCraftableItems() {
        if(!this.isValidRecipe()) {
            return null;
        }
        
        ItemStack[] contents = this.getInventory().getMatrix();
        
        int lowest = 0;
        
        for(ItemStack is : contents) {
            if(is == null || is.getType() == null) {
                continue;
            }

            if(is.getType() == Material.AIR) {
                continue;
            }
            if(is.getAmount() > lowest) {
                lowest = is.getAmount();
            }
        }
        
        for(ItemStack is : contents) {
            if(is == null || is.getType() == null) {
                continue;
            }
            
            if(is.getType() == Material.AIR) {
                continue;
            }
            
            if(is.getAmount() <= lowest) {
                lowest = is.getAmount();
            }
        }
        
        
        ItemStack[] items = new ItemStack[lowest];
        for(int i = 0; i < lowest; i++) {
            items[i] = this.getCurrentItem();
        }
        
        return items;
    }
    
    public ItemStack[] getCraftedItems() {
        
        ItemStack[] start = this.getCraftedItem();
        
        if(this.isShiftClick()) {
            start = this.getCraftableItems();
        }
        
        return start;
    }
    
    public ItemStack getCursor() {
        return getView().getCursor();
    }
}