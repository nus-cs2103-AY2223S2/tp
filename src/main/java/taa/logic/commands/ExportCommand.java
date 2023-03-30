package taa.logic.commands;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVPrinter;

import javafx.collections.ObservableList;
import taa.commons.util.AppUtil;
import taa.commons.util.CsvUtil;
import taa.commons.util.FileUtil;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;

public class ExportCommand extends CsvCommand {
    public static final String COMMAND_WORD = "export";
    public static final String MSG_USAGE = COMMAND_WORD + ": Export data in CSV format from file. Parameter: FILE_PATH";
    public static final String MSG_FILE_EXISTS = "The specified file already exists. Use -force to overwrite.";
    public static final String MSG_FILE_RD_ONLY = "The specified file is read-only and cannot be modified.";
    public static final String MSG_SUCC = "%d student(s) saved to %s.";

    /** Create export command by passing a file. Nothing is checked. */
    public ExportCommand(String f, boolean isForced) {
        super(f.endsWith(CsvUtil.FILE_PREFIX) ? f : f + CsvUtil.FILE_PREFIX, isForced);
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            if (!f.createNewFile()) { // File already exists
                if (isNotForced) {
                    throw new CommandException(MSG_FILE_EXISTS);
                }
                if (!f.canWrite()) {
                    throw new CommandException(MSG_FILE_RD_ONLY);
                }
            }
        } catch (SecurityException e) {
            throw new CommandException(FileUtil.MSG_FILE_ACCESS_DENIED);
        } catch (IOException e) {
            throwIoExceptionAsCmdException();
        }

        final ObservableList<Student> toSave = model.getFilteredStudentList();
        FileWriter fw = null;
        CSVPrinter printer = null;
        try {
            fw = new FileWriter(f);
            printer = new CSVPrinter(fw, CsvUtil.STU_FMT);
            for (Student stu : toSave) {
                final Attendance atd=stu.getAtd();
                StringBuilder tags=new StringBuilder();
                for(Tag tag:stu.getClassTags()){
                    tags.append(tag.tagName).append(' ');
                }
                printer.printRecord(stu.getName(), atd.listAtdString(),atd.listPpString(),tags);
            }
        } catch (IOException e) {
            throwIoExceptionAsCmdException();
        } finally {
            AppUtil.closeIfClosable(printer);
            AppUtil.closeIfClosable(fw);
        }
        return new CommandResult(String.format(MSG_SUCC, toSave.size(), f));
    }
}







