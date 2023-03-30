package seedu.connectus.model.tag;

/**
 * Represents a Cca in the ConnectUS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Cca extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "CCA names and CCA Positions should be " +
            "alphanumeric and may have spaces";
    public final String ccaName;
    public final String ccaPositionName;
    public final String coupledCcaName;
    public final String decoupledCcaName;

    private String cca;
    private String position;
    private String decoupled;

    /**
     * Constructs a {@code Module}.
     *
     * @param coupledCcaName A valid CCA name.
     */
    public Cca(String coupledCcaName) {
        super(coupledCcaName, MESSAGE_CONSTRAINTS);
        this.coupledCcaName = coupledCcaName;
        decoupleCcaName();
        ccaName = cca;
        ccaPositionName = position;
        decoupledCcaName = decoupled;
    }

    /**
     * Decouples command into desired tag name.
     */
    public void decoupleCcaName() {
        String[] arr = coupledCcaName.split("#");
        if (arr.length == 2) {
            cca = arr[0];
            position = arr[1];
            decoupled = cca + "-" + position;
        }
        else {
            cca = arr[0];
            position = "";
            decoupled = cca;
        }
    }


    /**
     * Returns true if a given string is a valid cca name.
     */
    public static boolean isValidCcaName(String test) {
        return isValidTagName(test);
    }

    @Override
    public String toString() {
        return decoupledCcaName;
    }
}
