package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;

/**
 * import modules from archive.
 */

public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    //TODO: CHANGE THIS

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": import specified modules in archive file" + "\n"
            + "\n"
            + "*** Command Format *** " + "\n"
            + COMMAND_WORD + " {file_name}" + "\n"
            + "\n"
            + "*** Example *** " + "\n"
            + COMMAND_WORD + " EG2310";
    public static final String MESSAGE_SUCCESS = "Modules %1$s imported to Le Tracker";

    private final String fileName;
    private Set<ModuleCode> moduleCodeSet;
    private final boolean isOverwritingExistingModule;
    private final boolean isImportingAllModules;

    /**
     * Creates an ImportCommand to import modules from archive file into Le Tracker
     */

    public ImportCommand(String fileName, Set<ModuleCode> moduleCodeSet,
                         boolean isOverwritingExistingModule, boolean isImportingAllModules) {
        this.fileName = fileName;
        this.moduleCodeSet = moduleCodeSet;
        this.isOverwritingExistingModule = isOverwritingExistingModule;
        this.isImportingAllModules = isImportingAllModules;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path archivePath = Paths.get("data", fileName);

        List<String> moduleCodeList = moduleCodeSet.stream()
                .map(moduleCode -> moduleCode.code).collect(Collectors.toList());

        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join(", ", moduleCodeList)), archivePath,
                isImportingAllModules, isOverwritingExistingModule, moduleCodeSet);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherCommand = (ImportCommand) other;

        return fileName.equals(otherCommand.fileName)
                && moduleCodeSet.equals(otherCommand.moduleCodeSet)
                && (isOverwritingExistingModule == otherCommand.isOverwritingExistingModule)
                && (isImportingAllModules == otherCommand.isImportingAllModules);
    }
}

