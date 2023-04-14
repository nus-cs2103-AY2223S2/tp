package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.commands.sortcommand.SortCommand;
import tfifteenfour.clipboard.logic.commands.sortcommand.studentcomparators.AlphaNumericSidComparator;
import tfifteenfour.clipboard.logic.commands.sortcommand.studentcomparators.AlphabeticalNameComparator;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    private final CurrentSelection currentSelection;

    public SortCommandParser(CurrentSelection currentSelection) {
        this.currentSelection = currentSelection;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @param args The arguments provided by the user.
     * @return The new SortCommand object.
     * @throws ParseException If the user input does not conform to the expected format.
     * @throws CommandException if command was performed on wrong page.
     */
    public SortCommand parse(String args) throws ParseException, CommandException {
        String trimmedArgs = args.trim();

        if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to student page to sort students");
        }

        SortCategory category = SortCategory.fromString(trimmedArgs);

        switch (category) {
        case SORT_BY_NAME:
            return new SortCommand(new AlphabeticalNameComparator(), category.getCategory());
        case SORT_BY_STUDENT_ID:
            return new SortCommand(new AlphaNumericSidComparator(), category.getCategory());
        default:
            throw new ParseException("Unable to parse category for sorting\n" + SortCommand.MESSAGE_USAGE);
        }
    }
}

enum SortCategory {
    SORT_BY_NAME("name"),
    SORT_BY_STUDENT_ID("id");

    private String category;

    private SortCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public static SortCategory fromString(String category) throws ParseException {
        for (SortCategory sc : SortCategory.values()) {
            if (sc.getCategory().equalsIgnoreCase(category)) {
                return sc;
            }
        }

        throw new ParseException("Unable to parse category for sorting\n" + SortCommand.MESSAGE_USAGE);
    }
}
