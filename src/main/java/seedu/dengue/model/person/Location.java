package seedu.dengue.model.person;

public enum Location {
    DIST01("Raffles Place, Cecil, Marina, People's Park"),
    DIST02("Anson, Tanjong Pagar"),
    DIST03("Queenstown, Tiong Bahru"),
    DIST04("Telok Blangah, Harbourfront"),
    DIST05("Pasir Panjang, Hong Leong Garden, Clementi New Town"),
    DIST06("High Street, Beach Road (part)"),
    DIST07("Middle Road, Golden Mile"),
    DIST08("Little India"),
    DIST09("Orchard, Cairnhill, River Valley"),
    DIST10("Ardmore, Bukit Timah, Holland Road, Tanglin"),
    DIST11("Watten Estate, Novena, Thomson"),
    DIST12("Balestier, Toa Payoh, Serangoon"),
    DIST13("Macpherson, Braddell"),
    DIST14("Geylang, Eunos"),
    DIST15("Katong, Joo Chiat, Amber Road"),
    DIST16("Bedok, Upper East Coast, Eastwood, Kew Drive"),
    DIST17("Loyang, Changi"),
    DIST18("Tampines, Pasir Ris"),
    DIST19("Serangoon Garden, Hougang, Punggol"),
    DIST20("Bishan, Ang Mo Kio"),
    DIST21("Upper Bukit Timah, Clementi Park, Ulu Pandan"),
    DIST22("Jurong"),
    DIST23("Hillview, Dairy Farm, Bukit Panjang, Choa Chu Kang"),
    DIST24("Lim Chu Kang, Tengah"),
    DIST25("Kranji, Woodgrove"),
    DIST26("Upper Thomson, Springleaf"),
    DIST27("Yishun, Sembawang"),
    DIST28("Seletar");

    private final String location;

    private Location(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return location;
    }

}
