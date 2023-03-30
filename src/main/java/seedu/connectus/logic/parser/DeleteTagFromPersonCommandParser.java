package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA_POSITION;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.DeleteTagFromPersonCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;

/**
 * Deletes a tag from a person identified using their displayed indexes from ConnectUS.
 */
public class DeleteTagFromPersonCommandParser implements Parser<DeleteTagFromPersonCommand> {
    @Override
    public DeleteTagFromPersonCommand parse(String userInput) throws ParseException {
        var argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_REMARK, PREFIX_MODULE,
            PREFIX_CCA, PREFIX_CCA_POSITION);

        Index personIndex;
        Index remarkIndex;
        Index moduleIndex;
        Index ccaIndex;
        Index ccaPositionIndex;

        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            remarkIndex = argMultimap.getValue(PREFIX_REMARK).isPresent()
                ? ParserUtil.parseIndex(argMultimap.getValue(PREFIX_REMARK).get())
                : null;
            moduleIndex = argMultimap.getValue(PREFIX_MODULE).isPresent()
                ? ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MODULE).get())
                : null;
            ccaIndex = argMultimap.getValue(PREFIX_CCA).isPresent()
                ? ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CCA).get())
                : null;
            ccaPositionIndex = argMultimap.getValue(PREFIX_CCA_POSITION).isPresent()
                ? ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CCA_POSITION).get())
                : null;

            if (!isAnyNonNull(remarkIndex, moduleIndex, ccaIndex, ccaPositionIndex)) {
                throw new ParseException("");
            }
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteTagFromPersonCommand(personIndex, moduleIndex, ccaIndex, ccaPositionIndex, remarkIndex);
    }
}
