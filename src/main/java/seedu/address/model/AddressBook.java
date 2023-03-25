package seedu.address.model;

import static java.lang.Math.floor;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);
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

    public PDDocument exportProgress(Person key) throws IOException {
        requireAllNonNull(key);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        float horizontalWrap = 432;
        float xInit = 90;
        float yInit = 702;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;

        List<Object> name = wrapText(key.getName().fullName, horizontalWrap, xInit, yInit, fontBold, 20, contentStream, document);
        contentStream = (PDPageContentStream) name.get(0);
        yInit = (float) name.get(2) - 2 * textHeight(fontBold, 20);

        List<Object> taskListString = wrapText("Task List", horizontalWrap, xInit, yInit, fontBold, 18, contentStream, document);
        contentStream = (PDPageContentStream) taskListString.get(0);
        yInit = (float) taskListString.get(2) - textHeight(fontBold, 18);

        contentStream.close();
        return document;
    }


    public PDPageContentStream setUpContentStream(PDPageContentStream contentStream, PDFont font, int fontSize, float x, float y, PDDocument document) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.moveTextPositionByAmount(x, y);
        return contentStream;
    }

    public float textHeight(PDFont font, int fontSize) {
        return font.getFontDescriptor().getCapHeight() / 1000 * fontSize;
    }

    public float textLength(String curString, PDFont font, int fontSize) throws IOException {
        return font.getStringWidth(curString) / 1000 * fontSize;
    }

    public int getNumberOfCharsPossible(String curString, float wrap, PDFont font, int fontSize) throws IOException {
        float len = 0;
        int count = 0;
        for(int i = 0; i < curString.length(); i++) {
            float lenCur = textLength(String.valueOf(curString.charAt(i)), font, fontSize);
            if (len + lenCur > wrap) break;
            len += lenCur;
            count += 1;
        }
        return count;
    }

    public PDPageContentStream handleNextPage(PDPageContentStream contentStream, PDDocument document) throws IOException {
        contentStream.close();
        PDPage newPage = new PDPage();
        document.addPage(newPage);
        contentStream = new PDPageContentStream(document, newPage);
        return contentStream;
    }

    public List<Object> wrapText(String text, float wrap, float xInit, float yInit, PDFont font, int fontSize, PDPageContentStream contentStream, PDDocument document) throws IOException {
        String[] textSplit = text.split(" ");
        float x = xInit;
        float y = yInit;
        float lengthUsed = 0;
        for (int i = 0; i < textSplit.length; i++) {
            String curString = textSplit[i];
            lengthUsed += textLength(curString, font, fontSize);
            int limit = (textLength(curString, font, fontSize) <= wrap) ? curString.length() : getNumberOfCharsPossible(curString, wrap, font, fontSize);
            logger.info(String.valueOf(limit));
            if (textLength(curString, font, fontSize) <= wrap) {
                curString = curString + " ";
                lengthUsed += 1;
            } else {
                curString = curString.substring(0, limit);
                textSplit[i] = "-" + textSplit[i].substring(limit);
                i--;
            }
            if (lengthUsed > wrap) {
                y -= textHeight(font, fontSize);
                x = xInit;
                lengthUsed = textLength(curString, font, fontSize);
            }
            if (y <= 92) {
                handleNextPage(contentStream, document);
                x = xInit;
                y = yInit;
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
