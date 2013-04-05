package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.LinearLayout;
import cs.ecl.karpAndMamidala.liveforever.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.WeightItem;
import cs.ecl.karpAndMamidala.liveforever.R;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class GraphWeightActivity extends Activity implements AlertDialogFragment.NoticeDialogListener{
    private GraphicalView theChart;
    private WeightDataSource dataSource;
    private XYMultipleSeriesDataset theDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer theRenderer = new XYMultipleSeriesRenderer();

    private boolean showAll = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        dataSource = new WeightDataSource(this);

        initChart();
        generateWeightGraph();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.graph_weight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_genWeight:
                AlertDialogFragment frag = new AlertDialogFragment(R.string.action_genWeight,
                        R.string.menu_yes,
                        R.string.menu_no);
                frag.show(getFragmentManager(), "AlertDialogFragment");
                return true;
            case R.id.menu_toggleData:
                if(this.showAll == true) {
                    this.showAll = false;
                    this.generateWeightGraph();
                    item.setTitle(R.string.action_showAll);
                    theRenderer.setChartTitle("Your Weight (last 30 days)");
                }
                else {
                    this.showAll = true;
                    this.generateWeightGraph();
                    item.setTitle(R.string.action_show30Days);
                    theRenderer.setChartTitle("Your Weight (all time)");
                }
            default:
                return super.onOptionsItemSelected(item);
        }
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

        theRenderer.setPanEnabled(false, false);
        theRenderer.setShowLegend(false);
        theRenderer.setAxisTitleTextSize(20);
        theRenderer.setYAxisMin(0);
        theRenderer.setYTitle("Weight");
        theRenderer.setXTitle("Time");
        theRenderer.setChartTitle("Your Weight (last 30 days)");
        theRenderer.setChartTitleTextSize(30);
    }

    private void generateWeightGraph() {
        // clear out data series
        int isr = theRenderer.getSeriesRendererCount();
        for (int i = 0; i < isr; i++) {
            theRenderer.removeSeriesRenderer(theRenderer.getSeriesRendererAt(i));
        }
        int isd = theDataset.getSeriesCount();
        for (int i = 0; i < isd; i++) {
            theDataset.removeSeries(i);
        }

        // initialize series
        TimeSeries series = new TimeSeries("Weight");
        theDataset.addSeries(series);
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(renderer);
        // TODO: renderer properties
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);
        renderer.setFillBelowLine(true);
        renderer.setFillBelowLineColor(Color.BLUE);

        // populate list with values from DB & add to series
        dataSource.open();
        List<WeightItem> itemList;
        if(this.showAll == true) {
            itemList = dataSource.getAllWeightItems();
        }
        else {
            itemList = dataSource.getLast30DaysWeightItems();
        }

        for (WeightItem i: itemList) {
            series.add(i.getDate(), i.getWeight());
        }

        dataSource.close();
        // re-draw graph
        theChart.repaint();
    }

    private void generateWeightData() {
        dataSource.generateWeightData();
        this.generateWeightGraph();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // delete user & data
        this.generateWeightData();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // do nothing
    }
}