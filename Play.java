package ProjectMinoyFaustinoRegulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Play extends JPanel implements MouseListener {
    public final int GRID_SIZE = 5;       // Grid of 5x5
    public final int CELL_SIZE = 100;     // Size of each grid cell
    public final int DOT_RADIUS = 20;     // Dot size
    public ArrayList<ColoredPoint> dots;  // Colored visible dots
    public ArrayList<ColoredLine> lines;  // Lines between dots
    public ColoredPoint[][] invisibleDots; // Grid of invisible dots
    public ColoredPoint firstClick = null;

    public Play() {
        this.dots = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.invisibleDots = new ColoredPoint[GRID_SIZE][GRID_SIZE];
        addMouseListener(this);
        initializeGame();
    }

    public void initializeGame() {
        dots.clear();
        lines.clear();

        // Create invisible guiding dots for every grid cell
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                invisibleDots[i][j] = new ColoredPoint(i, j, Color.LIGHT_GRAY);
            }
        }

        // Add colored dot pairs (visible dots)
        dots.add(new ColoredPoint(0, 0, Color.RED));   // Top-left
        dots.add(new ColoredPoint(4, 4, Color.RED));   // Bottom-right

        dots.add(new ColoredPoint(0, 4, Color.BLUE));  // Top-right
        dots.add(new ColoredPoint(4, 0, Color.BLUE));  // Bottom-left

        dots.add(new ColoredPoint(2, 0, Color.GREEN)); // Center-top
        dots.add(new ColoredPoint(2, 4, Color.GREEN)); // Center-bottom
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw grid
        g2.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= GRID_SIZE; i++) {
            g2.drawLine(0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE, i * CELL_SIZE);
            g2.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        }

        // Draw invisible dots (guide grid)
        g2.setColor(new Color(200, 200, 200, 50));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Point p = invisibleDots[i][j].toScreenPoint(CELL_SIZE);
                g2.fillOval(p.x - DOT_RADIUS / 4, p.y - DOT_RADIUS / 4, DOT_RADIUS / 2, DOT_RADIUS / 2);
            }
        }

        // Draw lines
        g2.setStroke(new BasicStroke(5));
        for (ColoredLine line : lines) {
            g2.setColor(line.color);
            for (int k = 1; k < line.path.size(); k++) {
                Point p1 = line.path.get(k - 1).toScreenPoint(CELL_SIZE);
                Point p2 = line.path.get(k).toScreenPoint(CELL_SIZE);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        // Draw visible dots
        for (ColoredPoint dot : dots) {
            g2.setColor(dot.color);
            Point p = dot.toScreenPoint(CELL_SIZE);
            g2.fillOval(p.x - DOT_RADIUS / 2, p.y - DOT_RADIUS / 2, DOT_RADIUS, DOT_RADIUS);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ColoredPoint clickedDot = getClickedDot(e.getPoint());
        if (clickedDot != null) {
            if (firstClick == null) {
                firstClick = clickedDot; // Select the first dot
            } else {
                if (isValidConnection(firstClick, clickedDot)) {
                    lines.add(new ColoredLine(firstClick, clickedDot, firstClick.color, invisibleDots));
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid connection!");
                }
                firstClick = null; // Reset
            }
            repaint();
        }
    }

    private ColoredPoint getClickedDot(Point mousePoint) {
        for (ColoredPoint dot : dots) {
            Point dotPoint = dot.toScreenPoint(CELL_SIZE);
            if (mousePoint.distance(dotPoint) <= DOT_RADIUS) {
                return dot;
            }
        }
        return null;
    }

    private boolean isValidConnection(ColoredPoint p1, ColoredPoint p2) {
        if (p1.equals(p2)) return false;
        if (!p1.color.equals(p2.color)) return false; // Must match color
        for (ColoredLine line : lines) {
            if (line.intersects(p1, p2)) return false; // Prevent overlap
        }
        return true;
    }

    private static class ColoredPoint {
        int gridX, gridY;
        Color color;

        ColoredPoint(int gridX, int gridY, Color color) {
            this.gridX = gridX;
            this.gridY = gridY;
            this.color = color;
        }

        Point toScreenPoint(int cellSize) {
            return new Point(gridX * cellSize + cellSize / 2, gridY * cellSize + cellSize / 2);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ColoredPoint) {
                ColoredPoint other = (ColoredPoint) obj;
                return this.gridX == other.gridX && this.gridY == other.gridY;
            }
            return false;
        }
    }

    private static class ColoredLine {
        ArrayList<ColoredPoint> path;
        Color color;

        ColoredLine(ColoredPoint start, ColoredPoint end, Color color, ColoredPoint[][] grid) {
            this.color = color;
            this.path = new ArrayList<>();

            // Pathfinding using adjacent invisible dots
            int x = start.gridX, y = start.gridY;
            int targetX = end.gridX, targetY = end.gridY;

            while (x != targetX || y != targetY) {
                path.add(grid[x][y]);
                if (x < targetX) x++;
                else if (x > targetX) x--;
                else if (y < targetY) y++;
                else if (y > targetY) y--;
            }
            path.add(grid[targetX][targetY]);
        }

        boolean intersects(ColoredPoint p1, ColoredPoint p2) {
            return path.contains(p1) && path.contains(p2);
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Connect the Dots Game");
        Play game = new Play();

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            game.initializeGame();
            game.repaint();
        });

        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.add(restartButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
