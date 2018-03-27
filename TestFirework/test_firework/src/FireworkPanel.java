/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class FireworkPanel
extends JPanel {
    protected static final int PANEL_WIDTH = 600;
    protected static final int PANEL_HEIGHT = 600;

    public FireworkPanel() {
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.BLACK);
    }
}
