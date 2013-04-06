package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import cs.ecl.karpAndMamidala.liveforever.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.WeightItem;
import cs.ecl.karpAndMamidala.liveforever.R;

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
        // create data rows
        // add rows to table
    }
}