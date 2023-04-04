package seedu.dengue.model.person;

/**
 * Represents the first two digits of a postal code.
 */
public enum PostalSector {
    SECTOR01("01"), SECTOR02("02"), SECTOR03("03"), SECTOR04("04"), SECTOR05("05"),
    SECTOR06("06"), SECTOR07("07"), SECTOR08("08"), SECTOR09("09"), SECTOR10("10"),
    SECTOR11("11"), SECTOR12("12"), SECTOR13("13"), SECTOR14("14"), SECTOR15("15"),
    SECTOR16("16"), SECTOR17("17"), SECTOR18("18"), SECTOR19("19"), SECTOR20("20"),
    SECTOR21("21"), SECTOR22("22"), SECTOR23("23"), SECTOR24("24"), SECTOR25("25"),
    SECTOR26("26"), SECTOR27("27"), SECTOR28("28"), SECTOR29("29"), SECTOR30("30"),
    SECTOR31("31"), SECTOR32("32"), SECTOR33("33"), SECTOR34("34"), SECTOR35("35"),
    SECTOR36("36"), SECTOR37("37"), SECTOR38("38"), SECTOR39("39"), SECTOR40("40"),
    SECTOR41("41"), SECTOR42("42"), SECTOR43("43"), SECTOR44("44"), SECTOR45("45"),
    SECTOR46("46"), SECTOR47("47"), SECTOR48("48"), SECTOR49("49"), SECTOR50("50"),
    SECTOR51("51"), SECTOR52("52"), SECTOR53("53"), SECTOR54("54"), SECTOR55("55"),
    SECTOR56("56"), SECTOR57("57"), SECTOR58("58"), SECTOR59("59"), SECTOR60("60"),
    SECTOR61("61"), SECTOR62("62"), SECTOR63("63"), SECTOR64("64"), SECTOR65("65"),
    SECTOR66("66"), SECTOR67("67"), SECTOR68("68"), SECTOR69("69"), SECTOR70("70"),
    SECTOR71("71"), SECTOR72("72"), SECTOR73("73"), SECTOR75("75"),
    SECTOR76("76"), SECTOR77("77"), SECTOR78("78"), SECTOR79("89"), SECTOR80("80"),
    SECTOR81("81"), SECTOR82("82"),
    SECTOR91("91");

    private final String postalSector;

    PostalSector(String postalSector) {
        this.postalSector = postalSector;
    }

    @Override
    public String toString() {
        return postalSector;
    }

}
