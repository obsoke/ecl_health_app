package cs.ecl.karpAndMamidala.tawmylf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
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
        // get value/validate form
        TextView tv_error = (TextView) findViewById(R.id.tv_error);
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
        boolean isFemale = false;

        if (gender == R.id.rb_male) {
            isFemale = false;
        }
        else if (gender == R.id.rb_female){
            isFemale = true;
        }
        else {
            tv_error.setText(R.string.error_gender);
            return;
        }

        if(name.isEmpty() ||
                address.isEmpty() ||
                phone.isEmpty() ||
                emerg_name.isEmpty() ||
                emerg_address.isEmpty() ||
                emerg_phone.isEmpty()) {
            tv_error.setText(R.string.error_entire_form);
            return;
        }

        //TODO: if form is valid, create new user
        tv_error.setText(R.string.success_creating_user);
    }
}