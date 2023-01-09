package jsonReader;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public interface PropertyManager {
	
	public final Model m = ModelFactory.createDefaultModel();
	
	public final String ns = "http://www.example.org/statesOf#";

	public final Property icao24Pr = m.createProperty(ns + "icao24");
	public final Property timePositionPr = m.createProperty(ns + "timePos");
	public final Property lastContactPr = m.createProperty(ns + "lastContact");
	public final Property longitudePr = m.createProperty(ns + "longitude");
	public final Property laltitudePr = m.createProperty(ns + "laltitude");
	public final Property baroAltitudePr = m.createProperty(ns + "baroAltitude");
	public final Property velocityPr = m.createProperty(ns + "velocity");
	public final Property trueTrackPr = m.createProperty(ns + "trueTrack");
	public final Property verticalRatePr = m.createProperty(ns + "verticalRate");
	public final Property geoAltitudePr = m.createProperty(ns + "geoAltitude"); 
	
	public final Property belongsToAirplanePr = m.createProperty(ns + "belongsTo");
    


}
