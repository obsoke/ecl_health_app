package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import cs.ecl.karpAndMamidala.tawmylf.R;

public class RecordWeightActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_weight_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}
