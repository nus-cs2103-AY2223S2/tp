package seedu.address.model.documents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVER_LETTER_LINK_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_NETFLIX;
import static seedu.address.testutil.TypicalDocuments.DOCUMENTS_GOOGLE;
import static seedu.address.testutil.TypicalDocuments.DOCUMENTS_NETFLIX;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DocumentsBuilder;

public class DocumentsTest {

    @Test
    public void equals() {
        // same values -> returns true
        Documents googleCopy = new DocumentsBuilder(DOCUMENTS_GOOGLE).build();
        assertEquals(DOCUMENTS_GOOGLE, googleCopy);

        // same object -> returns true
        assertEquals(DOCUMENTS_GOOGLE, DOCUMENTS_GOOGLE);

        // null -> returns false
        assertNotEquals(null, DOCUMENTS_GOOGLE);

        // different type -> returns false
        assertNotEquals(5, DOCUMENTS_GOOGLE);

        // different documents -> returns false
        assertNotEquals(DOCUMENTS_GOOGLE, DOCUMENTS_NETFLIX);

        // different resume link -> returns false
        Documents editedDocuments = new DocumentsBuilder(DOCUMENTS_GOOGLE)
                .withResumeLink(VALID_RESUME_LINK_NETFLIX).build();
        assertNotEquals(DOCUMENTS_GOOGLE, editedDocuments);

        // different cover letter link -> returns false
        editedDocuments = new DocumentsBuilder(DOCUMENTS_GOOGLE)
                .withCoverLetterLink(VALID_COVER_LETTER_LINK_NETFLIX).build();
        assertNotEquals(DOCUMENTS_GOOGLE, editedDocuments);
    }
}
