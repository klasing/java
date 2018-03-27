<<<<<<< HEAD
/*
 * Decompiled with CFR 0_118.
 */
package test_fractal.mandelbrot;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import test_fractal.mandelbrot.MandelbrotPanel;

public class MandelbrotFrame
extends JFrame
implements ActionListener {
    private static MandelbrotFrame mandelbrotFrame = new MandelbrotFrame();
    private static JMenuItem jMIZoomIn;
    private static JMenuItem jMIZoomOut;
    private static MandelbrotPanel mandelbrotPanel;
    private static JMenuItem jMIShiftUp;
    private static JMenuItem jMIShiftLeft;
    private static JMenuItem jMIShiftRight;
    private static JMenuItem jMIShiftDown;

    public MandelbrotFrame() {
        this.setTitle("Mandelbrot Frame");
        this.setDefaultCloseOperation(3);
        this.setSize(800, 800);
        JMenuBar jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar);
        JMenu jMenu = new JMenu("Zoom");
        jMenu.setMnemonic(90);
        jMenuBar.add(jMenu);
        jMIZoomIn = new JMenuItem("In");
        jMIZoomIn.setMnemonic(73);
        jMIZoomIn.setAccelerator(KeyStroke.getKeyStroke(116, 1));
        jMenu.add(jMIZoomIn);
        jMIZoomIn.addActionListener(this);
        jMIZoomOut = new JMenuItem("Out");
        jMIZoomOut.setMnemonic(79);
        jMIZoomOut.setAccelerator(KeyStroke.getKeyStroke(117, 1));
        jMenu.add(jMIZoomOut);
        jMIZoomOut.addActionListener(this);
        JMenu jMenu2 = new JMenu("Shift");
        jMenu2.setMnemonic(83);
        jMenuBar.add(jMenu2);
        jMIShiftUp = new JMenuItem("Up");
        jMIShiftUp.setMnemonic(85);
        jMIShiftUp.setAccelerator(KeyStroke.getKeyStroke(85, 8));
        jMenu2.add(jMIShiftUp);
        jMIShiftUp.addActionListener(this);
        jMIShiftLeft = new JMenuItem("Left");
        jMIShiftLeft.setMnemonic(76);
        jMIShiftLeft.setAccelerator(KeyStroke.getKeyStroke(76, 8));
        jMenu2.add(jMIShiftLeft);
        jMIShiftLeft.addActionListener(this);
        jMIShiftRight = new JMenuItem("Right");
        jMIShiftRight.setMnemonic(82);
        jMIShiftRight.setAccelerator(KeyStroke.getKeyStroke(82, 8));
        jMenu2.add(jMIShiftRight);
        jMIShiftRight.addActionListener(this);
        jMIShiftDown = new JMenuItem("Down");
        jMIShiftDown.setMnemonic(68);
        jMIShiftDown.setAccelerator(KeyStroke.getKeyStroke(68, 8));
        jMenu2.add(jMIShiftDown);
        jMIShiftDown.addActionListener(this);
        mandelbrotPanel = new MandelbrotPanel();
        this.add(mandelbrotPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (jMIZoomIn == actionEvent.getSource()) {
            mandelbrotPanel.zoomIn();
        }
        if (jMIZoomOut == actionEvent.getSource()) {
            mandelbrotPanel.zoomOut();
        }
        if (jMIShiftUp == actionEvent.getSource()) {
            mandelbrotPanel.shiftUp();
        }
        if (jMIShiftLeft == actionEvent.getSource()) {
            mandelbrotPanel.shiftLeft();
        }
        if (jMIShiftRight == actionEvent.getSource()) {
            mandelbrotPanel.shiftRight();
        }
        if (jMIShiftDown == actionEvent.getSource()) {
            mandelbrotPanel.shiftDown();
        }
    }

    public static void main(String[] arrstring) {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                mandelbrotFrame.setVisible(true);
            }
        });
    }

}
=======
/*
 * Decompiled with CFR 0_118.
 */
