package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.MemberOfGroupPredicate;

/**
 * Finds and lists all groups in address book with name containing any of the argument keywords. The people who belong
 * to these groups are also listed.
 * Keyword matching is case-insensitive.
 */
public class GroupFindCommand extends Command {
    public static final String COMMAND_WORD = "group_find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " : Finds group with names that contain any of "
            + "the specified keywords (case-insensitive) "
            + "and displays the people in the group as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2101";

    private final GroupNameContainsKeywordsPredicate groupPredicate;
    private final MemberOfGroupPredicate memberPredicate;

    /**
     * Creates a GroupFindCommand to find matching groups in the address book
     */
    public GroupFindCommand(GroupNameContainsKeywordsPredicate groupPredicate, MemberOfGroupPredicate memberPredicate) {
        this.groupPredicate = groupPredicate;
        this.memberPredicate = memberPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroupList(groupPredicate);
        model.updateFilteredPersonList(memberPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW,
                        model.getFilteredGroupList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupFindCommand // instanceof handles nulls
                && groupPredicate.equals(((GroupFindCommand) other).groupPredicate)
                && memberPredicate.equals(((GroupFindCommand) other).memberPredicate)); // state check
    }
}
