import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Original code/class ShapeDrawer borrowed from 
 * @author Obicere
 * This code is used to animate the world created by
 * the LifeBoard class (which uses the Cell class)
 * - Tueday, July 7, 2020
 */
public class LifeDrawer {

    public LifeDrawer() {
        final JFrame frame = new JFrame("Game of Life with wrap-around");
        final MyPanel panel = new MyPanel();

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        createRepaintTimer(frame);
    }

    // Just makes calls for the frame to paint every 15 milliseconds
    private void createRepaintTimer(final JFrame frame) {
        final Timer timer = new Timer(120, null);

        timer.addActionListener(e -> {
            if (!frame.isVisible()) {
                timer.stop();
            } else {
                frame.repaint();
            }
        });

        timer.start();
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(LifeDrawer::new);
    }

    public class MyPanel extends JPanel {

        private long start;
		private LifeBoard board;

        public MyPanel() {
            start = System.currentTimeMillis();
			board = new LifeBoard(200,200,true,3); //(rows, cols, wrap, pixelSize)
        }

        @Override
        protected void paintComponent(final Graphics g) {
            // Calling to clear the artifacts!
            super.paintComponent(g);
			for(int r = 0; r < board.COLS; r++) {
				for(int c = 0; c < board.ROWS; c++) {
					int x = c * board.pixelWidth;
					int y = r * board.pixelHeight;
					int value = board.world[r][c].getCell();
					
					//set colors depending on neighbors (if cell is alive)
					//happy == green (2 or 3 neighbors)
					//lonely == yellow (1 neighbor)
					//overcrowded == red (4 or more neighbors)
					if (value > 0) { //if alive
						if (board.world[r][c].getNeighbors() == 1) {
							g.setColor(new Color(value, value, 0));
						} else if (board.world[r][c].getNeighbors() <= 3) {
							g.setColor(new Color(0, value, 0));
						} else {
							g.setColor(new Color(value, 0, 0));
						}
					} else { //set black
						g.setColor(new Color(value, value, value));
					}
					g.fillRect(x, y, board.pixelWidth, board.pixelHeight);
				}
			}
			board.updateWorld();
            // g.setColor(Color.RED);

            // Difference between when the current time and when we started
            // final long difference = System.currentTimeMillis() - start;

            // Difference in seconds
            // final double seconds = difference / 1000d;

            // 1 rotation per second
            // final double rotation = seconds * 1440 / 1000;

            // final int x = (int) (Math.cos(rotation) * 100);
            // final int y = (int) (Math.sin(rotation) * 100);

            // g.drawOval(200 + x - 5, 200 + y - 5, 10, 10);
        }

        // Just setting the default size of the panel
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(board.COLS * board.pixelWidth, board.ROWS * board.pixelHeight);
        }

    }

}