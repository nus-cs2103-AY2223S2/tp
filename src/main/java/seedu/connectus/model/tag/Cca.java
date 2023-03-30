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
    public final String decoupledCcaName;

    private String cca;
    private String position;
    private String decoupled;

    /**
     * Constructs a {@code Cca}.
     *
     * @param coupledCcaName A valid CCA name.
     */
    public Cca(String coupledCcaName) {
        super(coupledCcaName);
        checkArgument(isValidCcaName(coupledCcaName), MESSAGE_CONSTRAINTS);
        this.coupledCcaName = coupledCcaName;
        decoupleCcaName();
        ccaName = cca;
        ccaPositionName = position;
        decoupledCcaName = decoupled;
    }

    /**
     * Decouples command into CCA Name and CCA Position.
     */
    public void decoupleCcaName() {
        String[] arr = coupledCcaName.split("#");
        if (arr.length == 2) {
            cca = arr[0];
            position = arr[1];
            decoupled = cca + " - " + position;
        } else {
            cca = arr[0];
            position = "";
            decoupled = cca;
        }
    }

    /**
     * Returns true if a given string is a valid ccaName.
     */
    public static boolean isValidCcaName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return decoupledCcaName;
    }
}
