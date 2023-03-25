package seedu.address.ui;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Class to handle File UI icon.
 */
public class UiFile {
    private String fileName;
    private Person person;
    private ObservableList<UiFile> fileList;

    /**
     * Ui file with the person it belongs to and its name.
     */
    public UiFile(String fileName, Person person, ObservableList<UiFile> fileList) {
        this.fileName = fileName;
        this.person = person;
        this.fileList = fileList;
    }
    public String getFileName() {
        return fileName;
    }
    public Person getPerson() {
        return this.person;
    }
    public void delete() {
        fileList.remove(this);
    }
}
