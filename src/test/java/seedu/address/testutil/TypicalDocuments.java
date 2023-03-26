package seedu.address.testutil;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.documents.Documents;

/**
 * A utility class containing a list of {@code Documents} objects to be used in tests.
 */
public class TypicalDocuments {

    public static final Documents DOCUMENTS_BANK_OF_AMERICA = new DocumentsBuilder()
            .withResumeLink(CommandTestUtil.VALID_RESUME_LINK_BANK_OF_AMERICA)
            .withCoverLetterLink(CommandTestUtil.VALID_COVER_LETTER_LINK_BANK_OF_AMERICA)
            .build();

    public static final Documents DOCUMENTS_META = new DocumentsBuilder()
            .withResumeLink(CommandTestUtil.VALID_RESUME_LINK_META)
            .withCoverLetterLink(CommandTestUtil.VALID_COVER_LETTER_LINK_META)
            .build();
    private TypicalDocuments() {} // prevents instantiation
}
