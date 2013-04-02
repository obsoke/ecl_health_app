package cs.ecl.karpAndMamidala.tawmylf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
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

    public void toSignUp(View v) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}