package de.iew.sg;

import de.iew.sg.model.ImageModel;
import de.iew.sg.model.Tile;
import de.iew.sg.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Idee: Man hat ein Bild in Teilbilder (Tiles) zerlegt und möchte es zusammensetzen. Ein Stück des Bildes fehlt. In
 * die Lücke kann durch Klick auf ein Teilbild, das Teilbild verschoben werden.
 * <h1>Lernziel 1 - Arbeitsumgebung kennen lernen</h1>
 * <ul>
 * <li>Einfaches Hello World Programm (statische Methode, die Hello World zurückgibt)</li>
 * <li>Einfacher Testfall, der den Rückgabestring prüft</li>
 * <li>Logging</li>
 * </ul>
 * <h1>Lernziel 2 - Einfache Datenstrukturen und Java Features</h1>
 * <ul>
 * <li>Doppelt verkettete Liste zum Verwalten von Strings</li>
 * <li>Doppelt verkettete Liste erweitern auf beliebige Objekte</li>
 * <li>Doppelt verkettete Liste umbauen auf generische Typen und mit Initialisierung der Listenlänge</li>
 * </ul>
 * <h1>Lernziel 3 - Java Swing und Java 2D kennen lernen</h1>
 * <ul>
 * <li>Eigenes Window erstellen mit bestimmten Eigenschaften</li>
 * <li>Bühne zum Zeichnen erstellen</li>
 * <li>Einfache Grafikobjekte mit Java 2D auf der Bühne zeichnen</li>
 * <li>Bild in resources Ordner ablegen und in Main laden und Größe ausgeben</li>
 * <li>Ein Bild auf der Bühne zeichnen</li>
 * </ul>
 * <p/>
 * <p>
 * Weitere Ideen: Einführung in SCM z.B. Github Registrierung. Boardgröße als Parameter angeben
 * </p>
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @see <a href="http://de.wikipedia.org/wiki/Java_2D">Java 2D</a>
 * @see <a href="http://de.wikipedia.org/wiki/Datenkapselung_(Programmierung)">Datenkapselung / Information Hiding<a/>
 * @see <a href="http://glossar.hs-augsburg.de/Programmierprinzipien">Programmierprinzipien</a>
 * @see <a href="http://stackoverflow.com/questions/6118737/how-to-draw-in-jpanel-swing-graphics-java">Zeichnen in Java Swing</a>
 * @see <a href="http://zetcode.com/tutorials/javagamestutorial/basics/">Zeichnen in Java 2D</a>
 * @see <a href="http://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html">Maus Handler in Java Swing</a>
 * @since 06.01.14 - 22:28
 */
public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static final int DEFAULT_BOARD_SIZE = 3;

    public static final int MINIMUM_BOARD_SIZE = 2;

    public static BufferedImage loadImage() throws IOException {
        InputStream in = Main.class.getResourceAsStream("/images/yosemite-valley_001.jpg");

        BufferedImage image = ImageIO.read(in);

        return image;
    }

    public static BufferedImage loadImageAndPrintSize() throws IOException {
        final BufferedImage image = loadImage();

        log.info("Das Bild hat die Breite {} und Höhe {}", image.getWidth(), image.getHeight());

        return image;
    }

    /**
     * Validates the board dimension commandline arg.
     *
     * @param commandlineArg the commandline arg
     * @return the board dimension or -1 in case of a (validation) error
     */
    public static int validateBoardDimension(final String commandlineArg) {
        final int boardDimension;
        try {
            boardDimension = Integer.parseInt(commandlineArg);
        } catch (NumberFormatException e) {
            log.error("Ungültige Boardgröße {} angegeben. Muss eine Zahl sein >= {} sein.", commandlineArg, MINIMUM_BOARD_SIZE);
            System.exit(1);
            return -1;
        }

        if (boardDimension < MINIMUM_BOARD_SIZE) {
            log.error("Ungültige Boardgröße {} angegeben. Muss eine Zahl sein >= {} sein.", commandlineArg, MINIMUM_BOARD_SIZE);
            System.exit(1);
            return -1;
        }

        return boardDimension;
    }

    public static void main(String[] argv) {
        log.info("*Simple Game* wird gestartet");

        try {
            final BufferedImage bi = loadImageAndPrintSize();

            int boardDimension = DEFAULT_BOARD_SIZE;

            if (argv.length > 0) {
                boardDimension = validateBoardDimension(argv[0]);
            }

            final int stageDimenionsion = 480;

            final ImageModel imageModel = new ImageModel(bi, stageDimenionsion);
            final Tile[] tiles = imageModel.createTiles(boardDimension);
            final Board board = new Board(boardDimension, tiles);

            board.removeTileAt(boardDimension - 1, boardDimension - 1);

            final Stage stage = new Stage();
            stage.setImageModel(imageModel);
            stage.setBoard(board);
            stage.setPreferredSize(new Dimension(stageDimenionsion, stageDimenionsion));

            Window mainWindow = new Window();
            mainWindow.addStage(stage);
            mainWindow.setVisible(true);

            stage.startGame();
        } catch (Exception e) {
            log.error("Fehler beim Starten", e);
        }
    }
}
