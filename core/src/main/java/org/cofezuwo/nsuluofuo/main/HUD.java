package org.cofezuwo.nsuluofuo.main;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;

public class HUD {

    public void renderHealth(ATG g, int health) {
        if(health <= 0) return;

        int fullHearts = health / 2;
        int halfHeart = health % 2;

        for (int i = 0; i < fullHearts; i++) {
            g.drawImage(Assets.heart, 5 + 16 * i, 5);
        }

        if (halfHeart == 1) {
            g.drawImage(Assets.hheart, 5 + 16 * fullHearts, 5);
        }
    }
}
