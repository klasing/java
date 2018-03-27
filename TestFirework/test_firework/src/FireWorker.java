/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Graphics;
import java.util.List;
import javax.swing.SwingWorker;
import test_firework.Trajectories;

public class FireWorker
extends SwingWorker<Object, Object> {
    private static Graphics g;

    public FireWorker(Graphics graphics) {
        g = graphics;
    }

    @Override
    public Object doInBackground() {
        while (!this.isCancelled()) {
            Trajectories trajectories = new Trajectories();
            trajectories.drawTrajectory(g);
        }
        return null;
    }

    @Override
    public void process(List list) {
    }

    @Override
    public void done() {
    }
}
