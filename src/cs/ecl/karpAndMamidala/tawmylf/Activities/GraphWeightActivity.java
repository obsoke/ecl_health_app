package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import cs.ecl.karpAndMamidala.tawmylf.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Models.ExerciseItem;
import cs.ecl.karpAndMamidala.tawmylf.Models.WeightItem;
import cs.ecl.karpAndMamidala.tawmylf.R;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class GraphWeightActivity extends Activity {
    private GraphicalView theChart;
    private WeightDataSource dataSource;
    private XYMultipleSeriesDataset theDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer theRenderer = new XYMultipleSeriesRenderer();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        dataSource = new WeightDataSource(this);

        initChart();
        generateWeightGraph();
    }

    private void initChart() {
        // initialize chart
        if(theChart == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            theChart = ChartFactory.getTimeChartView(this, theDataset, theRenderer, "yyyy-MM-dd @ hh:mm");
            // TODO: set up theRenderer
            layout.addView(theChart, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        else {
            theChart.repaint();
        }
        // TODO: renderer reset
        theRenderer.setShowCustomTextGrid(true);
    }

    private void generateWeightGraph() {
        // initialize series
        TimeSeries series = new TimeSeries("Weight");
        theDataset.addSeries(series);
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(renderer);
        // TODO: renderer properties
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        // populate list with values from DB & add to series
        dataSource.open();
        List<WeightItem> itemList = dataSource.getAllWeightItems();
        for (WeightItem i: itemList) {
            // TODO: add data to graph
            series.add(i.getDate(), i.getWeight());
        }
        dataSource.close();
        // re-draw graph
        theChart.repaint();
    }
}