package com.operacano.magical.player.magic.magicphysics;

public class MagicClass {
    private final String name;
    private final MagicType type;
    private final int baseManaCost;

    public MagicClass(String name, MagicType type, int baseManaCost) {
        this.name = name;
        this.type = type;
        this.baseManaCost = baseManaCost;
    }

    public String getName() {
        return name;
    }

    public MagicType getType() {
        return type;
    }

    public int getBaseManaCost() {
        return baseManaCost;
    }
}
