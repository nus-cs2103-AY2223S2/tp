package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;

import seedu.address.logic.commands.AddPairCommand;
import seedu.address.model.pair.Pair;

/**
 * A utility class for Person.
 */
public class PairUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddPairCommand(Pair pair) {
        return AddPairCommand.COMMAND_WORD + " " + getPairDetails(pair);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPairDetails(Pair pair) {
        String sb = PREFIX_NRIC_ELDERLY + pair.getElderly().getName().fullName + " "
                + PREFIX_NRIC_VOLUNTEER + pair.getVolunteer().getName().fullName + " ";
        return sb;
    }
}
