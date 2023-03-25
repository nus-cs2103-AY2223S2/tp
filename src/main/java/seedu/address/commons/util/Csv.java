package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import com.opencsv.CSVReader;

/**
 * Represents the contents of a CSV file. The first row of a CSV file acts as the headers for each column.
 */
public class Csv {

    private List<String[]> csvNestedArray;
    private List<String> headers;
    private int numOfCols;
    private int numOfRows;

    /**
     * Constructs a CSV object from a given CSV file.
     *
     * @param csvFileReader capable of reading the given CSV file.
     * @throws IOException thrown if error occurs due to reading the file.
     */
    public Csv(FileReader csvFileReader) throws IOException {
        requireNonNull(csvFileReader);

        CSVReader csvReader = new CSVReader(csvFileReader);
        csvNestedArray = csvReader.readAll();
        headers = Arrays.asList(csvNestedArray.get(0));
        numOfCols = headers.size();
        numOfRows = csvNestedArray.size();
    }

    /**
     * Checks that the given index is non-negative and less than the number of rows in the CSV object.
     *
     * @param index given row index.
     */
    private void requireValidRowIndex(int index) {
        if (index < 0 || index >= numOfRows) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks that the given index is non-negative and less than the number of columns in the CSV object.
     *
     * @param index given column index
     */
    private void requireValidColInput(int index) {
        if (index < 0 || index >= numOfCols) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks that the given header is an existing one.
     *
     * @param header
     */
    private void requireValidColInput(String header) {
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

}
