package seedu.vms.model.vaccination;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Similar to {@link VaxRecord} however, only containing the key to the
 * {@link VaxType} instead of the instance.
 */
public class VaxRecordKey {
    private final VaxName vaxKey;
    private final LocalDateTime timeTaken;


    /**
     * Constructs a {@code VaxRecordKey}.
     *
     * @param vaxKey - name of the vaccination.
     * @param timeTaken - the time the vaccination was taken.
     * @throws NullPointerException if any parameters are {@code null}.
     */
    public VaxRecordKey(VaxName vaxKey, LocalDateTime timeTaken) {
        this.vaxKey = Objects.requireNonNull(vaxKey);;
        this.timeTaken = Objects.requireNonNull(timeTaken);
    }


    public String getVaxTypeKey() {
        return vaxKey.toString();
    }


    public LocalDateTime getTimeTaken() {
        return timeTaken;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VaxRecordKey)) {
            return false;
        }

        VaxRecordKey casted = (VaxRecordKey) other;
        return vaxKey.equals(casted.vaxKey) && timeTaken.equals(casted.timeTaken);
    }


    @Override
    public int hashCode() {
        return Objects.hash(vaxKey, timeTaken);
    }
}
