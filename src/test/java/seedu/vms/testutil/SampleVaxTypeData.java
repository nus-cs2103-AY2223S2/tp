package seedu.vms.testutil;

import java.util.HashSet;
import java.util.List;

import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.Requirement.RequirementType;
import seedu.vms.model.vaccination.VaxType;

/** Utility class to store sample vaccination type data for tests. */
public class SampleVaxTypeData {
    public static final GroupName NAME_REAL = new GroupName("Dose 1 (Pfizer)");
    public static final HashSet<GroupName> GROUPS_REAL = new HashSet<>(List.of(
            new GroupName("DOSE 1"),
            new GroupName("Pfizer"),
            new GroupName("Vaccination")));
    public static final Age MIN_AGE_REAL = new Age(5);
    public static final Age MAX_AGE_REAL = Age.MAX_AGE;
    public static final List<Requirement> HISTORY_REQS_REAL = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    new GroupName("DOSE 1")))));
    public static final HashSet<GroupName> INGREDIENTS_REAL = new HashSet<>(List.of(
                    new GroupName("ALC-0315"),
                    new GroupName("ALC-0159"),
                    new GroupName("DSPC"),
                    new GroupName("Cholesterol"),
                    new GroupName("Sucrose"),
                    new GroupName("Phosphate"),
                    new GroupName("Tromethamine"),
                    new GroupName("Tromethamine hydrochloride")));
    public static final VaxType TYPE_REAL = new VaxType(
            NAME_REAL,
            GROUPS_REAL,
            MIN_AGE_REAL,
            MAX_AGE_REAL,
            INGREDIENTS_REAL,
            HISTORY_REQS_REAL);
    public static final String CMD_NAME_REAL = "Dose 1 (Pfizer)";
    public static final String CMD_GROUPS_REAL = CliSyntax.DELIMITER + CliSyntax.PREFIX_VAX_GROUPS.getPrefix() + " "
            + "DOSE 1,"
            + "Pfizer,"
            + "Vaccination";
    public static final String CMD_MIN_AGE_REAL = CliSyntax.DELIMITER + CliSyntax.PREFIX_MIN_AGE.getPrefix() + " 5";
    public static final String CMD_MAX_AGE_REAL = "";
    public static final String CMD_INGREDIENTS_REAL = CliSyntax.DELIMITER
            + CliSyntax.PREFIX_INGREDIENTS.getPrefix() + " 
            + "ALC-0315,"
            + "ALC-0159,"
            + "DSPC,"
            + "Cholesterol,"
            + "Sucrose,"
            + "Phosphate,"
            + "Tromethamine,"
            + "Tromethamine hydrochloride";
    public static final String CMD_HISTORY_REQS_REAL = CliSyntax.DELIMITER
            + CliSyntax.PREFIX_HISTORY_REQ.getPrefix()
            + " none::DOSE 1";

    public static final GroupName NAME_1 = new GroupName("UNCHI");
    public static final HashSet<GroupName> GROUPS_1 = new HashSet<>(List.of(
            new GroupName("UNCHI")));
    public static final Age MIN_AGE_1 = new Age(35);
    public static final Age MAX_AGE_1 = new Age(45);
    public static final List<Requirement> HISTORY_REQS_1 = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    new GroupName("UNCHI")))));
    public static final HashSet<GroupName> INGREDIENTS_1 = new HashSet<>(List.of(
                    new GroupName("UNCHI")));
    public static final VaxType TYPE_1 = new VaxType(
            NAME_1,
            GROUPS_1,
            MIN_AGE_1,
            MAX_AGE_1,
            INGREDIENTS_1,
            HISTORY_REQS_1);
    public static final String CMD_NAME_1 = "UNCHI";
    public static final String CMD_GROUPS_1 = CliSyntax.DELIMITER + CliSyntax.PREFIX_VAX_GROUPS.getPrefix()
            + " UNCHI";
    public static final String CMD_MIN_AGE_1 = CliSyntax.DELIMITER + CliSyntax.PREFIX_MIN_AGE.getPrefix() + " 35";
    public static final String CMD_MAX_AGE_1 = CliSyntax.DELIMITER + CliSyntax.PREFIX_MAX_AGE.getPrefix() + " 45";
    public static final String CMD_INGREDIENTS_1 = CliSyntax.DELIMITER + CliSyntax.PREFIX_INGREDIENTS.getPrefix()
            + " UNCHI";
    public static final String CMD_HISTORY_REQS_1 = CliSyntax.DELIMITER + CliSyntax.PREFIX_HISTORY_REQ.getPrefix()
            + " none::UNCHI";
}
