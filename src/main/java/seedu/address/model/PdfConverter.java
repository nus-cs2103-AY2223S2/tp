package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javafx.collections.ObservableList;
import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Converts student's progress (tasks and scores) to pdf format
 */

public class PdfConverter {
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    private final float horizontalWrap = 432;
    private final float xInit = 90;
    private final float yInit = 702;

    private float x = 90;
    private float y = 702;
    private final float margin = 5;
    private float yInitTable = 702;
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

    /**
     * Exports {@code key}'s task and score list in the form of a PDF file from this {@code AddressBook}.
     */
    public PDDocument exportProgress(Person key) throws IOException {
        requireAllNonNull(key);
        this.document = new PDDocument();
        this.page = new PDPage();
        this.document.addPage(page);
        this.contentStream = new PDPageContentStream(document, page);
        this.x = this.xInit;
        this.y = this.yInit;
        this.curColor = black;

        String docTitle = key.getName().fullName + "'s Progress Report";
        String dateCreated = "Date created: " + LocalDate.now();
        String taskList = "Task List";
        String scoreList = "Score List";

        wrapText(docTitle, this.horizontalWrap, this.fontBold, this.fontTitleSize, List.of());
        this.x = this.xInit;
        this.y -= textHeight(fontBold, fontTitleSize, margin / 2);

        wrapText(dateCreated, this.horizontalWrap, fontItalic, this.fontDateSize, List.of());
        this.x = this.xInit;
        this.y -= 3 * textHeight(fontBold, fontTitleSize, 0);

        wrapText(taskList, this.horizontalWrap, fontBold, this.fontHeadingSize, List.of());
        this.x = this.xInit;
        this.y -= 2 * textHeight(fontBold, fontHeadingSize, 0);

        createTaskTable(key.getTaskList());
        this.x = this.xInit;
        this.y -= 3 * textHeight(fontBold, fontTitleSize, 0);

        wrapText(scoreList, this.horizontalWrap, fontBold, this.fontHeadingSize, List.of());
        this.x = this.xInit;
        this.y -= 2 * textHeight(fontBold, 18, 0);

        createScoreTable(key.getScoreList());

        this.contentStream.close();
        return this.document;
    }

    private void createTaskTable(TaskList tasks) throws IOException {
        if (tasks.size() == 0) {
            wrapText("No task found", this.horizontalWrap, this.fontBold, this.fontTableHeaderSize, List.of());
            return;
        }
        List<String> headers = Arrays.asList("Status", "Name");
        List<String> maxContentWidthString = Arrays.asList("In Progress", "");
        this.yInitTable = (this.y + textHeight(this.fontBold, this.fontTableHeaderSize, 0) / 2 + this.margin);
        createTableRow(headers, maxContentWidthString, this.fontBold, this.fontTableHeaderSize);

        this.x = this.leftMarginPosition;
        this.y -= textHeight(this.fontBold, this.fontTableContentSize, 0) / 2 + 2 * this.margin;
        createTableContentForTask(tasks, maxContentWidthString, this.font, this.fontTableContentSize);
    }

    private void createScoreTable(ScoreList scores) throws IOException {
        if (scores.size() == 0) {
            wrapText("No score history found", this.horizontalWrap, this.fontBold, this.fontTableHeaderSize, List.of());
            return;
        }
        List<String> headers = Arrays.asList("Date", "Test", "Score");
        List<String> maxContentWidthString = Arrays.asList("8888-88-88", "WWWWWWWWWWWWWWWWWW", "88.8");
        this.yInitTable = (this.y + textHeight(this.fontBold, this.fontTableHeaderSize, 0) / 2 + this.margin);
        createTableRow(headers, maxContentWidthString, this.fontBold, this.fontTableHeaderSize);

        this.x = this.leftMarginPosition;
        this.y -= textHeight(this.fontBold, this.fontTableContentSize, 0) / 2 + 2 * this.margin;
        createTableContentForScore(scores, maxContentWidthString, this.font, this.fontTableContentSize);
    }

