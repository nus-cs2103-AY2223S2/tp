package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;

import seedu.address.logic.commands.AddPairCommand;
import seedu.address.model.pair.Pair;

/**
 * A utility class for Pair.
 */
public class PairUtil {

    /**
     * Returns an add command string for adding the {@code pair}.
     */
    public static String getAddPairCommand(Pair pair) {
        return AddPairCommand.COMMAND_WORD + " " + getPairDetails(pair);
    }

    /**
     * Returns the part of command string for the given {@code pair}'s details.
     */
    public static String getPairDetails(Pair pair) {
        String sb = PREFIX_NRIC_ELDERLY + pair.getElderly().getNric().value + " "
                + PREFIX_NRIC_VOLUNTEER + pair.getVolunteer().getNric().value + " ";
        return sb;
    }
}
