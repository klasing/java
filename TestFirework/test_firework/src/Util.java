<<<<<<< HEAD
/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static List<Color> listColor = new ArrayList<Color>();

    protected static int getNofStart() {
        return (int)(Math.round(Math.random() * 2.0) + 1);
    }

    protected static Color getColor() {
        int n = (int)(Math.random() * (double)listColor.size());
        return listColor.get(n);
    }

    protected static int getRadius() {
        return (int)(Math.random() * 60.0 + 60.0);
    }

    protected static double getPositionRatio() {
        if (Math.random() <= 0.5) {
            return 0.25;
        }
        return 0.75;
    }

    protected static int getAngle(double d) {
        int n = d < 0.5 ? 75 : 95;
        return (int)(Math.random() * 10.0 + (double)n);
    }

    protected static int getSpeed() {
        return (int)(Math.random() * 40.0 + 70.0);
    }

    static {
        listColor.add(Color.BLUE);
        listColor.add(Color.GREEN);
        listColor.add(Color.CYAN);
        listColor.add(Color.RED);
        listColor.add(Color.MAGENTA);
        listColor.add(Color.PINK);
        listColor.add(Color.ORANGE);
        listColor.add(Color.YELLOW);
        listColor.add(Color.WHITE);
    }
}
=======
/*
 * Decompiled with CFR 0_118.
 */
package test_firework;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static List<Color> listColor = new ArrayList<Color>();

    protected static int getNofStart() {
        return (int)(Math.round(Math.random() * 2.0) + 1);
    }

    protected static Color getColor() {
        int n = (int)(Math.random() * (double)listColor.size());
        return listColor.get(n);
    }

    protected static int getRadius() {
        return (int)(Math.random() * 60.0 + 60.0);
    }

    protected static double getPositionRatio() {
        if (Math.random() <= 0.5) {
            return 0.25;
        }
        return 0.75;
    }

    protected static int getAngle(double d) {
        int n = d < 0.5 ? 75 : 95;
        return (int)(Math.random() * 10.0 + (double)n);
    }

    protected static int getSpeed() {
        return (int)(Math.random() * 40.0 + 70.0);
    }

    static {
        listColor.add(Color.BLUE);
        listColor.add(Color.GREEN);
        listColor.add(Color.CYAN);
        listColor.add(Color.RED);
        listColor.add(Color.MAGENTA);
        listColor.add(Color.PINK);
        listColor.add(Color.ORANGE);
        listColor.add(Color.YELLOW);
        listColor.add(Color.WHITE);
    }
}
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
