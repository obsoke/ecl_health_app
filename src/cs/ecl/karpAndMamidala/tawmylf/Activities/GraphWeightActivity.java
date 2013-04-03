package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.os.Bundle;
import cs.ecl.karpAndMamidala.tawmylf.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Models.ExerciseItem;
import cs.ecl.karpAndMamidala.tawmylf.Models.WeightItem;
import cs.ecl.karpAndMamidala.tawmylf.R;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class GraphWeightActivity extends Activity {
    private WeightDataSource dataSource;
    private GraphicalView theChart;
    private XYSeries currentSeries;
    private XYSeriesRenderer currentSeriesRenderer;
    private XYMultipleSeriesDataset theDataset;
    private XYMultipleSeriesRenderer theRenderer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        dataSource = new WeightDataSource(this);

        currentSeries = new XYSeries("Weight");
        theDataset = new XYMultipleSeriesDataset();
        theRenderer = new XYMultipleSeriesRenderer();
        currentSeriesRenderer = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(currentSeriesRenderer);

        theDataset.addSeries(currentSeries);
        generateWeightGraph();
    }

    private void generateWeightGraph() {
        dataSource.open();
        List<WeightItem> itemList = dataSource.getAllWeightItems();
        for (WeightItem i: itemList) {
            // TODO: add data to graph
            long date = i.getDate().getTime();
            float weight = i.getWeight();
            currentSeries.add(date, weight);
        }
        dataSource.close();

    }
}