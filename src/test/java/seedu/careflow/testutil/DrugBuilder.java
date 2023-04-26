package seedu.careflow.testutil;

import seedu.careflow.model.drug.ActiveIngredient;
import seedu.careflow.model.drug.Direction;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.Purpose;
import seedu.careflow.model.drug.SideEffect;
import seedu.careflow.model.drug.StorageCount;
import seedu.careflow.model.drug.TradeName;

/**
 * A utility class to help with building Drug objects.
 * Example usage: <br>
 *     {@code Drug ab = new DrugBuilder().withTradeName("Panadol").build();}
 */
public class DrugBuilder {
    public static final String DEFAULT_TRADE_NAME = "Tylenol";
    public static final String DEFAULT_ACTIVE_INGREDIENT = "Acetaminophen";
    public static final String DEFAULT_DIRECTION = "The usual recommended dose of Tylenol for adults is 325 to "
            + "1000 mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
            + "based on age, weight, and other medical conditions.";
    public static final String DEFAULT_PURPOSE = "Tylenol is a pain reliever and fever reducer that is "
            + "used to treat mild to moderate pain, such as headaches, toothaches, menstrual cramps, "
            + "back pain, and arthritis.";
    public static final String DEFAULT_SIDE_EFFECT = "Common side effects of Tylenol may include nausea, "
            + "vomiting, stomach pain, and constipation. Less common side effects may include rash, "
            + "itching, and hives.";
    public static final String DEFAULT_STORAGE_COUNT = "20";

    private TradeName tradeName;
    private ActiveIngredient activeIngredient;
    private Direction direction;
    private Purpose purpose;
    private SideEffect sideEffect;
    private StorageCount storageCount;

    /**
     * Creates a {@code DrugBuilder} with the default details.
     */
    public DrugBuilder() {
        tradeName = new TradeName(DEFAULT_TRADE_NAME);
        activeIngredient = new ActiveIngredient(DEFAULT_ACTIVE_INGREDIENT);
        direction = new Direction(DEFAULT_DIRECTION);
        purpose = new Purpose(DEFAULT_PURPOSE);
        sideEffect = new SideEffect(DEFAULT_SIDE_EFFECT);
        storageCount = new StorageCount(DEFAULT_STORAGE_COUNT);
    }

    /**
     * Initializes the DrugBuilder with the data of {@code drugToCopy}.
     */
    public DrugBuilder(Drug drugToCopy) {
        tradeName = drugToCopy.getTradeName();
        activeIngredient = drugToCopy.getActiveIngredient();
        direction = drugToCopy.getDirection();
        purpose = drugToCopy.getPurposes();
        sideEffect = drugToCopy.getSideEffects();
        storageCount = drugToCopy.getStorageCount();
    }

    /**
     * Sets the {@code TradeName} of the {@code Drug} that we are building.
     */
    public DrugBuilder withTradeName(String tradeName) {
        this.tradeName = new TradeName(tradeName);
        return this;
    }

    /**
     * Sets the {@code ActiveIngredient} of the {@code Drug} that we are building.
     */
    public DrugBuilder withActiveIngredient(String activeIngredient) {
        this.activeIngredient = new ActiveIngredient(activeIngredient);
        return this;
    }

    /**
     * Sets the {@code Direction} of the {@code Drug} that we are building.
     */
    public DrugBuilder withDirection(String direction) {
        this.direction = new Direction(direction);
        return this;
    }

    /**
     * Sets the {@code Purpose} of the {@code Drug} that we are building.
     */
    public DrugBuilder withPurpose(String purpose) {
        this.purpose = new Purpose(purpose);
        return this;
    }

    /**
     * Sets the {@code SideEffect} of the {@code Drug} that we are building.
     */
    public DrugBuilder withSideEffect(String sideEffect) {
        this.sideEffect = new SideEffect(sideEffect);
        return this;
    }

    /**
     * Sets the {@code StorageCount} of the {@code Drug} that we are building.
     */
    public DrugBuilder withStorageCount(String storageCount) {
        this.storageCount = new StorageCount(storageCount);
        return this;
    }

    public Drug build() {
        return new Drug(tradeName, activeIngredient, direction, purpose, sideEffect, storageCount);
    }
}
