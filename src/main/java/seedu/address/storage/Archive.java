package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

/**
 * A class to export tracker to archive and import modules from archive to tracker
 */

public class Archive {
    private final Storage storage;

    public Archive(Storage storage) {
        this.storage = storage;
    }

    /**
     * Export current tracker to archive
     * @param archivedPath the archive file path
     * @param tracker the current tracker
     * @param isOverwriting whether the command will be overwriting existing archive file
     * @throws CommandException
     */
    public void exportToArchive(Path archivedPath, ReadOnlyTracker tracker, boolean isOverwriting)
            throws CommandException {
        if (Files.exists(archivedPath) && Files.isRegularFile(archivedPath) && !isOverwriting) {
            throw new CommandException(String.format(Messages.MESSAGE_ARCHIVE_FILE_ALREADY_EXIST));
        }

        try {
            storage.saveTracker(tracker, archivedPath);
        } catch (IOException ioe) {
            throw new CommandException(LogicManager.FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    /**
     * Import modules from archive to current tracker
     * @param archivedPath the archive file path
     * @param isOverwriting whether the command will be overwriting existing modules in tracker
     * @throws CommandException
     */
    public void importFromArchive(Path archivedPath, Model model, boolean isImportingWholeArchive,
                                  boolean isOverwriting,
                                  Set<ModuleCode> moduleCodeToImport) throws CommandException {

        if (!Files.exists(archivedPath) || !Files.isRegularFile(archivedPath)) {
            throw new CommandException(String.format(Messages.MESSAGE_FILE_DOES_NOT_EXIST));
        }
        ReadOnlyTracker archiveTracker;

        try {
            archiveTracker = storage.readTracker(archivedPath).get();
        } catch (IOException ioe) {
            throw new CommandException(LogicManager.FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        } catch (DataConversionException dce) {
            throw new CommandException(LogicManager.FILE_OPS_ERROR_MESSAGE + dce, dce);
        }

        if (isImportingWholeArchive) {
            for (ReadOnlyModule module : archiveTracker.getModuleList()) {
                moduleCodeToImport.add(module.getCode());
            }
        }

        checkIfModuleExistInArchive(moduleCodeToImport, archiveTracker);
        checkIfModuleExistInCurrentTracker(moduleCodeToImport, model.getTracker(), isOverwriting);

        for (ModuleCode moduleCode : moduleCodeToImport) {
            ReadOnlyModule module = archiveTracker.getModule(moduleCode);
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

    private void checkIfModuleExistInCurrentTracker(Set<ModuleCode> moduleCodeSet, ReadOnlyTracker currentTracker,
                                                    boolean isOverwriting)
            throws CommandException {

        List<ModuleCode> currentModuleList =
                currentTracker.getModuleList().stream().map(ReadOnlyModule::getCode).collect(Collectors.toList());

        List<String> moduleExistInCurrentTracker =
                moduleCodeSet.stream().filter(currentModuleList::contains)
                        .map(moduleCode -> moduleCode.code)
                        .collect(Collectors.toList());

        if (!moduleExistInCurrentTracker.isEmpty() && !isOverwriting) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_MODULE_ALREADY_EXIST_IN_TRACKER, String.join(", ",
                            moduleExistInCurrentTracker)));
        }
    }

    private void checkIfModuleExistInArchive(Set<ModuleCode> moduleCodeSet, ReadOnlyTracker tracker)
            throws CommandException {

        List<ModuleCode> archivedModuleList =
                tracker.getModuleList().stream().map(ReadOnlyModule::getCode).collect(Collectors.toList());

        List<String> moduleDoesNotExistInArchive =
                moduleCodeSet.stream().filter(moduleCode -> !archivedModuleList.contains(moduleCode))
                        .map(moduleCode -> moduleCode.code)
                        .collect(Collectors.toList());

        if (!moduleDoesNotExistInArchive.isEmpty()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST_IN_ARCHIVE, String.join(", ",
                            moduleDoesNotExistInArchive)));
        }
    }
}
