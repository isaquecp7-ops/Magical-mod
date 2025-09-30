package com.operacano.magical.player.magic.magicphysics;

public class ManaManager {
    private int mana;
    private int maxMana;

    public ManaManager(int maxMana) {
        this.maxMana = maxMana;
        this.mana = maxMana;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void regenMana(int amount) {
        mana = Math.min(maxMana, mana + amount);
    }

    public boolean consumeMana(int amount) {
        if (mana >= amount) {
            mana -= amount;
            return true;
        }
        return false;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
        if (mana > maxMana) {
            mana = maxMana;
        }
    }
}
