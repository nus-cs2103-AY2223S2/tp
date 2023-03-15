package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_IMAGE;
import static seedu.address.model.util.ImageUtil.importImage;

import java.io.IOException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddImageCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Image;



/**
 * Parses input arguments and creates a new {@code AddImageCommand} object
 */
public class AddImageCommandParser implements Parser<AddImageCommand> {

    /**
     * Parses given {@code String} of arguments in the context of the {@code AddImageCommand}.
     * @param args yser input
     * @return {@code AddImageCommand} object for execution
     * @throws ParseException if the user input does not conform to expected format
     */
    public AddImageCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ADD_IMAGE);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddImageCommand.MESSAGE_USAGE), ive);
        }

        String path = argMultimap.getValue(PREFIX_ADD_IMAGE).orElse("");

        try {
            String fileName = importImage(path);
            Image image = new Image(fileName);
            return new AddImageCommand(index, image);
        } catch (IOException io) {
            throw new ParseException("Upload image failed.");
        } catch (ParseException pe) {
            throw pe;
        }

    }

}
