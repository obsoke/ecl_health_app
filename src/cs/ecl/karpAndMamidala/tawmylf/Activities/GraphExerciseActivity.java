package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cs.ecl.karpAndMamidala.tawmylf.Database.ExerciseDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Models.ExerciseItem;
import cs.ecl.karpAndMamidala.tawmylf.R;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.*;

public class GraphExerciseActivity extends Activity {
    private GraphicalView theChart;
    private ExerciseDataSource dataSource;
    private ArrayList<ExerciseItem> cardioItems;
    private ArrayList<ExerciseItem> strengthItems;
    private XYMultipleSeriesDataset theDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer theRenderer = new XYMultipleSeriesRenderer();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        dataSource = new ExerciseDataSource(this);

        initChart();
        generateBPData();
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

    private void generateBPData() {
        cardioItems = new ArrayList<ExerciseItem>();
        strengthItems = new ArrayList<ExerciseItem>();
        // populate list with values from DB
        dataSource.open();
        List<ExerciseItem> itemList = dataSource.getAllExerciseItems();
        dataSource.close();
        for (ExerciseItem i: itemList) {
            if (i.getType().equalsIgnoreCase("Cardio")) {
                cardioItems.add(i);
            }
            else {
                strengthItems.add(i);
            }
        } // end for each
    } // end generateBPData()

    private void generateBPGraph() {
        // initialize series
        TimeSeries strengthSeries = new TimeSeries("Stength Exercise");
        TimeSeries cardioSeries = new TimeSeries("Cardio Exercise");
        theDataset.addSeries(strengthSeries);
        theDataset.addSeries(cardioSeries);
        XYSeriesRenderer strengthRenderer = new XYSeriesRenderer();
        XYSeriesRenderer cardioRenderer = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(strengthRenderer);
        theRenderer.addSeriesRenderer(cardioRenderer);
        // renderer properties
        strengthRenderer.setPointStyle(PointStyle.CIRCLE);
        strengthRenderer.setFillPoints(true);
        strengthRenderer.setColor(Color.RED);
        cardioRenderer.setPointStyle(PointStyle.DIAMOND);
        cardioRenderer.setFillPoints(true);

        // populate strength series
        for (ExerciseItem i: strengthItems) {
            strengthSeries.add(i.getDate(), i.getIntensity());
        }

        // populate cardio series
        for (ExerciseItem i: cardioItems) {
            cardioSeries.add(i.getDate(), i.getDuration());
        }
        // re-draw graph
        theChart.repaint();
    }
}