    private void createHorizontalLine(float y) throws IOException {
        this.contentStream.drawLine(90, y, 522, y);
    }
    private void createTableRow(List<String> headers, List<String> maxContentWidthString, PDFont font, int fontSize)
            throws IOException {
        float yFinal = this.y;
        boolean isDoneDrawLine = false;
        if (this.y - this.margin > 90) {
            isDoneDrawLine = true;
            createHorizontalLine((this.y + textHeight(font, fontSize, 0) / 2 + this.margin));
        }

        for (int i = 0; i < headers.size(); i++) {
            float wrapCur;
            if (i != 0 && i != headers.size() - 1) {
                wrapCur = this.horizontalWrap - (this.x - this.xInit) - 4 * this.margin
                        - textLength(maxContentWidthString.get(i + 1), font,
                        fontSize);
            } else {
                wrapCur = this.horizontalWrap - (this.x - this.xInit) - 2 * this.margin;
            }
            logger.info(String.valueOf(wrapCur));
            this.x += this.margin;
            this.y -= this.margin;
            wrapText(headers.get(i), wrapCur, font, fontSize, maxContentWidthString);

            yFinal = this.y;
            this.x += textLength(maxContentWidthString.get(i), this.fontBold, this.fontTableHeaderSize) + this.margin;
            this.y += this.margin;

            if (!isDoneDrawLine && i == 0) {
                createHorizontalLine((this.y + textHeight(font, fontSize, 0) / 2 + this.margin));
            }
        }
        this.y = yFinal;
    }

    private String convertStatusString(String input) {
        String statement = input;
        String converted = "";
        switch(statement) {
        case "INPROGRESS":
            converted = "In Progress";
            break;
        case "COMPLETE":
            converted = "Complete";
            break;
        case "LATE":
            converted = "Late";
            break;
        default:
            converted = "";
            break;
        }
        return converted;
    }

    private void createTableContentForTask(TaskList tasks, List<String> maxContentWidthString, PDFont font,
                                           int fontSize)
            throws IOException {
        float yFinal = this.y;
        ObservableList<Task> sortedTaskList = tasks.getInternalList();
        for (int i = 0; i < tasks.size(); i++) {
            String status = convertStatusString(sortedTaskList.get(i).getStatus().name());
            if (status.equals("Late")) {
                this.curColor = this.red;
            } else if (status.equals("Complete")) {
                this.curColor = this.green;
            }
            List<String> contents = Arrays.asList(status, sortedTaskList.get(i).getName().fullName);
            createTableRow(contents, maxContentWidthString, font, fontSize);
            this.curColor = this.black;
            yFinal = this.y - margin;
            this.x = this.xInit;
            this.y -= textHeight(font, fontSize, 0) / 2 + this.margin * 2;
        }
        createVerticalLines(yFinal, maxContentWidthString);
        createHorizontalLine(yFinal);
    }

    private void createTableContentForScore(ScoreList scores, List<String> maxContentWidthString, PDFont font,
                                            int fontSize) throws IOException {
        float yFinal = this.y;
        ObservableList<Score> sortedScoreList = scores.getSortedScoreList();
        for (int i = 0; i < scores.size(); i++) {
            Score currentScore = sortedScoreList.get(i);
            double value = currentScore.getValue().value;
            if (value == 100) {
                this.curColor = this.green;
            }
            if (value < 80) {
                this.curColor = this.yellow;
            }
            if (value < 50) {
                this.curColor = this.red;
            }
            List<String> contents = Arrays.asList(currentScore.getDate().toString(), currentScore.getLabel().toString(),
                    currentScore.getValue().toString());
            createTableRow(contents, maxContentWidthString, font, fontSize);
            this.curColor = this.black;
            yFinal = this.y - this.margin;
            this.x = this.xInit;
            this.y -= textHeight(font, fontSize, 0) / 2 + this.margin * 2;
        }
        createVerticalLines(yFinal, maxContentWidthString);
        createHorizontalLine(yFinal);
    }
    private void setUpContentStream(PDFont font, int fontSize, float x, float y) throws IOException {
        this.contentStream.beginText();
        this.contentStream.setFont(font, fontSize);
        this.contentStream.moveTextPositionByAmount(x, y);
    }

