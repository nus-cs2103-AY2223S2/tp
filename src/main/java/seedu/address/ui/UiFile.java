package seedu.address.ui;

import seedu.address.model.person.Person;

/**
 * Class to handle File UI icon.
 */
public class UiFile {
    private String fileName;
    private Person person;

    /**
     * Ui file with the person it belongs to and its name.
     */
    public UiFile(String fileName, Person person) {
        this.fileName = fileName;
        this.person = person;
    }
    public String getFileName() {
        return fileName;
    }
    public Person getPerson() {
        return this.person;
    }
}
