package seedu.address.files;


import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;



/**
 * The type Pdf reader.
 */
public class PdfReader implements FileReader<PDDocument> {
    private Path path;

    public PdfReader(Path path) {
        this.path = path;
    }

    @Override
    public PDDocument loadFile(Path path) {
        try {
            return PDDocument.load(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
            //return empty pdf to avoid NPE
        }
    }

    @Override
    public Path getPath() {
        return this.path;
    }

    @Override
    public String getFileName(Path path) {
        return path.getFileName().getFileName().toString();
    }

    /**
     * Display pdf.
     */
    public void displayPdf() {
        try {
            //Load the PDF document
            PDDocument doc = loadFile(this.path.toRealPath());

            //Create a PDF renderer
            PDFRenderer renderer = new PDFRenderer(doc);

            //Set the DPI for rendering the PDF
            int dpi = Toolkit.getDefaultToolkit().getScreenResolution();

            //Loop through each page in the PDF and render it as an image
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, dpi, ImageType.RGB);

                //Display the image in a new window
                JFrame frame = new JFrame();
                JLabel label = new JLabel(new ImageIcon(image));
                frame.getContentPane().add(label, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
            //Close the PDF document
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
