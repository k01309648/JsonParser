package readStaticData;


import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdfconnection.RDFConnection;

import airplane.Aircraft;
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

	private static ArrayList<Aircraft> dataBase; //Aircraft

    public static ArrayList<Aircraft> readStaticData() throws IOException {
        
    	dataBase = new ArrayList<>();

        try {
        	
            String path = "test2.csv";
            
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

                    }
                }
            }
            parseToRDF(dataBase);


            for(Aircraft a : dataBase){
                System.out.println(a);
            }

            System.out.println("dataBase: " + dataBase.size());

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

        Aircraft acDataBaseLine = new Aircraft();
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


    public static void parseToRDF(ArrayList<Aircraft> dataBase) {
    	
        for(Aircraft ac : dataBase){
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

        return valid;
    }
}

