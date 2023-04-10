package seedu.dengue.testutil;

import java.util.ArrayList;

import com.opencsv.bean.CsvBindByName;

/**
 * A utility class for test cases.
 */
public class CsvTestClass {

    public static final CsvTestClass[] TEST_ARRAY = new CsvTestClass[]{
        new CsvTestClass("row 1", "row 1"),
        new CsvTestClass("row 2", "row 2")};

    // Identity fields
    @CsvBindByName(column = "Column One")
    private String columnOne;
    @CsvBindByName(column = "Column Two")
    private String columnTwo;

    public CsvTestClass() {}

    /**
     * Creates a Csv test class
     * @param columnOne Value in the first column.
     * @param columnTwo Value in the second column.
     */
    public CsvTestClass(String columnOne, String columnTwo) {
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
    }

    public String getColumnOne() {
        return this.columnOne;
    }

    public String getColumnTwo() {
        return this.columnTwo;
    }

    /**
     * Returns an array of the string headers for CSV storage
     * @return A String[] representing the Headers for CsvTestClass class formatted for CSV storage.
     */
    public static String[] getHeaders() {
        return new String[]{"Column One", "Column Two"};
    }

    /**
     * Returns an array of Strings representing the fields of this CsvTestClass object formatted as a CSV string.
     * The string array is generated in the following order: column 1, column 2.
     * @return A String[] representing the fields of this Person formatted as a CSV string
     */
    public String[] toCsvString() {
        ArrayList<String> result = new ArrayList<>();
        result.add(this.getColumnOne());
        result.add(this.getColumnTwo());
        String[] csvString = new String[2];
        return result.toArray(csvString);
    }

    /**
     * Generates a CSV (Comma-Separated Values) string representation of data using headers and an array of objects.
     *
     * @return A string containing the CSV representation of data, with headers and values separated by commas.
     */
    public static String csvStringRepresentation() {
        StringBuilder builder = new StringBuilder();
        builder.append(convertToString(getHeaders()));
        for (CsvTestClass i:TEST_ARRAY) {
            builder.append(convertToString(i.toCsvString()));
        }
        return builder.toString();
    }

    /**
     * Converts an array of strings into a single string with comma-separated values.
     *
     * @param arr The array of strings to be converted.
     * @return A string containing the elements of the input array, enclosed in double quotes and separated by commas.
     */
    public static String convertToString(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append("\"").append(arr[i]).append("\"");
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CsvTestClass)) {
            return false;
        }
        return columnOne.equals(((CsvTestClass) obj).getColumnOne())
                && columnTwo.equals(((CsvTestClass) obj).getColumnTwo());
    }
}
