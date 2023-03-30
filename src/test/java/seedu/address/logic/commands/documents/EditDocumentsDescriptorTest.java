package seedu.address.logic.commands.documents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DOCUMENTS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DOCUMENTS_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVER_LETTER_LINK_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_NETFLIX;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditDocumentsDescriptorBuilder;

public class EditDocumentsDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDocumentsCommand.EditDocumentsDescriptor descriptorWithSameValues =
                new EditDocumentsCommand.EditDocumentsDescriptor(DESC_DOCUMENTS_GOOGLE);
        assertEquals(DESC_DOCUMENTS_GOOGLE, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_DOCUMENTS_GOOGLE, DESC_DOCUMENTS_GOOGLE);

        // null -> returns false
        assertNotEquals(null, DESC_DOCUMENTS_GOOGLE);

        // different types -> returns false
        assertNotEquals(5, DESC_DOCUMENTS_GOOGLE);

        // different values -> returns false
        assertNotEquals(DESC_DOCUMENTS_GOOGLE, DESC_DOCUMENTS_NETFLIX);

        // different resume link -> returns false
        EditDocumentsCommand.EditDocumentsDescriptor editedGoogleDocuments =
                new EditDocumentsDescriptorBuilder(DESC_DOCUMENTS_GOOGLE)
                        .withResumeLink(VALID_RESUME_LINK_NETFLIX).build();
        assertNotEquals(DESC_DOCUMENTS_GOOGLE, editedGoogleDocuments);

        // different cover letter link -> returns false
        editedGoogleDocuments = new EditDocumentsDescriptorBuilder(DESC_DOCUMENTS_GOOGLE)
                .withCoverLetterLink(VALID_COVER_LETTER_LINK_NETFLIX).build();
        assertNotEquals(DESC_DOCUMENTS_GOOGLE, editedGoogleDocuments);
    }
}
