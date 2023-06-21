package info3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.game.graphics.GameCanvas;

public class Menu {

    public static Menu menu;
    private GameCanvas m_canvas;
    private MenuListener m_listener;
    private JFrame m_frame;
    private JLabel m_text;
    private long m_textElapsed;

    public static void main(String args[]) throws Exception {
        try {
            System.out.println("Menu starting...");
            new Menu();
            System.out.println("Menu started.");
        } catch (Throwable th) {
            th.printStackTrace(System.err);
        }
    }

    public Menu() {
        Menu.menu = this;
        this.m_listener = new MenuListener(this);
        this.m_canvas = new GameCanvas(m_listener);
        System.out.println("  - creating frame...");
        Dimension d = new Dimension(1024, 768);
        m_frame = m_canvas.createFrame(d);
        System.out.println("  - setting up the frame...");
        setupFrame();
    }

    private void setupFrame() {

        m_frame.setTitle("Game");
        m_frame.setLayout(new BorderLayout());
        m_frame.add(m_canvas, BorderLayout.CENTER);
        m_text = new JLabel();
        m_text.setText("Tick: 0ms FPS=0");
        m_frame.add(m_text, BorderLayout.NORTH);
        // center the window on the screen
        m_frame.setLocationRelativeTo(null);

        // make the vindow visible
        m_frame.setVisible(true);
    }

    public void paint(Graphics g) {
        // get the size of the canvas
        int width = m_canvas.getWidth();
        int height = m_canvas.getHeight();

        // erase background
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
        g.fillRect(0, 0, 100, 100);
    }

    public void tick(long elapsed) {
        // Update every second
        // the text on top of the frame: tick and fps
        m_textElapsed += elapsed;
        if (m_textElapsed > 1000) {
            m_textElapsed = 0;
            float period = m_canvas.getTickPeriod();
            int fps = m_canvas.getFPS();

            String txt = "Tick=" + period + "ms";
            while (txt.length() < 15)
                txt += " ";
            txt = txt + fps + " fps   ";
            m_text.setText(txt);
        }
    }
}
