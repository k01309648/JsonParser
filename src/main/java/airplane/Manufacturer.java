package airplane;
public class Manufacturer {
    String icao;
    String name;

    public Manufacturer() {
    }

    public Manufacturer(String manufacturericao, String manufacturername) {
        this.icao = manufacturericao;
        this.name = manufacturername;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String manufacturericao) {
        this.icao = manufacturericao;
    }

    public String getName() {
        return name;
    }

    public void setManufacturername(String manufacturername) {
        this.name = manufacturername;
    }

    @Override
    public String toString() {
        return "icao: " + getIcao() + " name: " + getName();
    }

    public boolean equals(Manufacturer m){
        return (this.icao.equals(m.getIcao()) && this.name.equals(m.getName()));
    }
}
