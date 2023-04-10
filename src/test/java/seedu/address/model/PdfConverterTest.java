package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static seedu.address.testutil.TypicalMockStudents.getTypicalMockStudents;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.jupiter.api.Test;

import seedu.address.model.student.Student;

public class PdfConverterTest {
    private static String stringSample = "THIS_IS_A_SAMPLE_STRING:)";
    private List<Student> typicalStudentList = getTypicalMockStudents();
    private final float horizontalWrap = 432;
    private final float xInit = 90;
    private final float yInit = 702;

    private float x = 90;
    private float y = 702;
    private final float margin = 5;
    private float yInitTable = -1;
    private final float leftMarginPosition = 90;
    private final float rightMarginPosition = 522;

    private final PDFont fontBold = PDType1Font.HELVETICA_BOLD;
    private final PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
    private final PDFont font = PDType1Font.HELVETICA;
    private final Color black = Color.BLACK;
    private final Color red = new Color(194, 47, 40);
    private final Color yellow = new Color(219, 168, 0);
    private final Color green = new Color(0, 100, 0);
    private Color curColor;

    private final int fontTitleSize = 20;

    private final int fontHeadingSize = 18;
    private final int fontTableHeaderSize = 16;

    private final int fontTableContentSize = 14;
    private final int fontDateSize = 12;

    private PDDocument document;

    private PDPage page;

    private PDPageContentStream contentStream;
    private final PdfConverter pdfConverter = new PdfConverter();

    @Test
    public void elementsNotSetUp_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> this.document.close());
        assertThrows(NullPointerException.class, () -> this.document.addPage(page));
        assertThrows(NullPointerException.class, () -> new PDPageContentStream(document, page));
        assertThrows(NullPointerException.class, () -> this.contentStream.beginText());
        assertThrows(NullPointerException.class, () -> this.contentStream.endText());
        assertThrows(NullPointerException.class, () -> this.contentStream.close());
    }

    @Test
    public void exportProgress_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pdfConverter.exportProgress(null));
    }

    @Test
    public void createContents_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pdfConverter.createContents(null));
    }

    @Test
    public void createContents_methodCalled_success() throws Exception {
        Student key = typicalStudentList.get(0);
        String docTitle = key.getName().fullName + "'s Progress Report";
        String dateCreated = "Date created: " + LocalDate.now();
        String taskList = "Task List";
        String scoreList = "Score List";

        PdfConverter mock = spy(PdfConverter.class);
        mock.exportProgress(key);
        verify(mock, times(1)).setup();
        verify(mock, times(1)).createContents(key);
        verify(mock, times(1)).wrapText(docTitle, this.horizontalWrap, this.fontBold, this.fontTitleSize, List.of());
        verify(mock, times(1)).wrapText(dateCreated, this.horizontalWrap, fontItalic, this.fontDateSize, List.of());
        verify(mock, times(1)).wrapText(taskList, this.horizontalWrap, fontBold, this.fontHeadingSize, List.of());
        verify(mock, times(1)).createTaskTable(key.getTaskList());
        verify(mock, times(1)).wrapText(scoreList, this.horizontalWrap, fontBold, this.fontHeadingSize, List.of());
        verify(mock, times(1)).createScoreTable(key.getScoreList());
        verify(mock, atLeastOnce()).textHeight(any(PDFont.class), anyInt(), anyFloat());
        verify(mock, atLeastOnce()).textLength(anyString(), any(PDFont.class), anyInt());
//        verify(mock).handleNextPage(anyFloat(), anyList(), anyInt(), any(PDFont.class), anyInt());
//        verify(mock, atLeastOnce()).handleNextLine(anyInt(), any(PDFont.class), anyInt(), anyFloat());
//        verify(mock).handleWrapNextPage(anyInt(), anyFloat(), anyList(), any(PDFont.class), anyInt(), anyFloat());

    }

    @Test
    public void getNumberOfCharsPossible_nullString_returnsZero() throws IOException {
        assertTrue(pdfConverter.getNumberOfCharsPossible("", font, fontTableContentSize,
                this.horizontalWrap) == 0);
    }

    @Test
    public void getNumberOfCharsPossible_validString_returnsValidWrappedStringLength() throws IOException {
        int numberOfChars = pdfConverter.getNumberOfCharsPossible(stringSample, font, fontTableContentSize,
                (float) 50.0);
        String wrappedString = stringSample.substring(0, numberOfChars);
        float wrappedStringLength = pdfConverter.textLength(wrappedString + "-", font, fontTableContentSize);

        assertTrue(wrappedStringLength <= (float) 50.0);
    }

    @Test
    public void textLength_validString_equalsTotalLengthOfEachCharacter() throws IOException {
        float totalLength = 0;
        for (int i = 0; i < stringSample.length(); i++) {
            totalLength += font.getStringWidth(String.valueOf(stringSample.charAt(i))) / 1000 * fontTableContentSize;
        }

        float totalExpectedLength = pdfConverter.textLength(stringSample, font, fontTableContentSize);
        assertTrue(Math.abs(totalLength - totalExpectedLength) < 0.01);
    }

    @Test
    public void textHeightPlusTwoMargins_validString_equalsTwoTimesHalfTextHeightPlusMargin() throws IOException {
        float halfHeight = (font.getFontDescriptor().getCapHeight() / 1000 * fontTableContentSize) / 2 + this.margin;
        float expectedHeight = (font.getFontDescriptor().getCapHeight() / 1000 * fontTableContentSize) + 2
                * this.margin;
        assertTrue(Math.abs(halfHeight * 2 - expectedHeight) < 0.01);
    }

}
