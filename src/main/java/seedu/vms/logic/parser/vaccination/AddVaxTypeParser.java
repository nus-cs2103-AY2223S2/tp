package seedu.vms.logic.parser.vaccination;

import java.util.HashSet;
import java.util.Optional;

import seedu.vms.logic.commands.vaccination.AddVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.Parser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Parser of vaccination type add command arguments.
 */
public class AddVaxTypeParser implements Parser<AddVaxTypeCommand> {
    public static final String COMMAND_WORD = "add";


    @Override
    public AddVaxTypeCommand parse(String args) throws ParseException {
        ArgumentMultimap argsMap = ArgumentTokenizer.tokenize(args);

        GroupName name = ParserUtil.parseGroupName(argsMap.getPreamble());

        VaxTypeBuilder builder = VaxTypeBuilder.of(name);

        builder = mapGroupSet(argsMap.getValue(CliSyntax.PREFIX_VAX_GROUPS))
                .map(builder::setGroups)
                .orElse(builder);
        builder = mapInteger(argsMap.getValue(CliSyntax.PREFIX_MIN_AGE))
                .map(builder::setMinAge)
                .orElse(builder);
        builder = mapInteger(argsMap.getValue(CliSyntax.PREFIX_MAX_AGE))
                .map(builder::setMaxAge)
                .orElse(builder);
        builder = mapInteger(argsMap.getValue(CliSyntax.PREFIX_MIN_SPACING))
                .map(builder::setMinSpacing)
                .orElse(builder);

        return new AddVaxTypeCommand(builder);
    }


    private Optional<HashSet<GroupName>> mapGroupSet(Optional<String> grpSetArg) throws ParseException {
        if (!grpSetArg.isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ParserUtil.parseGroups(grpSetArg.get()))
                .map(HashSet::new);
    }


    private Optional<Integer> mapInteger(Optional<String> intArg) throws ParseException {
        if (!intArg.isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ParserUtil.parseInteger(intArg.get()));
    }
}
