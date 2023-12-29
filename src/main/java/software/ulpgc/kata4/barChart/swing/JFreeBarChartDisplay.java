package software.ulpgc.kata4.barChart.swing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata4.barChart.BarChart;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

public class JFreeBarChartDisplay extends JPanel implements BarChartDisplay {
    public JFreeBarChartDisplay() {
        setLayout(new BorderLayout());
    }

    @Override
    public void show(BarChart barChart) {
        add(new ChartPanel(toChart(createDataset(barChart))));
    }

    private JFreeChart toChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "",
                "Values",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        return chart;
    }

    private CategoryDataset createDataset(BarChart barChart) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        IntStream.range(0, barChart.series().size())
                .forEach(i -> dataset.addValue(barChart.values().get(i), "", barChart.series().get(i)));

        return dataset;
    }
}
