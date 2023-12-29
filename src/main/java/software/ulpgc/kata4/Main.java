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
        List<ElectricVehicle> vehicles;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:Electric_Vehicle.db")){
            ElectricVehicleLoader loader = new SqliteElectricVehicleLoader(connection);
            vehicles = loader.load();
        }

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
