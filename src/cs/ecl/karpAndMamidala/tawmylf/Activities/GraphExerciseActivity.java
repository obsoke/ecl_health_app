package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cs.ecl.karpAndMamidala.tawmylf.Database.BloodPressureDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Database.ExerciseDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Models.BloodPressureItem;
import cs.ecl.karpAndMamidala.tawmylf.Models.ExerciseItem;
import cs.ecl.karpAndMamidala.tawmylf.R;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class GraphExerciseActivity extends Activity {
    private GraphicalView theChart;
    private ExerciseDataSource dataSource;
    private XYMultipleSeriesDataset theDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer theRenderer = new XYMultipleSeriesRenderer();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        dataSource = new ExerciseDataSource(this);

        initChart();
        generateBPGraph();
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
        // TODO: renderer settings
        theRenderer.setShowCustomTextGrid(true);
    }

    private void generateBPGraph() {
        // initialize series
        TimeSeries series = new TimeSeries("Exercise");
        theDataset.addSeries(series);
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(renderer);
        // TODO: renderer properties
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        // populate list with values from DB & add to series
        dataSource.open();
        List<ExerciseItem> itemList = dataSource.getAllExerciseItems();
        for (ExerciseItem i: itemList) {
            // TODO: add data to graph
        }
        dataSource.close();
        // re-draw graph
        theChart.repaint();
    }
}