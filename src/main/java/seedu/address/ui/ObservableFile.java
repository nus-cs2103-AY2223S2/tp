package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to provide input for FileList.
 */
public class ObservableFile {
    private List<String> listOfFileName;
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
    public ObservableFile(List<String> listOfFileName) {
        this.listOfFileName = listOfFileName;
    }

    public ObservableList<UiFile> getObservableFileList() {
        ObservableList<UiFile> fileList = FXCollections.observableArrayList();
        for (String s: listOfFileName) {
            UiFile file = new UiFile(s);
            fileList.add(file);
        }
        return fileList;
    }
    public static void clickGenerate() {
        System.out.println("generate MC");
    }
}
