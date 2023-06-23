package info3.game.menu;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import info3.game.Game;
import info3.level.editor.LevelEditor;

public class Menu {

    public static Menu menu;
    private JFrame m_frame;
    private ButtonGroup bg1, bg2;
    private List mapList;
    private String arg;

    public static void main(String args[]) throws Exception {
        try {
            System.out.println("Menu starting...");
            if (args.length == 0) {
                System.out.println("Il faut fournir en argument le fichier gal");
                return;
            }
            new Menu(args[0]);
            System.out.println("Menu started.");
        } catch (Throwable th) {
            th.printStackTrace(System.err);
        }
    }

    public Menu(String arg) throws IOException {
        this.arg = arg;
        Menu.menu = this;
        System.out.println("  - creating frame...");
        m_frame = createFrame();
        System.out.println("  - setting up the frame...");
        setupFrame();
    }

    java.awt.EventQueue eventQueue;

    private JFrame createFrame() {
        java.awt.Toolkit tk = Toolkit.getDefaultToolkit();
        eventQueue = tk.getSystemEventQueue();

        m_frame = new WindowFrame();
        m_frame.addWindowListener(new WindowListener());
        m_frame.setFocusable(true);
        m_frame.requestFocusInWindow();

        return m_frame;
    }

    private void setupFrame() throws IOException {
        m_frame.setTitle("Menu");
        m_frame.setLayout(new BorderLayout());
        m_frame.add(new JLabel("Player selection"), BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        panel.add(new JLabel("Player1"), BorderLayout.CENTER);
        panel.add(new JLabel("Player2"), BorderLayout.CENTER);
        JRadioButton jrPlayer1Mexican, jrPlayer1Engineer, jrPlayer2Mexican, jrPlayer2Engineer;
        jrPlayer1Mexican = new JRadioButton("Mexican", true);
        jrPlayer2Mexican = new JRadioButton("Mexican", false);
        jrPlayer1Engineer = new JRadioButton("Engineer", false);
        jrPlayer2Engineer = new JRadioButton("Engineer", true);
        jrPlayer1Mexican.setActionCommand("Mexican");
        jrPlayer2Mexican.setActionCommand("Mexican");
        jrPlayer1Engineer.setActionCommand("Engineer");
        jrPlayer2Engineer.setActionCommand("Engineer");

        bg1.add(jrPlayer1Mexican);
        bg1.add(jrPlayer1Engineer);
        bg2.add(jrPlayer2Mexican);
        bg2.add(jrPlayer2Engineer);

        panel.add(jrPlayer1Mexican);
        panel.add(jrPlayer2Mexican);
        panel.add(jrPlayer1Engineer);
        panel.add(jrPlayer2Engineer);
        m_frame.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton playButton = new JButton("Play game !");
        playButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        playButton.addMouseListener(new playButtonListener());

        JButton levelEditorButton = new JButton("Level Editor");
        levelEditorButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        levelEditorButton.addMouseListener(new levelEditorButtonListener());

        buttonPanel.add(playButton);
        buttonPanel.add(levelEditorButton);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        bottomPanel.add(new JLabel("Map selection"), BorderLayout.NORTH);
        mapList = new List();
        File mapDirectory = new File("resources/maps");
        if (!mapDirectory.exists()) {
            throw new IOException("Could not found maps folder in resources");
        }
        File[] mapFiles = mapDirectory.listFiles();
        for (File f : mapFiles) {
            mapList.add(f.getName());
        }

        bottomPanel.add(mapList, BorderLayout.CENTER);
        m_frame.add(bottomPanel, BorderLayout.SOUTH);

        m_frame.pack();
        // center the window on the screen
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);
    }

    private class levelEditorButtonListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (mapList.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Please select a map before loading", "Map Selection Error",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                new LevelEditor("resources/maps/" + mapList.getSelectedItem());
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // do nothing
        }

    }

    private class playButtonListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (mapList.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Please select a map before loading", "Map Selection Error",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                new Game(arg,"resources/maps/" + mapList.getSelectedItem(), bg1.getSelection().getActionCommand(),
                        bg2.getSelection().getActionCommand());
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // do nothing
        }

    }

    private class WindowListener implements java.awt.event.WindowListener {

        WindowListener() {
        }

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }

    private class RunnableEvent extends AWTEvent implements Runnable {
        private static final long serialVersionUID = 1L;
        public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 1;
        Runnable runnable;

        RunnableEvent(Object target, Runnable runnable) {
            super(target, EVENT_ID);
            this.runnable = runnable;
        }

        public void run() {
            runnable.run();
        }
    }

    private class WindowFrame extends JFrame {
        private static final long serialVersionUID = 1L;

        WindowFrame() {
            enableEvents(RunnableEvent.EVENT_ID);
        }
    }

}
