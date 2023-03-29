package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditApplicationCommand;
import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditApplicationCommand object
 */
public class EditApplicationCommandParser implements ApplicationParser<EditApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_COMPANY_EMAIL, PREFIX_STATUS);

        Index index;

        try {
            index = ApplicationParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicationCommand.MESSAGE_USAGE), pe);
        }

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editApplicationDescriptor.setRole(ApplicationParserUtil.parseRole(
                    argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editApplicationDescriptor.setCompanyName(ApplicationParserUtil.parseCompanyName(
                    argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY_EMAIL).isPresent()) {
            editApplicationDescriptor.setCompanyEmail(ApplicationParserUtil.parseCompanyEmail(
                    argMultimap.getValue(PREFIX_COMPANY_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editApplicationDescriptor.setStatus(ApplicationParserUtil.parseStatus(
                    argMultimap.getValue(PREFIX_STATUS).get()));
        }

        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditApplicationCommand.MESSAGE_NOT_EDITED);
        }

        return new EditApplicationCommand(index, editApplicationDescriptor);
    }

}

