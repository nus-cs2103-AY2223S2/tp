package seedu.vms.logic.parser.vaccination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * A command parser to parse commands that require {@link VaxTypeBuilder}.
 */
public abstract class VaxTypeBuilderParser implements CommandParser {
    private static final String FIELD_NAME_VAX_NAME = "VAX_NAME";
    private static final String FIELD_NAME_GRP_SET = "GROUP";
    private static final String FIELD_NAME_MIN_AGE = "MIN_AGE";
    private static final String FIELD_NAME_MAX_AGE = "MAX_AGE";
    private static final String FIELD_NAME_INGREDIENTS = "INGREDIENT";
    private static final String FIELD_NAME_HISTORY = "HISTORY_REQ";


    /**
     * Parses a {@link VaxTypeBuilder} from the given argument map. However,
     * unlike {@link #parseBuilder(ArgumentMultimap)}, the rename parameters
     * are ignored.
     *
     * @param argsMap - the argument map to parse.
     * @return the parsed builder.
     * @throws ParseException if the builder cannot be parsed.
     */
    protected VaxTypeBuilder parseBuilderNoRename(ArgumentMultimap argsMap) throws ParseException {
        VaxTypeBuilder builder = VaxTypeBuilder.of();

        builder = mapGroupSet(argsMap.getAllValues(CliSyntax.PREFIX_VAX_GROUPS), FIELD_NAME_GRP_SET)
                .map(builder::setGroups)
                .orElse(builder);
        builder = mapAge(argsMap.getValue(CliSyntax.PREFIX_MIN_AGE), FIELD_NAME_MIN_AGE)
                .map(builder::setMinAge)
                .orElse(builder);
        builder = mapAge(argsMap.getValue(CliSyntax.PREFIX_MAX_AGE), FIELD_NAME_MAX_AGE)
                .map(builder::setMaxAge)
                .orElse(builder);
        builder = mapGroupSet(argsMap.getAllValues(CliSyntax.PREFIX_INGREDIENTS), FIELD_NAME_INGREDIENTS)
                .map(builder::setIngredients)
                .orElse(builder);
        builder = mapReqList(argsMap.getAllValues(CliSyntax.PREFIX_HISTORY_REQ), FIELD_NAME_HISTORY)
                .map(builder::setHistoryReqs)
                .orElse(builder);

        return builder;
    }


    /**
     * Parses a {@link VaxTypeBuilder} from the given argument map.
     *
     * @param argsMap - the argument map to parse.
     * @return the parsed builder.
     * @throws ParseException if the builder cannot be parsed.
     */
    protected VaxTypeBuilder parseBuilder(ArgumentMultimap argsMap) throws ParseException {
        VaxTypeBuilder builder = parseBuilderNoRename(argsMap);

        builder = mapRenamedName(argsMap.getValue(CliSyntax.PREFIX_NAME))
                .map(builder::setName)
                .orElse(builder);

        return builder;
    }


    private GroupName mapName(String nameArg) throws ParseException {
        try {
            return ParserUtil.parseGroupName(nameArg);
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", FIELD_NAME_VAX_NAME, parseEx.getMessage()));
        }
    }


    private Optional<GroupName> mapRenamedName(Optional<String> newName) throws ParseException {
        if (!newName.isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(mapName(newName.get()));
    }


    private Optional<HashSet<GroupName>> mapGroupSet(Collection<String> grpArgs, String fieldName)
                throws ParseException {
        if (grpArgs.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(ParserUtil.parseGroups(grpArgs));
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", fieldName, parseEx.getMessage()));
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
