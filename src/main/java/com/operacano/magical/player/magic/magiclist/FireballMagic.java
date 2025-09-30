package com.operacano.magical.player.magic.magiclist;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Example fireball magic. Spawns a fireball in the direction the player is looking.
 */
public class FireballMagic {
    private final int manaCost = 10;
    private final int cooldown = 40; // ticks

    public boolean cast(Player player) {
        Level level = player.level();
        Vec3 look = player.getLookAngle();
        Vec3 pos = player.getEyePosition();
        // TODO: Replace with your custom fireball entity or effect
        // Example: spawn a vanilla fireball or particle
        // level.addFreshEntity(new SmallFireball(level, pos.x, pos.y, pos.z, look.x, look.y, look.z));
        // For now, just print to log (or use a debug message)
        System.out.println("Fireball cast by " + player.getName().getString() + " at " + pos + " direction " + look);
        return true;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCooldown() {
        return cooldown;
    }
}
