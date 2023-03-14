package taa.commons.util;

import org.apache.commons.csv.CSVFormat;

public class CsvUtil {
    public final static String KW_NAME = "name";
    public final static String KW_TAGS = "tags";
    public final static CSVFormat STU_FMT = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader(KW_NAME, KW_TAGS).build();
}
