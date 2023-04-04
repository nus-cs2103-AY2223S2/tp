package trackr.logic.parser.supplier;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import trackr.logic.commands.supplier.FindSupplierCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.Parser;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.person.PersonNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindSupplierCommand object
 */
public class FindSupplierCommandParser implements Parser<FindSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindSupplierCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        PersonNameContainsKeywordsPredicate predicate = new PersonNameContainsKeywordsPredicate();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] supplierNameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            predicate.setPersonNameKeywords(Arrays.asList(supplierNameKeywords));
        }


        return new FindSupplierCommand(predicate);
    }

}
