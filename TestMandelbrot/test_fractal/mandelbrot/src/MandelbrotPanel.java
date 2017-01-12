/*
 * Decompiled with CFR 0_118.
 */
package test_fractal.mandelbrot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import javax.swing.JPanel;

public class MandelbrotPanel
extends JPanel {
    protected static final int FRAME_WIDTH = 800;
    protected static final int FRAME_HEIGHT = 800;
    private static Image img;
    private static double iZoomX;
    private static double iZoomY;
    private static double iHorzShift;
    private static double iVertShift;

    public MandelbrotPanel() {
        this.createImage();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(img, 0, 0, null);
    }

    protected void zoomIn() {
        iZoomX /= 2.0;
        iZoomY /= 2.0;
        this.createImage();
    }

    protected void zoomOut() {
        iZoomX *= 2.0;
        iZoomY *= 2.0;
        this.createImage();
    }

    protected void shiftUp() {
        iVertShift -= 400.0;
        this.createImage();
    }

    protected void shiftLeft() {
        iHorzShift -= 400.0;
        this.createImage();
    }

    protected void shiftRight() {
        iHorzShift += 400.0;
        this.createImage();
    }

    protected void shiftDown() {
        iVertShift += 400.0;
        this.createImage();
    }

    private void createImage() {
        int[] arrn = new int[640000];
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 800; ++i) {
            double d = ((double)(i - 400) + iVertShift) / (800.0 / iZoomY);
            for (int j = 0; j < 800; ++j) {
                double d2;
                double d3;
                double d4 = ((double)(j - 400) + iHorzShift) / (800.0 / iZoomX);
                double d5 = 0.0;
                double d6 = 0.0;
                for (n2 = 0; n2 < 32 && (d3 = d6 * d6) + (d2 = d5 * d5) < 4.0; ++n2) {
                    double d7 = d3 - d2 + d4;
                    double d8 = 2.0 * d6 * d5 + d;
                    d6 = d7;
                    d5 = d8;
                }
                arrn[n] = n2 == 32 ? -16777216 : this.setColor(n2);
                ++n;
            }
        }
        img = this.createImage(new MemoryImageSource(800, 800, arrn, 0, 800));
        this.repaint();
    }

    private int setColor(int n) {
        int n2 = 0;
        switch (n % 13) {
            case 0: {
                n2 = -16777216;
                break;
            }
            case 1: {
                n2 = -16776961;
                break;
            }
            case 2: {
                n2 = -16711936;
                break;
            }
            case 3: {
                n2 = -16711681;
                break;
            }
            case 4: {
                n2 = -12566464;
                break;
            }
            case 5: {
                n2 = -8355712;
                break;
            }
            case 6: {
                n2 = -4144960;
                break;
            }
            case 7: {
                n2 = -65536;
                break;
            }
            case 8: {
                n2 = -65281;
                break;
            }
            case 9: {
                n2 = -20561;
                break;
            }
            case 10: {
                n2 = -14336;
                break;
            }
            case 11: {
                n2 = -256;
                break;
            }
            case 12: {
                n2 = -1;
            }
        }
        return n2;
    }

    static {
        iZoomX = 4.0;
        iZoomY = 4.0;
        iHorzShift = 0.0;
        iVertShift = 0.0;
    }
}
