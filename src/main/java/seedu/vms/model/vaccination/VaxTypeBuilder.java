package seedu.vms.model.vaccination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.vms.commons.core.ValueChange;
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

    private static final String MESSAGE_DUPLICATE_TYPE = "Vaccination type already exist: %s";
    private static final String MESSAGE_MISSING_TYPE = "Vaccination type does not exist: %s";

    private final GroupName refName;
    private final GroupName name;
    private final Optional<HashSet<GroupName>> setGrps;
    private final Optional<Age> setMinAge;
    private final Optional<Age> setMaxAge;
    private final Optional<HashSet<GroupName>> setIngredients;
    private final Optional<List<Requirement>> setHistoryReqs;


    private VaxTypeBuilder(GroupName refName, GroupName name, Optional<HashSet<GroupName>> setGrps,
                Optional<Age> setMinAge, Optional<Age> setMaxAge,
                Optional<HashSet<GroupName>> setIngredients, Optional<List<Requirement>> setHistoryReqs) {
        this.refName = refName;
        this.name = name;
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
     * @param name - the name of the {@code VaxType} to create.
     */
    public static VaxTypeBuilder of(GroupName refName, GroupName name) {
        return new VaxTypeBuilder(refName, name,
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
    }


    /**
     * Factory method to create a {@code VaxTypeBuilder} without a reference.
     *
     * @param name - the name of the {@code VaxType} to create.
     */
    public static VaxTypeBuilder of(GroupName name) {
        return VaxTypeBuilder.of(name, name);
    }


    public VaxTypeBuilder setName(GroupName name) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge,
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setGroups(HashSet<GroupName> grps) {
        return new VaxTypeBuilder(refName, name, Optional.ofNullable(grps),
                setMinAge, setMaxAge,
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setMinAge(Age minAge) {
        return new VaxTypeBuilder(refName, name, setGrps,
                Optional.ofNullable(minAge), setMaxAge,
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setMaxAge(Age maxAge) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, Optional.ofNullable(maxAge),
                setIngredients, setHistoryReqs);
    }


    public VaxTypeBuilder setIngredients(HashSet<GroupName> allergyReqs) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge,
                Optional.ofNullable(allergyReqs), setHistoryReqs);
    }


    public VaxTypeBuilder setHistoryReqs(List<Requirement> historyReqs) {
        return new VaxTypeBuilder(refName, name, setGrps,
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
    public ValueChange<VaxType> create(VaxTypeManager manager) throws IllegalValueException {
        if (manager.contains(refName.toString()) || manager.contains(name.toString())) {
            throw new IllegalValueException(String.format(MESSAGE_DUPLICATE_TYPE, name.toString()));
        }
        return build(manager);
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
    public ValueChange<VaxType> update(VaxTypeManager manager) throws IllegalValueException {
        if (!manager.contains(refName.toString())) {
            throw new IllegalValueException(String.format(MESSAGE_MISSING_TYPE, refName.toString()));
        }
        if (!refName.equals(name) && manager.contains(name.getName())) {
            throw new IllegalValueException(String.format(MESSAGE_DUPLICATE_TYPE, name.toString()));
        }
        return build(manager);
    }


    private ValueChange<VaxType> build(VaxTypeManager manager) throws IllegalValueException {
        Optional<VaxType> refVaxType = manager.get(refName.toString());

        HashSet<GroupName> grps = setGrps.orElse(refVaxType
                .map(VaxType::getGroups)
                .orElse(VaxType.DEFAULT_GROUP_SET));
        Age minAge = setMinAge.orElse(refVaxType
                .map(VaxType::getMinAge)
                .orElse(VaxType.DEFAULT_MIN_AGE));
        Age maxAge = setMaxAge.orElse(refVaxType
                .map(VaxType::getMaxAge)
                .orElse(VaxType.DEFAULT_MAX_AGE));
        HashSet<GroupName> ingredients = setIngredients.orElse(refVaxType
                .map(VaxType::getIngredients)
                .orElse(VaxType.DEFAULT_INGREDIENTS));
        List<Requirement> historyReqs = setHistoryReqs.orElse(refVaxType
                .map(VaxType::getHistoryReqs)
                .orElse(VaxType.DEFAULT_HISTORY_REQS));

        Optional<String> errMessage = validateParams(grps, minAge, maxAge, ingredients, historyReqs);
        if (errMessage.isPresent()) {
            throw new IllegalValueException(errMessage.get());
        }

        VaxType newValue = new VaxType(name, grps, minAge, maxAge, ingredients, historyReqs);
        VaxType oldValue = manager.remove(refName.toString()).orElse(null);
        manager.add(newValue);
        return new ValueChange<>(oldValue, newValue);
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
