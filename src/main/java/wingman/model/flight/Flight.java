package wingman.model.flight;

import java.util.List;
import java.util.UUID;

import wingman.commons.util.GetUtil;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.crew.Crew;
import wingman.model.crew.FlightCrewType;
import wingman.model.item.Item;
import wingman.model.link.Link;
import wingman.model.link.exceptions.LinkException;
import wingman.model.location.FlightLocationType;
import wingman.model.location.Location;
import wingman.model.pilot.FlightPilotType;
import wingman.model.pilot.Pilot;
import wingman.model.plane.FlightPlaneType;
import wingman.model.plane.Plane;

/**
 * Represents a flight object in wingman
 */
public class Flight implements Item {
    public final Link<FlightPilotType, Pilot, ReadOnlyItemManager<Pilot>> pilotLink;
    public final Link<FlightCrewType, Crew, ReadOnlyItemManager<Crew>> crewLink;
    public final Link<FlightPlaneType, Plane, ReadOnlyItemManager<Plane>> planeLink;
    public final Link<FlightLocationType, Location, ReadOnlyItemManager<Location>> locationLink;
    private final String code;
    private final String id;

    //TODO: Add exceptions to ensure departure and arrival locations are distinct

    /**
     * Creates a new flight
     *
     * @param id           the id of the  flight
     * @param code         the code
     * @param pilotLink    the link to the pilot
     * @param crewLink     the link to the crew
     * @param planeLink    the link to the plane
     * @param locationLink the link to the location
     */
    public Flight(
            String id,
            String code,
            Link<FlightPilotType, Pilot, ReadOnlyItemManager<Pilot>> pilotLink,
            Link<FlightCrewType, Crew, ReadOnlyItemManager<Crew>> crewLink,
            Link<FlightPlaneType, Plane, ReadOnlyItemManager<Plane>> planeLink,
            Link<FlightLocationType, Location, ReadOnlyItemManager<Location>> locationLink
    ) {
        this.id = id;
        this.code = code;
        this.pilotLink = pilotLink;
        this.crewLink = crewLink;
        this.planeLink = planeLink;
        this.locationLink = locationLink;
    }

    /**
     * Creates a flight with a random UUID as its id
     *
     * @param code the code of the flight
     */
    public Flight(String code) {
        this(UUID.randomUUID().toString(), code,
                new Link<>(
                        Pilot.SHAPE,
                        GetUtil.getLazy(Model.class).map(Model::getPilotManager)
                ), new Link<>(
                        Crew.SHAPE,
                        GetUtil.getLazy(Model.class).map(Model::getCrewManager)
                ), new Link<>(
                        Plane.SHAPE,
                        GetUtil.getLazy(Model.class).map(Model::getPlaneManager)
                ), new Link<>(
                        Location.SHAPE,
                        GetUtil
                                .getLazy(Model.class)
                                .map(Model::getLocationManager)
                )
        );
    }

    public String getCode() {
        return this.code;
    }

    /**
     * Returns the pilot link of the flight.
     *
     * @return the link to pilots
     */
    public Link<FlightPilotType, Pilot, ReadOnlyItemManager<Pilot>> getPilotLink() {
        return pilotLink;
    }

    /**
     * Returns the crew link of the flight.
     *
     * @return the link to crews
     */
    public Link<FlightCrewType, Crew, ReadOnlyItemManager<Crew>> getCrewLink() {
        return crewLink;
    }

    /**
     * Returns the plane link of the flight.
     *
     * @return the link to planes
     */
    public Link<FlightPlaneType, Plane, ReadOnlyItemManager<Plane>> getPlaneLink() {
        return planeLink;
    }

    /**
     * Returns the location link of the flight.
     *
     * @return the link to locations
     */
    public Link<FlightLocationType, Location, ReadOnlyItemManager<Location>> getLocationLink() {
        return locationLink;
    }

    /**
     * Sets a departure or arrival location for this flight.
     * If the location is already set, it will replace the existing location.
     *
     * @param type     the type of location (departure or arrival).
     * @param location the location to set.
     * @throws LinkException if the key is not valid.
     */
    public void setLocation(FlightLocationType type, Location location) throws LinkException {
        locationLink.putRevolve(type, location);
    }

    /**
     * Removes a departure or arrival location for this flight.
     * If the location is not set, it will do nothing.
     *
     * @param type the type of location (departure or arrival).
     * @param location the location to remove.
     * @throws LinkException if the key is not valid.
     */
    public void removeLocation(FlightLocationType type, Location location) throws LinkException {
        locationLink.delete(type, location);
    }

    @Override
    public String toString() {
        return getCode();
    }

    @Override
    public List<String> getDisplayList() {
        return List.of(
                String.format("%s", code),
                String.format("%s: %s\n", "Plane", planeLink.toString()),
                String.format("%s: %s\n", "Pilots", pilotLink.toString()),
                String.format("%s: %s\n", "Crew", crewLink.toString()),
                String.format("%s: %s\n", "Locations", locationLink.toString())
        );
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Flight)) {
            return false;
        }

        Flight other = (Flight) obj;

        return other.getCode().equals(getCode());
    }
}
