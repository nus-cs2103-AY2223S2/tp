package seedu.address.ui;
import java.util.Comparator;

/**
 * Comparator for UiFile
 */
public class FileComparator implements Comparator<UiFile> {
    @Override
    public int compare(UiFile file1, UiFile file2) {
        return file1.getFileName().compareTo(file2.getFileName());
    }
}
