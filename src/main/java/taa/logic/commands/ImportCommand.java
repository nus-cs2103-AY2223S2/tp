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

    private static void throwEntryFormatError(CSVRecord rec, String errMsg) throws CommandException {
        throw new CommandException(MSG_ENTRY_FMT_ERR + '\"' + Arrays.toString(rec.values()) + "\". " + errMsg);
    }

    private static Name parseName(CSVRecord rec) throws CommandException {
        if (!rec.isMapped(CsvUtil.KW_NAME)) {
            throwEntryFormatError(rec, mkMsgNoColumn(CsvUtil.KW_NAME));
        }
        final String nameStr = rec.get(CsvUtil.KW_NAME).trim();
        if (!Name.isValidName(nameStr)) {
            throwEntryFormatError(rec, Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(nameStr);
    }

    private static String parseAtd(CSVRecord rec) throws CommandException {
        if (!rec.isMapped(CsvUtil.KW_ATD)) {
            throwEntryFormatError(rec, mkMsgNoColumn(CsvUtil.KW_ATD));
        }
        final String atd = rec.get(CsvUtil.KW_ATD);
        final String atdStr;
        if (atd.isBlank()) {
            atdStr = Attendance.ORIGINAL_ATD;
        } else {
            atdStr = atd.trim();
            if (!Attendance.isValidAtdStr(atdStr)) {
                throwEntryFormatError(rec, Attendance.ATD_ERROR_MSG);
            }
        }
        return atdStr;
    }

    private static String parsePp(CSVRecord rec) throws CommandException {
        if (!rec.isMapped(CsvUtil.KW_PP)) {
            throwEntryFormatError(rec, mkMsgNoColumn(CsvUtil.KW_PP));
        }
        final String pp = rec.get(CsvUtil.KW_PP);
        final String ppStr;
        if (pp.isBlank()) {
            ppStr = Attendance.ORIGINAL_PP;
        } else {
            ppStr = rec.get(CsvUtil.KW_PP).trim();
            if (!Attendance.isAcceptablePpStr(ppStr)) {
                throwEntryFormatError(rec, Attendance.PP_UNACCEPTABLE_MSG);
            }
        }
        return ppStr;
    }

    private static ArrayList<String> parseSubmissions(CSVRecord rec) throws CommandException {
        final ArrayList<String> submissions = new ArrayList<>();
        if (!rec.isMapped(CsvUtil.KW_SUBMISSION)) {
            throwEntryFormatError(rec, mkMsgNoColumn(CsvUtil.KW_SUBMISSION));
        }
        final String submitStr = rec.get(CsvUtil.KW_SUBMISSION);
        if (!submitStr.isBlank()) {
            Collections.addAll(submissions, submitStr.trim().split(";"));
        }
        try {
            AssignmentList.INSTANCE.testValidCsvSubmissions(submissions);
        } catch (ParseException e) {
            throwEntryFormatError(rec, e.getMessage());
        }
        return submissions;
    }

    private static Set<Tag> parseClass(CSVRecord rec) throws CommandException {
        if (!rec.isMapped(CsvUtil.KW_CLASS)) {
            throwEntryFormatError(rec, mkMsgNoColumn(CsvUtil.KW_CLASS));
        }
        final String tagStr = rec.get(CsvUtil.KW_CLASS).trim();
        Set<Tag> parsedClass = null;
        try {
            // ignore all tokens that are empty strings.
            return ParserUtil.parseTags(
                    Arrays.stream(tagStr.split(";")).filter(IS_UNEMPTY).collect(Collectors.toList()));
        } catch (ParseException e) {
            throwEntryFormatError(rec, Tag.MESSAGE_CONSTRAINTS);
        }
        return parsedClass;
    }

    private static Student parseFromCsvRec(CSVRecord rec) throws CommandException {
        if (!rec.isConsistent()) {
            throwEntryFormatError(rec, MSG_INCONSISTENT_ENTRY);
        }
        return new Student(parseName(rec), parseAtd(rec), parsePp(rec), parseSubmissions(rec), parseClass(rec));
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
        for (CSVRecord rec : parser) {
            final Student stu = parseFromCsvRec(rec);
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
