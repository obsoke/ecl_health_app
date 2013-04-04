package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs.ecl.karpAndMamidala.tawmylf.Database.WeightDataSource;
import cs.ecl.karpAndMamidala.tawmylf.R;

import java.util.Date;

public class RecordWeightActivity extends Activity {
    private WeightDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_weight_layout);

        dataSource = new WeightDataSource(this);
	}

    public void onWeightSubmit(View view) {
        EditText weightTV = (EditText)findViewById(R.id.weight_info);

        String weightStr = weightTV.getText().toString();
        if(weightStr.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.error_form_fields, Toast.LENGTH_SHORT).show();
            return;
        }
        float weight = Float.parseFloat(weightStr);

        dataSource.open();
        //TODO: create new entry
        Date d = new Date();
        dataSource.createWeightItem(d.getTime(), weight);
        dataSource.close();

        // show success toast
        Toast.makeText(getBaseContext(), R.string.weight_success, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, DashboardActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }
}
