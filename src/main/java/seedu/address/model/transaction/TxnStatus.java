package seedu.address.model.transaction;

import java.time.LocalDateTime;

public class TxnStatus {

    private final boolean isClosed;
    private final LocalDateTime lastUpdate;

    public TxnStatus() {
        this.isClosed = false;
        this.lastUpdate = LocalDateTime.now();
    }

    public TxnStatus(boolean isClosed) {
        this.isClosed = isClosed;
        this.lastUpdate = LocalDateTime.now();
    }

    public boolean isClosed() {
        return isClosed;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
