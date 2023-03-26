package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreList;
import seedu.address.model.task.TaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);
    private final UniquePersonList persons;
    private Person selectedPerson;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void checkPerson(Person key) {
        selectedPerson = key;
    }

    public Person findCheckedPerson() {
        return selectedPerson;
    }

    /**
     * Exports {@code key}'s task and score list in the form of a PDF file from this {@code AddressBook}.
     */
    public PDDocument exportProgress(Person key) throws IOException {
        requireAllNonNull(key);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        float horizontalWrap = 432;
        float xInit = 90;
        float yInit = 702;
        float margin = 5;

        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont font = PDType1Font.HELVETICA;

        List<Object> name = wrapText(key.getName().fullName + "'s Progress Report", horizontalWrap, xInit, yInit,
                fontBold, 20, contentStream, document, margin, -1, List.of());
        contentStream = (PDPageContentStream) name.get(0);
        yInit = (float) name.get(2) - textHeight(fontBold, 20, margin / 2);

        List<Object> date = wrapText("Date created: " + LocalDate.now(), horizontalWrap, xInit, yInit, fontItalic,
                12, contentStream, document, margin, -1, List.of());
        contentStream = (PDPageContentStream) date.get(0);
        yInit = (float) date.get(2) - 3 * textHeight(fontBold, 20, 0);

        List<Object> taskListString = wrapText("Task List", horizontalWrap, xInit, yInit, fontBold, 18,
                contentStream, document, margin, -1, List.of());
        contentStream = (PDPageContentStream) taskListString.get(0);
        yInit = (float) taskListString.get(2) - 2 * textHeight(fontBold, 18, 0);

        List<Object> taskTable = createTaskTable(key.getTaskList(), xInit, yInit, fontBold, 16, font,
                14, contentStream, document, horizontalWrap, margin);
        contentStream = (PDPageContentStream) taskTable.get(0);
        yInit = (float) taskTable.get(2);

        List<Object> scoreListString = wrapText("Score List", horizontalWrap, xInit, yInit, fontBold, 18,
                contentStream, document, margin, -1, List.of());
        contentStream = (PDPageContentStream) scoreListString.get(0);
        yInit = (float) scoreListString.get(2) - 2 * textHeight(fontBold, 18, 0);

        List<Object> scoreTable = createScoreTable(key.getScoreList(), xInit, yInit, fontBold, 16, font,
                14, contentStream, document, horizontalWrap, margin);
        contentStream = (PDPageContentStream) scoreTable.get(0);
        yInit = (float) scoreTable.get(2);

        contentStream.close();
        return document;
    }

    private List<Object> createTaskTable(TaskList tasks, float x, float y, PDFont fontBold, int fontSizeBold,
                                         PDFont font, int fontSize, PDPageContentStream contentStream,
                                         PDDocument document, float wrap, float margin) throws IOException {
        List<String> headers = Arrays.asList("Status", "Name");
        List<String> maxContentWidthString = Arrays.asList("In Progress", "");
        float yInitTable = (float) (y + textHeight(fontBold, fontSizeBold, 0) / 2 + margin);
        List<Object> tableHeaders = createTableRow(headers, maxContentWidthString, x, y, fontBold, fontSizeBold,
                contentStream, document, wrap, margin, yInitTable);
        contentStream = (PDPageContentStream) tableHeaders.get(0);
        y = (float) tableHeaders.get(2) - textHeight(fontBold, fontSizeBold, 0) / 2 - 2 * margin;

        List<Object> tableContent = createTableContentForTask(tasks, maxContentWidthString, 90, y, font, fontSize,
                contentStream, document, wrap, margin, yInitTable);
        contentStream = (PDPageContentStream) tableContent.get(0);

        List<Object> next = new ArrayList<>();
        next.add(contentStream);
        next.add(x);
        next.add(y);
        return next;
    }

    private List<Object> createScoreTable(ScoreList scores, float x, float y, PDFont fontBold, int fontSizeBold,
                                          PDFont font, int fontSize, PDPageContentStream contentStream,
                                          PDDocument document, float wrap, float margin) throws IOException {
        List<String> headers = Arrays.asList("Date", "Test", "Score");
        List<String> maxContentWidthString = Arrays.asList("8888-88-88", "WWWWWWWWWWWWWWWWWW", "88.8");
        float yInitTable = (float) (y + textHeight(fontBold, fontSizeBold, 0) / 2 + margin);
        List<Object> tableHeaders = createTableRow(headers, maxContentWidthString, x, y, fontBold, fontSizeBold,
                contentStream, document, wrap, margin, yInitTable);
        contentStream = (PDPageContentStream) tableHeaders.get(0);
        y = (float) tableHeaders.get(2) - textHeight(fontBold, fontSizeBold, 0) / 2 - 2 * margin;

        List<Object> tableContent = createTableContentForScore(scores, maxContentWidthString, 90, y, font,
                fontSize, contentStream, document, wrap, margin, yInitTable);
        contentStream = (PDPageContentStream) tableContent.get(0);

        List<Object> next = new ArrayList<>();
        next.add(contentStream);
        next.add(x);
        next.add(y);
        return next;
    }

    private void createHorizontalLine(PDPageContentStream contentStream, float y) throws IOException {
        contentStream.drawLine(90, y, 522, y);
    }

    private List<Object> createTableRow(List<String> headers, List<String> maxContentWidthString, float x, float y,
                                        PDFont font, int fontSize, PDPageContentStream contentStream,
                                        PDDocument document, float wrap, float margin, float yInit) throws IOException {
        float xInit = x;
        float yFinal = y;
        createHorizontalLine(contentStream, (float) (y + textHeight(font, fontSize, 0) / 2 + margin));
        for (int i = 0; i < headers.size(); i++) {
            float wrapCur;
            if (i != 0 && i != headers.size() - 1) {
                wrapCur = wrap - (x - xInit) - 4 * margin - textLength(maxContentWidthString.get(i + 1), font,
                        fontSize);
            } else {
                wrapCur = wrap - (x - xInit) - 2 * margin;
            }
            List<Object> current = wrapText(headers.get(i), wrapCur, x + margin, y - margin, font, fontSize,
                    contentStream, document, margin, yInit, maxContentWidthString);
            contentStream = (PDPageContentStream) current.get(0);
            yFinal = (float) current.get(2);
            yInit = (float) current.get(3);
            x += textLength(maxContentWidthString.get(i), PDType1Font.HELVETICA_BOLD, 16) + 2 * margin;
        }
        List<Object> next = new ArrayList<>();
        next.add(contentStream);
        next.add(x);
        next.add(yFinal);
        next.add(yInit);
        return next;
    }

    private List<Object> createTableContentForTask(TaskList tasks, List<String> maxContentWidthString, float x, float y,
                                                   PDFont font, int fontSize, PDPageContentStream contentStream,
                                                   PDDocument document, float wrap, float margin, float yInit)
            throws IOException {
        float yFinal = y;
        for (int i = 0; i < tasks.size(); i++) {
            List<String> contents = Arrays.asList("In Progress", tasks.get(i).getName().fullName);
            List<Object> row = createTableRow(contents, maxContentWidthString, x, y, font, fontSize, contentStream,
                    document, wrap, margin, yInit);
            contentStream = (PDPageContentStream) row.get(0);
            yInit = (float) row.get(3);
            yFinal = (float) row.get(2) - margin;
            y = (float) row.get(2) - textHeight(font, 14, 0) / 2 - margin * 2;
        }
        createVerticalLines(contentStream, yInit, yFinal, maxContentWidthString, margin);
        createHorizontalLine(contentStream, yFinal);
        List<Object> next = new ArrayList<>();
        next.add(contentStream);
        next.add(x);
        next.add(y);
        return next;
    }

    private List<Object> createTableContentForScore(ScoreList scores, List<String> maxContentWidthString, float x,
                                                    float y, PDFont font, int fontSize,
                                                    PDPageContentStream contentStream, PDDocument document, float wrap,
                                                    float margin, float yInit) throws IOException {
        float yFinal = y;
        for (int i = 0; i < scores.size(); i++) {
            Score currentScore = scores.get(i);
            List<String> contents = Arrays.asList(currentScore.getDate().toString(), currentScore.getLabel().toString(),
                    currentScore.getValue().toString());
            List<Object> row = createTableRow(contents, maxContentWidthString, x, y, font, fontSize, contentStream,
                    document, wrap, margin, yInit);
            contentStream = (PDPageContentStream) row.get(0);
            yInit = (float) row.get(3);
            yFinal = (float) row.get(2) - margin;
            y = (float) row.get(2) - textHeight(font, 14, 0) / 2 - margin * 2;
        }
        createVerticalLines(contentStream, yInit, yFinal, maxContentWidthString, margin);
        createHorizontalLine(contentStream, yFinal);
        List<Object> next = new ArrayList<>();
        next.add(contentStream);
        next.add(x);
        next.add(y);
        return next;
    }
    private PDPageContentStream setUpContentStream(PDPageContentStream contentStream, PDFont font, int fontSize,
                                                   float x, float y, PDDocument document) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.moveTextPositionByAmount(x, y);
        return contentStream;
    }

    private float textHeight(PDFont font, int fontSize, float margin) {
        return (font.getFontDescriptor().getCapHeight() / 1000 * fontSize) + 2 * margin;
    }

    private float textLength(String curString, PDFont font, int fontSize) throws IOException {
        return font.getStringWidth(curString) / 1000 * fontSize;
    }

    private int getNumberOfCharsPossible(String curString, float wrap, PDFont font, int fontSize) throws IOException {
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

    private void createVerticalLines(PDPageContentStream contentStream, float yInitTable, float yFinal,
                                     List<String> maxContentWidthString, float margin) throws IOException {
        contentStream.drawLine(90, yInitTable, 90, yFinal);
        contentStream.drawLine(522, yInitTable, 522, yFinal);
        float xPos = 90;
        for (int i = 0; i < maxContentWidthString.size() - 1; i++) {
            float cur = textLength(maxContentWidthString.get(i), PDType1Font.HELVETICA_BOLD, 16) + 2 * margin;
            xPos += cur;
            contentStream.drawLine(xPos, yInitTable, xPos, yFinal);
        }
    }

    private PDPageContentStream handleNextPage(PDPageContentStream contentStream, PDDocument document, float yInitTable,
                                               float yFinal, List<String> maxContentWidthString, float margin)
            throws IOException {
        createVerticalLines(contentStream, yInitTable, yFinal, maxContentWidthString, margin);
        contentStream.close();
        PDPage newPage = new PDPage();
        document.addPage(newPage);
        contentStream = new PDPageContentStream(document, newPage);
        return contentStream;
    }

    private List<Object> wrapText(String text, float wrap, float xInit, float yInit, PDFont font, int fontSize,
                                  PDPageContentStream contentStream, PDDocument document, float margin,
                                  float yInitTable, List<String> maxContentWidthString) throws IOException {
        String[] textSplit = text.split(" ");
        float x = xInit;
        float y = yInit;
        float lengthUsed = 0;
        for (int i = 0; i < textSplit.length; i++) {
            String curString = textSplit[i];
            lengthUsed += textLength(curString, font, fontSize);
            int limit = (textLength(curString, font, fontSize) <= wrap) ? curString.length()
                    : getNumberOfCharsPossible(curString, wrap, font, fontSize);
            logger.info(String.valueOf(limit));
            if (textLength(curString, font, fontSize) <= wrap) {
                curString = curString + " ";
                lengthUsed += 1;
            } else {
                curString = curString.substring(0, limit - 1) + "-";
                textSplit[i] = textSplit[i].substring(limit - 1);
                i--;
            }
            float yPrev = y;
            if (lengthUsed > wrap) {
                y -= textHeight(font, fontSize, (float) margin / 2);
                x = xInit;
                lengthUsed = textLength(curString, font, fontSize);
            }
            if (y <= 92) {
                contentStream = handleNextPage(contentStream, document, yInitTable, yPrev - margin,
                        maxContentWidthString, margin);
                x = xInit;
                y = 702;
                yInitTable = y + textHeight(font, fontSize, 0) / 2 + 2 * margin;
            }
            contentStream = setUpContentStream(contentStream, font, fontSize, x, y, document);
            contentStream.drawString(curString);
            contentStream.endText();
            x += textLength(curString, font, fontSize);
        }
        List<Object> next = new ArrayList<>();
        next.add(contentStream);
        next.add(x);
        next.add(y);
        next.add(yInitTable);
        return next;
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
