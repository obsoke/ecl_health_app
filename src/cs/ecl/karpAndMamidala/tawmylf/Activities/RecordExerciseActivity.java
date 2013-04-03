package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs.ecl.karpAndMamidala.tawmylf.Database.ExerciseDataSource;
import cs.ecl.karpAndMamidala.tawmylf.R;

import java.util.Date;

public class RecordExerciseActivity extends Activity {
    private ExerciseDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_exercise_layout);

        dataSource = new ExerciseDataSource(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

    public void onWeightSubmit(View view) {
        EditText typeTV = (EditText)findViewById(R.id.exercise_type);
        EditText durationTV = (EditText)findViewById(R.id.exercise_duration);
        EditText intensityTV = (EditText)findViewById(R.id.exercise_intensity);

        String typeStr = typeTV.getText().toString();
        String durationStr = durationTV.getText().toString();
        String intensityStr = intensityTV.getText().toString();
        if(typeStr.isEmpty() ||
                durationStr.isEmpty() ||
                intensityStr.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.error_form_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        int duration = Integer.parseInt(durationStr);
        int intensity = Integer.parseInt(intensityStr);

        dataSource.open();
        Date d = new Date();
        dataSource.createExerciseItem(d.getTime(), typeStr, duration, intensity);
        dataSource.close();

        // show success toast
        Toast.makeText(getBaseContext(), R.string.exercise_success, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, DashboardActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

}
