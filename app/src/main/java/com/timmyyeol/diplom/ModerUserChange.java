package com.timmyyeol.diplom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModerUserChange extends AppCompatActivity {
    String log = "", history = "", fio = "", birthday = "", address = "", moder ="", status="", abo ="", password ="", fios ="", logins = "";
    String first_login="", first_fio="", loginsend="", fiosend="";
    String num ="";
    String [] fiosmas;
    String [] loginsmas;
    int keyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moder_user_change);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        EditText edtlogin = (EditText) findViewById(R.id.edtlogin);
        EditText edtfio = (EditText) findViewById(R.id.edtfio);
        EditText edtdate = (EditText) findViewById(R.id.edtdate);
        EditText edtaddress = (EditText) findViewById(R.id.edtaddress);
        EditText edtpassword = (EditText) findViewById(R.id.edtpassword);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ToggleButton tgb = (ToggleButton) findViewById(R.id.toggleButton);
        Bundle extras = getIntent().getExtras();
        keyt = 0;
        if (extras != null) {
            log = extras.getString("login");
            fio = extras.getString("fio");
            birthday = extras.getString("birthday");
            address = extras.getString("address");
            abo = extras.getString("abo");
            moder = extras.getString("moder");
            password = extras.getString("password");
            fios = extras.getString("fios");
            logins = extras.getString("logins");
            history = extras.getString("history");
            status = extras.getString("status");
        }
            edtlogin.setText(log);
            edtaddress.setText(address);
            edtdate.setText(birthday);
            edtpassword.setText(password);
            edtfio.setText(fio);
            if (abo.equals("Выберите группу крови")) {
                spinner.setSelection(0);
            }
            if (abo.equals("O+(I)")) {
                spinner.setSelection(1);
            }
            if (abo.equals("O-(I)")) {
                spinner.setSelection(2);
            }
            if (abo.equals("A+(II)")) {
                spinner.setSelection(3);
            }
            if (abo.equals("A-(II)")) {
                spinner.setSelection(4);
            }
            if (abo.equals("B+(III)")) {
                spinner.setSelection(5);
            }
            if (abo.equals("B-(III)")) {
                spinner.setSelection(6);
            }
            if (abo.equals("AB+(IV)")) {
                spinner.setSelection(7);
            }
            if (abo.equals("AB-(IV)")) {
                spinner.setSelection(8);
            }

            if (moder.equals("yes")) {
                tgb.setChecked(true);
            }

            first_fio = fio;
            first_login = log;

         fiosmas = fios.split(",");
         loginsmas = logins.split(",");
        edtpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                final EditText login = (EditText) findViewById(R.id.editText2);
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE &&login.length()!=0) {
                    addClick(v);

                    return handled;
                }
                return handled;
            }
        });

if(edtaddress.getText().toString().equals("")|| edtdate.getText().toString().equals("")|| edtpassword.getText().toString().equals("")|| edtfio.getText().toString().equals(""))
{
        Toast toast = Toast.makeText(getApplicationContext(),
                "Выполяется обработка данных. Подождите.", Toast.LENGTH_SHORT);
        toast.show();
    onBackPressed();
}
    }
        public void addClick(View view) {
        EditText fioe = (EditText) findViewById(R.id.edtfio);
        EditText date = (EditText) findViewById(R.id.edtdate);
        EditText address = (EditText) findViewById(R.id.edtaddress);
        EditText login = (EditText) findViewById(R.id.edtlogin);
        EditText password = (EditText) findViewById(R.id.edtpassword);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ToggleButton tgb = (ToggleButton) findViewById(R.id.toggleButton);

        if (!(date.getText().toString().contains("."))||fioe.getText().toString().contains(",")||fio.length() == 0 || login.length() !=11 || password.length() <6 || date.length() != 10 || address.length() == 0||!(address.getText().toString().contains(","))) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.error_exc, Toast.LENGTH_SHORT);
            toast.show(); }
        else
            { FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference("users/" + login.getText().toString());
                myRef.setValue(login.getText().toString());

                DatabaseReference myRef2 = database.getReference("users/" + login.getText().toString() + "/fio");
                myRef2.setValue(fioe.getText().toString());

                DatabaseReference setDate = database.getReference("users/" + login.getText().toString() + "/birthday");
                setDate.setValue(date.getText().toString());

                DatabaseReference setAddress = database.getReference("users/" + login.getText().toString() + "/address");
                setAddress.setValue(address.getText().toString());

                DatabaseReference setPassword = database.getReference("users/" + login.getText().toString() + "/password");
                setPassword.setValue(password.getText().toString());

                DatabaseReference setHistory = database.getReference("users/" + login.getText().toString() + "/history");
                setHistory.setValue(history);

                DatabaseReference setStatus = database.getReference("users/" + login.getText().toString() + "/status");
                setStatus.setValue(status);

                DatabaseReference setAbo = database.getReference("users/" + login.getText().toString() + "/abo");
                setAbo.setValue(spinner.getSelectedItem().toString());

                DatabaseReference setModer = database.getReference("users/" + login.getText().toString() + "/moder");
                setModer.setValue(tgb.getText());

                for (int i = 0; i < fiosmas.length; i++) {
                    if (loginsmas[i].equals(log)) {
                        fiosmas[i] = fioe.getText().toString();
                        loginsmas[i] = login.getText().toString();
                    }
                }
                if (!(log.equals(login.getText().toString()))) {
                    DatabaseReference myRef3 = database.getReference("users/" + log);
                    myRef3.removeValue();
                }
                String fiosn = "";
                String loginsn = "";
                for (int i = 0; i < fiosmas.length; i++) {
                    if (i < fiosmas.length - 1) {
                        fiosn += fiosmas[i] + ",";
                        loginsn += loginsmas[i] + ",";
                    } else {
                        fiosn += fiosmas[i];
                        loginsn += loginsmas[i];
                    }
                }
                DatabaseReference setFios = database.getReference("search/fios");
                setFios.setValue(fiosn);
                DatabaseReference setLogins = database.getReference("search/logins");
                setLogins.setValue(loginsn);
                keyt = 1;
                Intent i = new Intent(getApplicationContext(), Moder_main.class);
                i.putExtra("fiosn", fiosn);
                i.putExtra("loginsn", loginsn);
                i.putExtra("keyt", keyt);
                startActivity(i);
                onBackPressed();
        }
    }
    @Override
    public void onBackPressed() { finish(); }
}