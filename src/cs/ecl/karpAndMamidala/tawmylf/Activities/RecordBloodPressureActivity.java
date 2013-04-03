package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs.ecl.karpAndMamidala.tawmylf.Database.BloodPressureDataSource;
import cs.ecl.karpAndMamidala.tawmylf.R;

import java.util.Date;

public class RecordBloodPressureActivity extends Activity {
    private BloodPressureDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_bloodpressure_layout);

        dataSource = new BloodPressureDataSource(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

    public void onBPSubmit(View view) {
        EditText sysTV = (EditText)findViewById(R.id.systolic);
        EditText diaTV = (EditText)findViewById(R.id.diastolic);
        EditText hrTV = (EditText)findViewById(R.id.pulse_rate);

        String systolic = sysTV.getText().toString();
        String diastolic = diaTV.getText().toString();
        String heartrate = hrTV.getText().toString();

        if(systolic.isEmpty() ||
           diastolic.isEmpty() ||
           heartrate.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.error_form_fields, Toast.LENGTH_SHORT).show();
            return;
        }
        float sys = Float.parseFloat(systolic);
        float dia = Float.parseFloat(diastolic);
        float hr = Float.parseFloat(heartrate);

        dataSource.open();
        //TODO: create new entry
        Date d = new Date();
        dataSource.createBloodPressureItem(d.getTime(), sys, dia, hr);
        dataSource.close();

        // show success toast
        Toast.makeText(getBaseContext(), R.string.bp_success, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, DashboardActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

}
