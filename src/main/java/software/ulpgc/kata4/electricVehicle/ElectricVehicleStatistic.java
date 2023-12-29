package software.ulpgc.kata4.electricVehicle;

import java.util.List;
import java.util.Map;

public interface ElectricVehicleStatistic {
    Map<String,Integer> calculate(List<ElectricVehicle> vehicles);
}
