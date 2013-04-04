package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import cs.ecl.karpAndMamidala.tawmylf.R;

public class DashboardActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
    }

    public void onClickRecordBP(View view) {
        Intent i = new Intent(this, RecordBloodPressureActivity.class);
        startActivity(i);
    }

    public void onClickGraphBP(View view) {
        Intent i = new Intent(this, GraphBPActivity.class);
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