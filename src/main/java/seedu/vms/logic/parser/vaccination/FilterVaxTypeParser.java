package seedu.vms.logic.parser.vaccination;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.vaccination.FilterVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * Parser of vaccination filter command.
 */
public class FilterVaxTypeParser implements CommandParser {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = VaccinationParser.FEATURE_NAME + " " + COMMAND_WORD
            + ": Finds a vaccination by its name\n"
            + "Syntax: "
            + "vaccination find VAX_NAME\n"
            + "Example: vaccination find dose 1";


    @Override
    public Command parse(ArgumentMultimap args) throws ParseException {
        List<String> namePatterns = parseNamePatterns(args.getPreamble());
        return new FilterVaxTypeCommand(namePatterns);
    }


    private List<String> parseNamePatterns(String namePatternString) throws ParseException {
        if (namePatternString.isBlank()) {
            throw new ParseException(String.format("VAX_NAME: Argument is blank\n%s", MESSAGE_USAGE));
        }
        ArrayList<String> namePatterns = new ArrayList<>();
        try (Scanner scanner = new Scanner(namePatternString)) {
            while (scanner.hasNext()) {
                namePatterns.add(scanner.next());
            }
        }
        return namePatterns;
    }
}
