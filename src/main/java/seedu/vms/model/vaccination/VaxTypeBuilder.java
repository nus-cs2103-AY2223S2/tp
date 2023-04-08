package seedu.vms.model.vaccination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.AppUtil;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;


/**
 * A factory class to create a {@code VaxType}.
 */
public class VaxTypeBuilder {
    private static final String FORMAT_IVE_MESSAGE = "The following vaccination constraints have been violated\n%s";

    private final Optional<GroupName> setName;
    private final Optional<HashSet<GroupName>> setGrps;
    private final Optional<Age> setMinAge;
    private final Optional<Age> setMaxAge;
    private final Optional<HashSet<GroupName>> setIngredients;
    private final Optional<List<Requirement>> setHistoryReqs;


    private VaxTypeBuilder(Optional<GroupName> setName, Optional<HashSet<GroupName>> setGrps,
                Optional<Age> setMinAge, Optional<Age> setMaxAge,
                Optional<HashSet<GroupName>> setIngredients, Optional<List<Requirement>> setHistoryReqs) {
        this.setName = setName;
        this.setGrps = setGrps.map(HashSet::new);
        this.setMinAge = setMinAge;
        this.setMaxAge = setMaxAge;
        this.setIngredients = setIngredients.map(HashSet::new);
        this.setHistoryReqs = setHistoryReqs.map(Requirement::copy);
    }


    /**
     * Factory method to create a {@code VaxTypeBuilder}.
     */
    public static VaxTypeBuilder of() {
        return new VaxTypeBuilder(Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
    }


    public VaxTypeBuilder setName(GroupName setName) {
        return new VaxTypeBuilder(Optional.ofNullable(setName), setGrps,
                setMinAge, setMaxAge,
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setGroups(HashSet<GroupName> grps) {
        return new VaxTypeBuilder(setName, Optional.ofNullable(grps),
                setMinAge, setMaxAge,
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setMinAge(Age minAge) {
        return new VaxTypeBuilder(setName, setGrps,
                Optional.ofNullable(minAge), setMaxAge,
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setMaxAge(Age maxAge) {
        return new VaxTypeBuilder(setName, setGrps,
                setMinAge, Optional.ofNullable(maxAge),
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setIngredients(HashSet<GroupName> allergyReqs) {
        return new VaxTypeBuilder(setName, setGrps,
                setMinAge, setMaxAge,
                Optional.ofNullable(allergyReqs), setHistoryReqs);
    }


    public VaxTypeBuilder setHistoryReqs(List<Requirement> historyReqs) {
        return new VaxTypeBuilder(setName, setGrps,
                setMinAge, setMaxAge,
                setIngredients, Optional.ofNullable(historyReqs));
    }


    /**
     * Creates the a vaccination of the specified name with parameters that the
     * builder is set to produce. Any unset attributes will result in its
     * default value to be used.
     *
     * @return the built {@code VaxType}.
     * @throws IllegalValueException if the vaccination cannot be created due
     *      to illegally set parameters.
     */
    public VaxType create(GroupName name) throws IllegalValueException {
        return build(
                new VaxType(
                        name,
                        VaxType.DEFAULT_GROUP_SET,
                        VaxType.DEFAULT_MIN_AGE,
                        VaxType.DEFAULT_MAX_AGE,
                        VaxType.DEFAULT_INGREDIENTS,
                        VaxType.DEFAULT_HISTORY_REQS),
                true);
    }


    /**
     * Creates a vaccination based on the given reference and the parameters
     * that the builder is set to produce.
     *
     * @param reference - the reference {@code VaxType} to build from.
     * @param isSet - if list like attributes should be SET operation.
     * @return the built {@code VaxType}.
     * @throws IllegalValueException if the vaccination cannot be created due
     *      to illegally set parameters.
     */
    public VaxType update(VaxType reference, boolean isSet) throws IllegalValueException {
        return build(reference, isSet);
    }


    private VaxType build(VaxType reference, boolean isSet) throws IllegalValueException {
        GroupName name = setName.orElse(reference.getGroupName());
        Age minAge = setMinAge.orElse(reference.getMinAge());
        Age maxAge = setMaxAge.orElse(reference.getMaxAge());

        HashSet<GroupName> grps = reference.getGroups();
        HashSet<GroupName> ingredients = reference.getIngredients();
        List<Requirement> historyReqs = reference.getHistoryReqs();

        if (isSet) {
            setGrps.ifPresent(grpSet -> setAll(grps, grpSet));
            setIngredients.ifPresent(ingredientSet -> setAll(ingredients, ingredientSet));
            setHistoryReqs.ifPresent(historyReqSet -> setAll(historyReqs, historyReqSet));
        } else {
            setGrps.ifPresent(grpSet -> grps.addAll(grpSet));
            setIngredients.ifPresent(ingredientSet -> ingredients.addAll(ingredientSet));
            setHistoryReqs.ifPresent(historyReqSet -> historyReqs.addAll(historyReqSet));
        }

        Optional<String> errMessage = validateParams(grps, minAge, maxAge, ingredients, historyReqs);
        if (errMessage.isPresent()) {
            throw new IllegalValueException(errMessage.get());
        }

        return new VaxType(name, grps, minAge, maxAge, ingredients, historyReqs);
    }


    private <T> void setAll(Collection<T> to, Collection<T> from) {
        to.clear();
        to.addAll(from);
    }


    private Optional<String> validateParams(HashSet<GroupName> groups,
                Age minAge, Age maxAge,
                HashSet<GroupName> ingredients,
                List<Requirement> historyReq) {
        ArrayList<String> errMessages = new ArrayList<>();

        if (!AppUtil.isWithinLimit(groups, VaxType.LIMIT_GROUPS)) {
            errMessages.add(VaxType.MESSAGE_GROUPS_CONSTRAINTS);
        }
        if (!VaxType.isValidRange(minAge, maxAge)) {
            errMessages.add(VaxType.MESSAGE_AGE_CONSTRAINTS);
        }
        if (!AppUtil.isWithinLimit(ingredients, VaxType.LIMIT_INGREDIENTS)) {
            errMessages.add(VaxType.MESSAGE_INGREDIENTS_CONSTRAINTS);
        }
        if (!AppUtil.isWithinLimit(historyReq, VaxType.LIMIT_HISTORY_REQ)) {
            errMessages.add(VaxType.MESSAGE_HISTORY_REQ_CONSTRAINTS);
        }

        if (errMessages.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(StringUtil.formatErrorMessage(errMessages, FORMAT_IVE_MESSAGE));
    }
}
