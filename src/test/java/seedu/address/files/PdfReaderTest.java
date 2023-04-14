package seedu.address.files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;



class PdfReaderTest {

    private static final String SAMPLE_PDF_PATH = "lib/MC.pdf";

    private PdfReader pdfReader;

    @BeforeEach
    void setUp() {
        Path path = Paths.get(SAMPLE_PDF_PATH);
        pdfReader = new PdfReader(path);
    }


    @Test
    void loadFile_validPath_returnsPDocument() throws IOException {
        assertNotNull(pdfReader.loadFile(Paths.get(SAMPLE_PDF_PATH)));
    }


    @Test
    void getPath() {
        assertEquals(Paths.get(SAMPLE_PDF_PATH), pdfReader.getPath());
    }

    @Test
    void getFileName() {
        String fileName = pdfReader.getFileName(Paths.get(SAMPLE_PDF_PATH));
        assertEquals("MC.pdf", fileName);
    }


    @Test
    void loadFile_invalidPath(@TempDir Path tempDir) {
        Path nonExistentPath = tempDir.resolve("nonexistent.pdf");
        PDDocument emptyDoc = pdfReader.loadFile(nonExistentPath);

        assertNotNull(emptyDoc);
        assertEquals(0, emptyDoc.getNumberOfPages());
    }

    @Test
    void loadFile_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pdfReader.loadFile(null));
    }
}
