package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import taa.commons.util.CsvUtil;
import taa.logic.commands.exceptions.CommandException;
import taa.logic.parser.ParserUtil;
import taa.logic.parser.exceptions.ParseException;
import taa.model.Model;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;


public class ImportCommand extends Command {
    public final static String COMMAND_WORD = "import";
    public final static String MSG_USAGE = COMMAND_WORD + ": Import data in CSV format from file. Parameter: FILE_PATH";
    public final static String MSG_FILE_NOT_EXIST = "The specified file does not exist.";
    public final static String MSG_FILE_IS_DIR = "The specified file path is a directory.";
    public final static String MSG_FILE_CANT_RD = "The specified file does not grant read permission.";
    public final static String MSG_FILE_ACCESS_DENIED = "Access to the specified file is denied by system.";
    public final static String MSG_FILE_NOT_FOUND = "The specified file cannot be opened for reading.";
    public final static String MSG_RD_IO_EXCEPTION = "An IOException occurred while reading specified file.";
    public final static String MSG_ENTRY_FMT_ERR = "The following entry does not comply with format: ";
    public final static String MSG_INCONSISTENT_ENTRY = "This entry has more columns than defined fields.";
    public static final String MSG_SUCCESS = "%d student(s) added.";
    private static final Predicate<String> IS_UNEMPTY = s -> !s.isEmpty();
    private final File f;

    public ImportCommand(File f) {
        requireNonNull(f);
        this.f = f;
    }

    private static String mkMsgNoColumn(String keyword) {
        return "This entry has no \"" + keyword + "\"column.";
    }

    static Student parseFromCsvRec(CSVRecord record) throws CommandException {
        if (!record.isConsistent()) {
            throw new CommandException(MSG_ENTRY_FMT_ERR + '\"' + record + "\". " + MSG_INCONSISTENT_ENTRY);
        }

        if (!record.isMapped(CsvUtil.KW_NAME)) {
            throw new CommandException(MSG_ENTRY_FMT_ERR + '\"' + record + "\". " + mkMsgNoColumn(CsvUtil.KW_NAME));
        }
        final String name = record.get(CsvUtil.KW_NAME).trim();
        if (!Name.isValidName(name)) {
            throw new CommandException(MSG_ENTRY_FMT_ERR + '\"' + record + "\". " + Name.MESSAGE_CONSTRAINTS);
        }

        if (!record.isMapped(CsvUtil.KW_TAGS)) {
            throw new CommandException(MSG_ENTRY_FMT_ERR + '\"' + record + "\". " + mkMsgNoColumn(CsvUtil.KW_TAGS));
        }
        final String tags = record.get(CsvUtil.KW_TAGS).trim();

        final Set<Tag> parsedTags;
        try {
            //ignore all tokens that are empty strings.
            parsedTags = ParserUtil.parseTags(
                    Arrays.stream(tags.split(" ")).filter(IS_UNEMPTY).collect(Collectors.toList()));
        } catch (ParseException e) {
            throw new CommandException(MSG_ENTRY_FMT_ERR + '\"' + record + "\". " + Tag.MESSAGE_CONSTRAINTS);
        }

        return new Student(new Name(name), parsedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            if (!f.exists()) {
                throw new CommandException(MSG_FILE_NOT_EXIST);
            }
            if (f.isDirectory()) {
                throw new CommandException(MSG_FILE_IS_DIR);
            }
            if (!f.canRead()) {
                throw new CommandException(MSG_FILE_CANT_RD);
            }
        } catch (SecurityException e) {
            throw new CommandException(MSG_FILE_ACCESS_DENIED);
        }

        final FileReader reader;
        try {
            reader = new FileReader(f);
        } catch (FileNotFoundException e) {
            throw new CommandException(MSG_FILE_NOT_FOUND);
        }

        final CSVParser parser;
        try {
            parser = CsvUtil.STU_FMT.parse(reader);
        } catch (IOException e) {
            throw new CommandException(MSG_RD_IO_EXCEPTION);
        }

        int nLines = 0;
        for (CSVRecord record : parser) {
            new AddCommand(parseFromCsvRec(record)).execute(model);
            nLines++;
        }
        return new CommandResult(String.format(MSG_SUCCESS, nLines));
    }
}










