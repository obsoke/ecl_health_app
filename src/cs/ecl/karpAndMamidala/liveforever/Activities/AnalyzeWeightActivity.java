package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import cs.ecl.karpAndMamidala.liveforever.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.WeightItem;
import cs.ecl.karpAndMamidala.liveforever.R;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnalyzeWeightActivity extends Activity {
    private WeightDataSource dataSource;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics_layout);

        dataSource = new WeightDataSource(this);

        TableLayout tableView = (TableLayout) findViewById(R.id.analyticsTable);
        // get all weight items
        List<WeightItem> itemList = dataSource.getAllWeightItems();
        List<Float> weightList = this.getWeight(itemList);
        // create data rows
        // add rows to table
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