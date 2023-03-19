package seedu.address.files;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import seedu.address.model.person.Person;



/**
 * The type File generator.
 */
public class FileGenerator {
    //store this field at Json
    private static int formId = 0;
    private Person person;
    private String doctorName;
    private String description;
    private int days;

    /**
     * Instantiates a new File generator.
     *
     * @param person      the person
     * @param doctorName  the doctor name
     * @param description the description
     * @param days        the days
     */
    public FileGenerator(Person person, String doctorName, String description, int days) throws IOException {
        this.person = person;
        this.doctorName = doctorName;
        this.description = description;
        this.days = days;
        formId++;
        createForm();
    }

    private void createForm() {
        try {
            // Load the original PDF form
            PDDocument pdfDocument = PDDocument.load(new File("lib/MC.pdf"));

            // Get the PDF form fields
            PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();

            //Get all fields

            List<PDField> fieldList = acroForm.getFields();

            for (PDField field: fieldList) {
                if (field instanceof PDTextField) {
                    String fileName = field.getFullyQualifiedName();
                    if (fileName.equals("name")) {
                        field.setValue(person.getName().fullName);
                    } else if (fileName.equals("DOB")) {
                        field.setValue("222-2222");
                    } else if (fileName.equals("days")) {
                        field.setValue(Integer.toString(days));
                    } else if (fileName.equals("today")) {
                        // Fill in the date fields
                        LocalDate now = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        field.setValue(now.format(formatter));
                    } else if (fileName.equals("startDate")) {
                        field.setValue("2019-11-11");
                    } else if (fileName.equals("endDate")) {
                        field.setValue("2019-11-12");
                    } else if (fileName.equals("DoctorName")) {
                        field.setValue(doctorName);
                    } else {
                        field.setValue(Integer.toString(formId));
                    }
                }
            }
            pdfDocument.save(new File("reports/" + person.getName().fullName + "/mc.pdf"));
            pdfDocument.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
