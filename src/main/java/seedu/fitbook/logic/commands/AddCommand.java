package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GOAL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;

/**
 * Adds a client to the FitBook.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to the FitBook. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_WEIGHT + "WEIGHT "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_GOAL + "GOAL "
            + "[" + PREFIX_CALORIE + "CALORIE_INTAKE]"
            + "[" + PREFIX_APPOINTMENT + "APPOINTMENT_TIME]..."
            + "[" + PREFIX_ROUTINE + "ROUTINE]..."
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_CALORIE + "2100 "
            + PREFIX_WEIGHT + "23.2 "
            + PREFIX_GENDER + "M "
            + PREFIX_APPOINTMENT + "13-12-2200 19:00 "
            + PREFIX_GOAL + "lose weight "
            + PREFIX_ROUTINE + "Cardio "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney ";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the FitBook";
    public static final String MESSAGE_ROUTINE_NAME_INVALID =
            "Invalid routine used for adding. Please use a valid routine from the routine list.";

    private final Client toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    /**
     * Changes the routine in the toAdd client with the matching one in the model.
     */
    private void addRoutine(FitBookModel model) throws CommandException {
        Set<Routine> routineToAdd = toAdd.getRoutines();
        List<Routine> routinesModel = model.getFitBookExerciseRoutine().getRoutineList();
        List<RoutineName> routineNamesToAdd = new ArrayList<>();
        routineToAdd.forEach(routine -> routineNamesToAdd.add(routine.getRoutineName()));
        Set<Routine> finalRoutineToAdd = new HashSet<>();
        routineNamesToAdd.forEach(routineName -> routinesModel.forEach(routine -> {
            if (routineName.equals(routine.getRoutineName())) {
                finalRoutineToAdd.add(routine);
            }
        }));
        if (routineNamesToAdd.size() != finalRoutineToAdd.size()) {
            throw new CommandException(MESSAGE_ROUTINE_NAME_INVALID);
        }
        toAdd.copyRoutines(finalRoutineToAdd);
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        addRoutine(model);
        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }
        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
