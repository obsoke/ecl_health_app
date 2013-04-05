package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import cs.ecl.karpAndMamidala.liveforever.R;
import cs.ecl.karpAndMamidala.liveforever.Models.User;
import cs.ecl.karpAndMamidala.liveforever.Database.UserDataSource;

public class WelcomeActivity extends Activity implements AlertDialogFragment.NoticeDialogListener{
    private UserDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        dataSource = new UserDataSource(this);

        // if a user exists, let's go right to the dashboard activity
        User user = null;
        dataSource.open();
        boolean doesUserExist = dataSource.doesUserExist();
        dataSource.close();

        if(doesUserExist == true) {
            Intent i = new Intent(this, DashboardActivity.class);
            startActivity(i);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_genUser:
                AlertDialogFragment frag = new AlertDialogFragment(R.string.action_genUser,
                        R.string.menu_yes,
                        R.string.menu_no);
                frag.show(getFragmentManager(), "AlertDialogFragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toSignUp(View v) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    private void generateDummyUser() {
        dataSource.generateDummyUser();
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // delete user & data
        this.generateDummyUser();
    }


    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // do nothing
    }
}