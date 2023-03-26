package taa.model;

public class Alarm {
    private final int minutes;
    private final String message;

    public Alarm(int minutes, String message) {
        this.minutes = minutes;
        this.message = message;
    }

    public int getTime() {
        return this.minutes;
    }

    public String getMessage() {
        return this.message;
    }
}
