package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Class to provide input for FileList.
 */
public class ObservableFile {
    private List<String> listOfFileName;
    private Person person;
    /**
   * Panel containing the list of persons.
   */
    public ObservableFile() {
        listOfFileName = new ArrayList<>();
        listOfFileName.add("a");
        listOfFileName.add("b");
        listOfFileName.add("b");
        listOfFileName.add("b");
        listOfFileName.add("b");
        listOfFileName.add("b");
        listOfFileName.add("b");
        listOfFileName.add("b");
    }
    /**
     * Construct ObservableFile with the person and its file names.
     */
    public ObservableFile(List<String> listOfFileName, Person person) {
        this.listOfFileName = listOfFileName;
        this.person = person;
    }

    public ObservableList<UiFile> getObservableFileList() {
        ObservableList<UiFile> fileList = FXCollections.observableArrayList();
        for (String s: listOfFileName) {
            UiFile file = new UiFile(s, person, fileList);
            fileList.add(file);
        }
        return fileList;
    }
    public static void clickGenerate() {
        System.out.println("generate MC");
    }
}
