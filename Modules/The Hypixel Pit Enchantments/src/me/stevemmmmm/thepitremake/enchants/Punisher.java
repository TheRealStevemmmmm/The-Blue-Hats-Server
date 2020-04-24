package me.stevemmmmm.thepitremake.enchants;

import me.stevemmmmm.thepitremake.managers.enchants.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

/*
 * Copyright (c) 2020. Created by Stevemmmmm.
 */

public class Punisher extends CustomEnchant {
    private final LevelVariable<Float> damageIncrease = new LevelVariable<>(.6f, .12f, .18f);

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            attemptEnchantExecution(((Player) event.getDamager()).getInventory().getItemInHand(), event.getEntity(), event);
        }
    }

    @Override
    public void applyEnchant(int level, Object... args) {
        Player damaged = (Player) args[0];
        EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) args[1];

        if (damaged.getHealth() < damaged.getMaxHealth() / 2) {
            DamageManager.getInstance().addDamage(event, damageIncrease.at(level), CalculationMode.ADDITIVE);
        }
    }

    @Override
    public String getName() {
        return "Punisher";
    }

    @Override
    public String getEnchantReferenceName() {
        return "Punisher";
    }

    @Override
    public ArrayList<String> getDescription(int level) {
        return new LoreBuilder()
                .addVariable("+6%", "+12%", "+18%")
                .write("Deal ").writeVariable(ChatColor.RED, 0, level).write(" damage vs. player").nextLine()
                .write("below 50% HP")
                .build();
    }

    @Override
    public boolean isRemovedFromPassiveWorld() {
        return false;
    }

    @Override
    public EnchantGroup getEnchantGroup() {
        return EnchantGroup.A;
    }

    @Override
    public boolean isRareEnchant() {
        return false;
    }

    @Override
    public Material getEnchantItemType() {
        return Material.GOLD_SWORD;
    }
}