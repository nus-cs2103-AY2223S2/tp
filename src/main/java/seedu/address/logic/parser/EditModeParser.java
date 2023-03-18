package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditValueCommand.MESSAGE_INVALID_ENTITY_TYPE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.address.experimental.model.Model;
import seedu.address.logic.commands.BackCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditValueCommand;
import seedu.address.logic.commands.EditValueCommand.EditCharacterDescriptor;
import seedu.address.logic.commands.EditValueCommand.EditEntityDescriptor;
import seedu.address.logic.commands.EditValueCommand.EditItemDescriptor;
import seedu.address.logic.commands.EditValueCommand.EditMobDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Stats;
import seedu.address.model.tag.Tag;

/**
 * Parses user input while in edit mode.
 */
public class EditModeParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<fieldWord>\\S+)(?<arguments>.*)");

    public static final String MESSAGE_INVALID_FIELD = "%s is not a valid editable field!";
    public static final String MESSAGE_INVALID_VALUE_FORMAT = "%s is not a valid value for %s!";

    private final Model model;

    public EditModeParser(Model model) {
        this.model = model;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String fieldWord = matcher.group("fieldWord");
        final String arguments = matcher.group("arguments").trim();

        if (fieldWord.equalsIgnoreCase("back")) {
            return new BackCommand();
        }

        EditEntityDescriptor editData = new EditEntityDescriptor();
        Entity toEdit = model.getCurrentSelectedEntity();
        if (toEdit instanceof Character) {
            editData = generateCharacterData((Character) toEdit, fieldWord, arguments);
        } else if (toEdit instanceof Mob) {
            editData = generateMobData((Mob) toEdit, fieldWord, arguments);
        } else if (toEdit instanceof Item) {
            editData = generateItemData((Item) toEdit, fieldWord, arguments);
        } else {
            throw new ParseException(MESSAGE_INVALID_ENTITY_TYPE);
        }
        return new EditValueCommand(model.getCurrentSelectedEntity(), editData);
    }

    private EditCharacterDescriptor generateCharacterData(Character toEdit,
        String fieldWord, String value) throws ParseException {
        EditCharacterDescriptor outData = new EditCharacterDescriptor();
        switch (fieldWord.toLowerCase()) {
        case "name":
            outData.setName(new Name(value));
            break;
        case "tag":
        case "tags":
            Optional<Set<Tag>> tags = parseTagsForEdit(List.of(value.split("\\s+")));
            tags.ifPresent(realTags -> outData.setTags(realTags));
            break;
        case "str":
            outData.setStats(new Stats(Integer.valueOf(value),
                toEdit.getStats().getDexterity(), toEdit.getStats().getIntelligence()));
            break;
        case "dex":
            outData.setStats(new Stats(toEdit.getStats().getStrength(),
                Integer.valueOf(value), toEdit.getStats().getIntelligence()));
            break;
        case "int":
            outData.setStats(new Stats(toEdit.getStats().getStrength(),
                toEdit.getStats().getDexterity(), Integer.valueOf(value)));
            break;
        case "level":
        case "lvl":
            outData.setLevel(Integer.valueOf(value));
            break;
        case "xp":
        case "exp":
            outData.setXp(Integer.valueOf(value));
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_FIELD, fieldWord));
        }
        return outData;
    }

    private EditMobDescriptor generateMobData(Mob toEdit, String fieldWord, String value) throws ParseException {
        EditMobDescriptor outData = new EditMobDescriptor();
        switch (fieldWord.toLowerCase()) {
        case "name":
            outData.setName(new Name(value));
            break;
        case "tag":
        case "tags":
            Optional<Set<Tag>> tags = parseTagsForEdit(List.of(value.split("\\s+")));
            tags.ifPresent(realTags -> outData.setTags(realTags));
            break;
        case "str":
            outData.setStats(new Stats(Integer.valueOf(value),
                toEdit.getStats().getDexterity(), toEdit.getStats().getIntelligence()));
            break;
        case "dex":
            outData.setStats(new Stats(toEdit.getStats().getStrength(),
                Integer.valueOf(value), toEdit.getStats().getIntelligence()));
            break;
        case "int":
            outData.setStats(new Stats(toEdit.getStats().getStrength(),
                toEdit.getStats().getDexterity(), Integer.valueOf(value)));
            break;
        case "challengerating":
        case "cr":
            outData.setChallengeRating(Integer.valueOf(value));
            break;
        case "islegendary":
        case "legend":
        case "l":
            outData.setIsLegendary(Boolean.getBoolean(value));
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_FIELD, fieldWord));
        }
        return outData;
    }

    private EditItemDescriptor generateItemData(Item toEdit, String fieldWord, String value) throws ParseException {
        EditItemDescriptor outData = new EditItemDescriptor();
        switch (fieldWord.toLowerCase()) {
        case "name":
            outData.setName(new Name(value));
            break;
        case "tag":
        case "tags":
            Optional<Set<Tag>> tags = parseTagsForEdit(List.of(value.split("\\s+")));
            tags.ifPresent(realTags -> outData.setTags(realTags));
            break;
        case "cost":
        case "c":
            outData.setCost(Integer.valueOf(value));
            break;
        case "weight":
        case "w":
            outData.setWeight(Float.valueOf(value));
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_FIELD, fieldWord));
        }
        return outData;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty. If {@code tags}
     * contain only one element which is an empty string, it will be parsed into a {@code Set<Tag>} containing zero
     * tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
