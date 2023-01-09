package readStaticData;


import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdfconnection.RDFConnection;

import airplane.ACDataBaseLine;
import airplane.Aircraft;
import airplane.AircraftType;
import airplane.Manufacturer;
import airplane.Operator;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;

import java.io.*;
import java.util.ArrayList;


public class ParseStaticData implements PropertyManager {
	
	public ParseStaticData() {
		
	}

	private static ArrayList<ACDataBaseLine> dataBase; //Aircraft
    private static ArrayList<Operator> operators;
    private static ArrayList<Manufacturer> manufacturers;
    private static ArrayList<AircraftType> aircraftTypes;
    private static ArrayList<Aircraft> aircrafts;
    
    public static ArrayList<ACDataBaseLine> readStaticData() throws IOException {
        
    	dataBase = new ArrayList<>();
        operators = new ArrayList<>();
        manufacturers = new ArrayList<>();
        aircraftTypes = new ArrayList<>();
        aircrafts = new ArrayList<>();

        try {
        	
            String path = "C:\\Users\\georg\\Documents\\GitHub\\JsonParser 5.0\\DKE_Proj\\test2.csv";
            String path2 = "";
            
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            int i = 0;
            while((line = reader.readLine()) != null && i < 10){                                                                    //ToDo: remove i < 10
                String[] dataLine = line.split(",");

                if(dataLine.length == 27) {
                    if(isDachRegion(dataLine[1])){
                        removeQuotationMarks(dataLine);                                                                             // remove Quotationmarks
                        createDataBase(dataLine);
                        i++;


                        addAircraft(dataLine[0], dataLine[1], dataLine[4], dataLine[6], dataLine[7], dataLine[13], dataLine[14],   // Add new Manufacturer only if not already in List
                        dataLine[15], dataLine[16], dataLine[18], dataLine[19], dataLine[21], dataLine[26]);
                        addNewManufacturer(dataLine[2], dataLine[3]);                                                             // Add new Manufacturer only if not already in List
                        addNewOperator(dataLine[9], dataLine[10], dataLine[11], dataLine[12]);                                    // Add new Operator only if not already in List
                        addNewAircraftType(dataLine[5], dataLine[8]);
                    }
                }
            }
            parseToRDF(dataBase);

            /* ------------------------------TEST-PRINT --------------------------*/
//            for (Manufacturer m : manufacturers){
//                System.out.println(m);
//            }
//
//            for (AircraftType at : aircraftTypes){
//                System.out.println(at);
//            }
//            for (Operator o : operators){
//                System.out.println(o);
//            }
            for(ACDataBaseLine a : dataBase){
                System.out.println(a);
            }

            System.out.println("dataBase: " + dataBase.size());
            System.out.println("Aircrafts: " + aircrafts.size());
            System.out.println("Manufacturers: " + manufacturers.size());
            System.out.println("Operators: " + operators.size());
            System.out.println("Types: " + aircraftTypes.size());
            System.out.println("------------------------------------- ");

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
		return dataBase;
    }

    /*------------------------------------------------------------------------------------------*/

    public static boolean isDachRegion(String reg) {
        return (reg.contains("OE-") || reg.contains("HB-") || reg.contains("D-"));
    }

    public static boolean selectNotEmpty(String s) {
        return (s != null && !s.isEmpty());
    }

    private static void removeQuotationMarks(String[] dataLine) {
        for(int i = 0; i < dataLine.length; i++){
            if(dataLine[i].length() > 2) {
                dataLine[i] = dataLine[i].substring(1, dataLine[i].length()-1);
            }else{
                dataLine[i] = "";
            }
        }
    }

    private static void createDataBase(String[] dataLine) {

        ACDataBaseLine acDataBaseLine = new ACDataBaseLine();
        acDataBaseLine.setIcao24(dataLine[0]);
        acDataBaseLine.setRegistration(dataLine[1]);
        acDataBaseLine.setManufacturerIcao(dataLine[2]);
        acDataBaseLine.setManufacturerName(dataLine[3]);
        acDataBaseLine.setModel(dataLine[4]);
        acDataBaseLine.setTypeCode(dataLine[5]);
        acDataBaseLine.setSerialNumber(dataLine[6]);
        acDataBaseLine.setLineNumber(dataLine[7]);
        acDataBaseLine.setIcaoAircraftType(dataLine[8]);
        acDataBaseLine.setOperator(dataLine[9]);
        acDataBaseLine.setOperatorCallSign(dataLine[10]);
        acDataBaseLine.setOperatorIcao(dataLine[11]);
        acDataBaseLine.setOperatorIata(dataLine[12]);
        acDataBaseLine.setOwner(dataLine[13]);
        acDataBaseLine.setTestReg(dataLine[14]);
        acDataBaseLine.setRegistered(dataLine[15]);
        acDataBaseLine.setRegUntil(dataLine[16]);
        acDataBaseLine.setStatus(dataLine[17]);
        acDataBaseLine.setBuilt(dataLine[18]);
        acDataBaseLine.setFirstFlightDate(dataLine[19]);
        acDataBaseLine.setSeatConfiguration(dataLine[20]);
        acDataBaseLine.setEngines(dataLine[21]);
        acDataBaseLine.setModes(dataLine[22]);
        acDataBaseLine.setAdsb(dataLine[23]);
        acDataBaseLine.setAcars(dataLine[24]);
        acDataBaseLine.setNotes(dataLine[25]);
        acDataBaseLine.setCategoryDescription(dataLine[26]);
        dataBase.add(acDataBaseLine);
    }

    private static void addAircraft(String icao24, String registration, String model, String serialNo, String lineNo,
                                    String owner, String testReg, String registered, String regUntil, String built,
                                    String firstFlight, String engines, String catDescr) {
        if(icao24 != null && !icao24.isEmpty()){
            aircrafts.add(new Aircraft(icao24, registration, model, serialNo, lineNo, owner, testReg, registered, regUntil, built, firstFlight, engines, catDescr));
        }
    }

    private static void addNewOperator(String name, String callSign, String icao, String iata) {
        if (selectNotEmpty(name) || selectNotEmpty(callSign) || selectNotEmpty(icao) || selectNotEmpty(iata)) {

            Operator op = new Operator(name, callSign, icao, iata);
            boolean contains = false;

            for(Operator o : operators){
                if(o.equals(op)){
                    contains = true;
                    break;
                }
            }
            if(!contains){
                operators.add(op);
            }
        }
    }

    private static void addNewManufacturer(String icao, String name) {
        if (selectNotEmpty(icao) || selectNotEmpty(name)) {
            Manufacturer m = new Manufacturer(icao, name);
            boolean contains = false;
            for(Manufacturer man : manufacturers){
                if(m.equals(man)){
                    contains = true;
                    break;
                }
            }
            if(!contains){
                manufacturers.add(m);
            }
        }
    }

    private static void addNewAircraftType(String typeCode, String icaoAcType) {
        if(selectNotEmpty(typeCode) || selectNotEmpty(icaoAcType)){
            AircraftType type = new AircraftType(typeCode, icaoAcType);
            boolean contains = false;
            for(AircraftType a : aircraftTypes){
                if(a.equals(type)){
                    contains = true;
                    break;
                }
            }
            if(!contains){
                aircraftTypes.add(type);
            }
        }
    }

    public static void parseToRDF(ArrayList<ACDataBaseLine> dataBase) {
    	
        for(ACDataBaseLine ac : dataBase){
            // Aircraft-Properties
            m.createResource(ns + ac.getIcao24())
                    .addProperty(registrationProperty, ac.getRegistration()).addProperty(modelProperty, ac.getModel())
                    .addProperty(serialNoProperty, ac.getSerialNumber()).addProperty(lineNumberProperty, ac.getLineNumber())
                    .addProperty(ownerProperty, ac.getOwner()).addProperty(registeredProperty, ac.getRegistered())
                    .addProperty(testRegProperty, ac.getTestReg()).addProperty(regUntilProperty, ac.getRegUntil())
                    .addProperty(builtProperty, ac.getBuilt()).addProperty(firstFlightDateProperty, ac.getFirstFlightDate())
                    .addProperty(enginesProperty, ac.getEngines()).addProperty(categoryDescriptionProperty, ac.getCategoryDescription())

                    //Manufacturer-Properties
                    .addProperty(manufacturerProperty, m.createResource()
                            .addProperty(manufacturerIcaoProperty,ac.getManufacturerIcao())
                            .addProperty(manufacturerNameProperty, ac.getManufacturerName()))

                    //Types-Properties
                    .addProperty(aircraftTypesProperty, m.createResource()
                            .addProperty(aircraftTypesCodeProperty, ac.getTypeCode())
                            .addProperty(aircraftTypesIcaoProperty, ac.getIcaoAircraftType()))

                    //Operator-Properties
                    .addProperty(operatorProperty, m.createResource()
                            .addProperty(operatorNameProperty, ac.getOperator()).addProperty(operatorCallSignProperty, ac.getOperatorCallSign())
                            .addProperty(operatorIcaoProperty, ac.getOperatorIcao()).addProperty(operatorIataProperty, ac.getOperatorIata()));

            m.write(System.out, "TURTLE");
        }
        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/Test") ) {
            conn.load(m);
        }

        validateAircraft(m);
    }
    public static boolean validateAircraft(Model aircraftModel) {
        String SHAPES = "src/main/resources/shacl-shapes/shacl_shapes_aircraft.ttl";
        Graph aircraftShapesGraph = RDFDataMgr.loadGraph(SHAPES);

        Shapes shapes = Shapes.parse(aircraftShapesGraph);
        ValidationReport report = ShaclValidator.get().validate(shapes, aircraftModel.getGraph());

        boolean valid = report.conforms();
        RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);
        if (valid) {
            return true;
        } else {
            return false;
        }
    }
}

