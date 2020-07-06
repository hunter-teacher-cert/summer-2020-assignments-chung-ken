//day2day/01/ImageTest.java
// Java Graphics for 2D array

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageTest {

  private static final int HEIGHT = 250;
  private static final int WIDTH = 250;
private int[][] data;

public ImageTest(int[][] data) {
	this.data = data;
}

public void updateImage(int[][] data) {
	this.data = data;
	this.repaint();
}

  public void drawImage() {

    final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D)img.getGraphics();
    for(int i = 0; i < WIDTH; i++) {
      for(int j = 0; j < HEIGHT; j++) {
        float c = ((float) data[i][j])/255;
        g.setColor(new Color(c, c, c));
        g.fillRect(i, j, 1, 1);
      }
    }

    JFrame frame = new JFrame("Image test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          Graphics2D g2d = (Graphics2D)g;
          g2d.clearRect(0, 0, getWidth(), getHeight());
          g2d.setRenderingHint(
                               RenderingHints.KEY_INTERPOLATION,
                               RenderingHints.VALUE_INTERPOLATION_BILINEAR);
          // Or _BICUBIC
          g2d.scale(2, 2);
          g2d.drawImage(img, 0, 0, this);
        }
      };
    panel.setPreferredSize(new Dimension(WIDTH*2, HEIGHT*2));
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}