package airplane;
public class ACDataBaseLine {
    String icao24;
    String registration;
    String manufacturerIcao;
    String manufacturerName;
    String model;
    String typeCode;
    String serialNumber;
    String lineNumber;
    String icaoAircraftType;
    String operator;
    String operatorCallSign;
    String operatorIcao;
    String operatorIata;
    String owner;
    String testReg;
    String registered;
    String regUntil;
    String status;
    String built;
    String firstFlightDate;
    String seatConfiguration;
    String engines;
    String modes;
    String adsb;
    String acars;
    String notes;
    String categoryDescription;

    public ACDataBaseLine() {
    }


    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getManufacturerIcao() {
        return manufacturerIcao;
    }

    public void setManufacturerIcao(String manufacturerIcao) {
        this.manufacturerIcao = manufacturerIcao;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getIcaoAircraftType() {
        return icaoAircraftType;
    }

    public void setIcaoAircraftType(String icaoAircraftType) {
        this.icaoAircraftType = icaoAircraftType;
    }

    public String getOperatorCallSign() {
        return operatorCallSign;
    }

    public void setOperatorCallSign(String operatorCallSign) {
        this.operatorCallSign = operatorCallSign;
    }

    public String getOperatorIcao() {
        return operatorIcao;
    }

    public void setOperatorIcao(String operatorIcao) {
        this.operatorIcao = operatorIcao;
    }

    public String getOperatorIata() {
        return operatorIata;
    }

    public void setOperatorIata(String operatorIata) {
        this.operatorIata = operatorIata;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTestReg() { return testReg; }

    public void setTestReg(String testReg) { this.testReg = testReg;}

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getRegUntil() {
        return regUntil;
    }

    public void setRegUntil(String regUntil) {
        this.regUntil = regUntil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuilt() {
        return built;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
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

    public String getSeatConfiguration() {
        return seatConfiguration;
    }

    public void setSeatConfiguration(String seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }

    public String getEngines() {
        return engines;
    }

    public void setEngines(String engines) {
        this.engines = engines;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getAdsb() {
        return adsb;
    }

    public void setAdsb(String adsb) {
        this.adsb = adsb;
    }

    public String getAcars() {
        return acars;
    }

    public void setAcars(String acars) {
        this.acars = acars;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return "ACDataBaseLine{" +
                "icao24='" + icao24 + '\'' +
                ", registrationIRI='" + registration + '\'' +
                ", icao='" + manufacturerIcao + '\'' +
                ", name='" + manufacturerName + '\'' +
                ", modelIRI='" + model + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", icaoAircraftType='" + icaoAircraftType + '\'' +
                ", name='" + operator + '\'' +
                ", operatorCallSign='" + operatorCallSign + '\'' +
                ", operatorIcao='" + operatorIcao + '\'' +
                ", operatorIata='" + operatorIata + '\'' +
                ", owner='" + owner + '\'' +
                ", testReg='" + testReg + '\'' +
                ", registered='" + registered + '\'' +
                ", regUntil='" + regUntil + '\'' +
                ", status='" + status + '\'' +
                ", built='" + built + '\'' +
                ", firstFlightDate='" + firstFlightDate + '\'' +
                ", seatConfiguration='" + seatConfiguration + '\'' +
                ", engines='" + engines + '\'' +
                ", modes='" + modes + '\'' +
                ", adsb='" + adsb + '\'' +
                ", acars='" + acars + '\'' +
                ", notes='" + notes + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}

