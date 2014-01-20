package de.iew.sg.model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Implements a model to manage the image for the puzzle.
 *
 * @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @see <a href="http://stackoverflow.com/questions/1604319/getting-pixel-data-from-an-image-using-java">http://stackoverflow.com/questions/1604319/getting-pixel-data-from-an-image-using-java</a>
 * @since 19.01.14 - 21:52
 */
public class ImageModel {

    /**
     * The image.
     */
    private final BufferedImage image;

    /**
     * The maximum pixel dimension to paint in.
     */
    private final int maxPaintDimension;

    public ImageModel(BufferedImage image, int maxPaintDimension) {
        this.image = image;
        this.maxPaintDimension = maxPaintDimension;
    }

    /**
     * Gets the image width in pixel.
     *
     * @return image width
     */
    public int getImageWidth() {
        return this.image.getWidth();
    }

    /**
     * Gets image height in pixel.
     *
     * @return the image height
     */
    public int getImageHeight() {
        return this.image.getHeight();
    }

    /**
     * Gets the smaller of the two image dimensions in pixel.
     *
     * @return rectangle dimension
     */
    public int getRectangleDimension() {
        return Math.min(getImageWidth(), getImageHeight());
    }

    /**
     * Creates an array of {@link de.iew.sg.model.Tile}s to fill a
     * <code>boardDimension</code> x <code>boardDimension</code> board.
     *
     * @param boardDimension the board dimension
     * @return array of {@link de.iew.sg.model.Tile}s
     */
    public Tile[] createTiles(final int boardDimension) {
        final int totalBoardPaintDimension = Math.min(this.maxPaintDimension, getRectangleDimension());
        final int tilePaintDimension = totalBoardPaintDimension / boardDimension;

        final Tile[] tiles1 = new Tile[boardDimension * boardDimension];

        int tileNum = 0;
        BufferedImage tileImg;
        Tile tile;

        for (int y = 0; y < boardDimension; y++) {
            for (int x = 0; x < boardDimension; x++) {

                tileImg = new BufferedImage(tilePaintDimension, tilePaintDimension, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = tileImg.createGraphics();
                g2.translate(-(x * tilePaintDimension), -(y * tilePaintDimension));
                paint(g2);
                g2.dispose();

                tile = new Tile(tileNum, tileImg, tilePaintDimension, this);
                tiles1[tileNum] = tile;
                tileNum++;
            }
        }

        return tiles1;
    }

    /**
     * Paints the rectangular part defined by {@link #getRectangleDimension()} of the image.
     *
     * @param g2 the graphic context
     */
    public void paint(final Graphics2D g2) {
        final int maxPaintDimensions = this.maxPaintDimension;
        final int imageDimension = getRectangleDimension();

        g2.drawImage(this.image, 0, 0, maxPaintDimension, maxPaintDimensions, 0, 0, imageDimension, imageDimension, null);
    }

}
