package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.storage.Storage;

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

        if (!Files.exists(archivePath) || !Files.isRegularFile(archivePath)) {
            throw new CommandException(String.format(Messages.MESSAGE_FILE_DOES_NOT_EXIST, fileName));
        }

        List<String> moduleCodeList = moduleCodeSet.stream()
                .map(moduleCode -> moduleCode.code).collect(Collectors.toList());

        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join(", ", moduleCodeList)));
    }

    private void importModule(Set<ModuleCode> moduleCodeSet, ReadOnlyTracker tracker, Model model)
            throws CommandException {
        checkIfModuleExistInArchive(moduleCodeSet, tracker);
        checkIfModuleExistInCurrentTracker(moduleCodeSet, model);

        for (ModuleCode moduleCode : moduleCodeSet) {
            ReadOnlyModule module = tracker.getModule(moduleCode);
            Module moduleToAdd = new Module(module.getCode(),
                    module.getName(), module.getTags(), module.getLectureList());
            if (model.hasModule(moduleToAdd.getCode())) {
                ReadOnlyModule replacedModule = model.getModule(moduleToAdd.getCode());
                model.setModule(replacedModule, moduleToAdd);
            } else {
                model.addModule(moduleToAdd);
            }
        }
    }

    private void checkIfModuleExistInCurrentTracker(Set<ModuleCode> moduleCodeSet, Model model)
            throws CommandException {
        ReadOnlyTracker currentTracker = model.getTracker();

        List<ModuleCode> currentModuleList =
                currentTracker.getModuleList().stream().map(ReadOnlyModule::getCode).collect(Collectors.toList());

        List<String> moduleExistInCurrentTracker =
                moduleCodeSet.stream().filter(moduleCode -> currentModuleList.contains(moduleCode))
                        .map(moduleCode -> moduleCode.code)
                        .collect(Collectors.toList());

        if (!moduleExistInCurrentTracker.isEmpty() && !isOverwritingExistingModule) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_MODULE_ALREADY_EXIST_IN_TRACKER, String.join(", ",
                            moduleExistInCurrentTracker)));
        }
    }

    private void checkIfModuleExistInArchive(Set<ModuleCode> moduleCodeSet, ReadOnlyTracker tracker)
            throws CommandException {

        List<ModuleCode> archivedModuleList =
                tracker.getModuleList().stream().map(ReadOnlyModule::getCode).collect(Collectors.toList());

        List<String> moduleDoesNotExistInTracker =
                moduleCodeSet.stream().filter(moduleCode -> !archivedModuleList.contains(moduleCode))
                        .map(moduleCode -> moduleCode.code)
                        .collect(Collectors.toList());

        if (!moduleDoesNotExistInTracker.isEmpty()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST_IN_ARCHIVE, String.join(", ",
                            moduleDoesNotExistInTracker), fileName));
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

