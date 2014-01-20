package de.iew.sg.model;

import java.awt.*;

/**
 * Implements the board model to manage the puzzle tiles and their positions in the grid. The model maintains a matrix
 * of dimension {@link #dimension}.
 *
 * @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 19.01.14 - 23:21
 */
public class Board {

    private final Tile[][] tiles;

    private final int dimension;

    private final int[][] solution;

    public Board(final int boardDimension, final Tile[] tiles) {
        this.tiles = new Tile[boardDimension][boardDimension];
        this.solution = new int[boardDimension][boardDimension];
        this.dimension = boardDimension;

        int matrixPositionX;
        int matrixPositionY = -1;
        for (int i = 0; i < tiles.length; i++) {
            if (i % boardDimension == 0) {
                matrixPositionY++;
            }
            matrixPositionX = i % boardDimension;

            tiles[i].setMatrixPosition(matrixPositionX, matrixPositionY);
            this.tiles[matrixPositionX][matrixPositionY] = tiles[i];
            this.solution[matrixPositionX][matrixPositionY] = tiles[i].getTileNumber();
        }
    }

    /**
     * Paints the tiles in this board.
     *
     * @param g2 the graphic context
     */
    public void paint(Graphics2D g2) {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {

                if (tiles[i][j] != null) {
                    this.tiles[i][j].paint(g2);
                }
            }
        }
    }

    /**
     * Removes the tile at the specified matrix position.
     *
     * @param matrixPositionX the matrix position x
     * @param matrixPositionY the matrix position y
     */
    public void removeTileAt(final int matrixPositionX, final int matrixPositionY) {
        this.tiles[matrixPositionX][matrixPositionY] = null;
        this.solution[matrixPositionX][matrixPositionY] = -1;
    }

    /**
     * Returns the tile at the specified paint position. Returns NULL if there is no tile at the specified position.
     *
     * @param paintPositionX the paint position x
     * @param paintPositionY the paint position y
     * @return the tile or NULL
     */
    public Tile findTileAtPaintPosition(final int paintPositionX, final int paintPositionY) {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (this.tiles[i][j] != null
                        && this.tiles[i][j].containsPaintPosition(paintPositionX, paintPositionY)) {
                    return this.tiles[i][j];
                }
            }
        }

        return null;
    }

    /**
     * Checks if the specified tile can be moved.
     * <p>
     * A tile can be moved if a neighbour tile is NULL. Stores the target matrix position in the
     * <code>targetMatrixPositionHolder</code> parameter if and only if the tile can be moved. Otherwise the parameter
     * is unchanged.
     * </p>
     *
     * @param tileToCheck                the tile to check
     * @param targetMatrixPositionHolder the target matrix position holder
     * @return boolean
     */
    public boolean canMove(final Tile tileToCheck, final int[] targetMatrixPositionHolder) {
        if (tileToCheck != null) {
            final int tileX = tileToCheck.getMatrixPositionX();
            final int tileY = tileToCheck.getMatrixPositionY();

            if (hasNotTileAt(tileX - 1, tileY)) {

                targetMatrixPositionHolder[0] = tileX - 1;
                targetMatrixPositionHolder[1] = tileY;
                return true;

            } else if (hasNotTileAt(tileX + 1, tileY)) {

                targetMatrixPositionHolder[0] = tileX + 1;
                targetMatrixPositionHolder[1] = tileY;
                return true;

            } else if (hasNotTileAt(tileX, tileY - 1)) {

                targetMatrixPositionHolder[0] = tileX;
                targetMatrixPositionHolder[1] = tileY - 1;
                return true;

            } else if (hasNotTileAt(tileX, tileY + 1)) {

                targetMatrixPositionHolder[0] = tileX;
                targetMatrixPositionHolder[1] = tileY + 1;
                return true;

            }
        }
        return false;
    }

    /**
     * Moves the specified tile to the specified matrix position.
     *
     * @param tileToMove      the tile to move
     * @param matrixPositionX the matrix position x
     * @param matrixPositionY the matrix position y
     */
    public void moveTo(final Tile tileToMove, final int matrixPositionX, final int matrixPositionY) {
        final int tileX = tileToMove.getMatrixPositionX();
        final int tileY = tileToMove.getMatrixPositionY();

        tileToMove.setMatrixPosition(matrixPositionX, matrixPositionY);

        this.tiles[matrixPositionX][matrixPositionY] = tileToMove;
        this.tiles[tileX][tileY] = null;
    }

    /**
     * Checks wether there is a tile at specified matrix position or not. Returns TRUE if there is no tile at the
     * specified matrix position.
     *
     * @param matrixPositionX the matrix position x
     * @param matrixPositionY the matrix position y
     * @return boolean
     */
    public boolean hasNotTileAt(final int matrixPositionX, final int matrixPositionY) {
        try {
            return this.tiles[matrixPositionX][matrixPositionY] == null;
        } catch (ArrayIndexOutOfBoundsException e) {
            // Very dirty :-(
            return false;
        }
    }

    public boolean isPuzzleSolved() {
        Tile tile;

        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                tile = this.tiles[i][j];
                if (tile != null) {
                    if (this.solution[i][j] != tile.getTileNumber()) {
                        return false;
                    }
                } else {
                    if (this.solution[i][j] != -1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
