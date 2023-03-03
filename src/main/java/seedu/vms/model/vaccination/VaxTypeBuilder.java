package seedu.vms.model.vaccination;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


/**
 * A factory class to create, rename or edit {@link VaxType} to its set
 * {@link VaxTypeStorage}.
 */
public class VaxTypeBuilder {
    private final VaxTypeStorage storage;
    private final Optional<String> oriName;

    private final String name;
    private final HashSet<String> grps;
    private final int minAge;
    private final int maxAge;
    private final int minSpacing;
    private final List<Requirement> allergyReqs;
    private final List<Requirement> historyReqs;


    private VaxTypeBuilder(VaxTypeStorage storage, Optional<String> oriName,
                String name, HashSet<String> grps, int minAge, int maxAge, int minSpacing,
                List<Requirement> allergyReqs, List<Requirement> historyReqs) {
        this.storage = storage;
        this. oriName = oriName;
        this.name = name;
        this.grps = new HashSet<>(grps);
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.allergyReqs = Requirement.copy(allergyReqs);
        this.historyReqs = Requirement.copy(historyReqs);
    }


    public static VaxTypeBuilder of(VaxTypeStorage storage, String name) {
        Optional<VaxType> vaxType = storage.get(name);
        Optional<String> oriName = vaxType.map(VaxType::getName);
        HashSet<String> grps = vaxType.map(VaxType::getGroups)
                .orElse(VaxType.DEFAULT_GROUP_SET);
        int minAge = vaxType.map(VaxType::getMinAge)
                .orElse(VaxType.DEFAULT_MIN_AGE);
        int maxAge = vaxType.map(VaxType::getMaxAge)
                .orElse(VaxType.DEFAULT_MAX_AGE);
        int minSpacing = vaxType.map(VaxType::getMinSpacing)
                .orElse(VaxType.DEFAULT_MIN_SPACING);
        List<Requirement> allergyReqs = vaxType.map(VaxType::getAllergyReqs)
                .orElse(VaxType.DEFAULT_ALLERGY_REQS);
        List<Requirement> historyReqs = vaxType.map(VaxType::getHistoryReqs)
                .orElse(VaxType.DEFAULT_HISTORY_REQS);

        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxTypeBuilder setName(String name) {
        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxTypeBuilder setGroups(HashSet<String> grps) {
        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxTypeBuilder setMinAge(int minAge) {
        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxTypeBuilder setMaxAge(int maxAge) {
        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxTypeBuilder setMinSpacing(int minSpacing) {
        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxTypeBuilder setAllergyReqs(List<Requirement> allergyReqs) {
        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxTypeBuilder setHistoryReqs(List<Requirement> historyReqs) {
        return new VaxTypeBuilder(storage, oriName,
                name, grps, minAge, maxAge, minSpacing,
                allergyReqs, historyReqs);
    }


    public VaxType build() {
        oriName.ifPresent(storage::remove);
        VaxType vaxType = new VaxType(name, grps, minAge, maxAge, minSpacing,
                historyReqs, allergyReqs);
        storage.add(vaxType);
        return vaxType;
    }
}
