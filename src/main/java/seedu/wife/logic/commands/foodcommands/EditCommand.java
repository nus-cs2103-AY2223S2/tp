package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_UNIT;
import static seedu.wife.model.Model.PREDICATE_SHOW_ALL_FOODS;

import java.util.List;
import java.util.Optional;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.commons.util.CollectionUtil;
import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.food.ExpiryDate;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;

/**
 * Edits the details of an existing food item in WIFE.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the food identified "
            + "by the index number used in the displayed food list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_UNIT + "UNIT] "
            + "[" + PREFIX_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_EXPIRY_DATE + "EXPIRY DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Broccoli "
            + PREFIX_QUANTITY + "10";

    public static final String MESSAGE_EDIT_FOOD_SUCCESS = "Edited food item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided! \n%s";
    public static final String MESSAGE_DUPLICATE_FOOD = "This food already exists in WIFE.";

    private final Index index;
    private final EditFoodDescriptor editFoodDescriptor;

    /**
     * @param index of the food in the filtered food list to edit
     * @param editFoodDescriptor details to edit the food item with
     */
    public EditCommand(Index index, EditFoodDescriptor editFoodDescriptor) {
        requireNonNull(index);
        requireNonNull(editFoodDescriptor);

        this.index = index;
        this.editFoodDescriptor = new EditFoodDescriptor(editFoodDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToEdit = lastShownList.get(index.getZeroBased());
        Food editedFood = createEditedFood(foodToEdit, editFoodDescriptor);

        if (!foodToEdit.isSameFood(editedFood) && model.hasFood(editedFood)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.setFood(foodToEdit, editedFood);
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        return new CommandResult(String.format(MESSAGE_EDIT_FOOD_SUCCESS, editedFood));
    }

    /**
     * Creates and returns a {@code food} with the details of {@code foodToEdit}
     * edited with {@code editFoodDescriptor}.
     */
    private static Food createEditedFood(Food foodToEdit, EditFoodDescriptor editFoodDescriptor) {
        assert foodToEdit != null;

        Name updatedName = editFoodDescriptor.getName().orElse(foodToEdit.getName());
        Unit updatedUnit = editFoodDescriptor.getUnit().orElse(foodToEdit.getUnit());
        Quantity updatedQuantity = editFoodDescriptor.getQuantity().orElse(foodToEdit.getQuantity());
        ExpiryDate updatedExpiryDate = editFoodDescriptor.getExpiryDate().orElse(foodToEdit.getExpiryDate());

        return new Food(updatedName, updatedUnit, updatedQuantity, updatedExpiryDate, foodToEdit.getTags());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFoodDescriptor.equals(e.editFoodDescriptor);
    }

    /**
     * Stores the details to edit the food item with. Each non-empty field value will replace the
     * corresponding field value of the food. Note that tags cannot be changed in EditFoodDescriptor and EditCommand.
     */
    public static class EditFoodDescriptor {
        private Name name;
        private Unit unit;
        private Quantity quantity;
        private ExpiryDate expiryDate;

        public EditFoodDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFoodDescriptor(EditFoodDescriptor toCopy) {
            setName(toCopy.name);
            setUnit(toCopy.unit);
            setQuantity(toCopy.quantity);
            setExpiryDate(toCopy.expiryDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, unit, quantity, expiryDate);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setUnit(Unit unit) {
            this.unit = unit;
        }

        public Optional<Unit> getUnit() {
            return Optional.ofNullable(unit);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setExpiryDate(ExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Optional<ExpiryDate> getExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFoodDescriptor)) {
                return false;
            }

            // state check
            EditFoodDescriptor e = (EditFoodDescriptor) other;

            return getName().equals(e.getName())
                    && getUnit().equals(e.getUnit())
                    && getQuantity().equals(e.getQuantity())
                    && getExpiryDate().equals(e.getExpiryDate());
        }
    }
}
