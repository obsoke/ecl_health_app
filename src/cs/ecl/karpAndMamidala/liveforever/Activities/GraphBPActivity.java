package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cs.ecl.karpAndMamidala.liveforever.Database.BloodPressureDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.BloodPressureItem;
import cs.ecl.karpAndMamidala.liveforever.R;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Date;
import java.util.List;

public class GraphBPActivity extends Activity implements AlertDialogFragment.AlertDialogNotifier {
    private GraphicalView theChart;
    private BloodPressureDataSource dataSource;
    private XYMultipleSeriesDataset theDataset;
    private XYMultipleSeriesRenderer theRenderer;

    private boolean showAll = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        dataSource = new BloodPressureDataSource(this);

        initChart();
        generateBPGraph();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.graph_bp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_genBP:
                AlertDialogFragment frag = new AlertDialogFragment(R.string.action_genBP,
                        R.string.menu_yes,
                        R.string.menu_no);
                frag.show(getFragmentManager(), "AlertDialogFragment");
                return true;
            case R.id.menu_toggleData:
                if(this.showAll == true) {
                    this.showAll = false;
                    this.initChart();
                    this.generateBPGraph();
                    item.setTitle(R.string.action_showAll);
                    theRenderer.setChartTitle("Your Blood Pressure (last 30 days)");
                }
                else {
                    this.showAll = true;
                    this.initChart();
                    this.generateBPGraph();
                    item.setTitle(R.string.action_show30Days);
                    theRenderer.setChartTitle("Your Blood Pressure (all time)");
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initChart() {
        // initialize chart
        theDataset = new XYMultipleSeriesDataset();
        theRenderer = new XYMultipleSeriesRenderer();
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
        layout.removeAllViews();
        theChart = ChartFactory.getTimeChartView(this, theDataset, theRenderer, "yyyy-MM-dd");
        // TODO: set up theRenderer
        layout.addView(theChart, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        theRenderer.setPanEnabled(false, false);
        theRenderer.setAxisTitleTextSize(20);
        theRenderer.setFitLegend(true);
        theRenderer.setYAxisMin(0);
        theRenderer.setYTitle("Units");
        theRenderer.setXTitle("Time");
        theRenderer.setChartTitle("Your Blood Pressure (last 30 days)");
        theRenderer.setChartTitleTextSize(30);
    }

    private void generateBPGraph() {
        // clear out data series
        // doing this the lazy way and i get errors otherwise
        // initialize series
        TimeSeries sysSeries = new TimeSeries("Systolic (mmHg)");
        TimeSeries diaSeries = new TimeSeries("Diastolic (mmHg)");
        TimeSeries hrSeries = new TimeSeries("Pulse (bpm)");
        theDataset.addSeries(sysSeries);
        theDataset.addSeries(diaSeries);
        theDataset.addSeries(hrSeries);
        XYSeriesRenderer sysRend = new XYSeriesRenderer();
        XYSeriesRenderer diaRend = new XYSeriesRenderer();
        XYSeriesRenderer hrRend = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(sysRend);
        theRenderer.addSeriesRenderer(diaRend);
        theRenderer.addSeriesRenderer(hrRend);
        sysRend.setPointStyle(PointStyle.CIRCLE);
        sysRend.setFillPoints(true);
        sysRend.setColor(Color.RED);
        sysRend.setLineWidth(3);
        diaRend.setPointStyle(PointStyle.SQUARE);
        diaRend.setFillPoints(true);
        hrRend.setPointStyle(PointStyle.TRIANGLE);
        hrRend.setFillPoints(true);
        hrRend.setColor(Color.GREEN);
        hrRend.setLineWidth(5);
        // populate list with values from DB & add to series
        dataSource.open();
        List<BloodPressureItem> itemList;
        if(this.showAll == true) {
            itemList = dataSource.getAllBloodPressureItems();
        }
        else {
            itemList = dataSource.getLast30DaysBloodPressureItems();
        }
        dataSource.close();


        for (BloodPressureItem i: itemList) {
            Date d = i.getDate();
            sysSeries.add(d, i.getSystolicPressure());
            diaSeries.add(d, i.getDiastolicPressure());
            hrSeries.add(d, i.getHeartrate());
        }
        // re-draw graph
        theChart.repaint();
    }
    private void generateBPData() {
        dataSource.generateBPData();
        this.generateBPGraph();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // delete user & data
        this.generateBPData();
    }


    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // do nothing
    }
}