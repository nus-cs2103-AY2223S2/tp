package seedu.address.model.service;

import seedu.address.model.entity.Entity;
import seedu.address.model.service.exception.InsufficientPartException;

/**
 * This class represents a vehicle part.
 */
public class Part {

    private static int incrementalID = 0;
    private int id;
    private int stockLeft;
    private String name;
    private String description;
    private Entity purchasedFrom;
    private int cost;
    private final PartType type;

    /**
     * This represents the various types of vehicle parts.
     */
    public enum PartType {
        WHEELS, SUSPENSION, FRAME, GEARBOX, BOLT, HEADLAMP, LIGHT, HORN, STEERING
    }

    /**
     * This method is the constructor class for a vehicle part.
     *
     * @param name The name of this vehicle part.
     * @param type The type of the vehicle part.
     * @param cost The cost of the vehicle part.
     * @param quantity How many in stock.
     */
    public Part(String name, PartType type, int cost, int quantity) {
        id = ++incrementalID;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.stockLeft = quantity;
    }

    /**
     * This method is the constructor class for a vehicle part with a default stock of 0.
     *
     * @param name The name of this vehicle part.
     * @param type The type of the vehicle part.
     * @param cost The cost of the vehicle part.
     */
    public Part(String name, PartType type, int cost) {
        this(name, type, cost, 0);
    }

    /**
     * This method returns the ID of this part.
     * @return id of this part.
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns how much stock (quantity) is left for the particular part.
     *
     * @return the quantity of this Part.
     */
    public int getStockLeft() {
        return stockLeft;
    }

    /**
     * This method sets how much stock (quantity) is left for this particular part.
     *
     * @param quantity The amount in stock.
     */
    public void setStockLeft(int quantity) {
        stockLeft = quantity;
    }

    /**
     * Increase stock amt of part by a certain quantity
     * @param quantity Amount to add
     */
    public void increaseStock(int quantity) {
        setStockLeft(getStockLeft() + quantity);
    }

    /**
     * This method decrements the quantity of this part.
     *
     * @throws InsufficientPartException if not enough parts
     */
    public void useStock() throws InsufficientPartException {
        if (stockLeft < 1) {
            throw new InsufficientPartException(this);
        }
        stockLeft--;
    }

    /**
     * This method returns the name of this part.
     *
     * @return The name of this part.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of this part.
     *
     * @param name The name of this part.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the description of this part.
     *
     * @return The description of this part.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets the description of this part.
     *
     * @param description The description of this part.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns the Entity which sold us this part.
     *
     * @return The entity who sold us this part.
     */
    public Entity getPurchasedFrom() {
        return purchasedFrom;
    }

    /**
     * This method sets which Entity which sold us this part.
     *
     * @param purchasedFrom the Entity which sold us this part.
     */
    public void setPurchasedFrom(Entity purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }

    /**
     * This method returns the cost of this Part.
     * @return the cost of this Part.
     */
    public int getCost() {
        return cost;
    }

    /**
     * This method sets the cost of this Part.
     *
     * @param cost the new cost of this Part.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

}
