package seedu.careflow.testutil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.drug.Drug;

/**
 * Class for typical drugs
 */
public class TypicalDrugs {
    public static final Drug PROZAC = new DrugBuilder().withTradeName("Prozac").withActiveIngredient("Fluoxetine "
            + "hydrochloride").withDirection("The usual starting dose for adults is 20 mg once a day, "
            + "typically taken in the morning.").withPurpose("Prozac is an antidepressant medication "
            + "that is used to treat major depressive disorder, obsessive-compulsive disorder (OCD), bulimia nervosa, "
            + "and panic disorder.").withSideEffect("Common side effects of Prozac may include "
            + "nausea, vomiting, diarrhea, dry mouth, headache, dizziness, and nervousness.")
            .withStorageCount("10").build();
    public static final Drug ROBITUSSIN = new DrugBuilder().withTradeName("Robitussin").withActiveIngredient(
            "Dextromethorphan").withDirection("The usual recommended dose of Robitussin for adults is 10-20 mg every "
                    + "4-6 hours, up to a maximum of 120 mg per day.").withPurpose("Robitussin is a cough "
                    + "suppressant that is used to treat coughs caused by the common cold "
                    + "or other respiratory illnesses.")
            .withSideEffect("Common side effects of Robitussin may include dizziness, drowsiness, and upset stomach.")
            .withStorageCount("100").build();
    public static final Drug SUDAFED = new DrugBuilder().withTradeName("Sudafed").withActiveIngredient(
            "Pseudoephedrine").withDirection("The usual recommended dose of Sudafed for adults is 30-60 mg every 4-6 "
                    + "hours, up to a maximum of 240 mg per day.").withPurpose("Sudafed is a "
                    + "decongestant that is used to treat"
                    + " nasal congestion and a runny nose caused by allergies, "
                    + "the common cold, or other respiratory illnesses.")
            .withSideEffect("Common side effects of Sudafed may include headache, dry mouth, nervousness, and "
                    + "insomnia.").withStorageCount("22").build();
    public static final Drug VISINE = new DrugBuilder().withTradeName("Visine").withActiveIngredient(
            "Tetrahydrozoline").withDirection("The usual recommended dose of Visine for adults is 1-2 drops in the "
            +
            "affected eye(s) up to 4 times a day.").withPurpose("Visine is an eye drop that is used to relieve "
            +
            "redness in the eyes caused by minor eye irritations, such as smoke, dust, or wind.").withSideEffect(
                    "Common side effects of Visine may include temporary stinging or burning in the eye(s), dryness, "
                            +
                            "and blurred vision.").withStorageCount("7").build();
    public static final Drug METFORMIN = new DrugBuilder().withTradeName("Metformin").withActiveIngredient("Metformin"
                    + " Hydrochloride").withDirection("The usual recommended dose of Metformin for adults is typically "
                    + "500mg-1000mg taken twice daily with meals.").withPurpose("Metformin is an "
                    + "oral medication used to manage"
                    + " type 2 diabetes by helping to control blood sugar levels.").withSideEffect("Common side "
                    + "effects of Metformin may include diarrhea, nausea, vomiting, abdominal pain, "
                    + "and a metallic taste in the mouth.")
            .withStorageCount("23").build();
    public static final Drug IMODIUM = new DrugBuilder().withTradeName("Imodium").withActiveIngredient("Loperamide")
            .withDirection("The usual recommended dose of Imodium for adults is 4 mg initially, followed by 2 mg "
                    + "after each loose stool, up to a maximum of 16 mg per day.").withPurpose("Imodium is an "
                    + "over-the-counter medication used to treat acute diarrhea, including traveler's diarrhea and "
                    + "diarrhea associated with gastroenteritis.").withSideEffect("Common side effects of Imodium may "
                    + "include constipation, dizziness, and drowsiness.").withStorageCount("25").build();

    // Manually added

    public static final Drug ACYCLOVIR = new DrugBuilder().withTradeName("Acyclovir").withActiveIngredient("Acyclovir")
            .withDirection("The usual recommended dose of Acyclovir for adults is 800mg to be taken orally 5 times "
                    + "per day for 7-10 days.").withPurpose("Acyclovir is an antiviral medication used to treat "
                    + "chickenpox, herpes simplex virus, and shingles.").withSideEffect("Common side effects of "
                    + "Acyclovir may include nausea, vomiting, diarrhea, and headache.").withStorageCount("10").build();

    public static final Drug IBUPROFEN = new DrugBuilder().withTradeName("Advil").withActiveIngredient("Ibuprofen")
            .withDirection("The usual recommended dose of Advil for adults is 200-400 mg every 4-6 hours as needed, "
                    + "up to a maximum of 1200 mg per day.").withPurpose("Advil is a nonsteroidal anti-inflammatory "
                    + "drug (NSAID) used to relieve pain and reduce inflammation caused by muscle aches, headaches, "
                    + "toothaches and menstrual cramps.").withSideEffect("Common side effects of Advil may include "
                    + "stomach upset, nausea, and heartburn.").withStorageCount("27").build();
    public static final String VALID_TRADE_NAME_VISINE = "Visine";
    public static final String VALID_ACTIVE_INGREDIENT_VISINE = "Tetrahydrozoline";
    public static final String VALID_PURPOSE_VISINE = "Visine is an eye drop that is used to relieve "
            + "redness in the eyes caused by minor eye irritations, such as smoke, dust, or wind.";
    public static final String VALID_DIRECTION_VISINE = "The usual recommended dose of Visine for"
            + " adults is 1-2 drops in the affected eye(s) up to 4 times a day.";
    public static final String VALID_SIDE_EFFECT_VISINE = "Common side effects of Visine may include "
            + "temporary stinging or burning in the eye(s), dryness, and blurred vision.";
    public static final String VALID_STORAGE_COUNT_VISINE = "7";

    private TypicalDrugs() {} // prevents instantiation

    /**
     * Returns an {@code DrugInventory} with all the typical drugs.
     */
    public static DrugInventory getTypicalDrugInventory() {
        DrugInventory di = new DrugInventory();
        for (Drug drug: getTypicalDrugs()) {
            di.addDrug(drug);
        }
        return di;
    }

    public static List<Drug> getTypicalDrugs() {
        return new ArrayList<>(Arrays.asList(PROZAC, ROBITUSSIN, SUDAFED, VISINE, METFORMIN, IMODIUM));
    }
}
