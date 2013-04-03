package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.os.Bundle;
import cs.ecl.karpAndMamidala.tawmylf.Database.ExerciseDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Models.ExerciseItem;
import cs.ecl.karpAndMamidala.tawmylf.R;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class GraphExerciseActivity extends Activity {
    private ExerciseDataSource dataSource;
    private GraphicalView theChart;
    private XYSeries currentSeries;
    private XYSeriesRenderer currentSeriesRenderer;
    private XYMultipleSeriesDataset theDataset;
    private XYMultipleSeriesRenderer theRenderer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        dataSource = new ExerciseDataSource(this);

        currentSeries = new XYSeries("Exercise");
        theDataset = new XYMultipleSeriesDataset();
        theRenderer = new XYMultipleSeriesRenderer();
        currentSeriesRenderer = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(currentSeriesRenderer);

        theDataset.addSeries(currentSeries);
        generateWeightGraph();
    }

    private void generateWeightGraph() {
        dataSource.open();
        List<ExerciseItem> itemList = dataSource.getAllExerciseItems();
        for (ExerciseItem i: itemList) {
            // TODO: add data to graph
            // currentSeries.add();
        }
        dataSource.close();

    }
}