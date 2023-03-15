package seedu.internship.testutil;

import static seedu.internship.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;



import seedu.internship.logic.commands.AddCommand;
import seedu.internship.model.internship.Internship;



public class InternshipUtil {
    /**
     * Returns an add command string for adding the {@code internship}.
     */
    public static String getAddCommand(Internship internship) {
        return AddCommand.COMMAND_WORD + " " + getInternshipDetails(internship);
    }

    /**
     * Returns the part of command string for the given {@code internship}'s details.
     */
    public static String getInternshipDetails(Internship internship) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_POSITION + internship.getPosition().positionName + " ");
        sb.append(PREFIX_COMPANY + internship.getCompany().companyName + " ");
        sb.append(PREFIX_STATUS + String.valueOf(internship.getStatus().statusId) + " ");
        sb.append(PREFIX_DESCRIPTION + internship.getDescription().descriptionMessage + " ");
        internship.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
    // Can only be used after EditInternship has been created
//    /**
//     * Returns the part of command string for the given {@code EditInternshipDescriptor}'s details.
//     */
//    public static String getEditInternshipDescriptorDetails(EditCommand.EditInternshipDescriptor descriptor) {
//        StringBuilder sb = new StringBuilder();
//        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_POSITION).append(position.positionName).append(" "));
//        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.companyName).append(" "));
//        descriptor.geId().ifPresent(id -> sb.append(PREFIX_ID).append(id.id).append(" "));
//        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.statusId).append(" "));
//        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION).append(description.descriptionMessage).append(" "));
//        if (descriptor.getTags().isPresent()) {
//            Set<Tag> tags = descriptor.getTags().get();
//            if (tags.isEmpty()) {
//                sb.append(PREFIX_TAG);
//            } else {
//                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
//            }
//        }
//        return sb.toString();
//    }
}
