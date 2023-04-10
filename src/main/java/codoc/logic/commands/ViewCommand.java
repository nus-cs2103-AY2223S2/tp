package codoc.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import codoc.commons.core.Messages;
import codoc.commons.core.index.Index;
import codoc.logic.commands.exceptions.CommandException;
import codoc.model.Model;
import codoc.model.person.Person;



/**
 * Loads specific person to the right info panel.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details of the person identified "
            + "by the index number used in the displayed person list "
            + "or by different tab names\n"
            + "Parameters: c / m / s / index number\n"
            + "Examples: " + COMMAND_WORD + " m, " + COMMAND_WORD + " 2 ";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewing details of %1$s at index %2$d";

    public static final String MESSAGE_CHANGE_TAB_SUCCESS = "Viewing detailed %1$s information";

    private final Index index;

    private final String tab;

    /**
     * @param index of the person in the filtered person list to view
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
        this.tab = "default";
    }

    /**
     * @param tab of the currently viewing person to display on the InfoPanel
     */
    public ViewCommand(String tab) {
        requireNonNull(tab);
        this.index = Index.fromZeroBased(0);
        this.tab = tab;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (tab.equals("c") || tab.equals("m") || tab.equals("s")) { // If it is view tab
            model.setCurrentTab(tab);
            String tabName = "";
            if (tab.equals("c")) {
                tabName = "contact";
            }
            if (tab.equals("m")) {
                tabName = "module";
            }
            if (tab.equals("s")) {
                tabName = "skill";
            }
            return new CommandResult(String.format(MESSAGE_CHANGE_TAB_SUCCESS, tabName));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // View index
        Person protagonist = lastShownList.get(index.getZeroBased());
        model.setProtagonist(protagonist);
        String[] nameParsed = protagonist.getName().toString().split(" ", 2);

        return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, nameParsed[0], index.getOneBased()),
                false, false, String.valueOf(index.getZeroBased()));
    }
}
