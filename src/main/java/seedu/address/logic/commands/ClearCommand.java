package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.model.Model;
import seedu.address.model.module.ReadOnlyModule;

/**
 * Clears the tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Le Tracker has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearTracker();
        List<ReadOnlyModule> modules = model.getTracker()
                .getModuleList()
                .stream()
                .map(x -> (ReadOnlyModule) x)
                .collect(Collectors.toList());

        ModuleEditInfo[] clearedModuleInfos = new ModuleEditInfo[modules.size()];
        for (int i = 0; i < modules.size(); i++) {
            clearedModuleInfos[i] = new ModuleEditInfo(modules.get(i), null);
        }
        return new CommandResult(MESSAGE_SUCCESS, clearedModuleInfos);
    }
}
