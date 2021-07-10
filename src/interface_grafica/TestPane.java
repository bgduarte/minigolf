package interface_grafica;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TestPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -122088383791575745L;
	private BufferedImage tile;

	public TestPane(String path) {
		try {
			tile = ImageIO.read(getClass().getResource(path));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		int tileWidth = tile.getWidth();
		int tileHeight = tile.getHeight();
		for (int y = 0; y < getHeight(); y += tileHeight) {
			for (int x = 0; x < getWidth(); x += tileWidth) {
				g2d.drawImage(tile, x, y, this);
			}
		}
		g2d.dispose();
	}
}