package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.entity.Character.CharacterBuilder;
import static seedu.address.model.entity.Item.ItemBuilder;
import static seedu.address.model.entity.Mob.MobBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.MakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class MakeCommandParser implements Parser<MakeCommand> {
    // Arguments should have the format: CLASSIFICATION NAME
    // Example: char John Cena
    private static final Pattern COMMAND_FORMAT = Pattern.compile(
            "^(?<classification>\\w+)\\s+(?<name>.+)$"
    );
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MakeCommand parse(String args) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT.matcher(args.trim());

        // Arguments have the wrong format
        boolean hasInvalidFormat = !matcher.matches();
        if (hasInvalidFormat) {
            String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeCommand.MESSAGE_USAGE);
            throw new ParseException(errorMessage);
        }

        final String classificationString = matcher.group("classification");
        final String nameString = matcher.group("name");

        Name name = ParserUtil.parseName(nameString);
        Classification classification = ParserUtil.parseClassification(classificationString);
        assert name != null;
        assert classification != null;

        Entity newEntity = null;
        if (classification.isCharacter()) {
            newEntity = new CharacterBuilder(name).build();
        } else if (classification.isItem()) {
            newEntity = new ItemBuilder(name).build();
        } else if (classification.isMob()) {
            newEntity = new MobBuilder(name).build();
        }
        requireNonNull(newEntity);
        return new MakeCommand(newEntity);
    }
}
