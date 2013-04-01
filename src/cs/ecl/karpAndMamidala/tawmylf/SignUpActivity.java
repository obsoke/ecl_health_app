package cs.ecl.karpAndMamidala.tawmylf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 3/17/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignUpActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
    }

    public void signUp(View v) {
        //TODO: get value/validate form
        final EditText et_name = (EditText) findViewById(R.id.et_signup_name);
        final EditText et_address = (EditText) findViewById(R.id.et_signup_address);
        final EditText et_phone = (EditText) findViewById(R.id.et_signup_phone);
        final EditText et_emerg_name = (EditText) findViewById(R.id.et_signup_emerg_name);
        final EditText et_emerg_address = (EditText) findViewById(R.id.et_signup_emerg_address);
        final EditText et_emerg_phone = (EditText) findViewById(R.id.et_signup_emerg_phone);
        final RadioGroup et_gender = (RadioGroup) findViewById(R.id.rg_gender);

        final String name = et_name.getText().toString();
        final String address = et_address.getText().toString();
        final String phone = et_phone.getText().toString();
        final String emerg_name = et_emerg_name.getText().toString();
        final String emerg_address = et_emerg_address.getText().toString();
        final String emerg_phone = et_emerg_phone.getText().toString();
        final int gender = et_gender.getCheckedRadioButtonId();
        if (gender == R.id.rb_male) {
           //TODO: set gender to male
        }
        else if (gender == R.id.rb_female){
            //TODO: set gender to female
        }
        else {
            //TODO: cue error
        }

        if(name.isEmpty() ||
                address.isEmpty() ||
                phone.isEmpty() ||
                emerg_name.isEmpty() ||
                emerg_address.isEmpty() ||
                emerg_phone.isEmpty()) {
            Toast t = Toast.makeText(getBaseContext(), "owned", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        Toast t = Toast.makeText(getBaseContext(), "good job", Toast.LENGTH_SHORT);
        t.show();

        //TODO: if form is valid, create new user
        //TODO: if not, give error message to user
    }
}