package readStaticData;



import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;


public interface PropertyManager {
	
    static Model m = ModelFactory.createDefaultModel();
    static String ns = "http://www.example.org/aircraft#";
    static String man = "http://www.example.org/aircraft/manufacturer#";
    static String ty = "http://www.example.org/aircraft/type#";
    static String op = "http://www.example.org/aircraft/operator#";

    static Property registrationProperty = m.createProperty(ns + "hasRegistration");
    static Property modelProperty = m.createProperty(ns + "hasModel");
    static Property serialNoProperty = m.createProperty(ns + "hasSerialNo");
    static Property lineNumberProperty = m.createProperty(ns + "hasLineNumber");
    static Property ownerProperty = m.createProperty(ns + "hasOwner");
    static Property testRegProperty = m.createProperty(ns + "hasTestReg");
    static Property registeredProperty = m.createProperty(ns + "isRegistered");
    static Property regUntilProperty = m.createProperty(ns + "isRegUntil");
    static Property builtProperty = m.createProperty(ns + "wasBuilt");
    static Property firstFlightDateProperty = m.createProperty(ns + "hasFirstFlightDate");
    static Property enginesProperty = m.createProperty(ns + "hasEngines");
    static Property categoryDescriptionProperty = m.createProperty(ns + "hasCategoryDescription");

    static Property manufacturerProperty = m.createProperty(ns + "hasManufacturer");
    static Property aircraftTypesProperty = m.createProperty(ns + "hasAircraftType");
    static Property operatorProperty = m.createProperty(ns + "hasOperator");

    static Property manufacturerIcaoProperty = m.createProperty(man + "hasIcao");
    static Property manufacturerNameProperty = m.createProperty(man + "hasName");

    static Property operatorNameProperty = m.createProperty(op + "hasName");
    static Property operatorCallSignProperty = m.createProperty(op + "hasCallSign");
    static Property operatorIcaoProperty = m.createProperty(op + "hasIcao");
    static Property operatorIataProperty = m.createProperty(op + "hasIata");

    static Property aircraftTypesCodeProperty = m.createProperty(ty + "hasTypeCode");
    static Property aircraftTypesIcaoProperty = m.createProperty(ty + "hasIcaoType");

}
