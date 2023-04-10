package wingman.storage.json.adapted;

import java.util.Deque;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import wingman.commons.exceptions.IllegalValueException;
import wingman.commons.util.GetUtil;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.crew.Crew;
import wingman.model.crew.FlightCrewType;
import wingman.model.flight.Flight;
import wingman.model.link.Link;
import wingman.model.location.FlightLocationType;
import wingman.model.location.Location;
import wingman.model.pilot.FlightPilotType;
import wingman.model.pilot.Pilot;
import wingman.model.plane.FlightPlaneType;
import wingman.model.plane.Plane;
import wingman.storage.json.JsonAdaptedModel;

/**
 * Represents a Jackson-friendly version of a Flight
 */
public class JsonAdaptedFlight implements JsonAdaptedModel<Flight> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT =
            "Flight's %s field is missing!";

    /**
     * The id of the flight.
     */
    private final String id;

    /**
     * The code of the flight.
     */
    private final String code;

    private Map<FlightPilotType, Deque<String>> pilotLink;
    private Map<FlightCrewType, Deque<String>> crewLink;
    private Map<FlightPlaneType, Deque<String>> planeLink;
    private Map<FlightLocationType, Deque<String>> locationLink;

    /**
     * Constructs a {@code JsonAdaptedFlight} with the given flight details.
     * This is intended for Jackson to use.
     *
     * @param id           The id of the flight.
     * @param code         The name of the flight.
     * @param pilotLink    The link between pilot(s) and the flight
     * @param crewLink     The link between crew(s) and the flight
     * @param planeLink    The link between plane and the flight
     * @param locationLink The link between location(s) and the flight
     */
    @JsonCreator
    public JsonAdaptedFlight(
            @JsonProperty("id") String id,
            @JsonProperty("code") String code,
            @JsonProperty("pilotLink") Map<FlightPilotType, Deque<String>> pilotLink,
            @JsonProperty("crewLink") Map<FlightCrewType, Deque<String>> crewLink,
            @JsonProperty("planeLink") Map<FlightPlaneType, Deque<String>> planeLink,
            @JsonProperty("locationLink") Map<FlightLocationType, Deque<String>> locationLink
    ) {
        this.id = id;
        this.code = code;
        this.pilotLink = pilotLink;
        this.crewLink = crewLink;
        this.planeLink = planeLink;
        this.locationLink = locationLink;
    }

    /**
     * Converts a given {@code Flight} into this class for Jackson use.
     *
     * @param flight The flight to be converted.
     */
    public JsonAdaptedFlight(Flight flight) {
        this.id = flight.getId();
        this.code = flight.getCode();
        this.pilotLink = flight.getPilotLink().getCopiedContents();
        this.crewLink = flight.getCrewLink().getCopiedContents();
        this.planeLink = flight.getPlaneLink().getCopiedContents();
        this.locationLink = flight.getLocationLink().getCopiedContents();
    }

    @Override
    public Flight toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "id")
            );
        }
        if (code == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "code")
            );
        }

        Flight flight;
        Link<FlightPilotType, Pilot, ReadOnlyItemManager<Pilot>> linkPilot =
                Link.fromOrCreate(Pilot.SHAPE, pilotLink,
                        GetUtil
                                .getLazy(Model.class)
                                .map(Model::getPilotManager)
                );
        Link<FlightCrewType, Crew, ReadOnlyItemManager<Crew>> linkCrew =
                Link.fromOrCreate(Crew.SHAPE, crewLink,
                        GetUtil
                                .getLazy(Model.class)
                                .map(Model::getCrewManager)
                );
        Link<FlightPlaneType, Plane, ReadOnlyItemManager<Plane>> linkPlane =
                Link.fromOrCreate(Plane.SHAPE, planeLink,
                        GetUtil
                                .getLazy(Model.class)
                                .map(Model::getPlaneManager)
                );
        Link<FlightLocationType, Location, ReadOnlyItemManager<Location>> linkLocation =
                Link.fromOrCreate(Location.SHAPE, locationLink,
                        GetUtil
                                .getLazy(Model.class)
                                .map(Model::getLocationManager)
                );
        flight = new Flight(
                id,
                code,
                linkPilot,
                linkCrew,
                linkPlane,
                linkLocation
        );

        return flight;
    }
}
