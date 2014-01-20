package de.iew.sg.model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Implements a model to manage the state of a tile in the puzzle.
 *
 * @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 19.01.14 - 21:59
 */
public class Tile {

    private final int tileNumber;

    private final int paintDimension;

    private final ImageModel imageModel;

    private final BufferedImage tile;

    private int matrixPositionX;

    private int matrixPositionY;

    public Tile(final int tileNumber, final BufferedImage tile, final int paintDimension, final ImageModel imageModel) {
        this.tileNumber = tileNumber;
        this.paintDimension = paintDimension;
        this.imageModel = imageModel;
        this.tile = tile;
    }

    /**
     * Paints this tile.
     *
     * @param g2 the graphic context
     */
    public void paint(final Graphics2D g2) {
        final int x = this.matrixPositionX * this.paintDimension;
        final int y = this.matrixPositionY * this.paintDimension;

        g2.drawImage(this.tile, x, y, null);
    }

    /**
     * Checks if the specified position is contained in this tiles paint position.
     *
     * @param paintX the paint x
     * @param paintY the paint y
     * @return boolean
     */
    public boolean containsPaintPosition(final int paintX, final int paintY) {
        final int x = this.matrixPositionX * this.paintDimension;
        final int y = this.matrixPositionY * this.paintDimension;

        return paintX >= x
                && paintX < x + this.paintDimension
                && paintY >= y
                && paintY < y + paintDimension;
    }

    /**
     * Sets matrix position.
     *
     * @param matrixPositionX the matrix position x
     * @param matrixPositionY the matrix position y
     */
    public void setMatrixPosition(final int matrixPositionX, final int matrixPositionY) {
        this.matrixPositionX = matrixPositionX;
        this.matrixPositionY = matrixPositionY;
    }

    /**
     * Gets matrix position x.
     *
     * @return the matrix position x
     */
    public int getMatrixPositionX() {
        return matrixPositionX;
    }

    /**
     * Gets matrix position y.
     *
     * @return the matrix position y
     */
    public int getMatrixPositionY() {
        return matrixPositionY;
    }

    /**
     * Returns the tile number.
     *
     * @return tile number
     */
    public int getTileNumber() {
        return tileNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(this.tileNumber);
    }
}
