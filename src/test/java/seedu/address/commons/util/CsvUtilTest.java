package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;

/**
 * Tests Csv Read and Write.
 */
public class CsvUtilTest {

    private static final String VALID_HEADERS = "Name,Phone,Email,Address,Income,Tags";

    private static final String SAMPLE_CSV_DATA = "Name,Phone,Email,Address,Income,Tags\n"
            + "Alice Pauline,94351253,alice@example.com,\"123, Jurong West Ave 6, #08-111\",1000,friends\n"
            + "Benson Meier,98765432,johnd@example.com,\"311, Clementi Ave 2, #02-25\",1000,owesMoney,friends";

    @Test
    public void test_toCsvField() {
        // Normal String
        assertEquals(CsvUtil.toCsvField("hello world"), "hello world");

        // Contains '"'
        assertEquals(CsvUtil.toCsvField("hello \"world\""), "hello \"world\"");

        // Contains ','
        assertEquals(CsvUtil.toCsvField("hello, world"), "\"hello, world\"");

        // Contains ',' and '"'
        assertEquals(CsvUtil.toCsvField("hello, \"world\""), "\"hello, \"\"world\"\"\"");
        assertEquals(CsvUtil.toCsvField("hello, \"to, the\" world"), "\"hello, \"\"to, the\"\" world\"");
    }

    @Test
    public void test_validHeaders_valid() throws DataConversionException {
        // Normal headers
        String headers = VALID_HEADERS;

        assertEquals(true, CsvUtil.checkValidHeaders(headers, VALID_HEADERS));

        // Normal headers with whitespace
        String whitespaceHeaders = "Name,   Phone ,  Email,Address    ,Income,  Tags  ";

        assertEquals(true, CsvUtil.checkValidHeaders(whitespaceHeaders, VALID_HEADERS));
    }

    @Test
    public void test_validHeaders_invalid() throws DataConversionException {
        // Wrong headers
        String wrongHeaders = "Name,Phoney,Email,Address,Income,Tags";

        assertThrows(DataConversionException.class, () -> CsvUtil.checkValidHeaders(wrongHeaders, VALID_HEADERS));

        // Too many headers
        String tooManyHeaders = "Name,Phone,Email,Address,Income,Tags,AddedHeader";

        assertThrows(DataConversionException.class, () -> CsvUtil.checkValidHeaders(tooManyHeaders, VALID_HEADERS));

        // Too few headers
        String tooFewHeaders = "Name,Phone,Address,Income,Tags";

        assertThrows(DataConversionException.class, () -> CsvUtil.checkValidHeaders(tooFewHeaders, VALID_HEADERS));
    }

    @Test
    public void test_tokeniseCsv() throws DataConversionException {
        ArrayList<String> tokensAlice = new ArrayList<>();
        tokensAlice.add("Alice Pauline");
        tokensAlice.add("94351253");
        tokensAlice.add("alice@example.com");
        tokensAlice.add("123, Jurong West Ave 6, #08-111");
        tokensAlice.add("1000");
        tokensAlice.add("friends");

        ArrayList<String> tokensBenson = new ArrayList<>();
        tokensBenson.add("Benson Meier");
        tokensBenson.add("98765432");
        tokensBenson.add("johnd@example.com");
        tokensBenson.add("311, Clementi Ave 2, #02-25");
        tokensBenson.add("1000");
        tokensBenson.add("owesMoney");
        tokensBenson.add("friends");

        ArrayList<ArrayList<String>> tokens = new ArrayList<>();
        tokens.add(tokensAlice);
        tokens.add(tokensBenson);

        assertEquals(tokens, CsvUtil.tokenizeCsv(SAMPLE_CSV_DATA, VALID_HEADERS));
    }
}
