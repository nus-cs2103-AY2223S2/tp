package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERWRITE;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.storage.Storage;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ImportCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public ImportCommand parse(String args, Storage storage) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OVERWRITE, PREFIX_MODULE);
        boolean isOverwritingExistingModule = false;
        boolean isImportingAllModules = false;
        Set<ModuleCode> moduleCodeSet = new HashSet<>();

        String overwritingFileArgument = argMultimap.getValue(PREFIX_OVERWRITE).orElse("");
        if (overwritingFileArgument.equals("true")) {
            isOverwritingExistingModule = true;
        }

        //Potential bug: "/mod" has nothing
        String stringOfModuleCodes = argMultimap.getValue(PREFIX_MODULE).orElse("");
        if (!stringOfModuleCodes.isEmpty()) {
            moduleCodeSet = ParserUtil.parseMultiModuleCode(stringOfModuleCodes);
        } else {
            isImportingAllModules = true;
        }

        String fileName = argMultimap.getPreamble();

        if (fileName.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        return new ImportCommand(fileName, storage, moduleCodeSet, isOverwritingExistingModule, isImportingAllModules);
    }
}
