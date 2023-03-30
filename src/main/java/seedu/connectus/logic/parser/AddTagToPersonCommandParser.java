package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.commands.AddTagToPersonCommand.AddTagDescriptor;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA_POSITION;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.AddTagToPersonCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.CcaPosition;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * Parses input arguments and creates a new AddTagToPersonCommand object
 */
public class AddTagToPersonCommandParser implements Parser<AddTagToPersonCommand> {
    @Override
    public AddTagToPersonCommand parse(String userInput) throws ParseException {
        var argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_REMARK, PREFIX_MODULE,
            PREFIX_CCA, PREFIX_CCA_POSITION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE), pe);
        }

        var addTagDescriptor = new AddTagDescriptor(
            Optional.ofNullable(argMultimap.getAllValues(PREFIX_REMARK)).map(l -> l.stream().filter(s -> !s.isBlank())
                    .map(Remark::new).collect(Collectors.toSet())).orElse(new HashSet<>()),
            Optional.ofNullable(argMultimap.getAllValues(PREFIX_MODULE)).map(l -> l.stream().filter(s -> !s.isBlank())
                .map(Module::new).collect(Collectors.toSet())).orElse(new HashSet<>()),
            Optional.ofNullable(argMultimap.getAllValues(PREFIX_CCA)).map(l -> l.stream().filter(s -> !s.isBlank())
                .map(Cca::new).collect(Collectors.toSet())).orElse(new HashSet<>()),
            Optional.ofNullable(argMultimap.getAllValues(PREFIX_CCA_POSITION))
                .map(l -> l.stream().filter(s -> !s.isBlank())
                .map(CcaPosition::new).collect(Collectors.toSet())).orElse(new HashSet<>()));

        if (addTagDescriptor.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        }

        return new AddTagToPersonCommand(index, addTagDescriptor);
    }
}
