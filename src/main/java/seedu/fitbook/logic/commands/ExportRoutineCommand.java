package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.core.CsvConfig.CSV_EXTENSION;
import static seedu.fitbook.commons.core.CsvConfig.FILE_NAME_ROUTINE;
import static seedu.fitbook.commons.core.CsvConfig.COMMA_SEPARATOR;
import static seedu.fitbook.commons.core.CsvConfig.NEW_LINE;
import static seedu.fitbook.commons.core.CsvConfig.WHITE_SPACE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;

/**
 * Exports the routine and exercise details into a csv file.
 */
public class ExportRoutineCommand extends Command {

    public static final String COMMAND_WORD = "exportRoutine";
    public static final String MESSAGE_SUCCESS = "RoutineFile has been exported.";
    public static final String MESSAGE_FAILURE = "File could not be exported. Ensure the csv file is not opened in the "
            + "background ";

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        String feedback = writeToCsvFile(model);
        return new CommandResult(feedback);
    }

    /**
     * Writes to the csv file with details of {@code Routine}
     * @param model model {@code FitBookModel} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    public static String writeToCsvFile(FitBookModel model) throws CommandException {
        List<Routine> routines = model.getFilteredRoutineList().stream().collect(Collectors.toList());
        try {
            File csv = new File(FILE_NAME_ROUTINE + CSV_EXTENSION);
            PrintWriter pw = new PrintWriter(csv);
            writeHeaderRow(pw);
            writeRoutineRows(pw, routines);
        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        return ExportRoutineCommand.MESSAGE_SUCCESS;
    }

    /**
     * Writes the header row in the csv file.
     * @param pw the PrintWriter responsible for writing the header row into the csv file.
     */
    public static void writeHeaderRow(PrintWriter pw) {
        pw.printf("Routine, Exercises\n");
    }

    /**
     * Writes the routine and exercise rows into the csv file.
     * @param pw the PrintWriter responsible for writing rows into the csv file.
     * @param routines List of routines stored in FitBook.
     */
    public static void writeRoutineRows(PrintWriter pw, List<Routine> routines) {
        StringBuilder s = new StringBuilder("");
        for (Routine routine : routines) {
            s.append(routine.getRoutineName().toString() + COMMA_SEPARATOR);
            for(Exercise exercise : routine.getExercises()) {
                s.append(exercise.exerciseName + WHITE_SPACE );
            }
            s.append(NEW_LINE);
        }
        pw.print(s);
        pw.close();
    }

}