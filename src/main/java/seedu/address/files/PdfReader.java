package seedu.address.files;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;



/**
 * The type Pdf reader.
 */
public class PdfReader implements FileReader<PDDocument> {
    private Path path;

    PdfReader(Path path) {
        this.path = path;
    }

    @Override
    public PDDocument loadFile(Path path) {
        try {
            return Loader.loadPDF(path.toFile());
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

}
