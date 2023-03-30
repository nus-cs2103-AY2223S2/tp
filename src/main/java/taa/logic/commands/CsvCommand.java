package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;

import taa.commons.util.FileUtil;
import taa.logic.commands.exceptions.CommandException;
/** The elements shared by import and export command.*/
public abstract class CsvCommand extends Command {
    final File f;
    final boolean isNotForced;

    /** Create import command by passing a file. Nothing is checked. */
    public CsvCommand(String f, boolean isForced) {
        requireNonNull(f);
        this.f = new File(f);
        this.isNotForced = !isForced;
    }

    void throwIoExceptionAsCmdException() throws CommandException {
        throw new CommandException(FileUtil.MSG_FILE_IO_EXCEPTION);
    }
}
