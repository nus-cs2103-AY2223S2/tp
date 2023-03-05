package seedu.address.model.jobs;

import java.util.UUID;

/**
 * DeliveryPackage
 */
public class DeliveryPackage {
    // refine later
    private final UUID packageId;
    private double weight;
    private double length;
    private double breadth;
    private double height;

    public DeliveryPackage(double weight, double length, double breadth, double height) {
        this(UUID.randomUUID(), weight, length, breadth, height);
    }

    private DeliveryPackage(UUID packageId, double weight, double length, double breadth, double height) {
        this.packageId = packageId;
        this.weight = weight;
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public double getBreadth() {
        return breadth;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        String outString = "Package [%s]\n"
            + "weight: %s\n"
            + "LxBxH: %s x %s x %s\n";

        builder.append(
            String.format(outString,
                packageId,
                getWeight(),
                getLength(),
                getBreadth(),
                getHeight())
        );

        return builder.toString();
    }
}
