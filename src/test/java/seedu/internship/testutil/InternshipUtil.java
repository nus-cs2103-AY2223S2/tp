package seedu.internship.testutil;

import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.model.internship.Internship;


/**
 * Util commands required for internship tests.
 */
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
}

