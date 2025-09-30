
package com.operacano.magical.player.magic.magicphysics;

import java.util.ArrayList;
import java.util.List;


/**
 * PlayerMagicWheel manages the magics available to a player in a wheel UI.
 * This is a player-only magic selection system.
 */
public class PlayerMagicWheel {
    private final List<String> magicIds; // Use your Magic class/type instead of String if available
    private int selectedIndex;

    public PlayerMagicWheel() {
        this.magicIds = new ArrayList<>();
        this.selectedIndex = 0;
    }

    public void addMagic(String magicId) {
        if (!magicIds.contains(magicId)) {
            magicIds.add(magicId);
        }
    }

    public void removeMagic(String magicId) {
        magicIds.remove(magicId);
        if (selectedIndex >= magicIds.size()) {
            selectedIndex = Math.max(0, magicIds.size() - 1);
        }
    }

    public List<String> getMagics() {
        return new ArrayList<>(magicIds);
    }

    public String getSelectedMagic() {
        if (magicIds.isEmpty()) return null;
        return magicIds.get(selectedIndex);
    }

    public void selectNext() {
        if (!magicIds.isEmpty()) {
            selectedIndex = (selectedIndex + 1) % magicIds.size();
        }
    }

    public void selectPrevious() {
        if (!magicIds.isEmpty()) {
            selectedIndex = (selectedIndex - 1 + magicIds.size()) % magicIds.size();
        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int index) {
        if (index >= 0 && index < magicIds.size()) {
            selectedIndex = index;
        }
    }
}
