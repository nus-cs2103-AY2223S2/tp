package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.commands.AddTagToPersonCommand.AddTagDescriptor;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.HashSet;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.AddTagToPersonCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddTagToPersonCommand object
 */
public class AddTagToPersonCommandParser implements Parser<AddTagToPersonCommand> {
    @Override
    public AddTagToPersonCommand parse(String userInput) throws ParseException {
        var argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_REMARK, PREFIX_MODULE,
            PREFIX_CCA, PREFIX_MAJOR);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE), pe);
        }

        var addTagDescriptor = new AddTagDescriptor(
                ParserUtil.parseRemarksOptional(argMultimap.getAllValues(PREFIX_REMARK)).orElseGet(HashSet::new),
                ParserUtil.parseModulesOptional(argMultimap.getAllValues(PREFIX_MODULE)).orElseGet(HashSet::new),
                ParserUtil.parseCcasOptional(argMultimap.getAllValues(PREFIX_CCA)).orElseGet(HashSet::new),
                ParserUtil.parseMajorsOptional(argMultimap.getAllValues(PREFIX_MAJOR)).orElseGet(HashSet::new)
            );

        if (addTagDescriptor.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        }

        return new AddTagToPersonCommand(index, addTagDescriptor);
    }
}
