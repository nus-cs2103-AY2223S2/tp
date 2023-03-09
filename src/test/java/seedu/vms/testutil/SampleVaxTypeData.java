package seedu.vms.testutil;

import java.util.HashSet;
import java.util.List;

import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.Requirement.RequirementType;

/** Utility class to store sample vaccination type data for tests. */
public class SampleVaxTypeData {
    public static final GroupName NAME_REAL = new GroupName("Dose 1 (Pfizer)");
    public static final HashSet<GroupName> GROUPS_REAL = new HashSet<>(List.of(
            new GroupName("DOSE 1"),
            new GroupName("Pfizer"),
            new GroupName("Vaccination")));
    public static final int MIN_AGE_REAL = 5;
    public static final int MAX_AGE_REAL = Integer.MAX_VALUE;
    public static final int MIN_SPACING_REAL = 56;
    public static final List<Requirement> HISTORY_REQS_REAL = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    new GroupName("DOSE 1")))));
    public static final List<Requirement> ALLERGY_REQS_REAL = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    new GroupName("ALC-0315"),
                    new GroupName("ALC-0159"),
                    new GroupName("DSPC"),
                    new GroupName("Cholesterol"),
                    new GroupName("Sucrose"),
                    new GroupName("Phosphate"),
                    new GroupName("Tromethamine"),
                    new GroupName("Tromethamine hydrochloride")))));
    public static final VaxType TYPE_REAL = new VaxType(
            NAME_REAL,
            GROUPS_REAL,
            MIN_AGE_REAL,
            MAX_AGE_REAL,
            MIN_SPACING_REAL,
            ALLERGY_REQS_REAL,
            HISTORY_REQS_REAL);


    public static final GroupName NAME_1 = new GroupName("UNCHI");
    public static final HashSet<GroupName> GROUPS_1 = new HashSet<>(List.of(
            new GroupName("UNCHI")));
    public static final int MIN_AGE_1 = 35;
    public static final int MAX_AGE_1 = 45;
    public static final int MIN_SPACING_1 = 3545;
    public static final List<Requirement> HISTORY_REQS_1 = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    new GroupName("UNCHI")))));
    public static final List<Requirement> ALLERGY_REQS_1 = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    new GroupName("UNCHI")))));
    public static final VaxType TYPE_1 = new VaxType(
            NAME_1,
            GROUPS_1,
            MIN_AGE_1,
            MAX_AGE_1,
            MIN_SPACING_1,
            ALLERGY_REQS_1,
            HISTORY_REQS_1);
}
