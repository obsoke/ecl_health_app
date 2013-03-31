package cs.ecl.karpAndMamidala.tawmylf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
    }

    public void toSignUp(View v) {
        //TODO: send user to sign up activity
    }
}
