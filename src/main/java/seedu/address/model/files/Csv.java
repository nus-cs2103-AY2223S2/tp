package seedu.address.model.files;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import com.opencsv.CSVReader;

/**
 * Represents the contents of a CSV file. The first row of a CSV file acts as the headers for each column.
 */
public class Csv {

    public static final String MESSAGE_CONSTRAINTS = "Files should end in '.csv'";
    public static final String VALIDATION_REGEX = ".*(\\.csv)\\z";
    private final List<String[]> csvNestedArray;
    private final List<String> headers;
    private final int numOfCols;
    private final int numOfRows;
    private final boolean isEmpty;

    /**
     * Constructs a CSV object from a given CSV file.
     *
     * @param path path to the given CSV file.
     * @throws IOException thrown if error occurs due to reading the file.
     */
    public Csv(String path) throws IOException {
        requireNonNull(path);
        checkArgument(isValidCsvPath(path), MESSAGE_CONSTRAINTS);
        FileReader fr = new FileReader(path);
        CSVReader csvReader = new CSVReader(fr);
        csvNestedArray = csvReader.readAll();
        fr.close();
        if (csvNestedArray.isEmpty()) {
            isEmpty = true;
            headers = new ArrayList<>();
            numOfCols = 0;
            numOfRows = 0;
        } else {
            isEmpty = false;
            String[] headerArr = csvNestedArray.get(0);
            headerArr[0] = headerArr[0].replace("\uFEFF", "");
            headers = Arrays.asList(headerArr);
            numOfCols = headers.size();
            numOfRows = csvNestedArray.size();
        }
    }

    /**
     * Returns if a given path ends with .csv.
     */
    public static boolean isValidCsvPath(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if the {@code Csv} is empty.
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Returns true if each row of the CSV has the same number of columns.
     */
    public boolean isColNumFixedPerRow() {
        if (isEmpty) {
            return false;
        }

        for (int i = 0; i < numOfRows; i++) {
            if (csvNestedArray.get(i).length != numOfCols) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that the given index is non-negative and less than the number of rows in the CSV object.
     *
     * @param index given row index.
     */
    private void requireValidRowIndex(int index) {
        if (isEmpty || index < 0 || index >= numOfRows) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks that the given index is non-negative and less than the number of columns in the CSV object.
     *
     * @param index given column index
     */
    private void requireValidColInput(int index) {
        if (isEmpty || index < 0 || index >= numOfCols) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks that the given header is an existing one.
     */
    private void requireValidColInput(String header) {
        if (isEmpty) {
            throw new NoSuchElementException();
        }

        boolean isValid = false;
        for (int i = 0; i < numOfCols; i++) {
            if (header.equalsIgnoreCase(headers.get(i))) {
                isValid = true;
            }
        }

        if (!isValid) {
            throw new NoSuchElementException();
        }
    }

    public String[] getRow(int index) {
        requireNonNull(index);
        requireValidRowIndex(index);

        return csvNestedArray.get(index);
    }

    public String getEntry(int rowNumber, int columnNumber) {
        requireNonNull(rowNumber);
        requireValidRowIndex(rowNumber);

        requireNonNull(columnNumber);
        requireValidColInput(columnNumber);

        return csvNestedArray.get(rowNumber)[columnNumber];
    }

    public String getEntry(int rowNumber, String columnHeader) {
        requireNonNull(rowNumber);
        requireValidRowIndex(rowNumber);

        int colNumber = getColumnIndex(columnHeader);

        return csvNestedArray.get(rowNumber)[colNumber];
    }

    public int getColumnIndex(String columnHeader) {
        requireNonNull(columnHeader);
        requireValidColInput(columnHeader);

        for (int i = 0; i < numOfCols; i++) {
            String header = headers.get(i);
            if (columnHeader.equalsIgnoreCase(header)) {
                return i;
            }
        }
        assert false : "Given header should be valid, but was not found";
        throw new NoSuchElementException();
    }

    public int getNumOfCols() {
        return numOfCols;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    @Override
    public String toString() {
        return csvNestedArray.toString();
    }
}
