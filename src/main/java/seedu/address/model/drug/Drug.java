package seedu.address.model.drug;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Drug in the drug inventory
 */
public class Drug {
    // Identity fields
    private final TradeName tradeName;

    // Data fields
    private final ActiveIngredient activeIngredient;
    private final Direction direction;
    private final ExpiryDate expiryDate;
    private final Set<Purpose> purposes;
    private final Set<SideEffect> sideEffects;
    private final StorageCount storageCount;

    /**
     * Every field must be present and not null.
     */
    public Drug(TradeName tradeName, ActiveIngredient activeIngredient, Direction direction,
                ExpiryDate expiryDate, Set<Purpose> purposes, Set<SideEffect> sideEffects, StorageCount storageCount) {
        requireAllNonNull(tradeName, activeIngredient, direction, expiryDate, purposes, sideEffects, storageCount);
        this.tradeName = tradeName;
        this.activeIngredient = activeIngredient;
        this.direction = direction;
        this.expiryDate = expiryDate;
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

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public Set<Purpose> getPurposes() {
        return Collections.unmodifiableSet(purposes);
    }

    public StorageCount getStorageCount() {
        return storageCount;
    }

    /**
     * Returns an immutable side effect set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<SideEffect> getSideEffects() {
        return Collections.unmodifiableSet(sideEffects);
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
                && getExpiryDate().equals(otherDrug.getExpiryDate())
                && getPurposes().equals(otherDrug.getPurposes())
                && getSideEffects().equals(otherDrug.getSideEffects())
                && getStorageCount().equals(otherDrug.getStorageCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeName, activeIngredient, direction, expiryDate,
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
                .append("; Expiry Date: ")
                .append(getExpiryDate())
                .append("; Storage Count: ")
                .append(getStorageCount());

        Set<Purpose> purposes = getPurposes();
        if (!purposes.isEmpty()) {
            builder.append("; Purposes: ");
            purposes.forEach(builder::append);
        }
        Set<SideEffect> sideEffects = getSideEffects();
        if (!sideEffects.isEmpty()) {
            builder.append("; Side Effects: ");
            sideEffects.forEach(builder::append);
        }
        return builder.toString();
    }
}
