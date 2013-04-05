package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cs.ecl.karpAndMamidala.tawmylf.Database.BloodPressureDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.tawmylf.Models.BloodPressureItem;
import cs.ecl.karpAndMamidala.tawmylf.Models.WeightItem;
import cs.ecl.karpAndMamidala.tawmylf.R;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Date;
import java.util.List;

public class GraphBPActivity extends Activity implements AlertDialogFragment.NoticeDialogListener{
    private GraphicalView theChart;
    private BloodPressureDataSource dataSource;
    private XYMultipleSeriesDataset theDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer theRenderer = new XYMultipleSeriesRenderer();

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
        // TODO: renderer reset
        theRenderer.setShowCustomTextGrid(true);
    }

    private void generateBPGraph() {
        // initialize series
        TimeSeries sysSeries = new TimeSeries("Systolic Pressure");
        TimeSeries diaSeries = new TimeSeries("Diastolic Pressure");
        TimeSeries hrSeries = new TimeSeries("Heart Rate");
        theDataset.addSeries(sysSeries);
        theDataset.addSeries(diaSeries);
        theDataset.addSeries(hrSeries);
        XYSeriesRenderer sysRend = new XYSeriesRenderer();
        XYSeriesRenderer diaRend = new XYSeriesRenderer();
        XYSeriesRenderer hrRend = new XYSeriesRenderer();
        theRenderer.addSeriesRenderer(sysRend);
        theRenderer.addSeriesRenderer(diaRend);
        theRenderer.addSeriesRenderer(hrRend);
        // TODO: renderer properties
        sysRend.setPointStyle(PointStyle.CIRCLE);
        sysRend.setFillPoints(true);
        sysRend.setColor(Color.RED);
        diaRend.setPointStyle(PointStyle.SQUARE);
        diaRend.setFillPoints(true);
        hrRend.setPointStyle(PointStyle.TRIANGLE);
        hrRend.setFillPoints(true);
        hrRend.setColor(Color.GREEN);
        // populate list with values from DB & add to series
        dataSource.open();
        List<BloodPressureItem> itemList = dataSource.getAllBloodPressureItems();
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
        // clear out data series
        int isr = theRenderer.getSeriesRendererCount();

        // WARNING: may cause random crash
        // possible bug with achartengine
        for (int i = 0; i < isr; i++) {
            theRenderer.removeSeriesRenderer(theRenderer.getSeriesRendererAt(i));
        }
        int isd = theDataset.getSeriesCount();
        for (int i = 0; i < isd; i++) {
            theDataset.removeSeries(i);
        }
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