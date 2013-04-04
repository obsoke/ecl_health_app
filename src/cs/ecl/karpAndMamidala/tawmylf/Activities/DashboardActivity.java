package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import cs.ecl.karpAndMamidala.tawmylf.R;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 3/17/13
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class DashboardActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickRecordBP(View view) {
        Intent i = new Intent(this, RecordBloodPressureActivity.class);
        startActivity(i);
    }

    public void onClickRecordWeight(View view) {
        Intent i = new Intent(this, RecordWeightActivity.class);
        startActivity(i);
    }

    public void onClickGraphWeight(View view) {
        Intent i = new Intent(this, GraphWeightActivity.class);
        startActivity(i);
    }

    public void onClickRecordExercise(View view) {
        Intent i = new Intent(this, RecordExerciseActivity.class);
        startActivity(i);
    }

    public void onClickGraphExercise(View view) {
        Intent i = new Intent(this, GraphExerciseActivity.class);
        startActivity(i);
    }
}