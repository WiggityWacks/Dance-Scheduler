package ui;

import javax.swing.*;
import java.awt.*;

// Represents an image pop-up
public class Image extends JFrame {

    JLabel label;
    ImageIcon image;

    // EFFECTS: constructs an image from a given path
    public Image(String fileName) {
        setLayout(new FlowLayout());

        image = new ImageIcon(fileName);

        label = new JLabel(image);
        add(label);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
