package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ViewProfileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;



/**
 * Parses input arguments and creates a new ViewProfileCommand object
 */
public class ViewProfileCommandParser implements Parser {
    @Override
    public Command parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewProfileCommand.MESSAGE_USAGE));
        }

        Predicate<Student> namePredicate;
        List<String> nameList = new ArrayList<>();
        boolean defaultPredicateFlag;

        // If name is present, create a predicate to filter by name
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {

            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            // for all the names, trim the name and only take the first word
            for (int i = 0; i < nameKeywords.size(); i++) {
                String name = nameKeywords.get(i);
                name = name.trim();
                if (name.trim().isEmpty()) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            ViewProfileCommand.MESSAGE_USAGE));
                }
                int spaceIndex = name.indexOf(" ");
                nameKeywords.set(i, name);
            }
            nameList = nameKeywords;
            namePredicate = new NamePredicate(nameKeywords);
        } else {
            namePredicate = PREDICATE_SHOW_ALL_STUDENTS;
        }


        return new ViewProfileCommand(nameList, namePredicate);
    }
}
