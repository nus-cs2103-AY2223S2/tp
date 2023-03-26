package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.DeleteTagFromPersonCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;

/**
 * Deletes a tag from a person identified using their displayed indexes from ConnectUS.
 */
public class DeleteTagFromPersonCommandParser implements Parser<DeleteTagFromPersonCommand> {
    @Override
    public DeleteTagFromPersonCommand parse(String userInput) throws ParseException {
        var argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TAG, PREFIX_MODULE);

        Index personIndex;
        Index tagIndex;
        Index moduleIndex;

        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            tagIndex = argMultimap.getValue(PREFIX_TAG).isPresent()
                ? ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TAG).get())
                : null;
            moduleIndex = argMultimap.getValue(PREFIX_MODULE).isPresent()
                ? ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MODULE).get())
                : null;

            if (tagIndex == null && moduleIndex == null) {
                throw new ParseException("");
            }
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteTagFromPersonCommand(personIndex, tagIndex, moduleIndex);
    }
}
