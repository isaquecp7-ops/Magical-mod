package com.operacano.magical.player.magic.magicphysics;

import java.util.HashMap;
import java.util.Map;

public class MagicCooldownManager {
    private final Map<String, Integer> cooldowns = new HashMap<>();

    public void setCooldown(String magicId, int ticks) {
        cooldowns.put(magicId, ticks);
    }

    public boolean isOnCooldown(String magicId) {
        return cooldowns.getOrDefault(magicId, 0) > 0;
    }

    public void tick() {
        cooldowns.replaceAll((id, ticks) -> Math.max(0, ticks - 1));
    }
}
