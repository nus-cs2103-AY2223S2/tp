package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.ModuleTag;

/**
 * Parses input arguments and creates a new UntagCommand object
 */
public class UntagCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the UntagCommand
     * and returns an UntagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UntagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultiMap = ArgumentTokenizer.tokenize(args, Prefix.MODULE_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE), pe);
        }

        Optional<Set<ModuleTag>> modulesToRemove =
                ParserUtil.parseModuleTagsForCommands(argumentMultiMap.getAllValues(Prefix.MODULE_TAG));

        if (modulesToRemove.isEmpty()) {
            throw new ParseException(UntagCommand.MESSAGE_NO_TAGS);
        }

        return new UntagCommand(index, modulesToRemove.get());
    }

}
