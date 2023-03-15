package seedu.vms.model.vaccination;

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
    private static final String MESSAGE_DUPLICATE_TYPE = "Vaccination type already exist: %s";
    private static final String MESSAGE_MISSING_TYPE = "Vaccination type does not exist: %s";

    private final GroupName refName;
    private final GroupName name;
    private final Optional<HashSet<GroupName>> setGrps;
    private final Optional<Age> setMinAge;
    private final Optional<Age> setMaxAge;
    private final Optional<List<Requirement>> setAllergyReqs;
    private final Optional<List<Requirement>> setHistoryReqs;


    private VaxTypeBuilder(GroupName refName, GroupName name, Optional<HashSet<GroupName>> setGrps,
                Optional<Age> setMinAge, Optional<Age> setMaxAge,
                Optional<List<Requirement>> setAllergyReqs, Optional<List<Requirement>> setHistoryReqs) {
        this.refName = refName;
        this.name = name;
        this.setGrps = setGrps.map(HashSet::new);
        this.setMinAge = setMinAge;
        this.setMaxAge = setMaxAge;
        this.setAllergyReqs = setAllergyReqs.map(Requirement::copy);
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
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setGroups(HashSet<GroupName> grps) {
        return new VaxTypeBuilder(refName, name, Optional.ofNullable(grps),
                setMinAge, setMaxAge,
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setMinAge(Age minAge) {
        return new VaxTypeBuilder(refName, name, setGrps,
                Optional.ofNullable(minAge), setMaxAge,
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setMaxAge(Age maxAge) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, Optional.ofNullable(maxAge),
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setAllergyReqs(List<Requirement> allergyReqs) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge,
                Optional.ofNullable(allergyReqs), setHistoryReqs);
    }


    public VaxTypeBuilder setHistoryReqs(List<Requirement> historyReqs) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge,
                setAllergyReqs, Optional.ofNullable(historyReqs));
    }


    /**
     * Builds the vaccination type and adds it to the specified
     * {@code VaxTypeManager}.
     *
     * @return the built {@code VaxType}.
     * @throws IllegalValueException if the a vaccination type might be
     *      replaced.
     */
    public VaxType create(VaxTypeManager manager) throws IllegalValueException {
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
    public VaxType update(VaxTypeManager manager) throws IllegalValueException {
        if (!manager.contains(refName.toString())) {
            throw new IllegalValueException(String.format(MESSAGE_MISSING_TYPE, refName.toString()));
        }
        if (!refName.equals(name) && manager.contains(name.getName())) {
            throw new IllegalValueException(String.format(MESSAGE_DUPLICATE_TYPE, name.toString()));
        }
        return build(manager);
    }


    private VaxType build(VaxTypeManager manager) throws IllegalValueException {
        Optional<VaxType> refVaxType = manager.remove(refName.toString());

        HashSet<GroupName> grps = setGrps.orElse(refVaxType
                .map(VaxType::getGroups)
                .orElse(VaxType.DEFAULT_GROUP_SET));
        Age minAge = setMinAge.orElse(refVaxType
                .map(VaxType::getMinAge)
                .orElse(VaxType.DEFAULT_MIN_AGE));
        Age maxAge = setMaxAge.orElse(refVaxType
                .map(VaxType::getMaxAge)
                .orElse(VaxType.DEFAULT_MAX_AGE));
        List<Requirement> allergyReqs = setAllergyReqs.orElse(refVaxType
                .map(VaxType::getAllergyReqs)
                .orElse(VaxType.DEFAULT_ALLERGY_REQS));
        List<Requirement> historyReqs = setHistoryReqs.orElse(refVaxType
                .map(VaxType::getHistoryReqs)
                .orElse(VaxType.DEFAULT_HISTORY_REQS));

        if (!VaxType.isValidRange(minAge, maxAge)) {
            throw new IllegalValueException(VaxType.MESSAGE_AGE_CONSTRAINTS);
        }

        VaxType vaxType = new VaxType(name, grps, minAge, maxAge, allergyReqs, historyReqs);
        manager.add(vaxType);
        return vaxType;
    }
}
