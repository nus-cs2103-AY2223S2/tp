package seedu.address.model.person;

/**
 * Represents the region in which a person is located in.
 * Regions are immutable upon creation.
 * <p>
 * To create a region object, either call the base constructor with a
 * specific region selected from the {@code Regions} enum or, with a specific
 * town selected from the {@code Towns} enum.
 */
public class Region {

    public static final String MESSAGE_CONSTRAINTS =
            "Region in Singapore should be either North, South, East, West or Central\n"
            + "No other regions are accepted!\n";

    /**
     * Enum that represents all regions of Singapore
     */
    public enum Regions {
        NORTH, SOUTH, EAST, WEST, CENTRAL, UNKNOWN;

        /**
         * Returns a string to be displayed in the UI
         * @return string in all lower case with first letter capitalised
         */
        public String getDisplayString() {
            String allCapsRegion = this.toString();
            String capitaliseFirstLetter = allCapsRegion.substring(0, 1).toUpperCase()
                    + allCapsRegion.substring(1).toLowerCase();
            return "Region: " + capitaliseFirstLetter;
        }
    }

    /**
     * Enum that has all the towns in Singapore and their respective regions.
     * <p>
     * Town region is determined using this website:
     */
    public enum Towns {
        ADMIRALTY(Regions.NORTH), KRANJI(Regions.NORTH), WOODLANDS(Regions.NORTH),
        SEMBAWANG(Regions.NORTH), YISHUN(Regions.NORTH), YIO_CHU_KANG(Regions.NORTH),
        SELETAR(Regions.NORTH), SENGKANG(Regions.NORTH),

        HOLLAND_VILLAGE(Regions.SOUTH), QUEENSTOWN(Regions.SOUTH), BUKIT_MERAH(Regions.SOUTH),
        TELOK_BLANGAH(Regions.SOUTH), PASIR_PANJANG(Regions.SOUTH), SENTOSA(Regions.SOUTH),
        BUKIT_TIMAH(Regions.SOUTH), NEWTON(Regions.SOUTH), ORCHARD(Regions.SOUTH),
        CENTRAL_BUSINESS_DISTRICT(Regions.SOUTH), MARINA_SOUTH(Regions.SOUTH),

        SERANGOON(Regions.EAST), PUNGGOL(Regions.EAST), HOUGANG(Regions.EAST),
        TAMPINES(Regions.EAST), PASIR_RIS(Regions.EAST), LOYANG(Regions.EAST),
        SIMEI(Regions.EAST), KALLANG(Regions.EAST), KATONG(Regions.EAST),
        EAST_COAST(Regions.EAST), MACPHERSON(Regions.EAST), BEDOK(Regions.EAST),
        PULAU_UBIN(Regions.EAST), PULAU_TEKONG(Regions.EAST),

        LIM_CHU_KANG(Regions.WEST), CHOA_CHU_KANG(Regions.WEST), BUKIT_PANJANG(Regions.WEST),
        TUAS(Regions.WEST), JURONG_EAST(Regions.WEST), JURONG_WEST(Regions.WEST),
        JURONG_INDUSTRIAL_ESTATE(Regions.WEST), BUKIT_BATOK(Regions.WEST), HILLVIEW(Regions.WEST),
        WEST_COAST(Regions.WEST), CLEMENTI(Regions.WEST),

        THOMSON(Regions.CENTRAL), MARYMOUNT(Regions.CENTRAL), SIN_MING(Regions.CENTRAL),
        ANG_MO_KIO(Regions.CENTRAL), BISHAN(Regions.CENTRAL), SERANGOON_GARDENS(Regions.CENTRAL),
        MACRITCHIE(Regions.CENTRAL), TOA_PAYOH(Regions.CENTRAL), GEYLANG(Regions.CENTRAL),
        ALJUNIED(Regions.CENTRAL);

        private final Regions townRegion;

        private Towns(Regions townRegion) {
            this.townRegion = townRegion;
        }

        private Regions region() {
            return townRegion;
        }
    }

    private final Regions region;

    /**
     * Constructor for a new Region object.
     * <p>
     * Use {@code Regions} enum to select region
     *
     * @param region Region of this object
     */
    public Region(Regions region) {
        this.region = region;
    }

    /**
     * Factory method that returns the correct region based on the town.
     * Region classification is based on the following URL:
     * <p>
     * Use {@code Towns} enum to select the town
     *
     * @param town Town selected from {@code Towns} enum
     * @return the region that the town is in
     */
    public Region(Towns town) {
        this.region = town.region();
    }

    /**
     * Returns the region of this object
     *
     * @return a region defined by the {@code Regions} enum
     */
    public Regions getRegion() {
        return this.region;
    }

    /**
     * Returns the detected region based on the address <p>
     * Goes through all towns and attempts to find a match of a town name in the address.
     * If the town name cannot be found, it returns {@code Regions.UNKNOWN}
     * @param address address that we find to get the region from
     * @return detected region if there is one, {@code Regions.UNKNOWN} if no town is found
     */
    public static Regions getRegionFromAddress(String address) {
        String toUpperCaseAddress = address.toUpperCase();
        Towns[] allTowns = Towns.values();
        for (Towns t : allTowns) {
            String spacedTownName = t.toString().replace('_', ' ');
            if (toUpperCaseAddress.contains(spacedTownName)) {
                return t.townRegion;
            }
        }
        return Regions.UNKNOWN;
    }

}
