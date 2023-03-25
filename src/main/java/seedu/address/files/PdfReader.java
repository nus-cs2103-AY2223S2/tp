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
 * The PdfReader class reads and displays PDF files. It loads the PDF document,
 * creates a PDF renderer, and displays each page in a new window.
 */
public class PdfReader implements FileReader<PDDocument> {
    private Path path;

    /**
     * Constructs a new PdfReader instance with the specified path.
     * <p>
     * This constructor initializes a new PdfReader instance with the
     * provided path. The path represents the location of the PDF file
     * to be loaded and displayed.
     *
     * @param path The Path object representing the location of the PDF file.
     */
    public PdfReader(Path path) {
        this.path = path;
    }

    /**
     * Loads a PDF document from the given path.
     * <p>
     * This method attempts to read a PDDocument from the specified path.
     * If the PDF document is successfully loaded, it is returned. In case of an
     * IOException, an empty PDDocument is returned.
     *
     * @param path The Path object representing the location of the PDF file.
     * @return The loaded PDDocument, or an empty PDDocument if an exception occurs.
     */
    @Override
    public PDDocument loadFile(Path path) {
        try {
            return PDDocument.load(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return new PDDocument();
            //return empty pdf to avoid NPE
        }
    }

    /**
     * Retrieves the Path object associated with this instance.
     * <p>
     * This method returns the Path object representing the location of
     * the PDF file associated with this instance.
     *
     * @return The Path object representing the PDF file location.
     */
    @Override
    public Path getPath() {
        return this.path;
    }

    /**
     * Retrieves the file name of the PDF from the given path.
     * <p>
     * This method takes a Path object as input and extracts the file name
     * from it. The file name is returned as a String.
     *
     * @param path The Path object representing the location of the PDF file.
     * @return The file name of the PDF as a String.
     */
    @Override
    public String getFileName(Path path) {
        return path.getFileName().getFileName().toString();
    }

    /**
     * Displays the PDF in separate windows, one for each page.
     * <p>
     * This method loads the PDF document from the associated path, creates
     * a PDF renderer, and iterates through each page in the document. Each
     * page is rendered as an image and displayed in a new JFrame window.
     * The DPI for rendering the PDF is set to the screen resolution.
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
