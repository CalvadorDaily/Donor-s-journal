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


public class AddActivity extends AppCompatActivity {

    ToggleButton tgb;
    String fios, fios2;
    String logins, logins2;
    String loginsn, fiosn;
    int keyt2, get;
    String [] loginsmas;
    private static final String TAG = "MyApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        keyt2 = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fios = extras.getString("fios");
            logins = extras.getString("logins");
        }
        loginsmas = logins.split(",");
        EditText password = (EditText) findViewById(R.id.edtpassword);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
    }
    public void addClick(View view) {
        EditText fio = (EditText) findViewById(R.id.edtfio);
        EditText date = (EditText) findViewById(R.id.edtdate);
        EditText address = (EditText) findViewById(R.id.edtaddress);
        EditText login = (EditText) findViewById(R.id.edtlogin);
        EditText password = (EditText) findViewById(R.id.edtpassword);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        tgb = (ToggleButton) findViewById(R.id.toggleButton);
                if ( !(date.getText().toString().contains("."))||fio.getText().toString().contains(",")||fio.length() == 0 || login.length() != 11 || password.length() < 6 ||!(address.getText().toString().contains(","))|| date.length() != 10 || address.length() == 0) {

                        Toast toast = Toast.makeText(getApplicationContext(),
                                R.string.error_exc, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                    get=0;
                    for (int i = 0; i < loginsmas.length; i++) {
                        if (!(loginsmas[i].equals(login.getText().toString()))) {
                            if (i ==(loginsmas.length-1))
                            { } }
                        else { get =1;  }}
                    if (get==1) {
                        keyt2 = 1;
                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        DatabaseReference myRef = database.getReference("users/" + login.getText().toString());
                        myRef.setValue(login.getText().toString());

                        DatabaseReference myRef2 = database.getReference("users/" + login.getText().toString() + "/fio");
                        myRef2.setValue(fio.getText().toString());

                        DatabaseReference setDate = database.getReference("users/" + login.getText().toString() + "/birthday");
                        setDate.setValue(date.getText().toString());

                        DatabaseReference setAddress = database.getReference("users/" + login.getText().toString() + "/address");
                        setAddress.setValue(address.getText().toString());

                        DatabaseReference setPassword = database.getReference("users/" + login.getText().toString() + "/password");
                        setPassword.setValue(password.getText().toString());

                        DatabaseReference setHistory = database.getReference("users/" + login.getText().toString() + "/history");
                        setHistory.setValue("ДД.ММ.ГГГГ");

                        DatabaseReference setStatus = database.getReference("users/" + login.getText().toString() + "/status");
                        setStatus.setValue("Свободен.");

                        DatabaseReference setAbo = database.getReference("users/" + login.getText().toString() + "/abo");
                        setAbo.setValue(spinner.getSelectedItem().toString());

                        DatabaseReference setModer = database.getReference("users/" + login.getText().toString() + "/moder");
                        setModer.setValue(tgb.getText());

                        DatabaseReference setFios = database.getReference("search/fios");
                        setFios.setValue(fios + "," + fio.getText().toString());
                        DatabaseReference setLogins = database.getReference("search/logins");
                        setLogins.setValue(logins + "," + login.getText().toString());

                        Intent i = new Intent(getApplicationContext(), Moder_main.class);
                        i.putExtra("fiosn2", fios + "," + fio.getText().toString());
                        i.putExtra("loginsn2", logins + "," + login.getText().toString());
                        i.putExtra("keyt2", keyt2);
                        startActivity(i);
                        login.setText("");
                        fio.setText("");
                        date.setText("");
                        address.setText("");
                        password.setText("");
                        spinner.setSelection(0);
                    }
                    else
                    { Toast toast = Toast.makeText(getApplicationContext(),
                                "Такой телефон уже зарегистрирован.", Toast.LENGTH_SHORT);
                        toast.show();
                    }}}


    @Override
    public void onBackPressed() {  finish(); }
}
