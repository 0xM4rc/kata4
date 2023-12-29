package software.ulpgc.kata4.electricVehicle;

public class ElectricVehicle {
    private final String county;
    private final String city;
    private final String modelYear;
    private final String make;
    private final String model;

    public ElectricVehicle(String county, String city, String modelYear, String make, String model) {
        this.county = county;
        this.city = city;
        this.modelYear = modelYear;
        this.make = make;
        this.model = model;
    }

    public String getCounty() {
        return county;
    }

    public String getCity() {
        return city;
    }

    public String getModelYear() {
        return modelYear;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

}
