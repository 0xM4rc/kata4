package software.ulpgc.kata4;

import software.ulpgc.kata4.barChart.BarChart;
import software.ulpgc.kata4.barChart.swing.MainFrame;
import software.ulpgc.kata4.electricVehicle.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Cargar datos de vehículos eléctricos desde un archivo CSV
        List<ElectricVehicle> vehicles = CsvFileElectricVehicleLoader.with(
                        new File("src/main/resources/Electric_Vehicle_Population_Data_simplified.csv"))
                .load();

        ElectricVehicleStatistic statistic = new YearsElectricVehicleStatic();
        Map<String, Integer> map = statistic.calculate(vehicles);

        List<String> sortedYears = new ArrayList<>(map.keySet());
        sortedYears.sort(null);

        List<Double> sortedValues = new ArrayList<>();
        for (String year :
                sortedYears) {
            sortedValues.add(Double.valueOf(map.get(year)));
        }

        MainFrame frame = new MainFrame();

        frame.barChartDisplay().show(new BarChart(sortedValues, sortedYears, null));

        frame.setVisible(true);
    }
}
