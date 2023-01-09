package jsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONObject;

import airplaneStates.FlightDetails;
import airplaneStates.Pair;
import airplaneStates.States;

import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;

import org.json.JSONArray;

public class ReadFromJson implements PropertyManager {



	public ReadFromJson() {

	}

	public static void transformToRDF() throws IOException {

//	System.out.println("Type 1 for Prod Mode | 2 For Test Mode");

		int mode = System.in.read();

		FlightDetails allFlights = new FlightDetails();

		Pair result = parseJSON(
				"https://opensky-network.org/api/states/all?lamin=46.39025&lomin=15.0208&lamax=49.014&lomax=17.1483");

		//Pair result2 = parseJSON("");

		ArrayList<States> states;

//		if (mode == '1') {
//
			states = (ArrayList<States>) result.returnSecond();
//
//		} else if (mode == '2') {

			//states = (ArrayList<States>) result2.returnSecond();

//		} else {
//			System.out.println("Invalid entry");
//			return;
//		}

		// states.forEach(st -> System.out.println(st.toString()));
		long time = result.returnFirst();
		allFlights.add(0, states);

		for (int i = 0; i < states.size(); i++) {

			// Zeitpunkt adden
			Resource currRes = m.createResource(ns + "t?=" + time + "i?=" + states.get(i).getIcao24());

			// Add Icao24
			currRes.addProperty(icao24Pr, states.get(i).getIcao24());

			// Add TimePosition
			String tp2String = states.get(i).getTimePosition().toString();
			currRes.addProperty(timePositionPr, tp2String);

			// Add LastContact
			String lc2String = states.get(i).getLastContact().toString();
			currRes.addProperty(lastContactPr, lc2String);

			// Add Longitude
			String lg2String = String.valueOf(states.get(i).getLongitude());
			currRes.addProperty(longitudePr, lg2String);

			// Add Longitude
			String lt2String = String.valueOf(states.get(i).getLaltitude());
			currRes.addProperty(laltitudePr, lt2String);

			// Add BaroAltitude
			String ba2String = String.valueOf(states.get(i).getBaroAltitude());
			currRes.addProperty(baroAltitudePr, ba2String);

			// Add Velocity
			String vel2String = String.valueOf(states.get(i).getVelocity());
			currRes.addProperty(velocityPr, vel2String);

			// Add trueTrack
			String tt2String = String.valueOf(states.get(i).getTrueTrack());
			currRes.addProperty(trueTrackPr, tt2String);

			// Add Vertical Rate
			String vr2String = String.valueOf(states.get(i).getVerticalRate());
			currRes.addProperty(verticalRatePr, vr2String);

			// Add geoAltitude
			String ga2String = String.valueOf(states.get(i).getVelocity());
			currRes.addProperty(geoAltitudePr, ga2String);
			
			//Add State to Aircraft
			
			currRes.addProperty(belongsToAirplanePr, "http://www.example.org/aircraft#" + states.get(i).getIcao24());

		}

		m.setNsPrefix("states", "http://www.example.org/statesOf#");

		// Man muss ein DataSet im Fuseki Server erstellen, meines ist "Test". Weiters
		// muss der Fuseki-Server laufen.
		try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/Test")) {
			conn.load("http://dynamic#"+time, m);
//			conn.load(m);
		}

		m.write(System.out, "TURTLE");
		validateStates(m);

	}

	// Vertical Rate sind entweder Long oder Doubles, dafür die Hilfsfunktion
	private static double doubleValue(Object value) {
		return (value instanceof Number ? ((Number) value).doubleValue() : -1.0);
	}

	private static long longValue(Object value) {
		return (value instanceof Number ? ((Number) value).longValue() : -1);
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return new JSONObject(jsonText);

		} finally {
			is.close();
		}
	}

	public static Pair parseJSON(String url) throws IOException {

		JSONObject fullJson = readJsonFromUrl(url);

		Long time = longValue(fullJson.get("time"));
		// System.out.println("Time: " + time);
		ArrayList<States> statesArray = new ArrayList<States>();

		JSONArray states = (JSONArray) fullJson.get("states");

		for (int i = 0; i < states.length(); i++) {

			JSONArray currentState = (JSONArray) states.get(i);

			String icao24 = (String) currentState.get(0);
			Long timePosition = longValue(currentState.get(3));
			Long lastContact = longValue(currentState.get(4));
			double longitude = doubleValue(currentState.get(5));
			double latitude = doubleValue(currentState.get(6));
			double baroAltitude = doubleValue(currentState.get(7));
			double velocity = doubleValue(currentState.get(9));
			double trueTrack = doubleValue(currentState.get(10));

			double verticalRate = doubleValue(currentState.get(11));
			double geoAltitude = 0;
			if (currentState.get(13) != null) {
				geoAltitude = doubleValue(currentState.get(13));
			}

			States curr = new States(icao24, timePosition, lastContact, longitude, latitude, baroAltitude, velocity,
					trueTrack, verticalRate, geoAltitude);
			statesArray.add(curr);

		}

		Pair result = new Pair(time, statesArray);

		return result;
	}

	public static boolean validateStates(Model stateModel) {
		String SHAPE = "src/main/resources/shacl-shapes/shacl_shapes_flight.ttl";
		Graph statesShapesGraph = RDFDataMgr.loadGraph(SHAPE);

		Shapes shapes = Shapes.parse(statesShapesGraph);
		ValidationReport report = ShaclValidator.get().validate(shapes, stateModel.getGraph());

		boolean valid = report.conforms();
		RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);
		if (valid) {
			return true;
		} else {
			return false;
		}


	}
	
//	public static void addDynamicData(ArrayList<ACDataBaseLine> db) throws IOException {
//		
//		Pair result = parseJSON("file:///C:\\Users\\Florian\\DKE_PR\\DKE_Proj\\jsonfiles/TestFile.json");
//		
//		ArrayList<States> states = (ArrayList<States>) result.returnSecond();
//
//		long time = result.returnFirst();
//		
//		
//
//		
//	}
	


}
