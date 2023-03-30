package seedu.connectus.model.tag;

import static seedu.connectus.commons.util.AppUtil.checkArgument;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;

/**
 * Represents a Cca in the ConnectUS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCcaName(String)}
 */
public class Cca extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "CCA names and CCA Positions should be "
            + "alphanumeric and may contain spaces\n"
            + "CCA Positions are optional\n"
            + "Format: " + PREFIX_CCA + "CCA[#CCA_POSITION]";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]+#?[\\p{Alnum}\\s]+$";
    public final String ccaName;
    public final String ccaPositionName;
    public final String coupledCcaName;

    private String cca;
    private String position;
    private boolean isPosition = false;

    public boolean hasPosition() {
        return isPosition;
    }

    /**
     * Constructs a {@code Cca}.
     *
     * @param coupledCcaName A valid CCA name.
     */
    public Cca(String coupledCcaName) {
        super(coupledCcaName);
        checkArgument(isValidCcaName(coupledCcaName), MESSAGE_CONSTRAINTS);
        this.coupledCcaName = coupledCcaName;
        String[] arr = decouple(coupledCcaName);
        cca = arr[0];
        if (arr.length > 1) {
            isPosition = true;
            position = arr[1];
        }
        ccaName = cca;
        ccaPositionName = position;
    }

    /**
     * Decouples command into CCA Name and CCA Position.
     */
    public static String[] decouple(String coupledCcaName) {
        String[] arr = coupledCcaName.split("#");
        return arr;
    }

    /**
     * Returns true if a given string is a valid ccaName.
     */
    public static boolean isValidCcaName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (isPosition) {
            return cca + " - " + position;
        } else {
            return cca;
        }
    }
}
