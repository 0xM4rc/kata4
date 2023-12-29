package software.ulpgc.kata4.barChart.swing;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JFreeBarChartDisplay barChartDispaly
            ;

    public MainFrame() throws HeadlessException {
        setTitle("Bar Chart");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(createBarChartDisplay());
    }

    private Component createBarChartDisplay() {
        this.barChartDispaly = new JFreeBarChartDisplay();
        return this.barChartDispaly;
    }

    public BarChartDisplay barChartDisplay(){
        return this.barChartDispaly;
    }
}
