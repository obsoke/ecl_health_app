package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import cs.ecl.karpAndMamidala.liveforever.Database.UserDataSource;
import cs.ecl.karpAndMamidala.liveforever.Models.User;
import cs.ecl.karpAndMamidala.liveforever.R;

public class DashboardActivity extends Activity implements AlertDialogFragment.NoticeDialogListener {
    private UserDataSource dataSource;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        dataSource = new UserDataSource(this);

        TextView bio = (TextView) findViewById(R.id.tv_Bio);
        TextView emergBio = (TextView) findViewById(R.id.tv_EmergBio);
        Intent i = getIntent();
        User user = (User) i.getParcelableExtra("User");
        if (user == null) {
            user = dataSource.getUser();
        }
        String str = user.getName() + "\n";
        str += user.getAddress() + "\n";
        str += user.getPhone();
        bio.setText(str);

        str = user.getEmergName() + "\n";
        str += user.getEmergAddress() + "\n";
        str += user.getEmergPhone();
        emergBio.setText(str);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reset:
                AlertDialogFragment frag = new AlertDialogFragment(R.string.action_deleteDialogMsg,
                        R.string.action_deleteYes,
                        R.string.action_deleteNo);
                frag.show(getFragmentManager(), "AlertDialogFragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    public void onClickAnalyzeWeight(View view) {
        Intent i = new Intent(this, AnalyzeWeightActivity.class);
        startActivity(i);
    }

    public void onClickRecordExercise(View view) {
        Intent i = new Intent(this, RecordExerciseActivity.class);
        startActivity(i);
    }

    private void deleteUser() {
        dataSource.deleteUser();
        Intent i = new Intent(this, WelcomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // delete user & data
        deleteUser();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // do nothing
    }
}