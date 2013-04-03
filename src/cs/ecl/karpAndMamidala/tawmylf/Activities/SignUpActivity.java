package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import cs.ecl.karpAndMamidala.tawmylf.R;
import cs.ecl.karpAndMamidala.tawmylf.Models.User;
import cs.ecl.karpAndMamidala.tawmylf.Database.UserDataSource;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 3/17/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignUpActivity extends Activity {
    private UserDataSource dataSource;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        dataSource = new UserDataSource(this);
    }

    public void signUp(View v) {
        // get value/validate form
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
        final int gen = et_gender.getCheckedRadioButtonId();
        int gender;

        if (gen == R.id.rb_male) {
            gender = 0;
        }
        else if (gen == R.id.rb_female){
            gender = 1;
        }
        else {
            Toast.makeText(getBaseContext(), R.string.error_gender, Toast.LENGTH_SHORT).show();
            return;
        }

        if(name.isEmpty() ||
                address.isEmpty() ||
                phone.isEmpty() ||
                emerg_name.isEmpty() ||
                emerg_address.isEmpty() ||
                emerg_phone.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.error_entire_form, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = null;
        dataSource.open();
        user = dataSource.createUser(name, gender, address, phone, emerg_name, emerg_address, emerg_phone);
        dataSource.close();

        Intent i = new Intent(this, DashboardActivity.class);
        //TODO: pass User object to DashboardActivity
        startActivity(i);
    }
}