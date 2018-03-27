/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import test_firework.Util;

public class Trajectory {
    private static final double a = -9.81;
    private static final int NOF_ANGLE = 60;
    private Color color = Util.getColor();
    private int iRadius = Util.getRadius();
    private double dPositionRatio = Util.getPositionRatio();
    protected List<Point> listPoint = new ArrayList<Point>();
    protected List<ArrayList<Point>> listStreak = new ArrayList<ArrayList<Point>>();

    protected void setPositionRatio(double d) {
        this.dPositionRatio = d;
    }

    protected Color getColor() {
        return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }

    protected int getRadius() {
        return this.iRadius;
    }

    protected int getNofAngle() {
        return 60;
    }

    protected void createTrajectory() {
        int n = Util.getAngle(this.dPositionRatio);
        int n2 = Util.getSpeed();
        double d = (double)n * 0.017453292519943295;
        double d2 = Math.cos(d) * (double)n2;
        double d3 = Math.sin(d) * (double)n2;
        double d4 = Math.abs(2.0 * d3 / -9.81);
        for (double d5 = 0.0; d5 < 0.7 * d4; d5 += 0.1) {
            double d6 = d2 * d5;
            double d7 = d3 * d5 + -4.905 * Math.pow(d5, 2.0);
            int n3 = (int)(600.0 * this.dPositionRatio + d6);
            int n4 = (int)(600.0 - d7);
            this.listPoint.add(new Point(n3, n4));
        }
    }

    protected void createExplode() {
        Point point = this.listPoint.get(this.listPoint.size() - 1);
        int n = (int)point.getX();
        int n2 = (int)point.getY();
        double d = 6.0;
        int n3 = 0;
        for (int i = 0; i < this.iRadius; ++i) {
            this.listStreak.add(new ArrayList());
            double d2 = 0.0;
            for (int j = 0; j < 60; ++j) {
                double d3 = d2 * 0.017453292519943295;
                double d4 = Math.cos(d3) * (double)i;
                double d5 = Math.sin(d3) * (double)i;
                this.listStreak.get(n3).add(new Point((int)((double)n + d4), (int)((double)n2 + d5)));
                d2 += d;
            }
            ++n3;
        }
    }
}
