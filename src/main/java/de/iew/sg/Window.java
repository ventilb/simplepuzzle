package de.iew.sg;

import javax.swing.*;
import java.awt.*;

/**
 * Implements the main application window.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 06.01.14 - 22:32
 */
public class Window extends JFrame {

    public Window() throws HeadlessException {
        setTitle("Simple Java Game");
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(550, 550);
        setMinimumSize(new Dimension(550, 550));
    }

    /**
     * Adds the specified stage to the application window.
     *
     * @param stage the stage
     */
    public void addStage(Stage stage) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0;
        gbc.weighty= 0;
        add(stage, gbc);
    }

}
