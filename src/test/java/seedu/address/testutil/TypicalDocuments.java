package seedu.address.testutil;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.documents.Documents;

/**
 * A utility class containing a list of {@code Documents} objects to be used in tests.
 */
public class TypicalDocuments {

    public static final Documents DOCUMENTS_GOOGLE = new DocumentsBuilder()
            .withResumeLink(CommandTestUtil.VALID_RESUME_LINK_GOOGLE)
            .withCoverLetterLink(CommandTestUtil.VALID_COVER_LETTER_LINK_GOOGLE)
            .build();

    public static final Documents DOCUMENTS_NETFLIX = new DocumentsBuilder()
            .withResumeLink(CommandTestUtil.VALID_RESUME_LINK_NETFLIX)
            .withCoverLetterLink(CommandTestUtil.VALID_COVER_LETTER_LINK_NETFLIX)
            .build();
    private TypicalDocuments() {} // prevents instantiation
}
