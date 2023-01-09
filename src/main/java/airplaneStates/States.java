package airplaneStates;

public class States {

    String icao24;
    Long timePosition;
    Long lastContact;
    double longitude;
    double laltitude;
    double baroAltitude;
    double velocity;
    double trueTrack;
    double verticalRate;
    double geoAltitude;


    public States(String icao24, Long timePosition, Long lastContact, double longitude, double laltitude, double baroAltitude, double velocity, double trueTrack, double verticalRate, double geoAltitude) {
        this.icao24 = icao24;
        this.timePosition = timePosition;
        this.lastContact = lastContact;
        this.longitude = longitude;
        this.laltitude = laltitude;
        this.baroAltitude = baroAltitude;
        this.velocity = velocity;
        this.trueTrack = trueTrack;
        this.verticalRate = verticalRate;
        this.geoAltitude = geoAltitude;
    }

    public States() {
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public Long getTimePosition() {
        return timePosition;
    }

    public void setTimePosition(Long timePosition) {
        this.timePosition = timePosition;
    }

    public Long getLastContact() {
        return lastContact;
    }

    public void setLastContact(Long lastContact) {
        this.lastContact = lastContact;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLaltitude() {
        return laltitude;
    }

    public void setLaltitude(double laltitude) {
        this.laltitude = laltitude;
    }

    public double getBaroAltitude() {
        return baroAltitude;
    }

    public void setBaroAltitude(double baroAltitude) {
        this.baroAltitude = baroAltitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getTrueTrack() {
        return trueTrack;
    }

    public void setTrueTrack(double trueTrack) {
        this.trueTrack = trueTrack;
    }

    public double getVerticalRate() {
        return verticalRate;
    }

    public void setVerticalRate(double verticalRate) {
        this.verticalRate = verticalRate;
    }

    public double getGeoAltitude() {
        return geoAltitude;
    }

    public void setGeoAltitude(double geoAltitude) {
        this.geoAltitude = geoAltitude;
    }
    
    @Override
	public String toString() {
		return "AirplaneStates [icao24=" + icao24 + ", timePosition=" + timePosition + ", lastContact=" + lastContact
				+ ", longitude=" + longitude + ", laltitude=" + laltitude + ", baroAltitude=" + baroAltitude
				+ ", velocity=" + velocity + ", trueTrack=" + trueTrack + ", verticalRate=" + verticalRate
				+ ", geoAltitude=" + geoAltitude + "]";
	}
}
