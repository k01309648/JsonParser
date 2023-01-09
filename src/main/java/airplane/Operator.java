package airplane;
public class Operator {

    String name;
    String callSign;
    String icao;
    String iata;

    public Operator() {
    }

    public Operator(String operator, String operatorcallsign, String operatoricao, String operatoriata) {
        this.name = operator;
        this.callSign = operatorcallsign;
        this.icao = operatoricao;
        this.iata = operatoriata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public boolean equals(Operator op){
        return (this.name.equals(op.getName()) && this.callSign.equals(op.getCallSign()) &&
                this.getIcao().equals(op.getIcao()) && this.getIata().equals(op.getIata()));
    }

    @Override
    public String toString() {
        return "Operator {" +
                " operatorName ='" + name + '\'' +
                ", operatorCallSign='" + callSign + '\'' +
                ", operatorIcao='" + icao + '\'' +
                '}';
    }
}
