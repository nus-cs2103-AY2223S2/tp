package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ELDERLY_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VOLUNTEER_NRIC;

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
        String sb = PREFIX_ELDERLY_NRIC + pair.getElderly().getName().fullName + " "
                + PREFIX_VOLUNTEER_NRIC + pair.getVolunteer().getName().fullName + " ";
        return sb;
    }
}
