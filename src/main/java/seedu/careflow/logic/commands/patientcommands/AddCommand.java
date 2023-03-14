package seedu.careflow.logic.commands.patientcommands;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT_NUMBER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.person.Patient;

/**
 * Adds a patient to the patient records
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to patient records.\n"
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_PHONE + " PHONE "
            + PREFIX_EMAIL + " EMAIL "
            + PREFIX_ADDRESS + " ADDRESS "
            + PREFIX_DOB + " DOB "
            + PREFIX_GENDER + " GENDER "
            + PREFIX_IC + " IC "
            + PREFIX_DRUG_ALLERGY + " DRUG_ALLERGY "
            + PREFIX_EMERGENCY_CONTACT_NUMBER + " EMERGENCY_CONTACT_NUMBER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " Tom Smith "
            + PREFIX_PHONE + " 84356788 "
            + PREFIX_EMAIL + " tsmith@gmail.com "
            + PREFIX_ADDRESS + " 969 Lock Street #04-32 "
            + PREFIX_DOB + " 2001-05-28 "
            + PREFIX_GENDER + " MALE "
            + PREFIX_IC + " T0278234M "
            + PREFIX_DRUG_ALLERGY + " Aspirin "
            + PREFIX_EMERGENCY_CONTACT_NUMBER + " 93746552";


    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists.";
    private final Patient patientToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     * @param patient Patient to be added
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        patientToAdd = patient;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddCommand
                && patientToAdd.equals(((AddCommand) other).patientToAdd));
    }

    @Override
    public CommandResult execute(CareFlowModel careFlowModel) throws CommandException {
        requireNonNull(careFlowModel);

        if (careFlowModel.hasPatient(patientToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        careFlowModel.addPatient(patientToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, patientToAdd));
    }
}
