package seedu.careflow.model.drug;

import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Drug in the drug inventory
 */
public class Drug {
    // Identity fields
    private final TradeName tradeName;

    // Data fields
    private final ActiveIngredient activeIngredient;
    private final Direction direction;
    private final Purpose purposes;
    private final SideEffect sideEffects;
    private final StorageCount storageCount;

    /**
     * Every field must be present and not null.
     */
    public Drug(TradeName tradeName, ActiveIngredient activeIngredient, Direction direction,
                Purpose purposes, SideEffect sideEffects,
                StorageCount storageCount) {
        requireAllNonNull(tradeName, activeIngredient, direction, purposes, sideEffects,
                storageCount);
        this.tradeName = tradeName;
        this.activeIngredient = activeIngredient;
        this.direction = direction;
        this.purposes = purposes;
        this.sideEffects = sideEffects;
        this.storageCount = storageCount;
    }

    public TradeName getTradeName() {
        return tradeName;
    }

    public ActiveIngredient getActiveIngredient() {
        return activeIngredient;
    }

    public Direction getDirection() {
        return direction;
    }

    public Purpose getPurposes() {
        return purposes;
    }

    public StorageCount getStorageCount() {
        return storageCount;
    }

    /**
     * Returns an immutable side effect set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public SideEffect getSideEffects() {
        return sideEffects;
    }

    /**
     * Returns true if both drugs have the same name.
     * This defines a weaker notion of equality between two drugs.
     */
    public boolean isSameDrug(Drug otherDrug) {
        if (otherDrug == this) {
            return true;
        }
        return otherDrug != null
                && otherDrug.getTradeName().equals(getTradeName());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Drug)) {
            return false;
        }
        Drug otherDrug = (Drug) other;
        return getTradeName().equals(otherDrug.getTradeName())
                && getActiveIngredient().equals(otherDrug.getActiveIngredient())
                && getDirection().equals(otherDrug.getDirection())
                && getPurposes().equals(otherDrug.getPurposes())
                && getSideEffects().equals(otherDrug.getSideEffects())
                && getStorageCount().equals(otherDrug.getStorageCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeName, activeIngredient, direction,
                purposes, sideEffects, storageCount);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTradeName())
                .append("; Active Ingredient: ")
                .append(getActiveIngredient())
                .append("; Direction: ")
                .append(getDirection())
                .append("; Storage Count: ")
                .append(getStorageCount())
                .append("; Purposes: ")
                .append(getPurposes())
                .append("; Side Effects: ")
                .append(getSideEffects());

        return builder.toString();
    }
}
