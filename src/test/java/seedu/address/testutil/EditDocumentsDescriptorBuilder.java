package seedu.address.testutil;

import seedu.address.logic.commands.documents.EditDocumentsCommand;
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;

/**
 * A utility class to help with building EditDocumentsDescriptor objects.
 */
public class EditDocumentsDescriptorBuilder {
    private EditDocumentsCommand.EditDocumentsDescriptor descriptor;

    public EditDocumentsDescriptorBuilder() {
        descriptor = new EditDocumentsCommand.EditDocumentsDescriptor();
    }

    public EditDocumentsDescriptorBuilder(EditDocumentsCommand.EditDocumentsDescriptor descriptor) {
        this.descriptor = new EditDocumentsCommand.EditDocumentsDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDocumentsDescriptor} with fields containing {@code documents}'s details
     */
    public EditDocumentsDescriptorBuilder(Documents documents) {
        descriptor = new EditDocumentsCommand.EditDocumentsDescriptor();
        descriptor.setResumeLink(documents.getResumeLink());
        descriptor.setCoverLetterLink(documents.getCoverLetterLink());
    }

    /**
     * Sets the {@code ResumeLink} of the {@code EditDocumentsDescriptor} that we are building.
     */
    public EditDocumentsDescriptorBuilder withResumeLink(String resumeLink) {
        descriptor.setResumeLink(new ResumeLink(resumeLink));
        return this;
    }

    /**
     * Sets the {@code CoverLetterLink} of the {@code EditDocumentsDescriptor} that we are building.
     */
    public EditDocumentsDescriptorBuilder withCoverLetterLink(String coverLetterLink) {
        descriptor.setCoverLetterLink(new CoverLetterLink(coverLetterLink));
        return this;
    }

    public EditDocumentsCommand.EditDocumentsDescriptor build() {
        return descriptor;
    }
}
