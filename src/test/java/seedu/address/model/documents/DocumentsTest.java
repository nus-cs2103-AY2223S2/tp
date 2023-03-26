package seedu.address.model.documents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVER_LETTER_LINK_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_META;
import static seedu.address.testutil.TypicalDocuments.DOCUMENTS_BANK_OF_AMERICA;
import static seedu.address.testutil.TypicalDocuments.DOCUMENTS_META;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DocumentsBuilder;

public class DocumentsTest {

    @Test
    public void equals() {
        // same values -> returns true
        Documents bankOfAmericaCopy = new DocumentsBuilder(DOCUMENTS_BANK_OF_AMERICA).build();
        assertEquals(DOCUMENTS_BANK_OF_AMERICA, bankOfAmericaCopy);

        // same object -> returns true
        assertEquals(DOCUMENTS_BANK_OF_AMERICA, DOCUMENTS_BANK_OF_AMERICA);

        // null -> returns false
        assertNotEquals(null, DOCUMENTS_BANK_OF_AMERICA);

        // different type -> returns false
        assertNotEquals(5, DOCUMENTS_BANK_OF_AMERICA);

        // different documents -> returns false
        assertNotEquals(DOCUMENTS_BANK_OF_AMERICA, DOCUMENTS_META);

        // different resume link -> returns false
        Documents editedDocuments = new DocumentsBuilder(DOCUMENTS_BANK_OF_AMERICA)
                .withResumeLink(VALID_RESUME_LINK_META).build();
        assertNotEquals(DOCUMENTS_BANK_OF_AMERICA, editedDocuments);

        // different cover letter link -> returns false
        editedDocuments = new DocumentsBuilder(DOCUMENTS_BANK_OF_AMERICA)
                .withCoverLetterLink(VALID_COVER_LETTER_LINK_META).build();
        assertNotEquals(DOCUMENTS_BANK_OF_AMERICA, editedDocuments);
    }
}
