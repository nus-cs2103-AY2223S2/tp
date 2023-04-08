package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import taa.commons.util.AppUtil;
import taa.commons.util.CsvUtil;
import taa.commons.util.FileUtil;
import taa.logic.commands.exceptions.CommandException;
import taa.logic.parser.ParserUtil;
import taa.logic.parser.exceptions.ParseException;
import taa.model.Model;
import taa.model.assignment.AssignmentList;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.student.UniqueStudentList;
import taa.model.tag.Tag;

/**
 * Import student data in CSV format from file.
 */
public class ImportCommand extends CsvCommand {
    public static final String COMMAND_WORD = "import";
    public static final String MSG_USAGE = COMMAND_WORD + ": Import data in CSV format from file. Parameter: FILE_PATH";
    public static final String MSG_FILE_DNE = "The specified file does not exist.";
    public static final String MSG_FILE_CANT_RD = "The specified file does not grant read permission.";
    public static final String MSG_FILE_NOT_FOUND = "The specified file cannot be opened for reading.";
    public static final String MSG_ENTRY_FMT_ERR = "The following entry does not comply with format: ";
    public static final String MSG_INCONSISTENT_ENTRY = "This entry has more columns than defined fields.";
    public static final String MSG_DUP_STU_IN_FILE = "The file contains this student at least twice:";
    public static final String MSG_SUCC = "%d student(s) added.";
    private static final Predicate<String> IS_UNEMPTY = s -> !s.isEmpty();

    /**
     * Create import command by passing a file. Nothing is checked.
     *
     * @param f
     * @param isForced
     */
    public ImportCommand(String f, boolean isForced) {
        super(f, isForced);
    }


    private static String mkMsgNoColumn(String keyword) {
        return "This entry has no \"" + keyword + "\"column.";
    }

    private static void throwEntryFormatError(CSVRecord record, String errMsg) throws CommandException {
        throw new CommandException(MSG_ENTRY_FMT_ERR + '\"' + Arrays.toString(record.values()) + "\". " + errMsg);
    }

    static Student parseFromCsvRec(CSVRecord record) throws CommandException {
        if (!record.isConsistent()) {
            throwEntryFormatError(record, MSG_INCONSISTENT_ENTRY);
        }

        if (!record.isMapped(CsvUtil.KW_NAME)) {
            throwEntryFormatError(record, mkMsgNoColumn(CsvUtil.KW_NAME));
        }
        final String nameStr = record.get(CsvUtil.KW_NAME).trim();
        if (!Name.isValidName(nameStr)) {
            throwEntryFormatError(record, Name.MESSAGE_CONSTRAINTS);
        }

        if (!record.isMapped(CsvUtil.KW_ATTENDANCE)) {
            throwEntryFormatError(record, mkMsgNoColumn(CsvUtil.KW_ATTENDANCE));
        }
        final String atd = record.get(CsvUtil.KW_ATTENDANCE);
        final String atdStr;
        if (atd.isBlank()) {
            atdStr = Attendance.ORIGINAL_ATD;
        } else {
            atdStr = atd.trim();
            if (!Attendance.isValidAtdStr(atdStr)) {
                throwEntryFormatError(record, Attendance.ATD_ERROR_MSG);
            }
        }

        if (!record.isMapped(CsvUtil.KW_PP)) {
            throwEntryFormatError(record, mkMsgNoColumn(CsvUtil.KW_PP));
        }
        final String pp = record.get(CsvUtil.KW_PP);
        final String ppStr;
        if (pp.isBlank()) {
            ppStr = Attendance.ORIGINAL_PP;
        } else {
            ppStr = record.get(CsvUtil.KW_PP).trim();
            if (!Attendance.isAcceptablePpStr(ppStr)) {
                throwEntryFormatError(record, Attendance.PP_UNACCEPTABLE_MSG);
            }
        }

        final ArrayList<String> submissions = new ArrayList<>();
        if (!record.isMapped(CsvUtil.KW_SUBMISSIONS)) {
            throwEntryFormatError(record, mkMsgNoColumn(CsvUtil.KW_SUBMISSIONS));
        }
        final String submitStr = record.get(CsvUtil.KW_SUBMISSIONS);
        if (!submitStr.isBlank()) {
            Collections.addAll(submissions, submitStr.trim().split(";"));
        }
        try {
            AssignmentList.INSTANCE.testValidCsvSubmissions(submissions);
        } catch (ParseException e) {
            throwEntryFormatError(record, e.getMessage());
        }

        if (!record.isMapped(CsvUtil.KW_TAGS)) {
            throwEntryFormatError(record, mkMsgNoColumn(CsvUtil.KW_TAGS));
        }
        final String tagStr = record.get(CsvUtil.KW_TAGS).trim();
        Set<Tag> parsedTags = null;
        try {
            // ignore all tokens that are empty strings.
            parsedTags = ParserUtil.parseTags(
                    Arrays.stream(tagStr.split(";")).filter(IS_UNEMPTY).collect(Collectors.toList()));
        } catch (ParseException e) {
            throwEntryFormatError(record, Tag.MESSAGE_CONSTRAINTS);
        }

        return new Student(new Name(nameStr), atdStr, ppStr, submissions, parsedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            if (!f.exists()) {
                throw new CommandException(MSG_FILE_DNE);
            }
            if (f.isDirectory()) {
                throw new CommandException(FileUtil.MSG_FILE_IS_DIR);
            }
            if (!f.canRead()) {
                throw new CommandException(MSG_FILE_CANT_RD);
            }
        } catch (SecurityException e) {
            throw new CommandException(FileUtil.MSG_FILE_ACCESS_DENIED);
        }

        final FileReader reader;
        try {
            reader = new FileReader(f);
        } catch (FileNotFoundException e) {
            throw new CommandException(MSG_FILE_NOT_FOUND);
        }

        CSVParser parser = null;
        try {
            parser = CsvUtil.IN_FMT.parse(reader);
        } catch (IOException e) {
            throwIoExceptionAsCmdException();
        }

        final HashMap<Name, Student> nameToStu = UniqueStudentList.getNameToStuMap(model.getFilteredStudentList());
        final HashSet<Student> inFileStu = new HashSet<>();
        final ArrayList<Student> toAdd = new ArrayList<>();
        final ArrayList<Student> toDel = new ArrayList<>();
        for (CSVRecord record : parser) {
            final Student stu = parseFromCsvRec(record);
            toAdd.add(stu);
            final Student stuInList = nameToStu.get(stu.getName());
            if (stuInList == null) {
                if (!inFileStu.add(stu)) { // duplicate student in file
                    throw new CommandException(MSG_DUP_STU_IN_FILE + ' ' + stu);
                }
            } else {
                if (isNotForced) {
                    throw new CommandException(AddStudentCommand.MESSAGE_DUPLICATE_STUDENT + ": " + stu
                            + "\nUse -force to overwrite.");
                }
                toDel.add(stuInList);
            }
        }
        AppUtil.closeIfClosable(parser);
        AppUtil.closeIfClosable(reader);
        toDel.forEach(model::deleteStudent);
        toDel.forEach(model::deleteStudentSubmission);
        toAdd.forEach(model::addStudent);
        toAdd.forEach(model::addStudentAssignment);
        return new CommandResult(String.format(MSG_SUCC, toAdd.size()));
    }
}
