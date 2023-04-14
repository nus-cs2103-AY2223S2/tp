package seedu.address.ui;
import java.util.Comparator;

/**
 * Comparator for UiFile
 */
public class FileComparator implements Comparator<UiFile> {
    @Override
    public int compare(UiFile file1, UiFile file2) {
        String name1 = file1.getFileName();
        String name2 = file2.getFileName();
        boolean name2digit = Character.isDigit(name2.trim().charAt(0));
        boolean name1digit = Character.isDigit(name1.trim().charAt(0));
        if (name1digit
            && (!name2digit)) {
            return -1;
        } else if ((!name1digit)
            && name2digit) {
            return 1;
        } else if (name1digit && name2digit) {
            return Integer.parseInt(String.valueOf(name1.trim().indexOf(0)))
                - Integer.parseInt(String.valueOf(name2.trim().indexOf(0)));
        } else {
            return file1.getFileName().compareTo(file2.getFileName());
        }
    }
}