package test_fractal.mandelbrot;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import test_fractal.mandelbrot.MandelbrotPanel;

public class MandelbrotFrame
extends JFrame
implements ActionListener {
    private static MandelbrotFrame mandelbrotFrame = new MandelbrotFrame();
    private static JMenuItem jMIZoomIn;
    private static JMenuItem jMIZoomOut;
    private static MandelbrotPanel mandelbrotPanel;
    private static JMenuItem jMIShiftUp;
    private static JMenuItem jMIShiftLeft;
    private static JMenuItem jMIShiftRight;
    private static JMenuItem jMIShiftDown;

    public MandelbrotFrame() {
        this.setTitle("Mandelbrot Frame");
        this.setDefaultCloseOperation(3);
        this.setSize(800, 800);
        JMenuBar jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar);
        JMenu jMenu = new JMenu("Zoom");
        jMenu.setMnemonic(90);
        jMenuBar.add(jMenu);
        jMIZoomIn = new JMenuItem("In");
        jMIZoomIn.setMnemonic(73);
        jMIZoomIn.setAccelerator(KeyStroke.getKeyStroke(116, 1));
        jMenu.add(jMIZoomIn);
        jMIZoomIn.addActionListener(this);
        jMIZoomOut = new JMenuItem("Out");
        jMIZoomOut.setMnemonic(79);
        jMIZoomOut.setAccelerator(KeyStroke.getKeyStroke(117, 1));
        jMenu.add(jMIZoomOut);
        jMIZoomOut.addActionListener(this);
        JMenu jMenu2 = new JMenu("Shift");
        jMenu2.setMnemonic(83);
        jMenuBar.add(jMenu2);
        jMIShiftUp = new JMenuItem("Up");
        jMIShiftUp.setMnemonic(85);
        jMIShiftUp.setAccelerator(KeyStroke.getKeyStroke(85, 8));
        jMenu2.add(jMIShiftUp);
        jMIShiftUp.addActionListener(this);
        jMIShiftLeft = new JMenuItem("Left");
        jMIShiftLeft.setMnemonic(76);
        jMIShiftLeft.setAccelerator(KeyStroke.getKeyStroke(76, 8));
        jMenu2.add(jMIShiftLeft);
        jMIShiftLeft.addActionListener(this);
        jMIShiftRight = new JMenuItem("Right");
        jMIShiftRight.setMnemonic(82);
        jMIShiftRight.setAccelerator(KeyStroke.getKeyStroke(82, 8));
        jMenu2.add(jMIShiftRight);
        jMIShiftRight.addActionListener(this);
        jMIShiftDown = new JMenuItem("Down");
        jMIShiftDown.setMnemonic(68);
        jMIShiftDown.setAccelerator(KeyStroke.getKeyStroke(68, 8));
        jMenu2.add(jMIShiftDown);
        jMIShiftDown.addActionListener(this);
        mandelbrotPanel = new MandelbrotPanel();
        this.add(mandelbrotPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (jMIZoomIn == actionEvent.getSource()) {
            mandelbrotPanel.zoomIn();
        }
        if (jMIZoomOut == actionEvent.getSource()) {
            mandelbrotPanel.zoomOut();
        }
        if (jMIShiftUp == actionEvent.getSource()) {
            mandelbrotPanel.shiftUp();
        }
        if (jMIShiftLeft == actionEvent.getSource()) {
            mandelbrotPanel.shiftLeft();
        }
        if (jMIShiftRight == actionEvent.getSource()) {
            mandelbrotPanel.shiftRight();
        }
        if (jMIShiftDown == actionEvent.getSource()) {
            mandelbrotPanel.shiftDown();
        }
    }

    public static void main(String[] arrstring) {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                mandelbrotFrame.setVisible(true);
            }
        });
    }

}
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
