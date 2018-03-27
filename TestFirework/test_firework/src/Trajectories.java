<<<<<<< HEAD
/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import test_firework.Trajectory;
import test_firework.Util;

public class Trajectories {
    private int iNofStart = Util.getNofStart();
    private Trajectory[] aTrajectory = new Trajectory[this.iNofStart];

    public Trajectories() {
        int n;
        for (n = 0; n < this.iNofStart; ++n) {
            this.aTrajectory[n] = new Trajectory();
        }
        if (this.iNofStart > 1) {
            this.aTrajectory[0].setPositionRatio(0.25);
            this.aTrajectory[1].setPositionRatio(0.75);
        }
        for (n = 0; n < this.iNofStart; ++n) {
            this.aTrajectory[n].createTrajectory();
            this.aTrajectory[n].createExplode();
        }
    }

    protected void drawTrajectory(Graphics graphics) {
        int n;
        int[] arrn = new int[this.iNofStart];
        int[] arrn2 = new int[this.iNofStart];
        int n2 = 4;
        boolean bl = true;
        while (bl) {
            void var6_7;
            boolean bl2 = false;
            while (++var6_7 < this.iNofStart) {
                graphics.setColor(Color.WHITE);
                if (arrn[var6_7] < this.aTrajectory[var6_7].listPoint.size() - 1) {
                    graphics.drawLine((int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7]).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7]).getY(), (int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7] + 1).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7] + 1).getY());
                    if (arrn[var6_7] > 50) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine((int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7]).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7]).getY(), (int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7] + 1).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7] + 1).getY());
                        int[] arrn3 = arrn2;
                        void v1 = var6_7;
                        arrn3[v1] = arrn3[v1] + 1;
                    }
                    bl = true;
                    int[] arrn4 = arrn;
                    void v3 = var6_7;
                    arrn4[v3] = arrn4[v3] + 1;
                    continue;
                }
                bl = false;
            }
            try {
                Thread.sleep(n2);
            }
            catch (Exception i) {}
        }
        graphics.setColor(Color.BLACK);
        bl = true;
        while (bl) {
            void var6_10;
            boolean bl3 = false;
            while (++var6_10 < this.iNofStart) {
                if (arrn2[var6_10] < this.aTrajectory[var6_10].listPoint.size() - 1) {
                    graphics.drawLine((int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10]).getX(), (int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10]).getY(), (int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10] + 1).getX(), (int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10] + 1).getY());
                    bl = true;
                    int[] arrn5 = arrn2;
                    void v5 = var6_10;
                    arrn5[v5] = arrn5[v5] + 1;
                    continue;
                }
                bl = false;
            }
        }
        Color[] arrcolor = new Color[this.iNofStart];
        Point[] arrpoint = new Point[this.iNofStart];
        int[] arrn6 = new int[this.iNofStart];
        for (n = 0; n < this.iNofStart; ++n) {
            arrcolor[n] = this.aTrajectory[n].getColor();
            arrpoint[n] = this.aTrajectory[n].listPoint.get(this.aTrajectory[n].listPoint.size() - 1);
            arrn6[n] = this.aTrajectory[n].getRadius();
        }
        for (n = 0; n < this.iNofStart; ++n) {
            graphics.setColor(arrcolor[n]);
            for (ArrayList<Point> arrayList : this.aTrajectory[n].listStreak) {
                for (Point point : arrayList) {
                    graphics.drawLine((int)arrpoint[n].getX(), (int)arrpoint[n].getY(), (int)point.getX(), (int)point.getY());
                }
            }
        }
        int[] arrn7 = new int[this.iNofStart];
        graphics.setColor(Color.BLACK);
        bl = true;
        while (bl) {
            for (int i = 0; i < this.iNofStart; ++i) {
                if (arrn7[i] < 4 * arrn6[i]) {
                    graphics.fillArc((int)(arrpoint[i].getX() - (double)(arrn7[i] / 2)), (int)(arrpoint[i].getY() - (double)(arrn7[i] / 2)), arrn7[i], arrn7[i], 0, 360);
                    int[] arrn8 = arrn7;
                    int n3 = i;
                    arrn8[n3] = arrn8[n3] + 1;
                    bl = true;
                    try {
                        Thread.sleep(n2);
                    }
                    catch (Exception var11_19) {}
                    continue;
                }
                bl = false;
            }
        }
    }
=======
/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import test_firework.Trajectory;
import test_firework.Util;

