package fasttrack.logic.commands.edit;

import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_END_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TIMESPAN;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import fasttrack.commons.core.Messages;
import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Price;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.model.expense.RecurringExpenseType;
import fasttrack.ui.ScreenType;

/**
 * Edits a RecurringExpenseManager in the ExpenseTracker
 */
public class EditRecurringExpenseManagerCommand implements EditCommand {
    public static final String COMMAND_WORD = "edrec";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the recurring expense identified by the index number used in the displayed recurring"
            + " expenses list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "EXPENSE NAME] "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_PRICE + "AMOUNT] "
            + "[" + PREFIX_TIMESPAN + "FREQUENCY] \n"
            + "[" + PREFIX_END_DATE + "END-DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 n/KFC c/food p/10 t/weekly ed/20/03/23 ";

    private final Index targetIndex;
    private final String newExpenseName;
    private final String newExpenseCategoryInString;
    private final Double newExpenseAmount;
    private final String newFrequencyInString;
    private final LocalDate newExpenseEndDate;

    /**
     * Creates an EditRecurringExpenseManagerCommand to edit the specified
     * {@code RecurringExpenseManager}
     * @param targetIndex                index of the recurringexpensemanager in the
     *                                   filtered recurringexpensemanager list to
     *                                   edit.
     * @param newExpenseName             String representation of the new category
     *                                   name to be edited to, if applicable.
     * @param newExpenseAmount           New expense price to be edited to, if
     *                                   applicable.
     * @param newExpenseCategoryInString String representation of the new category's
     *                                   name to be edited to,
     *                                   if applicable.
     * @param newFrequencyInString       String representation of the frequency to
     *                                   be edited to, if applicable.
     * @param newExpenseEndDate          New recurring expense end date to be edited
     *                                   to, if applicable.
     */
    public EditRecurringExpenseManagerCommand(Index targetIndex, String newExpenseName, Double newExpenseAmount,
            String newExpenseCategoryInString, String newFrequencyInString,
            LocalDate newExpenseEndDate) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newExpenseName = newExpenseName;
        this.newExpenseAmount = newExpenseAmount;
        this.newExpenseCategoryInString = newExpenseCategoryInString;
        this.newFrequencyInString = newFrequencyInString;
        this.newExpenseEndDate = newExpenseEndDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Category> lastShownListOfCategories = model.getFilteredCategoryList();
        List<RecurringExpenseManager> lastShownListOfRecurringExpenseManagers = model.getRecurringExpenseGenerators();
        Category toBeAllocated = null;

        for (Category category : lastShownListOfCategories) {
            if (category.getCategoryName().equals(this.newExpenseCategoryInString)) {
                toBeAllocated = category;
                break;
            }
        }

        if (newExpenseName == null && newExpenseAmount == null
                && newFrequencyInString == null && newExpenseCategoryInString == null
                && this.newExpenseEndDate == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EDIT_FOR_EXPENSE);
        }

        // Check if index is valid
        if (targetIndex == null || targetIndex.getZeroBased() >= lastShownListOfRecurringExpenseManagers.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        RecurringExpenseManager generatorToEdit = lastShownListOfRecurringExpenseManagers
                .get(targetIndex.getZeroBased());

        if (toBeAllocated != null) {
            generatorToEdit.setExpenseCategory(toBeAllocated);
        } else if (this.newExpenseCategoryInString != null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_CATEGORY);
        }

        if (newExpenseName != null) {
            if (newExpenseName.stripTrailing().isEmpty()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_NAME);
            }
            generatorToEdit.setExpenseName(newExpenseName);
        }

        if (newExpenseAmount != null) {
            if (!Price.isValidPrice(String.valueOf(newExpenseAmount))) {
                throw new CommandException(Price.MESSAGE_CONSTRAINTS);
            }
            generatorToEdit.setAmount(newExpenseAmount);
        }

        if (newExpenseEndDate != null) {
            generatorToEdit.setEndDate(newExpenseEndDate);
        }

        if (newFrequencyInString != null) {
            // Check if it belongs in the enum
            try {
                RecurringExpenseType newTypeToSet = RecurringExpenseType.valueOf(newFrequencyInString);
                generatorToEdit.setRecurringExpenseType(newTypeToSet);
            } catch (IllegalArgumentException iae) {
                throw new CommandException(Messages.MESSAGE_INVALID_ENUM_FOR_FREQUENCY);
            }
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_RECURRING, generatorToEdit),
                ScreenType.RECURRING_EXPENSE_SCREEN);
    }
}
