
package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


import tfifteenfour.clipboard.logic.commands.ModuleCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.student.StudentTakingModulePredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ModuleCommandParser implements Parser<ModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ModuleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleCommand.MESSAGE_USAGE));
        }

        String moduleKeywords = trimmedArgs;
        return new ModuleCommand(new StudentTakingModulePredicate(moduleKeywords));
    }
}