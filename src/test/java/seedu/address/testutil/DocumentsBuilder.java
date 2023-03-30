package seedu.address.testutil;

import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;

/**
 * A utility class to help with building Documents objects.
 */
public class DocumentsBuilder {

    public static final String DEFAULT_RESUME_LINK = "https://example.com/resume";
    public static final String DEFAULT_COVER_LETTER_LINK = "https://example.com/coverletter";
    private ResumeLink resumeLink;
    private CoverLetterLink coverLetterLink;
    private Documents documents;

    /**
     * Creates a {@code DocumentsBuilder} with the default details.
     */
    public DocumentsBuilder() {
        resumeLink = new ResumeLink(DEFAULT_RESUME_LINK);
        coverLetterLink = new CoverLetterLink(DEFAULT_COVER_LETTER_LINK);
    }

    /**
     * Initializes the DocumentsBuilder with the data of {@code documentsToCopy}.
     */
    public DocumentsBuilder(Documents documentsToCopy) {
        resumeLink = documentsToCopy.getResumeLink();
        coverLetterLink = documentsToCopy.getCoverLetterLink();
    }

    /**
     * Sets the {@code ResumeLink} of the {@code Documents} that we are building.
     */
    public DocumentsBuilder withResumeLink(String resumeLink) {
        this.resumeLink = new ResumeLink(resumeLink);
        return this;
    }

    /**
     * Sets the {@code CoverLetterLink} of the {@code Documents} that we are building.
     */
    public DocumentsBuilder withCoverLetterLink(String coverLetterLink) {
        this.coverLetterLink = new CoverLetterLink(coverLetterLink);
        return this;
    }

    public Documents build() {
        return new Documents(resumeLink, coverLetterLink);
    }
}
