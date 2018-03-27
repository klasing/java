<<<<<<< HEAD
/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import test_firework.ButtonPanel;
import test_firework.FireWorker;
import test_firework.FireworkPanel;

public class FireworkFrame
extends JFrame {
    private static FireworkFrame fireworkFrame = new FireworkFrame();
    private static FireworkPanel fireworkPanel;
    private static ButtonPanel buttonPanel;
    private static FireWorker fireWorker;

    public FireworkFrame() {
        this.setTitle("Firework frame");
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        fireworkPanel = new FireworkPanel();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(fireworkPanel, gridBagConstraints);
        this.add(fireworkPanel);
        buttonPanel = new ButtonPanel(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(buttonPanel, gridBagConstraints);
        this.add(buttonPanel);
        this.pack();
    }

    protected void startFirework() {
        fireWorker = new FireWorker(fireworkPanel.getGraphics());
        fireWorker.execute();
    }

    protected void stopFirework() {
        fireWorker.cancel(true);
    }

    public static void main(String[] arrstring) {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                fireworkFrame.setVisible(true);
            }
        });
    }

}
=======
/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import test_firework.ButtonPanel;
import test_firework.FireWorker;
import test_firework.FireworkPanel;

public class FireworkFrame
extends JFrame {
    private static FireworkFrame fireworkFrame = new FireworkFrame();
    private static FireworkPanel fireworkPanel;
    private static ButtonPanel buttonPanel;
    private static FireWorker fireWorker;

    public FireworkFrame() {
        this.setTitle("Firework frame");
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        fireworkPanel = new FireworkPanel();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(fireworkPanel, gridBagConstraints);
        this.add(fireworkPanel);
        buttonPanel = new ButtonPanel(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(buttonPanel, gridBagConstraints);
        this.add(buttonPanel);
        this.pack();
    }

    protected void startFirework() {
        fireWorker = new FireWorker(fireworkPanel.getGraphics());
        fireWorker.execute();
    }

    protected void stopFirework() {
        fireWorker.cancel(true);
    }

    public static void main(String[] arrstring) {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                fireworkFrame.setVisible(true);
            }
        });
    }

}
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
