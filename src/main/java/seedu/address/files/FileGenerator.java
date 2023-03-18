package seedu.address.files;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import seedu.address.model.person.Person;



/**
 * The type File generator.
 */
public class FileGenerator {
    private static int form_Id;
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
        createForm();
    }

    private void createForm() {
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();

            // Create a new page in the document
            PDPage page = new PDPage();
            document.addPage(page);

            // Get the content stream of the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            //Set the font to Roboto Bold with size 28
            File myfont1 = new File("lib/Roboto/Roboto-Bold.ttf");
            PDFont font1 = PDType0Font.load(document, myfont1);
            contentStream.setFont(font1, 12);

            // Draw the certificate title
            String title = "Medical Certificate";
            float titleWidth = font1.getStringWidth(title) / 1000 * 12;
            float titleHeight = font1.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
            float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2;
            float titleY = page.getMediaBox().getHeight() - 50;
            contentStream.beginText();
            contentStream.newLineAtOffset(titleX, titleY);
            contentStream.showText(title);
            contentStream.endText();

            // Draw the patient name label
            String nameLabel = "Patient Name:";
            float nameLabelWidth = font1.getStringWidth(nameLabel) / 1000 * 12;
            float nameLabelHeight = font1.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
            float nameLabelX = page.getMediaBox().getWidth() / 2;
            float nameLabelY = titleY - 20;
            contentStream.beginText();
            contentStream.newLineAtOffset(nameLabelX, nameLabelY);
            contentStream.showText(nameLabel);
            contentStream.endText();

            // Draw the patient name
            String patientName = "John Doe";
            float patientNameWidth = font1.getStringWidth(patientName) / 1000 * 12;
            float patientNameHeight = font1.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
            float patientNameX = page.getMediaBox().getWidth() / 2 + 80;
            float patientNameY = nameLabelY;
            contentStream.beginText();
            contentStream.newLineAtOffset(patientNameX, patientNameY);
            contentStream.showText(patientName);
            contentStream.endText();

            // Draw the patient date of birth label
            String dobLabel = "Date of Birth:";
            float dobLabelWidth = font1.getStringWidth(dobLabel) / 1000 * 12;
            float dobLabelHeight = font1.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
            float dobLabelX = page.getMediaBox().getWidth() / 2;
            float dobLabelY = nameLabelY - 20;
            contentStream.beginText();
            contentStream.newLineAtOffset(dobLabelX, dobLabelY);
            contentStream.showText(dobLabel);
            contentStream.endText();

            // Draw the patient date of birth
            String dob = "01/01/2000";
            float dobWidth = font1.getStringWidth(dob) / 1000 * 12;
            float dobHeight = font1.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
            float dobX = page.getMediaBox().getWidth() / 2 + 80;
            float dobY = dobLabelY;
            contentStream.beginText();
            contentStream.newLineAtOffset(dobX, dobY);
            contentStream.showText(dob);
            contentStream.endText();

            // close the content stream
            contentStream.close();

            //Save the document to the specified file path
            document.save("reports/" + person.getName().fullName + "/medical_certificate.pdf");

            //Close the document
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
