package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddEntityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddEntityCommandParser implements Parser<AddEntityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEntityCommand parse(String args) throws ParseException {
        boolean isValidCommand = Pattern.matches("^make\\s+(char|item|mob)(\\s+[\\w]+)+$", args.trim());
        if (!isValidCommand) {
            throw new ParseException("Invalid command!");
        }
        String[] split = args.trim().split("\\s+", 3);
        Name name = ParserUtil.parseName(split[2]);
        Classification classification = ParserUtil.parseClassification(split[1]);

        Set<Tag> tagSet = new HashSet<Tag>();
        Entity newEntity = null;
        if (classification.isCharacter()) {
            newEntity = new Character(name, tagSet);
        } else if (classification.isItem()) {
            newEntity = new Item(name, tagSet);
        } else if (classification.isMob()) {
            newEntity = new Mob(name, tagSet);
        }
        requireNonNull(newEntity);
        return new AddEntityCommand(newEntity);
    }
}
