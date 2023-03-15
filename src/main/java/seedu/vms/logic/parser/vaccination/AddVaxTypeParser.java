package seedu.vms.logic.parser.vaccination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.vms.logic.commands.vaccination.AddVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.Parser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Parser of vaccination type add command arguments.
 */
public class AddVaxTypeParser implements Parser<AddVaxTypeCommand> {
    public static final String COMMAND_WORD = "add";

    // these names are meant to be they align so well
    private static final String FIELD_NAME_VAX_NAME = "Vaccination name";
    private static final String FIELD_NAME_GRP_SET = "Group set";
    private static final String FIELD_NAME_MIN_AGE = "Min age";
    private static final String FIELD_NAME_MAX_AGE = "Max age";
    private static final String FIELD_NAME_ALLERGY = "Allergy requirements";
    private static final String FIELD_NAME_HISTORY = "History requirements";


    @Override
    public AddVaxTypeCommand parse(String args) throws ParseException {
        ArgumentMultimap argsMap = ArgumentTokenizer.tokenize(args);

        VaxTypeBuilder builder = VaxTypeBuilder.of(mapName(argsMap.getPreamble()));

        builder = mapGroupSet(argsMap.getValue(CliSyntax.PREFIX_VAX_GROUPS))
                .map(builder::setGroups)
                .orElse(builder);
        builder = mapAge(argsMap.getValue(CliSyntax.PREFIX_MIN_AGE), FIELD_NAME_MIN_AGE)
                .map(builder::setMinAge)
                .orElse(builder);
        builder = mapAge(argsMap.getValue(CliSyntax.PREFIX_MAX_AGE), FIELD_NAME_MAX_AGE)
                .map(builder::setMaxAge)
                .orElse(builder);
        builder = mapReqList(argsMap.getAllValues(CliSyntax.PREFIX_ALLERGY_REQ), FIELD_NAME_ALLERGY)
                .map(builder::setAllergyReqs)
                .orElse(builder);
        builder = mapReqList(argsMap.getAllValues(CliSyntax.PREFIX_HISTORY_REQ), FIELD_NAME_HISTORY)
                .map(builder::setHistoryReqs)
                .orElse(builder);

        return new AddVaxTypeCommand(builder);
    }


    private GroupName mapName(String nameArg) throws ParseException {
        try {
            return ParserUtil.parseGroupName(nameArg);
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", FIELD_NAME_VAX_NAME, parseEx.getMessage()));
        }
    }


    private Optional<HashSet<GroupName>> mapGroupSet(Optional<String> grpSetArg) throws ParseException {
        if (!grpSetArg.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(ParserUtil.parseGroups(grpSetArg.get()))
                    .map(HashSet::new);
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", FIELD_NAME_GRP_SET, parseEx.getMessage()));
        }
    }


    private Optional<Integer> mapInteger(Optional<String> intArg, String fieldName) throws ParseException {
        if (!intArg.isPresent()) {
            return Optional.empty();
        }
        int value;
        try {
            value = ParserUtil.parseInteger(intArg.get());
            if (value < 0) {
                throw new ParseException("Number must be positive");
            }
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", fieldName, parseEx.getMessage()));
        }
        return Optional.ofNullable(ParserUtil.parseInteger(intArg.get()));
    }


    private Optional<Age> mapAge(Optional<String> intArg, String fieldName) throws ParseException {
        return mapInteger(intArg, fieldName)
                .map(Age::new);
    }


    private Optional<List<Requirement>> mapReqList(List<String> reqStrings, String fieldName)
                throws ParseException {
        if (reqStrings.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<Requirement> reqs = new ArrayList<>();
        try {
            for (String reqString : reqStrings) {
                reqs.add(ParserUtil.parseReq(reqString));
            }
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", fieldName, parseEx.getMessage()));
        }
        return Optional.ofNullable(reqs);
    }
}
