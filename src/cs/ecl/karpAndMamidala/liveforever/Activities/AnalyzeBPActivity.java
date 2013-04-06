package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import cs.ecl.karpAndMamidala.liveforever.Database.BloodPressureDataSource;
import cs.ecl.karpAndMamidala.liveforever.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.BloodPressureItem;
import cs.ecl.karpAndMamidala.liveforever.Models.WeightItem;
import cs.ecl.karpAndMamidala.liveforever.R;
import cs.ecl.karpAndMamidala.liveforever.Utilities.MathHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalyzeBPActivity extends Activity {
    private BloodPressureDataSource dataSource;
    private boolean showAll = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics_layout);

        dataSource = new BloodPressureDataSource(this);

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
        List<BloodPressureItem> itemList;
        if (this.showAll == true) {
            itemList = dataSource.getAllBloodPressureItems();
        }
        else {
            itemList = dataSource.getLast30DaysBloodPressureItems();
        }

        // get needed data
        List<Float> sysList = this.getSystolic(itemList);
        List<Float> diaList = this.getDiastolic(itemList);
        List<Float> pulseList = this.getPulse(itemList);
        float maxSys = MathHelper.getMax(sysList);
        float minSys = MathHelper.getMin(sysList);
        float avgSys = MathHelper.getAvg(sysList);
        float medianSys = MathHelper.getMedian(sysList);
        float modeSys = MathHelper.getMode(sysList);
        float rangeSys = MathHelper.getRange(sysList);
        float varianceSys = MathHelper.getVariance(sysList);
        double standardDevSys = MathHelper.getStandardDeviation(sysList);

        float maxDia = MathHelper.getMax(diaList);
        float minDia = MathHelper.getMin(diaList);
        float avgDia = MathHelper.getAvg(diaList);
        float medianDia = MathHelper.getMedian(diaList);
        float modeDia = MathHelper.getMode(diaList);
        float rangeDia = MathHelper.getRange(diaList);
        float varianceDia = MathHelper.getVariance(diaList);
        double standardDevDia = MathHelper.getStandardDeviation(diaList);

        float maxPulse = MathHelper.getMax(pulseList);
        float minPulse = MathHelper.getMin(pulseList);
        float avgPulse = MathHelper.getAvg(pulseList);
        float medianPulse = MathHelper.getMedian(pulseList);
        float modePulse = MathHelper.getMode(pulseList);
        float rangePulse = MathHelper.getRange(pulseList);
        float variancePulse = MathHelper.getVariance(pulseList);
        double standardDevPulse = MathHelper.getStandardDeviation(pulseList);
        // create data rows
        TableLayout tableView = (TableLayout) findViewById(R.id.analyticsTable);
        tableView.removeAllViews();
        // header
        TableRow headerRow = new TableRow(this);
        TextView headerText = new TextView(this);
        if(this.showAll == true) {
            headerText.setText("Blood Pressure Analysis (all time)");

        }
        else {
            headerText.setText("Blood Pressure Analysis (last 30 days)");
        }
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 4;
        headerText.setLayoutParams(params);
        headerRow.addView(headerText);
        TableRow listRow = new TableRow(this);
        TextView blankTV = new TextView(this);
        TextView sysTV = new TextView(this);
        sysTV.setText("Systolic");
        TextView diaTV = new TextView(this);
        diaTV.setText("Diastolic");
        TextView hrTV = new TextView(this);
        hrTV.setText("Pulse");
        listRow.addView(blankTV);
        listRow.addView(sysTV);
        listRow.addView(diaTV);
        listRow.addView(hrTV);

        // data: 2 columns: [name, value]
        TableRow maxRow = new TableRow(this);
        TextView maxLbl = new TextView(this);
        maxLbl.setText("Max: ");
        TextView maxSysTV = new TextView(this);
        TextView maxDiaTV = new TextView(this);
        TextView maxHrTV = new TextView(this);
        maxSysTV.setText(Float.toString(maxSys));
        maxDiaTV.setText(Float.toString(maxDia));
        maxHrTV.setText(Float.toString(maxPulse));
        maxRow.addView(maxLbl);
        maxRow.addView(maxSysTV);
        maxRow.addView(maxDiaTV);
        maxRow.addView(maxHrTV);

        TableRow minRow = new TableRow(this);
        TextView minLbl = new TextView(this);
        minLbl.setText("Min: ");
        TextView minSysTV = new TextView(this);
        TextView minDiaTV = new TextView(this);
        TextView minHrTV = new TextView(this);
        minSysTV.setText(Float.toString(minSys));
        minDiaTV.setText(Float.toString(minDia));
        minHrTV.setText(Float.toString(minPulse));
        minRow.addView(minLbl);
        minRow.addView(minSysTV);
        minRow.addView(minDiaTV);
        minRow.addView(minHrTV);

        TableRow avgRow = new TableRow(this);
        TextView avgLbl = new TextView(this);
        avgLbl.setText("Average: ");
        TextView avgSysTV = new TextView(this);
        TextView avgDiaTV = new TextView(this);
        TextView avgHrTV = new TextView(this);
        avgSysTV.setText(Float.toString(avgSys));
        avgDiaTV.setText(Float.toString(avgDia));
        avgHrTV.setText(Float.toString(avgPulse));
        avgRow.addView(avgLbl);
        avgRow.addView(avgSysTV);
        avgRow.addView(avgDiaTV);
        avgRow.addView(avgHrTV);

        TableRow medianRow = new TableRow(this);
        TextView medianLbl = new TextView(this);
        medianLbl.setText("Median: ");
        TextView medianSysTV = new TextView(this);
        TextView medianDiaTV = new TextView(this);
        TextView medianHrTV = new TextView(this);
        medianSysTV.setText(Float.toString(medianSys));
        medianDiaTV.setText(Float.toString(medianDia));
        medianHrTV.setText(Float.toString(medianPulse));
        medianRow.addView(medianLbl);
        medianRow.addView(medianSysTV);
        medianRow.addView(medianDiaTV);
        medianRow.addView(medianHrTV);

        TableRow modeRow = new TableRow(this);
        TextView modeLbl = new TextView(this);
        modeLbl.setText("Mode: ");
        TextView modeSysTV = new TextView(this);
        TextView modeDiaTV = new TextView(this);
        TextView modeHrTV = new TextView(this);
        modeSysTV.setText(Float.toString(modeSys));
        modeDiaTV.setText(Float.toString(modeDia));
        modeHrTV.setText(Float.toString(modePulse));
        modeRow.addView(modeLbl);
        modeRow.addView(modeSysTV);
        modeRow.addView(modeDiaTV);
        modeRow.addView(modeHrTV);

        TableRow rangeRow = new TableRow(this);
        TextView rangeLbl = new TextView(this);
        rangeLbl.setText("Range: ");
        TextView rangeSysTV = new TextView(this);
        TextView rangeDiaTV = new TextView(this);
        TextView rangeHrTV = new TextView(this);
        rangeSysTV.setText(Float.toString(rangeSys));
        rangeDiaTV.setText(Float.toString(rangeDia));
        rangeHrTV.setText(Float.toString(rangePulse));
        rangeRow.addView(rangeLbl);
        rangeRow.addView(rangeSysTV);
        rangeRow.addView(rangeDiaTV);
        rangeRow.addView(rangeHrTV);

        TableRow varianceRow = new TableRow(this);
        TextView varLbl = new TextView(this);
        varLbl.setText("Variance: ");
        TextView varianceSysTV = new TextView(this);
        TextView varianceDiaTV = new TextView(this);
        TextView varianceHrTV = new TextView(this);
        varianceSysTV.setText(Float.toString(varianceSys));
        varianceDiaTV.setText(Float.toString(varianceDia));
        varianceHrTV.setText(Float.toString(variancePulse));
        varianceRow.addView(varLbl);
        varianceRow.addView(varianceSysTV);
        varianceRow.addView(varianceDiaTV);
        varianceRow.addView(varianceHrTV);

        TableRow sdRow = new TableRow(this);
        TextView sdLbl = new TextView(this);
        sdLbl.setText("Standard Deviation: ");
        TextView sdSysTV = new TextView(this);
        TextView sdDiaTV = new TextView(this);
        TextView sdHrTV = new TextView(this);
        sdSysTV.setText(Double.toString(standardDevSys));
        sdDiaTV.setText(Double.toString(standardDevDia));
        sdHrTV.setText(Double.toString(standardDevPulse));
        sdRow.addView(sdLbl);
        sdRow.addView(sdSysTV);
        sdRow.addView(sdDiaTV);
        sdRow.addView(sdHrTV);

        // add rows to table
        tableView.addView(headerRow);
        tableView.addView(listRow);
        tableView.addView(maxRow);
        tableView.addView(minRow);
        tableView.addView(avgRow);
        tableView.addView(medianRow);
        tableView.addView(modeRow);
        tableView.addView(rangeRow);
        tableView.addView(varianceRow);
        tableView.addView(sdRow);
    }

    private List<Float> getSystolic(List<BloodPressureItem> wi) {
        List<Float> rv = new ArrayList<Float>();
        for (BloodPressureItem item: wi) {
            rv.add(item.getSystolicPressure());
        }
        Collections.sort(rv);
        return rv;
    }

    private List<Float> getDiastolic(List<BloodPressureItem> wi) {
        List<Float> rv = new ArrayList<Float>();
        for (BloodPressureItem item: wi) {
            rv.add(item.getDiastolicPressure());
        }
        Collections.sort(rv);
        return rv;
    }

    private List<Float> getPulse(List<BloodPressureItem> wi) {
        List<Float> rv = new ArrayList<Float>();
        for (BloodPressureItem item: wi) {
            rv.add(item.getHeartrate());
        }
        Collections.sort(rv);
        return rv;
    }
}