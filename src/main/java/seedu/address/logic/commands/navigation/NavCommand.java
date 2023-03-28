package seedu.address.logic.commands.navigation;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;

/**
 * Navigates to specified location.
 */
public abstract class NavCommand extends Command {

    public static final String COMMAND_WORD = "nav";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Navigates to the module / lecture specified "
                    + "that is relative to the current location. \n"
                    + "Parameters: {module_code / lecture_name} \n" + "Example: " + COMMAND_WORD
                    + " CS2040S \n" + COMMAND_WORD
                    + ": Navigates directly to a specified module or lecture. \n" + "Parameters: "
                    + "[" + PREFIX_MODULE + "module_code] " + "[" + PREFIX_LECTURE
                    + "lecture_name] \n" + "Example: " + COMMAND_WORD + " /mod CS2040S /lec L1 \n";

    public static final String MESSAGE_NAV_SUCCESS = "You navigated to %s";

    public static CommandResult getSuccessfulCommandResult(NavigationContext context, Model model) {
        return new CommandResult(NavCommand.getSuccessfulNavMessage(context), context.toString());
    }

    public static String getSuccessfulNavMessage(NavigationContext context) {
        return String.format(MESSAGE_NAV_SUCCESS, context);
    }

    protected void listAtRoot(Model model) throws CommandException {
        new ListCommand().execute(model);
    }

    protected void listAtModule(ModuleCode moduleCode, Model model) throws CommandException {
        new ListCommand(moduleCode).execute(model);
    }

    protected void listAtLecture(ModuleCode moduleCode, LectureName lectureName, Model model) throws CommandException {
        new ListCommand(moduleCode, lectureName).execute(model);
    }
}
