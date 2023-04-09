package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Pattern;

import seedu.address.logic.commands.MakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class MakeCommandParser implements Parser<MakeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MakeCommand parse(String args) throws ParseException {
        boolean isValidCommand = Pattern.matches("^(char|item|mob|c|i|m)(\\s+[\\w]+)+$", args.trim());
        if (!isValidCommand) {
            // Check what kind of invalid command it is
            boolean hasRightFields = Pattern.matches(".*((\\s+[\\w]+)+)$", args.trim());
            if (!hasRightFields) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeCommand.MESSAGE_USAGE));
            }
        }
        String[] split = args.trim().split("\\s+", 2);
        Name name = ParserUtil.parseName(split[1]);
        Classification classification = ParserUtil.parseClassification(split[0]);

        Entity newEntity = null;
        if (classification.isCharacter()) {
            newEntity = new Character.CharacterBuilder(name).build();
        } else if (classification.isItem()) {
            newEntity = new Item(name);
        } else if (classification.isMob()) {
            newEntity = new Mob(name);
        }
        requireNonNull(newEntity);
        return new MakeCommand(newEntity);
    }
}
