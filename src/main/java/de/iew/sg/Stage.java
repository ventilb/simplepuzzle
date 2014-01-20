package de.iew.sg;

import de.iew.sg.model.ImageModel;
import de.iew.sg.model.Tile;
import de.iew.sg.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Implements the stage where painting of the game will happen.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 06.01.14 - 22:35
 */
public class Stage extends JPanel {

    private static Logger log = LoggerFactory.getLogger(Stage.class);

    private ImageModel imageModel;

    private Board board;

    private final Repainter repainter;

    private final MouseClickHandler mouseClickHandler;

    public Stage() {
        this.repainter = new Repainter();
        this.mouseClickHandler = new MouseClickHandler();
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        this.repainter.start();
        addMouseListener(this.mouseClickHandler);
    }

    /**
     * Performs the painting.
     * s
     *
     * @param g the graphic context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        RenderingHints rh =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHints(rh);

        if (this.imageModel == null) {
            paintIfImageModelIsNull(g2);
        } else {
            paintTileModel(g2);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Paints the image model.
     *
     * @param g2 the graphic context
     */
    protected void paintImageModel(final Graphics2D g2) {
        this.imageModel.paint(g2);
    }

    /**
     * Paints the tile model.
     *
     * @param g2 the graphic context
     */
    protected void paintTileModel(final Graphics2D g2) {
        this.board.paint(g2);
    }

    /**
     * Test method to draw simple Java 2D objects
     *
     * @param g2 the graphic context
     */
    protected void paintIfImageModelIsNull(final Graphics2D g2) {
        // Zeichnen einfacher Graphicobjekte mit Java 2D
        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.gray);


        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at =
                    AffineTransform.getTranslateInstance(w / 2, h / 2);
            at.rotate(Math.toRadians(deg));
            g2.draw(at.createTransformedShape(e));
        }
    }

    /**
     * Sets image model.
     *
     * @param imageModel the image model
     */
    public void setImageModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    /**
     * Sets board.
     *
     * @param board the board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Mouse handler. Intercepts mouse clicks on tiles and applies the game rules to the clicked tile.
     */
    protected class MouseClickHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            log.debug("Maus wurde geklickt bei ({}, {})", e.getX(), e.getY());

            final int x = e.getX();
            final int y = e.getY();

            Tile tile = board.findTileAtPaintPosition(x, y);

            if (tile != null) {
                log.debug("Tile {} gefunden", tile);

                int[] moveTarget = new int[2];
                if (board.canMove(tile, moveTarget)) {
                    log.debug("Das Tile {} darf bewegt werden", tile);

                    board.moveTo(tile, moveTarget[0], moveTarget[1]);

                    if (board.isPuzzleSolved()) {
                        log.info("Das Puzzle wurde gelöst!!!");
                    } else {
                        log.info("Das Puzzle wurde nicht gelöst. Versuche es weiter.");
                    }

                } else {
                    log.debug("Tile {} kann nicht bewegt werden", tile);
                }
            } else {
                log.debug("Kein Tile gefunden bei Position ({}, {})", x, y);
            }
        }
    }

    /**
     * A very basic repainter to periodically redraw the stage.
     */
    protected class Repainter {

        private Thread thread;

        private int fps = 24;

        private volatile boolean running = false;

        public Repainter() {
            this.thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int sleep = 1000 / fps;

                    try {
                        while (running) {

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    repaint();
                                }
                            });


                            Thread.sleep(sleep);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void start() {
            final boolean running = this.running;
            if (running) {
                return;
            }

            this.running = true;
            this.thread.start();
        }

        public void stop() {
            final boolean running = this.running;
            if (running) {
                this.running = false;
            }
        }

    }
}
