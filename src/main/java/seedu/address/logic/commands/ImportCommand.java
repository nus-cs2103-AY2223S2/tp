package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERWRITE;

import java.nio.file.InvalidPathException;
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

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ":\n"
            + "(1) Import all modules from archive file.\n"
            + "Parameter: "
            + "{file_name}\n"
            + "Example: " + COMMAND_WORD + " hello.json\n\n"
            + "(2) Import specific modules from archive file.\n"
            + "Parameter: "
            + "{file_name} "
            + PREFIX_MODULE + " {module_1}[, {module_2}[, ...]]\n"
            + "Example: " + COMMAND_WORD + " hello.json " + PREFIX_MODULE + " EG2310, EG1311\n\n"
            + "(3) Import all modules from archive file, overwriting similar modules in Le Tracker.\n"
            + "Parameter: "
            + "{file_name} " + PREFIX_OVERWRITE + "\n"
            + "Example: " + COMMAND_WORD + " hello.json " + PREFIX_OVERWRITE + "\n\n"
            + "(4) Import specific modules from archive file, overwriting similar modules in Le Tracker.\n"
            + "Parameter: "
            + "{file_name} "
            + PREFIX_MODULE + " {module_1}[, {module_2}[, ...]] "
            + PREFIX_OVERWRITE + "\n"
            + "Example: " + COMMAND_WORD + " hello.json " + PREFIX_MODULE + " EG2310, EG1311 " + PREFIX_OVERWRITE;

    public static final String MESSAGE_SUCCESS = "Modules imported to Le Tracker";

    private final String fileName;
    private final Set<ModuleCode> moduleCodeSet;
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
        Path archivePath;

        try {
            archivePath = Paths.get("data", fileName);
        } catch (InvalidPathException pathException) {
            throw new CommandException(MESSAGE_INVALID_FILE_NAME);
        }

        List<String> moduleCodeList = moduleCodeSet.stream()
                .map(moduleCode -> moduleCode.code).collect(Collectors.toList());

        if (!moduleCodeList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, String.join(", ", moduleCodeList)), archivePath,
                    isImportingAllModules, isOverwritingExistingModule, moduleCodeSet);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, archivePath,
                    isImportingAllModules, isOverwritingExistingModule, moduleCodeSet);
        }
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
