package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ENTITY_NONEXISTENT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditValueCommand.MESSAGE_INVALID_ENTITY_TYPE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.BackCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditValueCommand;
import seedu.address.logic.commands.EditValueCommand.EditCharacterDescriptor;
import seedu.address.logic.commands.EditValueCommand.EditEntityDescriptor;
import seedu.address.logic.commands.EditValueCommand.EditItemDescriptor;
import seedu.address.logic.commands.EditValueCommand.EditMobDescriptor;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Stats;
import seedu.address.model.tag.Tag;

/**
 * Parses user input while in edit mode.
 */
public class EditModeParser {

    public static final String MESSAGE_INVALID_FIELD = "%s is not a valid editable field! \n"
            + "Use commands of the form FIELD (name of the field you wish to edit) VALUE (desired value). \n"
            + "Example: name Johnny Depp \n"
            + "You may also type 'back' to exit Edit Mode and return to the list view.";
    public static final String MESSAGE_INVALID_VALUE_FORMAT = "%s is not a valid value for %s!";

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<fieldWord>\\S+)(?<arguments>.*)");
    private static final Pattern INVENTORY_COMMAND_FORMAT = Pattern.compile("(?<actionWord>\\S+)(?<name>.*)");

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
    public Command parseCommand(String userInput) throws ParseException, NumberFormatException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String fieldWord = matcher.group("fieldWord");
        final String arguments = matcher.group("arguments").trim();

        if (fieldWord.equalsIgnoreCase("back")
                || fieldWord.equalsIgnoreCase("b")) {
            return new BackCommand();
        }


        EditEntityDescriptor editData = new EditEntityDescriptor();
        Entity toEdit = model.getCurrentSelectedEntity();
        if (toEdit instanceof Character) {
            editData = generateCharacterData((Character) toEdit, fieldWord, arguments);
        } else if (toEdit instanceof Mob) {
            editData = generateMobData((Mob) toEdit, fieldWord, arguments);
        } else if (toEdit instanceof Item) {
            editData = generateItemData(fieldWord, arguments);
        } else {
            throw new ParseException(MESSAGE_INVALID_ENTITY_TYPE);
        }
        return new EditValueCommand(model.getCurrentSelectedEntity(), editData);
    }

    private EditCharacterDescriptor generateCharacterData(Character toEdit,
            String fieldWord, String value) throws ParseException, NumberFormatException {
        // Where all the edits are stored and sent over to the EditValueCommand
        EditCharacterDescriptor outData = new EditCharacterDescriptor();
        switch (fieldWord.toLowerCase()) {
        case "n":
        case "name":
            if (Name.isValidName(value)) {
                outData.setName(new Name(value));
                break;
            } else {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
        case "t":
        case "tag":
        case "tags":
            Optional<Set<Tag>> tags = parseTagsForEdit(List.of(value.split("\\s+")));
            tags.ifPresent(realTags -> outData.setTags(realTags));
            break;
        case "s":
        case "str":
        case "strength":
            outData.setStats(new Stats(Integer.valueOf(value),
                    toEdit.getStats().getDexterity(), toEdit.getStats().getIntelligence()));
            break;
        case "d":
        case "dex":
        case "dexterity":
            outData.setStats(new Stats(toEdit.getStats().getStrength(),
                    Integer.valueOf(value), toEdit.getStats().getIntelligence()));
            break;
        case "int":
        case "intelligence":
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
        case "inv":
        case "inventory":
            // Check if add or delete
            Inventory editedInventory = new Inventory(toEdit.getInventory().getItems());
            parseInventoryCommand(value, editedInventory);
            outData.setInventory(editedInventory);
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_FIELD, fieldWord));
        }
        return outData;
    }

    private EditMobDescriptor generateMobData(Mob toEdit, String fieldWord, String value)
            throws ParseException, NumberFormatException {
        EditMobDescriptor outData = new EditMobDescriptor();
        switch (fieldWord.toLowerCase()) {
        case "name":
            if (Name.isValidName(value)) {
                outData.setName(new Name(value));
                break;
            } else {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
        case "tag":
        case "tags":
            Optional<Set<Tag>> tags = parseTagsForEdit(List.of(value.split("\\s+")));
            tags.ifPresent(outData::setTags);
            break;
        case "str":
        case "strength":
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
            outData.setChallengeRating(Float.valueOf(value));
            break;
        case "islegendary":
        case "legend":
        case "l":
            outData.setIsLegendary(Boolean.valueOf(value));
            break;
        case "inventory":
            // Check if add or delete
            Inventory editedInventory = new Inventory(toEdit.getInventory().getItems());
            parseInventoryCommand(value, editedInventory);
            outData.setInventory(editedInventory);
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_FIELD, fieldWord));
        }
        return outData;
    }

    private EditItemDescriptor generateItemData(String fieldWord, String value)
            throws ParseException, NumberFormatException {
        EditItemDescriptor outData = new EditItemDescriptor();
        switch (fieldWord.toLowerCase()) {
        case "name":
            if (Name.isValidName(value)) {
                outData.setName(new Name(value));
                break;
            } else {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
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
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags)
            throws ParseException, NumberFormatException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    private void parseInventoryCommand(String args, Inventory editInventory) throws ParseException {
        final Matcher matcher = INVENTORY_COMMAND_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditValueCommand.MESSAGE_USAGE));
        }
        final String actionWord = matcher.group("actionWord");
        final String itemName = matcher.group("name").trim();
        final Optional<Entity> item = model.getReroll().getEntities().getItemList()
                                                        .stream()
                                                        .filter(entity -> entity.getName().fullName.equals(itemName))
                                                        .findFirst();
        if (actionWord.equalsIgnoreCase("add")) {
            if (item.isPresent()) {
                editInventory.addItem((Item) item.get());
            } else {
                throw new ParseException(MESSAGE_ENTITY_NONEXISTENT);
            }
        } else if (actionWord.equalsIgnoreCase("remove")) {
            if (editInventory.hasItem(itemName)) {
                editInventory.deleteItem(itemName);
            } else {
                throw new ParseException(MESSAGE_ENTITY_NONEXISTENT);
            }
        } else {
            //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INVENTORY_COMMAND));
        }
    }
}
