package seedu.address.model.id;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Id {
    private static final AtomicInteger idCounter = new AtomicInteger();

    private String id;

    public Id() {
        this.id = generateUniqueId();
    }

    public String getId() {
        return id;
    }

    private static String generateUniqueId() {
        return Integer.toString(idCounter.getAndIncrement());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Id)) {
            return false;
        }
        Id other = (Id) obj;
        return id.equals(other.getId());
    }
}
