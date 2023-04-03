package seedu.dengue.model.person;

import java.util.HashMap;

//@@author valerietanhx
/**
 * A mapping of postal sectors to locations.
 */
public class PostalLocationMapping {
    private static final HashMap<PostalSector, Location> POSTAL_LOCATION_MAPPING = new HashMap<>();
    static {
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR01, Location.DIST01);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR02, Location.DIST01);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR03, Location.DIST01);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR04, Location.DIST01);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR05, Location.DIST01);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR06, Location.DIST01);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR07, Location.DIST02);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR08, Location.DIST02);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR14, Location.DIST03);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR15, Location.DIST03);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR16, Location.DIST03);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR09, Location.DIST04);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR10, Location.DIST04);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR11, Location.DIST05);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR12, Location.DIST05);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR13, Location.DIST05);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR17, Location.DIST06);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR18, Location.DIST07);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR19, Location.DIST07);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR20, Location.DIST08);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR21, Location.DIST08);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR22, Location.DIST09);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR23, Location.DIST09);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR24, Location.DIST10);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR25, Location.DIST10);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR26, Location.DIST10);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR27, Location.DIST10);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR28, Location.DIST11);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR29, Location.DIST11);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR30, Location.DIST11);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR31, Location.DIST12);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR32, Location.DIST12);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR33, Location.DIST12);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR34, Location.DIST13);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR35, Location.DIST13);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR36, Location.DIST13);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR37, Location.DIST13);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR38, Location.DIST14);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR39, Location.DIST14);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR40, Location.DIST14);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR41, Location.DIST14);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR42, Location.DIST15);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR43, Location.DIST15);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR44, Location.DIST15);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR45, Location.DIST15);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR46, Location.DIST16);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR47, Location.DIST16);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR48, Location.DIST16);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR49, Location.DIST17);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR50, Location.DIST17);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR81, Location.DIST17);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR91, Location.DIST17);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR51, Location.DIST18);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR52, Location.DIST18);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR53, Location.DIST19);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR54, Location.DIST19);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR55, Location.DIST19);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR82, Location.DIST19);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR56, Location.DIST20);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR57, Location.DIST20);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR58, Location.DIST21);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR59, Location.DIST21);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR60, Location.DIST22);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR61, Location.DIST22);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR62, Location.DIST22);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR63, Location.DIST22);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR64, Location.DIST22);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR65, Location.DIST23);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR66, Location.DIST23);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR67, Location.DIST23);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR68, Location.DIST23);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR69, Location.DIST24);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR70, Location.DIST24);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR71, Location.DIST24);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR72, Location.DIST25);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR73, Location.DIST25);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR77, Location.DIST26);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR78, Location.DIST26);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR75, Location.DIST27);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR76, Location.DIST27);

        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR79, Location.DIST28);
        POSTAL_LOCATION_MAPPING.put(PostalSector.SECTOR80, Location.DIST28);
    }

    //@@author axmszr
    /**
     * Maps the given {@code PostalSector} to its matching {@code Location}.
     *
     * @param postalSector The postal sector to map.
     * @return The corresponding location.
     */
    public static Location getLocation(PostalSector postalSector) {
        return POSTAL_LOCATION_MAPPING.get(postalSector);
    }

    /**
     * Maps the given {@code Postal} to its matching {@code Location}.
     *
     * @param postal The postal code to map.
     * @return The corresponding location.
     */
    public static Location getLocation(Postal postal) {
        PostalSector postalSector = postal.getPostalSector();
        return getLocation(postalSector);
    }
}
