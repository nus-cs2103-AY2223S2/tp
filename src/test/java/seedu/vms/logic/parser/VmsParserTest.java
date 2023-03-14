package seedu.vms.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.parser.exceptions.ParseException;

public class VmsParserTest {
    private static final String PATIENT_ADD_STRING = "add --n John Doe --p 98765432 --d 2001-03-19 --b B+ --a catfur "
            + "--a pollen --v covax";
    private static final String PATIENT_DELETE_STRING = "delete --index 5";
    private static final String PATIENT_FIND_STRING = "find --name john";
    private static final String PATIENT_LIST_STRING = "list";
    private static final String APPOINTMENT_ADD_STRING = "add --patient 5 --start 2023-3-5 0700 --end 2023-3-5 0800";
    private static final String APPOINTMENT_DELETE_STRING = "delete --index 5";
    private static final String APPOINTMENT_LIST_STRING = "list";
    private static final String VACCINATION_ADD_STRING = "add Pfizer (Dose 1) --groups DOSE 1, PFIZER, "
            + "VACCINATION --lal 5 --s 56 --a NONE::allergy1, allergy2, allergy3 --h NONE::DOES 1";
    private static final String VALID_ALTERNATIVE_PATIENT = "p";
    private static final String VALID_ALTERNATIVE_APPOINTMENT = "a";
    private static final String VALID_ALTERNATIVE_VACCINATION = "v";
    private static final String INVALID_ALTERNATIVE_PATIENT = "patien";
    private static final String INVALID_ALTERNATIVE_APPOINTMENT = "appointmen";
    private static final String INVALID_ALTERNATIVE_VACCINATION = "vaccinatio";

    private final VmsParser parser = new VmsParser();

    @Test
    public void parseCommand_patient_addCommand() throws ParseException {
        try {
            assertTrue(parser.parseCommand(VALID_ALTERNATIVE_PATIENT + PATIENT_ADD_STRING)
                    instanceof seedu.vms.logic.commands.patient.AddCommand);
            parser.parseCommand(INVALID_ALTERNATIVE_PATIENT + PATIENT_ADD_STRING);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_patient_deleteCommand() throws ParseException {
        try {
            assertTrue(parser.parseCommand(VALID_ALTERNATIVE_APPOINTMENT+ APPOINTMENT_DELETE_STRING)
                    instanceof seedu.vms.logic.commands.patient.DeleteCommand);
            parser.parseCommand(INVALID_ALTERNATIVE_APPOINTMENT + APPOINTMENT_DELETE_STRING);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_patient_findCommand() throws ParseException {
        try {
            assertTrue(parser.parseCommand(VALID_ALTERNATIVE_PATIENT + PATIENT_FIND_STRING)
                    instanceof seedu.vms.logic.commands.patient.FindCommand);
            parser.parseCommand(INVALID_ALTERNATIVE_PATIENT + PATIENT_FIND_STRING);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_patient_listCommand() throws ParseException {
        try {
            assertTrue(parser.parseCommand(VALID_ALTERNATIVE_PATIENT + PATIENT_LIST_STRING)
                    instanceof seedu.vms.logic.commands.patient.ListCommand);
            parser.parseCommand(INVALID_ALTERNATIVE_PATIENT + PATIENT_LIST_STRING);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_appointment_addCommand() throws ParseException {
        try {
            assertTrue(parser.parseCommand(VALID_ALTERNATIVE_APPOINTMENT + APPOINTMENT_ADD_STRING)
                    instanceof seedu.vms.logic.commands.appointment.AddCommand);
            parser.parseCommand(INVALID_ALTERNATIVE_APPOINTMENT + APPOINTMENT_ADD_STRING);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Test
    public void parseCommand_vaccination_addVaxTypeCommand() throws ParseException {
        try {
            assertTrue(parser.parseCommand(VALID_ALTERNATIVE_VACCINATION + VACCINATION_ADD_STRING)
                    instanceof seedu.vms.logic.commands.vaccination.AddVaxTypeCommand);
            parser.parseCommand(INVALID_ALTERNATIVE_VACCINATION + VACCINATION_ADD_STRING);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