public class Trajectories {
    private int iNofStart = Util.getNofStart();
    private Trajectory[] aTrajectory = new Trajectory[this.iNofStart];

    public Trajectories() {
        int n;
        for (n = 0; n < this.iNofStart; ++n) {
            this.aTrajectory[n] = new Trajectory();
        }
        if (this.iNofStart > 1) {
            this.aTrajectory[0].setPositionRatio(0.25);
            this.aTrajectory[1].setPositionRatio(0.75);
        }
        for (n = 0; n < this.iNofStart; ++n) {
            this.aTrajectory[n].createTrajectory();
            this.aTrajectory[n].createExplode();
        }
    }

    protected void drawTrajectory(Graphics graphics) {
        int n;
        int[] arrn = new int[this.iNofStart];
        int[] arrn2 = new int[this.iNofStart];
        int n2 = 4;
        boolean bl = true;
        while (bl) {
            void var6_7;
            boolean bl2 = false;
            while (++var6_7 < this.iNofStart) {
                graphics.setColor(Color.WHITE);
                if (arrn[var6_7] < this.aTrajectory[var6_7].listPoint.size() - 1) {
                    graphics.drawLine((int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7]).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7]).getY(), (int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7] + 1).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn[var6_7] + 1).getY());
                    if (arrn[var6_7] > 50) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine((int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7]).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7]).getY(), (int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7] + 1).getX(), (int)this.aTrajectory[var6_7].listPoint.get(arrn2[var6_7] + 1).getY());
                        int[] arrn3 = arrn2;
                        void v1 = var6_7;
                        arrn3[v1] = arrn3[v1] + 1;
                    }
                    bl = true;
                    int[] arrn4 = arrn;
                    void v3 = var6_7;
                    arrn4[v3] = arrn4[v3] + 1;
                    continue;
                }
                bl = false;
            }
            try {
                Thread.sleep(n2);
            }
            catch (Exception i) {}
        }
        graphics.setColor(Color.BLACK);
        bl = true;
        while (bl) {
            void var6_10;
            boolean bl3 = false;
            while (++var6_10 < this.iNofStart) {
                if (arrn2[var6_10] < this.aTrajectory[var6_10].listPoint.size() - 1) {
                    graphics.drawLine((int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10]).getX(), (int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10]).getY(), (int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10] + 1).getX(), (int)this.aTrajectory[var6_10].listPoint.get(arrn2[var6_10] + 1).getY());
                    bl = true;
                    int[] arrn5 = arrn2;
                    void v5 = var6_10;
                    arrn5[v5] = arrn5[v5] + 1;
                    continue;
                }
                bl = false;
            }
        }
        Color[] arrcolor = new Color[this.iNofStart];
        Point[] arrpoint = new Point[this.iNofStart];
        int[] arrn6 = new int[this.iNofStart];
        for (n = 0; n < this.iNofStart; ++n) {
            arrcolor[n] = this.aTrajectory[n].getColor();
            arrpoint[n] = this.aTrajectory[n].listPoint.get(this.aTrajectory[n].listPoint.size() - 1);
            arrn6[n] = this.aTrajectory[n].getRadius();
        }
        for (n = 0; n < this.iNofStart; ++n) {
            graphics.setColor(arrcolor[n]);
            for (ArrayList<Point> arrayList : this.aTrajectory[n].listStreak) {
                for (Point point : arrayList) {
                    graphics.drawLine((int)arrpoint[n].getX(), (int)arrpoint[n].getY(), (int)point.getX(), (int)point.getY());
                }
            }
        }
        int[] arrn7 = new int[this.iNofStart];
        graphics.setColor(Color.BLACK);
        bl = true;
        while (bl) {
            for (int i = 0; i < this.iNofStart; ++i) {
                if (arrn7[i] < 4 * arrn6[i]) {
                    graphics.fillArc((int)(arrpoint[i].getX() - (double)(arrn7[i] / 2)), (int)(arrpoint[i].getY() - (double)(arrn7[i] / 2)), arrn7[i], arrn7[i], 0, 360);
                    int[] arrn8 = arrn7;
                    int n3 = i;
                    arrn8[n3] = arrn8[n3] + 1;
                    bl = true;
                    try {
                        Thread.sleep(n2);
                    }
                    catch (Exception var11_19) {}
                    continue;
                }
                bl = false;
            }
        }
    }
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
}