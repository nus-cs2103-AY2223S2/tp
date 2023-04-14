package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NRIC = new Prefix("i/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_DOCTOR = new Prefix("ad/");
    public static final Prefix PREFIX_DATE_OF_BIRTH = new Prefix("dob/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DESC = new Prefix("b/");
    public static final Prefix PREFIX_DRUG_ALLERGY = new Prefix("d/");
    public static final Prefix PREFIX_MEDICINE = new Prefix("m/");
}
