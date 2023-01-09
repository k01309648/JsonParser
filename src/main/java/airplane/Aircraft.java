package airplane;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aircraft {
    String icao24;
    String registration;
    String model;
    String serialNumber;
    String lineNumber;
    String owner;
    String testReg;
    String registered;
    String reguntil;
    String built;
    String firstFlightDate;
    String engines;
    String categoryDescription;

    public Aircraft() {
    }

    public Aircraft(String icao24, String registration, String model, String serialNumber, String lineNumber, String owner, String testReg, String registered, String reguntil, String built, String firstFlightDate, String engines, String categoryDescription) {
        this.icao24 = icao24;
        this.registration = registration;
        this.model = model;
        this.serialNumber = serialNumber;
        this.lineNumber = lineNumber;
        this.owner = owner;
        this.testReg = testReg;
        this.registered = registered;
        this.reguntil = reguntil;
        this.built = built;
        this.firstFlightDate = firstFlightDate;
        this.engines = engines;
        this.categoryDescription = categoryDescription;
    }

    String getIRI(){
        return "http://example.com/"+icao24;
    }
    String serialize(String syntax){
        Model model = ModelFactory.createDefaultModel();
        Resource resource = model.createResource(getIRI());
        // add the property
        // resource.addProperty(, this.owner);
        StringWriter out = new StringWriter();
        model.write(out, syntax);
        return out.toString();
    }


    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTestReg() {
        return testReg;
    }

    public void setTestReg(String testReg) {
        this.testReg = testReg;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getReguntil() {
        return reguntil;
    }

    public void setReguntil(String reguntil) {
        this.reguntil = reguntil;
    }

    public String getBuilt() {
        return built;
    }

    public void setBuilt(String built) {
        this.built = built;
    }

    public String getFirstFlightDate() {
        return firstFlightDate;
    }

    public void setFirstFlightDate(String firstFlightDate) {
        this.firstFlightDate = firstFlightDate;
    }

    public String getEngines() {
        return engines;
    }

    public void setEngines(String engines) {
        this.engines = engines;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "icao24='" + icao24 + '\'' +
                ", registration='" + registration + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", owner='" + owner + '\'' +
                ", testReg='" + testReg + '\'' +
                ", registered='" + registered + '\'' +
                ", reguntil='" + reguntil + '\'' +
                ", built='" + built + '\'' +
                ", firstFlightDate='" + firstFlightDate + '\'' +
                ", engines='" + engines + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}

