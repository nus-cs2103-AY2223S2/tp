package seedu.internship.logic.parser;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.DeleteCommand;
import seedu.internship.logic.commands.FindCommand.FilterInternshipDescriptor;
import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.parser.CliSyntax.*;

public class FindCommandParser implements Parser<FindCommand> {


    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_STATUS);


        FilterInternshipDescriptor filterInternshipDescriptor = new FilterInternshipDescriptor();
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            filterInternshipDescriptor.setPosition(ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            filterInternshipDescriptor.setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            filterInternshipDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (!filterInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(FindCommand.MESSAGE_NOT_FILTERED);
        }
        return new FindCommand(filterInternshipDescriptor);
    }
}
