package taa.commons.util;

import org.apache.commons.csv.CSVFormat;

/**
 * Specifies the CSV file format used by this program.
 */
public class CsvUtil {
    public static final String KW_NAME = "name";
    public static final String KW_TAGS = "tags";
    public static final CSVFormat STU_FMT = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader(KW_NAME, KW_TAGS)
            .build();
}
