package seedu.dengue.model.person;

/**
 * Represents the locations that each postal sector maps to.
 */
public enum Location {
    DIST01("[01 02 03 04 05 06]",
            "Raffles Place, Cecil, Marina, People's Park"),
    DIST02("[07 08]",
            "Anson, Tanjong Pagar"),
    DIST03("[14 15 16]",
            "Queenstown, Tiong Bahru"),
    DIST04("[09 10]",
            "Telok Blangah, Harbourfront"),
    DIST05("[11 12 13]",
            "Pasir Panjang, Hong Leong Garden, Clementi New Town"),
    DIST06("[17]",
            "High Street, Beach Road (part)"),
    DIST07("[18 19]",
            "Middle Road, Golden Mile"),
    DIST08("[20 21]",
            "Little India"),
    DIST09("[22 23]",
            "Orchard, Cairnhill, River Valley"),
    DIST10("[24 25 26 27]",
            "Ardmore, Bukit Timah, Holland Road, Tanglin"),
    DIST11("[28 29 30]",
            "Watten Estate, Novena, Thomson"),
    DIST12("[31 32 33]",
            "Balestier, Toa Payoh, Serangoon"),
    DIST13("[34 35 36 37]",
            "Macpherson, Braddell"),
    DIST14("[38 39 40 41]",
            "Geylang, Eunos"),
    DIST15("[42 43 44 45]",
            "Katong, Joo Chiat, Amber Road"),
    DIST16("[46 47 48]",
            "Bedok, Upper East Coast, Eastwood, Kew Drive"),
    DIST17("[49 50 81 91]",
            "Loyang, Changi"),
    DIST18("[51 52]",
            "Tampines, Pasir Ris"),
    DIST19("[53 54 55 82]",
            "Serangoon Garden, Hougang, Punggol"),
    DIST20("[56 57]",
            "Bishan, Ang Mo Kio"),
    DIST21("[58 59]",
            "Upper Bukit Timah, Clementi Park, Ulu Pandan"),
    DIST22("[60 61 62 63 64]",
            "Jurong"),
    DIST23("[65 66 67 68]",
            "Hillview, Dairy Farm, Bukit Panjang, Choa Chu Kang"),
    DIST24("[69 70 71]",
            "Lim Chu Kang, Tengah"),
    DIST25("[72 73]",
            "Kranji, Woodgrove"),
    DIST26("[77 78]",
            "Upper Thomson, Springleaf"),
    DIST27("[75 76]",
            "Yishun, Sembawang"),
    DIST28("[79 80]",
            "Seletar");

    private final String postalSectors;
    private final String location;

    Location(String postalSectors, String location) {
        this.postalSectors = postalSectors;
        this.location = location;
    }

    @Override
    public String toString() {
        return postalSectors + ", " + location;
    }

}
