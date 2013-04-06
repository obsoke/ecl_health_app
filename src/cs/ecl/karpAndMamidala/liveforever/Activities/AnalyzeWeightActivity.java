package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import cs.ecl.karpAndMamidala.liveforever.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.WeightItem;
import cs.ecl.karpAndMamidala.liveforever.R;
import cs.ecl.karpAndMamidala.liveforever.Utilities.MathHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalyzeWeightActivity extends Activity {
    private WeightDataSource dataSource;
    private boolean showAll = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics_layout);

        dataSource = new WeightDataSource(this);

        this.genTable();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.analytics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_toggleData:
                if(this.showAll == true) {
                    this.showAll = false;
                    this.genTable();
                    item.setTitle(R.string.action_showAll);
                }
                else {
                    this.showAll = true;
                    this.genTable();
                    item.setTitle(R.string.action_show30Days);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void genTable() {
        // get weight items
        List<WeightItem> itemList;
        if (this.showAll == true) {
            itemList = dataSource.getAllWeightItems();
        }
        else {
            itemList = dataSource.getLast30DaysWeightItems();
        }
        if(itemList.size() == 0) {
            TableLayout tableView = (TableLayout) findViewById(R.id.analyticsTable);
            tableView.removeAllViews();
            TextView error = new TextView(this);
            error.setText("No data");
            tableView.addView(error);
            return;
        }
        // get needed data
        List<Float> weightList = this.getWeight(itemList);
        float max = MathHelper.getMax(weightList);
        float min = MathHelper.getMin(weightList);
        float avg = MathHelper.getAvg(weightList);
        float median = MathHelper.getMedian(weightList);
        float mode = MathHelper.getMode(weightList);
        float range = MathHelper.getRange(weightList);
        float variance = MathHelper.getVariance(weightList);
        double standardDev = MathHelper.getStandardDeviation(weightList);

        // create data rows
        TableLayout tableView = (TableLayout) findViewById(R.id.analyticsTable);
        tableView.removeAllViews();
        // header
        TableRow headerRow = new TableRow(this);
        TextView headerText = new TextView(this);
        if(this.showAll == true) {
            headerText.setText("Weight Analysis (all time)");

        }
        else {
            headerText.setText("Weight Analysis (last 30 days)");
        }
        headerRow.addView(headerText);
        // data: 2 columns: [name, value]
        TableRow maxRow = new TableRow(this);
        TextView maxLbl = new TextView(this);
        maxLbl.setText("Max: ");
        TextView maxVl = new TextView(this);
        maxVl.setText(Float.toString(max));
        maxRow.addView(maxLbl);
        maxRow.addView(maxVl);

        TableRow minRow = new TableRow(this);
        TextView minLbl = new TextView(this);
        minLbl.setText("Min: ");
        TextView minVl = new TextView(this);
        minVl.setText(Float.toString(min));
        minRow.addView(minLbl);
        minRow.addView(minVl);

        TableRow avgRow = new TableRow(this);
        TextView avgLbl = new TextView(this);
        avgLbl.setText("Average: ");
        TextView avgVl = new TextView(this);
        avgVl.setText(Float.toString(avg));
        avgRow.addView(avgLbl);
        avgRow.addView(avgVl);

        TableRow medianRow = new TableRow(this);
        TextView medianLbl = new TextView(this);
        medianLbl.setText("Median: ");
        TextView medianVl = new TextView(this);
        medianVl.setText(Float.toString(median));
        medianRow.addView(medianLbl);
        medianRow.addView(medianVl);

        TableRow modeRow = new TableRow(this);
        TextView modeLbl = new TextView(this);
        modeLbl.setText("Mode: ");
        TextView modeVl = new TextView(this);
        modeVl.setText(Float.toString(mode));
        modeRow.addView(modeLbl);
        modeRow.addView(modeVl);

        TableRow rangeRow = new TableRow(this);
        TextView rangeLbl = new TextView(this);
        rangeLbl.setText("Range: ");
        TextView rangeVl = new TextView(this);
        rangeVl.setText(Float.toString(range));
        rangeRow.addView(rangeLbl);
        rangeRow.addView(rangeVl);

        TableRow varianceRow = new TableRow(this);
        TextView varLbl = new TextView(this);
        varLbl.setText("Variance: ");
        TextView varVl = new TextView(this);
        varVl.setText(Float.toString(variance));
        varianceRow.addView(varLbl);
        varianceRow.addView(varVl);

        TableRow sdRow = new TableRow(this);
        TextView sdLbl = new TextView(this);
        sdLbl.setText("Standard Deviation: ");
        TextView sdVl = new TextView(this);
        sdVl.setText(Double.toString(standardDev));
        sdRow.addView(sdLbl);
        sdRow.addView(sdVl);

        // add rows to table
        tableView.addView(headerRow);
        tableView.addView(maxRow);
        tableView.addView(minRow);
        tableView.addView(avgRow);
        tableView.addView(medianRow);
        tableView.addView(modeRow);
        tableView.addView(rangeRow);
        tableView.addView(varianceRow);
        tableView.addView(sdRow);

    }



    private List<Float> getWeight(List<WeightItem> wi) {
        List<Float> rv = new ArrayList<Float>();
        for (WeightItem item: wi) {
            rv.add(item.getWeight());
        }
        Collections.sort(rv);
        return rv;
    }
}