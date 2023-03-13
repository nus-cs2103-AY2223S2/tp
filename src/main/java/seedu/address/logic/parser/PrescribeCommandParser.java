package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PrescribeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Medication;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;

public class PrescribeCommandParser implements Parser<PrescribeCommand> {

    public PrescribeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEDICATION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrescribeCommand.MESSAGE_USAGE));
        }

        Medication medication = new Medication(argMultimap.getValue(PREFIX_MEDICATION).orElse(""));

        return new PrescribeCommand(index, medication);
    }
}
