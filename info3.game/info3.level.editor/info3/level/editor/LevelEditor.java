package info3.level.editor;

import java.awt.Graphics;

import info3.game.graphics.GameCanvas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.game.sound.RandomFileInputStream;

public class LevelEditor {

	static LevelEditor levelEditor;

	Level level;
	ElementList brushSelector;

	GameCanvas m_canvas;
	CanvasListener m_listener;
	JFrame m_frame;
	JLabel m_text;
	JLabel selectedName;
	private long m_textElapsed;

	Graphics levelGraph;
	ElementContainer selected;

	public static void main(String args[]) throws Exception {
		try {
			System.out.println("Game starting...");
			new LevelEditor();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	public LevelEditor() throws Exception {
		this.levelEditor = this;
		// creating a cowboy, that would be a model
		// in an Model-View-Controller pattern (MVC)
		level = new Level("level.json");
		// get Real Canvas Size
		brushSelector = new ElementList((int) (1024 * 0.85), 40);
		selected = brushSelector.elems.get(0);
		// creating a listener for all the events
		// from the game canvas, that would be
		// the controller in the MVC pattern
		m_listener = new CanvasListener(this);
		// creating the game canvas to render the game,
		// that would be a part of the view in the MVC pattern
		m_canvas = new GameCanvas(m_listener);

		System.out.println("  - creating frame...");
		Dimension d = new Dimension(1024, 768);
		m_frame = m_canvas.createFrame(d);

		System.out.println("  - setting up the frame...");
		setupFrame();
	}

	private void setupFrame() {
		m_frame.setTitle("LevelEditor");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		selectedName = new JLabel();
		selectedName.setText("No element selected");
		m_frame.add(selectedName, BorderLayout.NORTH);

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
		// paint
		level.paint(g);

		brushSelector.paint(g);
	}

	public void tick(long elapsed) {
		level.tick(elapsed);
		brushSelector.tick(elapsed);
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

			// Update the selected element
			if (selected != null) {
				selectedName.setText(selected.toString());
			} else {
				selectedName.setText("No element selected");
			}
		}
	}

	public ElementContainer select(int x, int y) {
		System.out.println("Selecting element at " + x + ", " + y);
		// if (level.x <= x && x <= level.x + level.getRealWidth() && level.y <= y && y
		// <= level.y + level.getRealHeight()) {
		// System.out.println("Selecting element in level");
		// return level.select(x - level.x, y - level.y);
		// }

		if (brushSelector.x <= x && x <= brushSelector.x + brushSelector.getRealWidth() && brushSelector.y <= y
				&& y <= brushSelector.y + brushSelector.getRealHeight()) {
			System.out.println("Selecting element in brush selector");
			return brushSelector.select(x - brushSelector.x, y - brushSelector.y);
		}
		return null;
	}

	public void updateSelected(int x, int y) {
		ElementContainer select = select(x, y);
		if (select != null)
			this.selected = select(x, y);
	}

	public void paintingManager(int x, int y) {
		if (level.x <= x && x <= level.x + level.getRealWidth() && level.y <= y && y <= level.y + level.getRealHeight())
			level.changeElement(x - level.x, y - level.y);
	}

	/*
	 * ================================================================ All the
	 * methods below are invoked from the GameCanvas listener, once the window is
	 * visible on the screen.
	 * ==============================================================
	 */
	

	private int m_musicIndex = 0;
	private String[] m_musicNames = new String[] { "Runaway-Food-Truck" };

	String m_musicName;

	void loadMusic() {
		m_musicName = m_musicNames[m_musicIndex];
		String filename = "resources/" + m_musicName + ".ogg";
		m_musicIndex = (m_musicIndex + 1) % m_musicNames.length;
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			m_canvas.playMusic(fis, 0, 1.0F);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);

		}
	}
}
