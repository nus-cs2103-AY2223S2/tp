package seedu.address.logic.parser.navigation;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.Optional;

import seedu.address.logic.commands.navigation.DirectNavCommand;
import seedu.address.logic.commands.navigation.NavCommand;
import seedu.address.logic.commands.navigation.RelativeNavCommand;
import seedu.address.logic.commands.navigation.RootNavCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new NavCommand object
 */
public class NavCommandParser implements Parser<NavCommand> {

    @Override
    public NavCommand parse(String args) throws ParseException {
        // Navigate to root.
        if (args.isEmpty()) {
            return new RootNavCommand();
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_LECTURE);
        Optional<ModuleCode> moduleCode;
        Optional<LectureName> lectureName;
        try {
            moduleCode = argMultimap.getValue(PREFIX_MODULE).map(ModuleCode::new);
            lectureName = argMultimap.getValue(PREFIX_LECTURE).map(LectureName::new);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

        String target = argMultimap.getPreamble();

        boolean isModCodeOrLecNamePresent = (moduleCode.isPresent() || lectureName.isPresent());
        boolean bothDirectAndRelativeParamsFound = !target.isEmpty() && isModCodeOrLecNamePresent;

        if (bothDirectAndRelativeParamsFound) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NavCommand.MESSAGE_USAGE));
        }

        return target.isEmpty() ? new DirectNavCommand(moduleCode, lectureName)
                : new RelativeNavCommand(target);
    }
}
