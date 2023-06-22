package info3.game.menu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Menu {

    public static Menu menu;
    private MenuCanvas m_canvas;
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
        this.m_canvas = new MenuCanvas(m_listener);
        System.out.println("  - creating frame...");
        Dimension d = new Dimension(1024, 768);
        m_frame = m_canvas.createFrame(d);
        System.out.println("  - setting up the frame...");
        setupFrame();
    }

    private void setupFrame() {
        m_frame.setTitle("Menu");
        m_frame.setLayout(new BorderLayout());
        m_frame.add(new JLabel("Player selection"), BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        ButtonGroup bg1 = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();
        JRadioButton jr1 = new JRadioButton("Mexican", true);
        JRadioButton jr2 = new JRadioButton("Mexican", false);
        JRadioButton jr3 = new JRadioButton("Engineer", false);
        JRadioButton jr4 = new JRadioButton("Engineer", true);

        bg1.add(jr1);
        bg1.add(jr3);
        bg2.add(jr2);
        bg2.add(jr4);

        panel.add(jr1);
        panel.add(jr2);
        panel.add(jr3);
        panel.add(jr4);

        m_frame.add(panel, BorderLayout.CENTER);
        m_frame.add(new JButton("Play game !"), BorderLayout.SOUTH);

        m_frame.pack();
        // center the window on the screen
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);
    }

    public void paint(Graphics g) {
    }

    public void tick(long elapsed) {
    }
}
