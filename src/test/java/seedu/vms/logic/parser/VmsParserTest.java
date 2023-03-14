package seedu.vms.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.vms.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import org.junit.jupiter.api.Test;
import seedu.vms.logic.parser.exceptions.ParseException;

public class VmsParserTest {
    private static final String patientAddString = "add --n John Doe --p 98765432 --d 2001-03-19 --b B+ --a catfur " +
            "--a pollen --v covax";
    private static final String patientDeleteString = "delete --index 5";
    private static final String patientFindString = "find --name john";
    private static final String patientListString = "list";
    private static final String appointmentAddString = "add --patient 5 --start 2023-3-5 0700 --end 2023-3-5 0800";
    private static final String appointmentDeleteString = "delete --index 5";
    private static final String appointmentListString = "list";
    private static final String vaccinationAddString = "add Pfizer (Dose 1) --groups DOSE 1, PFIZER, " +
            "VACCINATION --lal 5 --s 56 --a NONE::allergy1, allergy2, allergy3 --h NONE::DOES 1";
    private static final String validAlternativePatient = "p";
    private static final String validAlternativeAppointment = "a";
    private static final String validAlternativeVaccination = "v";
    private static final String invalidAlternativePatient = "patien";
    private static final String invalidAlternativeAppointment = "appointmen";
    private static final String invalidAlternativeVaccination = "vaccinatio";

    private final VmsParser parser = new VmsParser();

    @Test
    public void parseCommand_patient_addCommand() throws ParseException{
        try {
            assertTrue(parser.parseCommand(validAlternativePatient + patientAddString)
                    instanceof seedu.vms.logic.commands.patient.AddCommand);
            parser.parseCommand(invalidAlternativePatient+ patientAddString);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_patient_deleteCommand() throws ParseException{
        try {
            assertTrue(parser.parseCommand(validAlternativePatient + patientDeleteString)
                    instanceof seedu.vms.logic.commands.patient.DeleteCommand);
            parser.parseCommand(invalidAlternativePatient+ patientDeleteString);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_patient_findCommand() throws ParseException{
        try {
            assertTrue(parser.parseCommand(validAlternativePatient + patientFindString)
                    instanceof seedu.vms.logic.commands.patient.FindCommand);
            parser.parseCommand(invalidAlternativePatient+ patientFindString);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_patient_listCommand() throws ParseException{
        try {
            assertTrue(parser.parseCommand(validAlternativePatient + patientListString)
                    instanceof seedu.vms.logic.commands.patient.ListCommand);
            parser.parseCommand(invalidAlternativePatient+ patientListString);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_appointment_addCommand() throws ParseException{
        try {
            assertTrue(parser.parseCommand(validAlternativeAppointment + appointmentAddString)
                    instanceof seedu.vms.logic.commands.appointment.AddCommand);
            parser.parseCommand(invalidAlternativeAppointment+ appointmentAddString);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_vaccination_addVaxTypeCommand() throws ParseException{
        try {
            assertTrue(parser.parseCommand(validAlternativeVaccination + vaccinationAddString)
                    instanceof seedu.vms.logic.commands.vaccination.AddVaxTypeCommand);
            parser.parseCommand(invalidAlternativeVaccination+ vaccinationAddString);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
