package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COVER_LETTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;

import seedu.address.logic.commands.documents.EditDocumentsCommand;
import seedu.address.model.documents.Documents;

/**
 * A utility class for Documents.
 */
public class DocumentsUtil {

    /**
     * Returns an add_docs command string for adding the {@code documents}.
     */
    public static String getAddDocumentsCommandArguments(Documents documents) {
        return getDocumentsDetails(documents);
    }

    /**
     * Returns the part of command string for the given {@code documents}'s details.
     */
    public static String getDocumentsDetails(Documents documents) {
        return PREFIX_RESUME + documents.getResumeLink().value + " "
                + PREFIX_COVER_LETTER + documents.getCoverLetterLink().value;
    }

    /**
     * Returns the part of command string for the given {@code EditDocumentsDescriptor}'s details.
     */
    public static String getEditDocumentsDescriptorDetails(EditDocumentsCommand.EditDocumentsDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getResumeLink().ifPresent(resumeLink ->
                sb.append(PREFIX_RESUME).append(resumeLink.value).append(" "));
        descriptor.getCoverLetterLink().ifPresent(coverLetterLink ->
                sb.append(PREFIX_COVER_LETTER).append(coverLetterLink.value));
        return sb.toString();
    }
}
