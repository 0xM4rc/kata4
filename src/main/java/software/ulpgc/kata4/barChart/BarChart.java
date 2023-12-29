package software.ulpgc.kata4.barChart;

import java.util.List;

public record BarChart(List<Double> values, List<String> series, List<String> category) {
}
