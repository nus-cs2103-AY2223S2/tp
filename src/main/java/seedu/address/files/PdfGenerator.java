package seedu.address.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import seedu.address.model.person.MedicalCondition;
import seedu.address.model.person.Person;




/**
 * The type File generator.
 */
public class PdfGenerator implements FileGenerator {

    private final Person person;
    private final String doctorName;
    private final String description;
    private String formId;
    private final int days;

    /**
     * Instantiates a new File generator.
     *
     * @param person      the person
     * @param doctorName  the doctor name
     * @param description the description
     * @param days        the days
     */
    public PdfGenerator(Person person, String doctorName, String description, int days) {
        this.person = person;
        this.doctorName = doctorName;
        this.description = description;
        this.days = days;
    }

    public String getFormId() {
        return formId;
    }

    /**
     * Create Mc form.
     */
    public void generate(String filename) throws IOException {
        //Load the original PDF form
        InputStream inputStream = getClass().getResourceAsStream("/MC.pdf");
        PDDocument pdfDocument = PDDocument.load(inputStream);
        //Get the PDF form fields
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();


        formId = filename.replace(".pdf", "");
        //Get all fields
        List<PDField> fieldList = acroForm.getFields();

        for (PDField field : fieldList) {
            if (field instanceof PDTextField) {
                String fileName = field.getFullyQualifiedName();
                LocalDate now = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                switch (fileName) {
                case "name":
                    field.setValue(person.getName().fullName);
                    break;
                case "DOB":
                    field.setValue("Hidden");
                    break;
                case "days":
                    field.setValue(Integer.toString(days));
                    break;
                case "today":
                case "startDate":
                    field.setValue(now.format(formatter));
                    break;
                case "endDate":
                    LocalDate endDate = now.plusDays(days);
                    field.setValue(endDate.format(formatter));
                    break;
                case "Doctor Name":
                    field.setValue(doctorName);
                    break;
                default:
                    field.setValue(formId);
                    break;
                }
            }
        }
        //making file name unique using store MC number do file check before save.
        pdfDocument.save(new File("reports/" + person.getName().fullName + "/" + filename + "-mc.pdf"));
        pdfDocument.close();
        setMedicalCondition();
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                null, "MC form created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE));
    }

    private void setMedicalCondition() {
        person.setMedicalCondition(new MedicalCondition(description));
    }
}
