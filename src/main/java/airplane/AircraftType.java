package airplane;


public class AircraftType {
    String typeCode;
    String icaoAircraftType;

    public AircraftType() {
    }

    public AircraftType(String typeCode, String icaoAircraftType) {
        this.typeCode = typeCode;
        this.icaoAircraftType = icaoAircraftType;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getIcaoAircraftType() {
        return icaoAircraftType;
    }

    public void setIcaoAircraftType(String icaoAircraftType) {
        this.icaoAircraftType = icaoAircraftType;
    }

    public boolean equals(AircraftType ac){
        return (this.icaoAircraftType.equals(ac.getIcaoAircraftType()) && this.typeCode.equals(ac.getTypeCode()));
    }

    @Override
    public String toString() {
        return "Aircrafttypes{" +
                "typeCode='" + typeCode + '\'' +
                ", icaoAircraftType='" + icaoAircraftType + '\'' +
                '}';
    }
}
