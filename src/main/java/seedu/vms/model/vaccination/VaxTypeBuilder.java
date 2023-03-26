package seedu.vms.model.vaccination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;


/**
 * A factory class to create, rename or edit {@link VaxType} to its set
 * {@link VaxTypeManager}.
 */
public class VaxTypeBuilder {
    private static final String FORMAT_IVE_MESSAGE = "The following vaccination constraints have been violated\n%s";
    private static final String FORMAT_CONSTRAINTS = "- %s\n";

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
     *
     * @param refName - the name of the existing vaccination type to build
     *      from.
     * @param setName - the name of the {@code VaxType} to create.
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
     * Builds the vaccination type and adds it to the specified
     * {@code VaxTypeManager}.
     *
     * @return the built {@code VaxType}.
     * @throws IllegalValueException if the a vaccination type might be
     *      replaced.
     */
    public VaxType create(GroupName name) throws IllegalValueException {
        return build(new VaxType(
                name,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_INGREDIENTS,
                VaxType.DEFAULT_HISTORY_REQS));
    }


    /**
     * Builds and updates the vaccination type in the specified
     * {@code VaxTypeManager}.
     *
     * @return the built {@code VaxType}.
     * @throws IllegalValueException if the reference vaccination is not
     *      present or if the vaccination type is being renamed to one that
     *      already exists.
     */
    public VaxType update(VaxType reference) throws IllegalValueException {
        return build(reference);
    }


    private VaxType build(VaxType reference) throws IllegalValueException {
        GroupName name = setName.orElse(reference.getGroupName());
        HashSet<GroupName> grps = setGrps.orElse(reference.getGroups());
        Age minAge = setMinAge.orElse(reference.getMinAge());
        Age maxAge = setMaxAge.orElse(reference.getMaxAge());
        HashSet<GroupName> ingredients = setIngredients.orElse(reference.getIngredients());
        List<Requirement> historyReqs = setHistoryReqs.orElse(reference.getHistoryReqs());

        Optional<String> errMessage = validateParams(grps, minAge, maxAge, ingredients, historyReqs);
        if (errMessage.isPresent()) {
            throw new IllegalValueException(errMessage.get());
        }

        return new VaxType(name, grps, minAge, maxAge, ingredients, historyReqs);
    }


    private Optional<String> validateParams(HashSet<GroupName> groups,
                Age minAge, Age maxAge,
                HashSet<GroupName> ingredients,
                List<Requirement> historyReq) {
        ArrayList<String> errMessages = new ArrayList<>();

        if (!VaxType.isValidGroups(groups)) {
            errMessages.add(VaxType.MESSAGE_GROUPS_CONSTRAINTS);
        }
        if (!VaxType.isValidRange(minAge, maxAge)) {
            errMessages.add(VaxType.MESSAGE_AGE_CONSTRAINTS);
        }
        if (!VaxType.isValidIngredients(ingredients)) {
            errMessages.add(VaxType.MESSAGE_INGREDIENTS_CONSTRAINTS);
        }
        if (!VaxType.isValidHistoryReq(historyReq)) {
            errMessages.add(VaxType.MESSAGE_HISTORY_REQ_CONSTRAINTS);
        }

        if (errMessages.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(formatErrorMessage(errMessages));
    }


    private String formatErrorMessage(List<String> errMessages) {
        StringBuilder builder = new StringBuilder();

        for (String message : errMessages) {
            builder.append(String.format(FORMAT_CONSTRAINTS, message));
        }

        return String.format(FORMAT_IVE_MESSAGE, builder.toString());
    }
}
