package seedu.vms.model.vaccination;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.vms.commons.exceptions.IllegalValueException;


/**
 * A factory class to create, rename or edit {@link VaxType} to its set
 * {@link VaxTypeManager}.
 */
public class VaxTypeBuilder {
    private static final String MESSAGE_DUPLICATE_TYPE = "Vaccination type already exist";
    private static final String MESSAGE_MISSING_TYPE = "Vaccination type does not exist";

    private final String refName;

    private final String name;
    private final Optional<HashSet<String>> setGrps;
    private final Optional<Integer> setMinAge;
    private final Optional<Integer> setMaxAge;
    private final Optional<Integer> setMinSpacing;
    private final Optional<List<Requirement>> setAllergyReqs;
    private final Optional<List<Requirement>> setHistoryReqs;


    private VaxTypeBuilder(String refName, String name, Optional<HashSet<String>> setGrps,
                Optional<Integer> setMinAge, Optional<Integer> setMaxAge, Optional<Integer> setMinSpacing,
                Optional<List<Requirement>> setAllergyReqs, Optional<List<Requirement>> setHistoryReqs) {
        this.refName = refName;
        this.name = name;
        this.setGrps = setGrps.map(HashSet::new);
        this.setMinAge = setMinAge;
        this.setMaxAge = setMaxAge;
        this.setMinSpacing = setMinSpacing;
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
    public static VaxTypeBuilder of(String refName, String name) {
        return new VaxTypeBuilder(refName, name,
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty());
    }


    /**
     * Factory method to create a {@code VaxTypeBuilder} without a reference.
     *
     * @param name - the name of the {@code VaxType} to create.
     */
    public static VaxTypeBuilder of(String name) {
        return VaxTypeBuilder.of(name, name);
    }


    public VaxTypeBuilder setName(String name) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge, setMinSpacing,
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setGroups(HashSet<String> grps) {
        return new VaxTypeBuilder(refName, name, Optional.ofNullable(grps),
                setMinAge, setMaxAge, setMinSpacing,
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setMinAge(int minAge) {
        return new VaxTypeBuilder(refName, name, setGrps,
                Optional.ofNullable(minAge), setMaxAge, setMinSpacing,
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setMaxAge(int maxAge) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, Optional.ofNullable(maxAge), setMinSpacing,
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setMinSpacing(int minSpacing) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge, Optional.ofNullable(minSpacing),
                setAllergyReqs, setHistoryReqs);
    }


    public VaxTypeBuilder setAllergyReqs(List<Requirement> allergyReqs) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge, setMinSpacing,
                Optional.ofNullable(allergyReqs), setHistoryReqs);
    }


    public VaxTypeBuilder setHistoryReqs(List<Requirement> historyReqs) {
        return new VaxTypeBuilder(refName, name, setGrps,
                setMinAge, setMaxAge, setMinSpacing,
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
        if (manager.contains(refName) || manager.contains(name)) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_TYPE);
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
        if (!manager.contains(refName) || (!refName.equals(name) && manager.contains(name))) {
            throw new IllegalValueException(MESSAGE_MISSING_TYPE);
        }
        return build(manager);
    }


    private VaxType build(VaxTypeManager manager) {
        Optional<VaxType> refVaxType = manager.remove(refName);

        HashSet<String> grps = setGrps.orElse(refVaxType
                .map(VaxType::getGroups)
                .orElse(VaxType.DEFAULT_GROUP_SET));
        Integer minAge = setMinAge.orElse(refVaxType
                .map(VaxType::getMinAge)
                .orElse(VaxType.DEFAULT_MIN_AGE));
        Integer maxAge = setMaxAge.orElse(refVaxType
                .map(VaxType::getMaxAge)
                .orElse(VaxType.DEFAULT_MAX_AGE));
        Integer minSpacing = setMinSpacing.orElse(refVaxType
                .map(VaxType::getMinSpacing)
                .orElse(VaxType.DEFAULT_MIN_SPACING));
        List<Requirement> allergyReqs = setAllergyReqs.orElse(refVaxType
                .map(VaxType::getAllergyReqs)
                .orElse(VaxType.DEFAULT_ALLERGY_REQS));
        List<Requirement> historyReqs = setHistoryReqs.orElse(refVaxType
                .map(VaxType::getHistoryReqs)
                .orElse(VaxType.DEFAULT_HISTORY_REQS));

        VaxType vaxType = new VaxType(name, grps, minAge, maxAge, minSpacing, allergyReqs, historyReqs);
        manager.add(vaxType);
        return vaxType;
    }
}
