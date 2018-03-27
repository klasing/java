/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import test_firework.FireworkFrame;

public class ButtonPanel
extends JPanel
implements ActionListener {
    private static FireworkFrame fireworkFrame;
    private static JButton jBNStart;
    private static JButton jBNStop;

    public ButtonPanel(FireworkFrame fireworkFrame) {
        ButtonPanel.fireworkFrame = fireworkFrame;
        jBNStart = new JButton("Start");
        jBNStart.setActionCommand("start");
        jBNStart.setMnemonic(83);
        jBNStart.setToolTipText("Start firework.");
        jBNStart.addActionListener(this);
        this.add(jBNStart);
        jBNStop = new JButton("Stop");
        jBNStop.setActionCommand("stop");
        jBNStop.setMnemonic(84);
        jBNStop.setToolTipText("Stop firework.");
        jBNStop.setEnabled(false);
        jBNStop.addActionListener(this);
        this.add(jBNStop);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("start".equals(actionEvent.getActionCommand())) {
            jBNStart.setEnabled(false);
            jBNStop.setEnabled(true);
            fireworkFrame.startFirework();
        }
        if ("stop".equals(actionEvent.getActionCommand())) {
            jBNStart.setEnabled(true);
            jBNStop.setEnabled(false);
            fireworkFrame.stopFirework();
        }
    }
}