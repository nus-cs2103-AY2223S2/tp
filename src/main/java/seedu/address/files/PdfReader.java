package seedu.address.files;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

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
        try (PDDocument doc = loadFile(this.path.toRealPath())) {
            if (doc == null) {
                throw new IOException("Error loading the PDF document");
            }

            PDFRenderer renderer = new PDFRenderer(doc);
            int dpi = getScreenResolution();

            JPanel panel = createPanelWithPdfPages(doc, renderer, dpi);
            JScrollPane scrollPane = createScrollPane(panel);

            displayPdfInFrame(scrollPane);

        } catch (IOException e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null,
                    "Error displaying PDF: " + e.getMessage(),
                    "PDF Viewer Error", JOptionPane.ERROR_MESSAGE));

        }
    }

    private int getScreenResolution() {
        return Toolkit.getDefaultToolkit().getScreenResolution();
    }

    /**
     * Creates a JPanel containing all pages of a PDF document as separate JLabels.
     * @param doc the PDF document
     * @param renderer the PDF renderer
     * @param dpi the DPI (dots per inch) for rendering the images
     * @return the JPanel containing the separate JLabels for each PDF page
     * @throws IOException if there is an error rendering the PDF pages
     */
    private JPanel createPanelWithPdfPages(PDDocument doc, PDFRenderer renderer, int dpi) throws IOException {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        for (int i = 0; i < doc.getNumberOfPages(); i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, dpi, ImageType.RGB);
            JLabel label = new JLabel(new ImageIcon(image));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            panel.add(label);
        }

        return panel;
    }

    /**
     * Creates a JScrollPane object from the given JPanel object.
     * @param panel the JPanel object to be displayed in the scroll pane
     * @return the created JScrollPane object
     */
    private JScrollPane createScrollPane(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        return scrollPane;
    }

    /**
     * Displays the PDF file in a JFrame window with a scrollable view of the PDF pages.
     * @param scrollPane the JScrollPane object containing the PDF pages
     */
    private void displayPdfInFrame(JScrollPane scrollPane) {
        JFrame frame = new JFrame();
        frame.setTitle("PDF Viewer: " + getFileName(path));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);

        // Register ESC key to close the window
        frame.getRootPane().registerKeyboardAction(e -> frame.dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

}
