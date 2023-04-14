package seedu.address.files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

class PdfGeneratorTest {

    private Person person;
    private String doctorName;
    private String description;
    private int days;

    @BeforeEach
    public void setUp() {
        Set<Tag> tags = new HashSet<>();
        // Create a sample person object
        person = new Person(new Name("John"), new Phone("12345678"),
                new Email("john@example.com"), new Address("123, Main Street"), tags);
        // Set other fields
        doctorName = "Dr. Smith";
        description = "Medical certificate";
        days = 3;
    }



    @Test
    public void testCreateMcForm() {
        FilesManager filesManager = new FilesManager(person);
        filesManager.initFile();

        PdfGenerator pdfGenerator = new PdfGenerator(person, doctorName, description, days);

        // Call the createMcForm() method
        try {
            pdfGenerator.generate("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Check if the file was created in the expected location
        File file = new File("reports/" + person.getName().fullName + "/-mc.pdf");
        assertTrue(file.exists());

        // Check if the form fields were filled in correctly
        // Load the PDF document
        PDDocument pdfDocument = null;

        try {
            pdfDocument = PDDocument.load(file);
            // Get the PDF form fields
            PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            // Get all fields
            for (PDField field : acroForm.getFields()) {
                if (field instanceof PDTextField) {
                    String fileName = field.getFullyQualifiedName();
                    LocalDate now = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    switch (fileName) {
                    case "name":
                        assertEquals(person.getName().fullName, field.getValueAsString());
                        break;
                    case "DOB":
                        assertEquals("Hidden", field.getValueAsString());
                        break;
                    case "days":
                        assertEquals(Integer.toString(days), field.getValueAsString());
                        break;
                    case "today":
                    case "startDate":
                        assertEquals(now.format(formatter), field.getValueAsString());
                        break;
                    case "endDate":
                        LocalDate endDate = now.plusDays(days);
                        assertEquals(endDate.format(formatter), field.getValueAsString());
                        break;
                    case "Doctor Name":
                        assertEquals(doctorName, field.getValueAsString());
                        break;
                    default:
                        assertEquals(pdfGenerator.getFormId(), field.getValueAsString());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdfDocument != null) {
                try {
                    pdfDocument.close();
                    filesManager.deleteAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
