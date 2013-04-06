package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import cs.ecl.karpAndMamidala.liveforever.Database.ExerciseDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.ExerciseItem;
import cs.ecl.karpAndMamidala.liveforever.R;
import cs.ecl.karpAndMamidala.liveforever.Utilities.MathHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalyzeExerciseActivity extends Activity implements AlertDialogFragment.AlertDialogNotifier {
    private ExerciseDataSource dataSource;
    private boolean showAll = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics_layout);

        dataSource = new ExerciseDataSource(this);

        this.genTable();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.analytics_exer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_genExer:
                AlertDialogFragment frag = new AlertDialogFragment(R.string.action_genExer,
                        R.string.menu_yes,
                        R.string.menu_no);
                frag.show(getFragmentManager(), "AlertDialogFragment");
                return true;
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
        List<ExerciseItem> itemList;
        if (this.showAll == true) {
            itemList = dataSource.getAllExerciseItems();
        }
        else {
            itemList = dataSource.getLast30DaysExerciseItems();
        }
        if(itemList.size() == 0) {
            TableLayout tableView = (TableLayout) findViewById(R.id.analyticsTable);
            tableView.removeAllViews();
            TextView error = new TextView(this);
            error.setText("No data");
            tableView.addView(error);
            return;
        }
        // get cardio & strength items
        List<ExerciseItem> cardioList = dataSource.getCardioItems(itemList);
        List<ExerciseItem> strengthList = dataSource.getStrengthItems(itemList);

        // get needed data
        List<Integer> cardioDuration = this.getDuration(cardioList);
        List<Integer> cardioIntensity = this.getIntensity(cardioList);
        List<Integer> strSets = this.getDuration(strengthList);
        List<Integer> strReps = this.getIntensity(strengthList);
        float maxCD = MathHelper.getMaxInt(cardioDuration);
        float minCD = MathHelper.getMinInt(cardioDuration);
        float avgCD = MathHelper.getAvgInt(cardioDuration);
        float medianCD = MathHelper.getMedianInt(cardioDuration);
        float modeCD = MathHelper.getModeInt(cardioDuration);
        float rangeCD = MathHelper.getRangeInt(cardioDuration);
        float varianceCD = MathHelper.getVarianceInt(cardioDuration);
        double standardDevCD = MathHelper.getStandardDeviationInt(cardioDuration);

        float maxCI = MathHelper.getMaxInt(cardioIntensity);
        float minCI = MathHelper.getMinInt(cardioIntensity);
        float avgCI = MathHelper.getAvgInt(cardioIntensity);
        float medianCI = MathHelper.getMedianInt(cardioIntensity);
        float modeCI = MathHelper.getModeInt(cardioIntensity);
        float rangeCI = MathHelper.getRangeInt(cardioIntensity);
        float varianceCI = MathHelper.getVarianceInt(cardioIntensity);
        double standardDevCI = MathHelper.getStandardDeviationInt(cardioIntensity);

        float maxSS = MathHelper.getMaxInt(strSets);
        float minSS = MathHelper.getMinInt(strSets);
        float avgSS = MathHelper.getAvgInt(strSets);
        float medianSS = MathHelper.getMedianInt(strSets);
        float modeSS = MathHelper.getModeInt(strSets);
        float rangeSS = MathHelper.getRangeInt(strSets);
        float varianceSS = MathHelper.getVarianceInt(strSets);
        double standardDevSS = MathHelper.getStandardDeviationInt(strSets);

        float maxSR = MathHelper.getMaxInt(strReps);
        float minSR = MathHelper.getMinInt(strReps);
        float avgSR = MathHelper.getAvgInt(strReps);
        float medianSR = MathHelper.getMedianInt(strReps);
        float modeSR = MathHelper.getModeInt(strReps);
        float rangeSR = MathHelper.getRangeInt(strReps);
        float varianceSR = MathHelper.getVarianceInt(strReps);
        double standardDevSR = MathHelper.getStandardDeviationInt(strReps);

        // create data rows
        TableLayout tableView = (TableLayout) findViewById(R.id.analyticsTable);
        tableView.removeAllViews();
        // header
        TableRow headerRow = new TableRow(this);
        TextView headerText = new TextView(this);
        if(this.showAll == true) {
            headerText.setText("Exercise Analysis (all time)");

        }
        else {
            headerText.setText("Exercise Pressure Analysis (last 30 days)");
        }
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 3;
        headerText.setLayoutParams(params);
        headerRow.addView(headerText);

        TableRow cardioHeader = new TableRow(this);
        TextView cardioHeadTV = new TextView(this);
        cardioHeadTV.setText("Cardio");
        cardioHeadTV.setLayoutParams(params);
        cardioHeader.addView(cardioHeadTV);


        TableRow listRow = new TableRow(this);
        TextView blankTV = new TextView(this);
        TextView durTV = new TextView(this);
        durTV.setText("Duration");
        TextView distTV = new TextView(this);
        distTV.setText("Distance");
        listRow.addView(blankTV);
        listRow.addView(durTV);
        listRow.addView(distTV);

        // data: 2 columns: [name, value]
        TableRow maxRow = new TableRow(this);
        TextView maxLbl = new TextView(this);
        maxLbl.setText("Max: ");
        TextView maxCDTV = new TextView(this);
        TextView maxCITV = new TextView(this);
        maxCDTV.setText(Float.toString(maxCD));
        maxCITV.setText(Float.toString(maxCI));
        maxRow.addView(maxLbl);
        maxRow.addView(maxCDTV);
        maxRow.addView(maxCITV);

        TableRow minRow = new TableRow(this);
        TextView minLbl = new TextView(this);
        minLbl.setText("Min: ");
        TextView minCDTV = new TextView(this);
        TextView minCITV = new TextView(this);
        minCDTV.setText(Float.toString(minCD));
        minCITV.setText(Float.toString(minCI));
        minRow.addView(minLbl);
        minRow.addView(minCDTV);
        minRow.addView(minCITV);

        TableRow avgRow = new TableRow(this);
        TextView avgLbl = new TextView(this);
        avgLbl.setText("Average: ");
        TextView avgCDTV = new TextView(this);
        TextView avgCITV = new TextView(this);
        avgCDTV.setText(Float.toString(avgCD));
        avgCITV.setText(Float.toString(avgCI));
        avgRow.addView(avgLbl);
        avgRow.addView(avgCDTV);
        avgRow.addView(avgCITV);

        TableRow medianRow = new TableRow(this);
        TextView medianLbl = new TextView(this);
        medianLbl.setText("Median: ");
        TextView medianCDTV = new TextView(this);
        TextView medianCITV = new TextView(this);
        medianCDTV.setText(Float.toString(medianCD));
        medianCITV.setText(Float.toString(medianCI));
        medianRow.addView(medianLbl);
        medianRow.addView(medianCDTV);
        medianRow.addView(medianCITV);

        TableRow modeRow = new TableRow(this);
        TextView modeLbl = new TextView(this);
        modeLbl.setText("Mode: ");
        TextView modeCDTV = new TextView(this);
        TextView modeCITV = new TextView(this);
        modeCDTV.setText(Float.toString(modeCD));
        modeCITV.setText(Float.toString(modeCI));
        modeRow.addView(modeLbl);
        modeRow.addView(modeCDTV);
        modeRow.addView(modeCITV);

        TableRow rangeRow = new TableRow(this);
        TextView rangeLbl = new TextView(this);
        rangeLbl.setText("Range: ");
        TextView rangeCDTV = new TextView(this);
        TextView rangeCITV = new TextView(this);
        rangeCDTV.setText(Float.toString(rangeCD));
        rangeCITV.setText(Float.toString(rangeCI));
        rangeRow.addView(rangeLbl);
        rangeRow.addView(rangeCDTV);
        rangeRow.addView(rangeCITV);

        TableRow varianceRow = new TableRow(this);
        TextView varLbl = new TextView(this);
        varLbl.setText("Variance: ");
        TextView varianceCDTV = new TextView(this);
        TextView varianceCITV = new TextView(this);
        varianceCDTV.setText(Float.toString(varianceCD));
        varianceCITV.setText(Float.toString(varianceCI));
        varianceRow.addView(varLbl);
        varianceRow.addView(varianceCDTV);
        varianceRow.addView(varianceCITV);

        TableRow sdRow = new TableRow(this);
        TextView sdLbl = new TextView(this);
        sdLbl.setText("Standard Deviation: ");
        TextView sdCDTV = new TextView(this);
        TextView sdCITV = new TextView(this);
        sdCDTV.setText(Double.toString(standardDevCD));
        sdCITV.setText(Double.toString(standardDevCI));
        sdRow.addView(sdLbl);
        sdRow.addView(sdCDTV);
        sdRow.addView(sdCITV);

        // add rows to table
        tableView.addView(headerRow);
        tableView.addView(cardioHeader);
        tableView.addView(listRow);
        tableView.addView(maxRow);
        tableView.addView(minRow);
        tableView.addView(avgRow);
        tableView.addView(medianRow);
        tableView.addView(modeRow);
        tableView.addView(rangeRow);
        tableView.addView(varianceRow);
        tableView.addView(sdRow);

        TableRow strHeader = new TableRow(this);
        TextView strHeadTV = new TextView(this);
        strHeadTV.setText("Strength");
        strHeader.setLayoutParams(params);
        strHeader.addView(strHeadTV);


        TableRow listRowStr = new TableRow(this);
        TextView blankTVStr = new TextView(this);
        TextView setTV = new TextView(this);
        setTV.setText("Sets");
        TextView repTV = new TextView(this);
        repTV.setText("Reps");
        listRowStr.addView(blankTVStr);
        listRowStr.addView(setTV);
        listRowStr.addView(repTV);

        // data: 2 columns: [name, value]
        TableRow maxRowStr = new TableRow(this);
        TextView maxLblStr = new TextView(this);
        maxLblStr.setText("Max: ");
        TextView maxSSTV = new TextView(this);
        TextView maxSRTV = new TextView(this);
        maxSSTV.setText(Float.toString(maxSS));
        maxSRTV.setText(Float.toString(maxSR));
        maxRowStr.addView(maxLblStr);
        maxRowStr.addView(maxSSTV);
        maxRowStr.addView(maxSRTV);

        TableRow minRowStr = new TableRow(this);
        TextView minLblStr = new TextView(this);
        minLblStr.setText("Min: ");
        TextView minSSTV = new TextView(this);
        TextView minSRTV = new TextView(this);
        minSSTV.setText(Float.toString(minSS));
        minSRTV.setText(Float.toString(minSR));
        minRowStr.addView(minLblStr);
        minRowStr.addView(minSSTV);
        minRowStr.addView(minSRTV);

        TableRow avgRowStr = new TableRow(this);
        TextView avgLblStr = new TextView(this);
        avgLblStr.setText("Average: ");
        TextView avgSSTV = new TextView(this);
        TextView avgSRTV = new TextView(this);
        avgSSTV.setText(Float.toString(avgSS));
        avgSRTV.setText(Float.toString(avgSR));
        avgRowStr.addView(avgLblStr);
        avgRowStr.addView(avgSSTV);
        avgRowStr.addView(avgSRTV);

        TableRow medianRowStr = new TableRow(this);
        TextView medianLblStr = new TextView(this);
        medianLblStr.setText("Median: ");
        TextView medianSSTV = new TextView(this);
        TextView medianSRTV = new TextView(this);
        medianSSTV.setText(Float.toString(medianSS));
        medianSRTV.setText(Float.toString(medianSR));
        medianRowStr.addView(medianLblStr);
        medianRowStr.addView(medianSSTV);
        medianRowStr.addView(medianSRTV);

        TableRow modeRowStr = new TableRow(this);
        TextView modeLblStr = new TextView(this);
        modeLblStr.setText("Mode: ");
        TextView modeSSTV = new TextView(this);
        TextView modeSRTV = new TextView(this);
        modeSSTV.setText(Float.toString(modeSS));
        modeSRTV.setText(Float.toString(modeSR));
        modeRowStr.addView(modeLblStr);
        modeRowStr.addView(modeSSTV);
        modeRowStr.addView(modeSRTV);

        TableRow rangeRowStr = new TableRow(this);
        TextView rangeLblStr = new TextView(this);
        rangeLblStr.setText("Range: ");
        TextView rangeSSTV = new TextView(this);
        TextView rangeSRTV = new TextView(this);
        rangeSSTV.setText(Float.toString(rangeSS));
        rangeSRTV.setText(Float.toString(rangeSR));
        rangeRowStr.addView(rangeLblStr);
        rangeRowStr.addView(rangeSSTV);
        rangeRowStr.addView(rangeSRTV);

        TableRow varianceRowStr = new TableRow(this);
        TextView varLblStr = new TextView(this);
        varLblStr.setText("Variance: ");
        TextView varianceSSTV = new TextView(this);
        TextView varianceSRTV = new TextView(this);
        varianceSSTV.setText(Float.toString(varianceSS));
        varianceSRTV.setText(Float.toString(varianceSR));
        varianceRowStr.addView(varLblStr);
        varianceRowStr.addView(varianceSSTV);
        varianceRowStr.addView(varianceSRTV);

        TableRow sdRowStr = new TableRow(this);
        TextView sdLblStr = new TextView(this);
        sdLblStr.setText("Standard Deviation: ");
        TextView sdSSTV = new TextView(this);
        TextView sdSRTV = new TextView(this);
        sdSSTV.setText(Double.toString(standardDevSS));
        sdSRTV.setText(Double.toString(standardDevSR));
        sdRowStr.addView(sdLblStr);
        sdRowStr.addView(sdSSTV);
        sdRowStr.addView(sdSRTV);

        // add rows to table
        tableView.addView(strHeader);
        tableView.addView(listRowStr);
        tableView.addView(maxRowStr);
        tableView.addView(minRowStr);
        tableView.addView(avgRowStr);
        tableView.addView(medianRowStr);
        tableView.addView(modeRowStr);
        tableView.addView(rangeRowStr);
        tableView.addView(varianceRowStr);
        tableView.addView(sdRowStr);
    }

    private List<Integer> getDuration(List<ExerciseItem> itemList) {
        List<Integer> rv = new ArrayList<Integer>();
        for (ExerciseItem item: itemList) {
            rv.add(item.getDuration());
        }
        Collections.sort(rv);
        return rv;
    }

    private List<Integer> getIntensity(List<ExerciseItem> itemList) {
        List<Integer> rv = new ArrayList<Integer>();
        for (ExerciseItem item: itemList) {
            rv.add(item.getIntensity());
        }
        Collections.sort(rv);
        return rv;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // generate dummy data
        this.generateExerData();
    }

    private void generateExerData() {
        dataSource.generateExerData();
        this.genTable();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // do nothing
    }
}