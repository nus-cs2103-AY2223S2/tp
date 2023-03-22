package seedu.address.files;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * The type Image reader.
 */
public class ImageReader implements FileReader<BufferedImage> {

    private Path path;

    ImageReader(Path path) {
        this.path = path;
    }

    @Override
    public BufferedImage loadFile(Path path) {
        try {
            BufferedImage image = ImageIO.read(path.toFile());
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
            //replace with an error image;
        }
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public String getFileName(Path path) {
        return path.getFileName().toString();
    }

    /**
     * Display image.
     */
    public void displayImage() {
        BufferedImage image = loadFile(path);
        if (image != null) {
            JFrame frame = new JFrame();
            frame.setSize(image.getWidth(), image.getHeight());
            JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label);
            frame.setVisible(true);
        }
    }
}
