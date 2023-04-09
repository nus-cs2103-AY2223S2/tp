package seedu.address.files;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;


/**
 * The type Image reader.
 */
public class ImageReader implements FileReader<BufferedImage> {

    private Path path;
    private final int windowWidth = 800;
    private final int windowHeight = 600;

    /**
     * Constructs a new ImageReader instance with the specified path.
     * <p>
     * This constructor initializes a new ImageReader instance with the
     * provided path. The path represents the location of the image file
     * to be loaded and displayed.
     *
     * @param path The Path object representing the location of the image file.
     */
    ImageReader(Path path) {
        this.path = path;
    }

    /**
     * Loads an image from the given path.
     * <p>
     * This method attempts to read a BufferedImage from the specified path.
     * If the image is successfully loaded, it is returned. In case of an
     * IOException, a default error image is returned instead.
     *
     * @param path The Path object representing the location of the image file.
     * @return The loaded BufferedImage, or a default error image if an exception occurs.
     */
    @Override
    public BufferedImage loadFile(Path path) {
        try {
            BufferedImage image = ImageIO.read(path.toFile());
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return createErrorImage();
        }
    }

    /**
     * Retrieves the Path object associated with this instance.
     * <p>
     * This method returns the Path object representing the location of
     * the image file associated with this instance.
     *
     * @return The Path object representing the image file location.
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * Retrieves the file name of the image from the given path.
     * <p>
     * This method takes a Path object as input and extracts the file name
     * from it. The file name is returned as a String.
     *
     * @param path The Path object representing the location of the image file.
     * @return The file name of the image as a String.
     */
    @Override
    public String getFileName(Path path) {
        return path.getFileName().toString();
    }

    /**
     * Displays an image in a JFrame, scaled to fit the window size.
     * <p>
     * This method loads a BufferedImage from the given path and scales it to
     * fit the window size specified by windowWidth and windowHeight. The scaled
     * image is then displayed in a JFrame using a JLabel with an ImageIcon.
     * If the image cannot be loaded, the method does nothing.
     */
    public void displayImage() {
        BufferedImage image = loadFile(path);
        if (image != null) {
            //Scale the image to fit the window size
            Image scaledImage = getScaledImage(image, windowWidth, windowHeight);

            //Create JFrame and JLabel
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(scaledImage));

            //Set the frame properties
            frame.setSize(windowWidth, windowHeight);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(label);
            frame.setVisible(true);

            // Register ESC key to close the window
            frame.getRootPane().registerKeyboardAction(e -> frame.dispose(),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);
        }
    }

    private Image getScaledImage(BufferedImage image, int width, int height) {
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();
        double imgRatio = (double) imgWidth / imgHeight;
        double targetRatio = (double) width / height;

        if (imgRatio > targetRatio) {
            height = (int) (width / imgRatio);
        } else {
            width = (int) (height * imgRatio);
        }

        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * Creates and returns a modern default error image.
     * <p>
     * This method generates a BufferedImage with a light gray background,
     * a red error icon, and an error message text "Error loading image" in black.
     * The image can be used as a default error image when the original image
     * fails to load.
     *
     * @return A BufferedImage containing a modern default error image.
     */
    private BufferedImage createErrorImage() {
        int width = 300;
        int height = 200;
        BufferedImage errorImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        //Create graphics object and set anti-aliasing
        Graphics2D g2d = errorImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw background
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, width, height);

        //Draw red error icon
        g2d.setColor(Color.RED);
        int iconSize = 50;
        g2d.fillOval(20, (height - iconSize) / 2, iconSize, iconSize);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(20 + iconSize / 2, (height - iconSize) / 2 + 10,
                20 + iconSize / 2, (height - iconSize) / 2 + iconSize - 10);
        g2d.drawLine(20 + iconSize / 2 - 7, (height - iconSize) / 2
                + iconSize - 20, 20 + iconSize / 2 + 7, (height - iconSize) / 2 + iconSize - 20);

        //Draw error message
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        g2d.drawString("Error loading image", 90, height / 2 - 10);

        g2d.dispose();

        return errorImage;
    }
}
