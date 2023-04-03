package seedu.address.model.documents;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CoverLetterLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CoverLetterLink(null));
    }

    @Test
    public void constructor_invalidCoverLetterLink_throwsIllegalArgumentException() {
        String invalidCoverLetterLink = "";
        assertThrows(IllegalArgumentException.class, () -> new CoverLetterLink(invalidCoverLetterLink));
    }

    @Test
    public void isValidCoverLetterLink() {
        // null cover letter link
        assertThrows(NullPointerException.class, () -> CoverLetterLink.isValidCoverLetterLink(null));

        // blank cover letter link
        assertFalse(CoverLetterLink.isValidCoverLetterLink("")); // empty string
        assertFalse(CoverLetterLink.isValidCoverLetterLink(" ")); // spaces only

        // missing cover letter link
        assertFalse(CoverLetterLink.isValidCoverLetterLink("example.com/coverletter")); // missing protocol
        assertFalse(CoverLetterLink.isValidCoverLetterLink("/coverletter")); // missing protocol and path
        assertFalse(CoverLetterLink.isValidCoverLetterLink("ftp://example")); // wrong protocol

        // invalid parts
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://-/coverletter")); // invalid domain name
        // underscore in domain name
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://exam_ple.com/coverletter"));
        // spaces in domain name
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://exam ple.com/coverletter"));
        // triple '/' after protocol
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https:///example.com/coverletter"));
        // domain name starts with a period
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://.example.com/coverletter"));
        // domain name ends with a period
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://example.com./coverletter"));
        // domain name starts with a hyphen
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://-example.com/coverletter"));
        // domain name ends with a hyphen
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://example.com-/coverletter"));
        // top level domain has less than two chars
        assertFalse(CoverLetterLink.isValidCoverLetterLink("https://example.c/coverletter"));

        // valid cover letter link
        // underscore in path
        assertTrue(CoverLetterLink.isValidCoverLetterLink("https://example.com/coverletter_1"));
        // period in path
        assertTrue(CoverLetterLink.isValidCoverLetterLink("https://example.com/coverletter.1"));
        // '+' symbol in path
        assertTrue(CoverLetterLink.isValidCoverLetterLink("https://example.com/coverletter+1"));
        // hyphen in path
        assertTrue(CoverLetterLink.isValidCoverLetterLink("https://example.com/coverletter-1"));
        // hyphen in domain name
        assertTrue(CoverLetterLink.isValidCoverLetterLink("https://example-1.com/coverletter-1"));
        assertTrue(CoverLetterLink.isValidCoverLetterLink("http://example.com/coverletter")); // alphabets only
        assertTrue(CoverLetterLink.isValidCoverLetterLink("http://12.34.56.78/9")); // IP address and path
        // mixture of alphanumeric and special characters
        assertTrue(CoverLetterLink.isValidCoverLetterLink("http://example1.com/a1+be."));
        // long domain name
        assertTrue(CoverLetterLink
                .isValidCoverLetterLink("https://very-very-very-long-example.com/coverletter_new"));
        // long path
        assertTrue(CoverLetterLink.isValidCoverLetterLink("https://example.com/if.you.dream.it_you.can.do.it"));
        // more than one period in domain
        assertTrue(CoverLetterLink.isValidCoverLetterLink("https://u.nus.edu/coverletter"));
    }
}
