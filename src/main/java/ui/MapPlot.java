package ui;

import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

public class MapPlot extends JFrame {

  public MapPlot(String title, XYDataset dataset) {
    super(title);

    // Create chart
    JFreeChart chart = ChartFactory.createScatterPlot(
        "Cidades", 
        "X-Axis", 
        "Y-Axis",
        dataset
    );

    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));    
   
    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    this.setContentPane(panel);
    this.setSize(800, 400);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
}
