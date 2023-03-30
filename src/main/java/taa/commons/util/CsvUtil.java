package taa.commons.util;

import org.apache.commons.csv.CSVFormat;

/**
 * Specifies the CSV file format used by this program.
 */
public class CsvUtil {
    public static final String FILE_PREFIX = ".csv";
    public static final String KW_NAME = "name";
    public static final String KW_ATTENDANCE = "attendance";
    public static final String KW_PP = "pp";
    public static final String KW_TAGS = "tags";
    public static final String KW_SUBMISSIONS = "submissions";
    private static final CSVFormat.Builder FMT = CSVFormat.Builder.create(CSVFormat.DEFAULT)
            .setHeader(KW_NAME, KW_ATTENDANCE, KW_PP, KW_SUBMISSIONS, KW_TAGS);
    public static final CSVFormat IN_FMT = FMT.build();
    public static final CSVFormat OUT_FMT = FMT.setSkipHeaderRecord(true).build();
}