    private float textHeight(PDFont font, int fontSize, float margin) {
        return (font.getFontDescriptor().getCapHeight() / 1000 * fontSize) + 2 * margin;
    }

    private float textLength(String curString, PDFont font, int fontSize) throws IOException {
        return font.getStringWidth(curString) / 1000 * fontSize;
    }

    private int getNumberOfCharsPossible(String curString, PDFont font, int fontSize, float wrap) throws IOException {
        float len = 0;
        int count = 0;
        for (int i = 0; i < curString.length(); i++) {
            float lenCur = textLength(String.valueOf(curString.charAt(i)), font, fontSize);
            if (len + lenCur > wrap) {
                break;
            }
            len += lenCur;
            count += 1;
        }
        return count;
    }

    private void createVerticalLines(float yFinal, List<String> maxContentWidthString) throws IOException {
        contentStream.drawLine(this.leftMarginPosition, this.yInitTable, this.leftMarginPosition, yFinal);
        contentStream.drawLine(this.rightMarginPosition, this.yInitTable, this.rightMarginPosition, yFinal);
        float xPos = this.leftMarginPosition;
        for (int i = 0; i < maxContentWidthString.size() - 1; i++) {
            float cur = textLength(maxContentWidthString.get(i), PDType1Font.HELVETICA_BOLD, 16) + 2
                    * this.margin;
            xPos += cur;
            contentStream.drawLine(xPos, yInitTable, xPos, yFinal);
        }
    }

    private PDPageContentStream handleNextPage(float yFinal, List<String> maxContentWidthString, float margin,
                                               PDFont font, int fontSize)
            throws IOException {
        if (this.yInitTable - 90 >= textHeight(font, fontSize, 0) + 2 * margin) {
            createVerticalLines(yFinal, maxContentWidthString);
        }
        contentStream.close();
        this.page = new PDPage();
        this.document.addPage(this.page);
        this.contentStream = new PDPageContentStream(document, page);
        return contentStream;
    }

    private void wrapText(String text, float wrap, PDFont font, int fontSize, List<String> maxContentWidthString)
            throws IOException {
        String[] textSplit = text.split(" ");
        float lengthUsed = 0;
        float xPosition = this.x;
        int count = 0;
        for (int i = 0; i < textSplit.length; i++) {
            String curString = textSplit[i];
            lengthUsed += textLength(curString, font, fontSize);
            if (textLength(curString, font, fontSize) <= wrap) {
                curString = curString + " ";
                lengthUsed += 1;
            } else {
                int limit = getNumberOfCharsPossible(curString, font, fontSize, wrap);
                curString = curString.substring(0, limit - 1) + "-";
                textSplit[i] = textSplit[i].substring(limit - 1);
                i--;
            }
            float yPrev = this.y;
            if (lengthUsed > wrap) {
                if (count != 0) this.y -= textHeight(font, fontSize, this.margin / 2);
                this.x = xPosition;
                lengthUsed = textLength(curString, font, fontSize);
            }
            if (this.y <= 90) {
                contentStream = handleNextPage(yPrev - this.margin, maxContentWidthString, margin, font,
                        fontSize);
                this.x = xPosition;
                this.y = yInit;
                this.yInitTable = this.y + textHeight(font, fontSize, 0) / 2 + 2 * this.margin;
            }
            setUpContentStream(font, fontSize, this.x, this.y);
            this.contentStream.setNonStrokingColor(this.curColor);
            this.contentStream.drawString(curString);
            this.contentStream.endText();
            this.x += textLength(curString, font, fontSize);
            count += 1;
        }
        this.x = xPosition;
    }
}
