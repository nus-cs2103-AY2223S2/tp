package seedu.address.model.documents;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ResumeLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ResumeLink(null));
    }

    @Test
    public void constructor_invalidResumeLink_throwsIllegalArgumentException() {
        String invalidResumeLink = "";
        assertThrows(IllegalArgumentException.class, () -> new ResumeLink(invalidResumeLink));
    }

    @Test
    public void isValidResumeLink() {
        // null resume link
        assertThrows(NullPointerException.class, () -> ResumeLink.isValidResumeLink(null));

        // blank resume link
        assertFalse(ResumeLink.isValidResumeLink("")); // empty string
        assertFalse(ResumeLink.isValidResumeLink(" ")); // spaces only

        // missing resume link
        assertFalse(ResumeLink.isValidResumeLink("example.com/resume")); // missing protocol
        assertFalse(ResumeLink.isValidResumeLink("/resume")); // missing protocol and path
        assertFalse(ResumeLink.isValidResumeLink("ftp://example")); // wrong protocol

        // invalid parts
        assertFalse(ResumeLink.isValidResumeLink("https://-/resume")); // invalid domain name
        assertFalse(ResumeLink.isValidResumeLink("https://exam_ple.com/resume")); // underscore in domain name
        assertFalse(ResumeLink.isValidResumeLink("https://exam ple.com/resume")); // spaces in domain name
        assertFalse(ResumeLink.isValidResumeLink("https:///example.com/resume")); // triple '/' after protocol
        // domain name starts with a period
        assertFalse(ResumeLink.isValidResumeLink("https://.example.com/resume"));
        assertFalse(ResumeLink.isValidResumeLink("https://example.com./resume")); // domain name ends with a period
        // domain name starts with a hyphen
        assertFalse(ResumeLink.isValidResumeLink("https://-example.com/resume"));
        assertFalse(ResumeLink.isValidResumeLink("https://example.com-/resume")); // domain name ends with a hyphen
        // top level domain has less than two chars
        assertFalse(ResumeLink.isValidResumeLink("https://example.c/resume"));

        // valid resume link
        assertTrue(ResumeLink.isValidResumeLink("https://example.com/resume_1")); // underscore in path
        assertTrue(ResumeLink.isValidResumeLink("https://example.com/resume.1")); // period in path
        assertTrue(ResumeLink.isValidResumeLink("https://example.com/resume+1")); // '+' symbol in path
        assertTrue(ResumeLink.isValidResumeLink("https://example.com/resume-1")); // hyphen in path
        assertTrue(ResumeLink.isValidResumeLink("https://example-1.com/resume-1")); // hyphen in domain name
        assertTrue(ResumeLink.isValidResumeLink("http://example.com/resume")); // alphabets only
        assertTrue(ResumeLink.isValidResumeLink("http://12.34.56.78/9")); // IP address and path
        // mixture of alphanumeric and special characters
        assertTrue(ResumeLink.isValidResumeLink("http://example1.com/a1+be."));
        // long domain name
        assertTrue(ResumeLink.isValidResumeLink("https://very-very-very-long-example.com/resume_new"));
        assertTrue(ResumeLink.isValidResumeLink("https://example.com/if.you.dream.it_you.can.do.it")); // long path
        assertTrue(ResumeLink.isValidResumeLink("https://u.nus.edu/resume")); // more than one period in domain
    }
}
