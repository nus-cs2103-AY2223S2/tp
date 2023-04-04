package seedu.address.logic.parser.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EITHER_INDEX_OR_ID;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_DELIVERED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.jobs.EditDeliveryJobCommand;
import seedu.address.logic.commands.jobs.EditDeliveryJobCommand.EditDeliveryJobDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditDeliveryJobCommandParser implements Parser<EditDeliveryJobCommand> {

    private static final String MESSAGE_DATE_FORMAT = "Invalid date format!\nDate/ <blank> | YYYY-MM-DD";
    private static final String MESSAGE_SLOT_FORMAT = "Invalid slot format!\nSlot/ <blank> | 1..5";
    private static final String MESSAGE_EARN_FORMAT = "Invalid earning format!\nearn/ d.f ";
    private static final String MESSAGE_EMPTY_SENDER_ID = "Sender id missing!\nsi/ id ";
    private static final String MESSAGE_EMPTY_JOB_ID = "Job id missing!\nji/ id ";
    private static final String MESSAGE_EMPTY_RECIPIENT_ID = "Recipient id missing!\nri/ id ";
    private static final String MESSAGE_EMPTY_STATUS = "Invalid status!\nDone/ t | f ";

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDeliveryJobCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_ID, PREFIX_SENDER_ID,
                PREFIX_RECIPIENT_ID,
                PREFIX_DELIVERY_DATE, PREFIX_DELIVERY_SLOT, PREFIX_EARNING, PREFIX_IS_DELIVERED);

        Optional<Index> index = Optional.empty();

        try {
            index = Optional.of(ParserUtil.parseIndex(argMultimap.getPreamble()));
        } catch (ParseException pe) {
            // try if index exist
        }

        if (argMultimap.getValue(PREFIX_JOB_ID).isPresent() && index.isPresent()) {
            throw new ParseException(
                String.format(MESSAGE_EITHER_INDEX_OR_ID, EditDeliveryJobCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_JOB_ID).isEmpty() && index.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDeliveryJobCommand.MESSAGE_USAGE));
        }

        EditDeliveryJobDescriptor editDeliveryJobDescriptor = new EditDeliveryJobDescriptor();
        argMultimap.getValue(PREFIX_JOB_ID).ifPresent(val -> editDeliveryJobDescriptor.setJobId(val));

        if (argMultimap.getValue(PREFIX_SENDER_ID).isPresent()) {
            String val = argMultimap.getValue(PREFIX_SENDER_ID).get();
            if (val.isBlank()) {
                throw new ParseException(String.format(MESSAGE_EMPTY_SENDER_ID));
            }

            editDeliveryJobDescriptor.setSender(val);
        }

        if (argMultimap.getValue(PREFIX_RECIPIENT_ID).isPresent()) {
            String val = argMultimap.getValue(PREFIX_RECIPIENT_ID).get();
            if (val.isBlank()) {
                throw new ParseException(String.format(MESSAGE_EMPTY_RECIPIENT_ID));
            }

            editDeliveryJobDescriptor.setRecipient(val);
        }

        if (argMultimap.getValue(PREFIX_DELIVERY_DATE).isPresent()) {
            try {
                String val = argMultimap.getValue(PREFIX_DELIVERY_DATE).get();
                DeliveryDate toEditDate;
                if (val.isBlank()) {
                    toEditDate = DeliveryDate.placeholder();
                } else {
                    toEditDate = new DeliveryDate(val);
                }
                editDeliveryJobDescriptor.setDeliveryDate(toEditDate);
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_DATE_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_DELIVERY_SLOT).isPresent()) {
            try {
                String val = argMultimap.getValue(PREFIX_DELIVERY_SLOT).get();
                DeliverySlot toEditSlot;
                if (val.isBlank()) {
                    toEditSlot = DeliverySlot.placeholder();
                } else {
                    toEditSlot = new DeliverySlot(val);
                }
                editDeliveryJobDescriptor.setDeliverySlot(toEditSlot);
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_SLOT_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_EARNING).isPresent()) {
            try {
                String val = argMultimap.getValue(PREFIX_EARNING).get();
                editDeliveryJobDescriptor.setEarning(new Earning(val));
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_EARN_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_IS_DELIVERED).isPresent()) {
            String val = argMultimap.getValue(PREFIX_IS_DELIVERED).get().toLowerCase();
            boolean isDone = false;
            if (val.isBlank() || (!val.equals("t") && !val.equals("f"))) {
                throw new ParseException(String.format(MESSAGE_EMPTY_STATUS));
            }
            if (val.startsWith("t")) {
                isDone = true;
            }
            editDeliveryJobDescriptor.setDelivered(isDone);
        }

        if (!editDeliveryJobDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditDeliveryJobCommand.MESSAGE_NOT_EDITED);
        }

        if (index.isPresent()) {
            return new EditDeliveryJobCommand(index.get(), editDeliveryJobDescriptor);
        } else {
            return new EditDeliveryJobCommand(editDeliveryJobDescriptor);
        }
    }
}
