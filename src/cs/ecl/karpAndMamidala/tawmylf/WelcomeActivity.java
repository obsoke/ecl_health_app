package cs.ecl.karpAndMamidala.tawmylf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
    }

    public void toSignUp(View v) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}
