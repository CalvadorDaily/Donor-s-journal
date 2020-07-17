package com.timmyyeol.diplom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Authorization extends AppCompatActivity {
CheckBox box;
    private final int IDD_THREE_BUTTONS = 0;
    String pass_from_fireb = "";
    private static final String TAG = "MyApp";
    public static String SQL_log="", SQL_pas="";
    TextView txt;
    public String fio_value = "", birthday_value = "", address_value = "", abo_value = "", history_value = "", moder_value = "", fios = "", logins = "", status = "";
    public ControlSQL dbSQL;
    public static String login_in = "";
    String [] loginsmas;
    public static Integer num_in = 0;
    int get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_authorization);
         EditText login = (EditText) findViewById(R.id.editText2);
        box = (CheckBox)findViewById(R.id.checkBox);
         EditText edt = (EditText) findViewById(R.id.editText3);
        dbSQL = new ControlSQL(this);
        Cursor newtable = dbSQL.getTable(1);
        if (newtable != null) {
            newtable.moveToFirst();
            num_in = newtable.getInt(0);
            login_in = newtable.getString(1);
            login.setText(login_in);
        }

        edt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                    final EditText log = (EditText) findViewById(R.id.editText2);
                    get=0;
                    loginsmas = logins.split(",");
                    for (int i = 0; i < loginsmas.length; i++) {
                        if (!(loginsmas[i].equals(log.getText().toString()))) {

                        }
                        else {
                            get =1;
                        }
                    }
    if (!(log.getText().toString().equals(""))|| get==1) {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
        String login =log.getText().toString();

        DatabaseReference get_password = database.getReference("users/" + login + "/password");
        get_password.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String value = dataSnapshot.getValue(String.class);
            pass_from_fireb = value;
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(TAG, "Нет такого пароля", databaseError.toException());
        }
    });
        DatabaseReference get_logins = database.getReference("search/logins");

        get_logins.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                logins = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет логинов всех пользователей", databaseError.toException());
            }
        });

        DatabaseReference get_fio = database.getReference("users/" + login + "/fio");

        get_fio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fio_value = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет ФИО.", databaseError.toException());
            }
        });

        DatabaseReference get_birthday = database.getReference("users/" + login + "/birthday");

        get_birthday.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                birthday_value = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет даты рождения", databaseError.toException());
            }
        });

        DatabaseReference get_address = database.getReference("users/" + login + "/address");

        get_address.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                address_value = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет адреса.", databaseError.toException());
            }
        });

        DatabaseReference get_abo = database.getReference("users/" + login+ "/abo");

        get_abo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                abo_value = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет группы крови.", databaseError.toException());
            }
        });

        DatabaseReference get_history = database.getReference("users/" + login + "/history");

        get_history.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                history_value = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет истории.", databaseError.toException());
            }
        });

        DatabaseReference get_fios = database.getReference("search/fios");

        get_fios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fios = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет ФИО всех пользователей.", databaseError.toException());
            }
        });



        DatabaseReference get_moder = database.getReference("users/" + login + "/moder");

        get_moder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                moder_value = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет таких прав модера.", databaseError.toException());
            }
        });

        DatabaseReference get_status = database.getReference("users/" + login + "/status");

        get_status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                status = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Нет статуса.", databaseError.toException());
            }
        });

    } else  {


        Toast toast = Toast.makeText(getApplicationContext(),
                "Проверьте правильность ввода логина.", Toast.LENGTH_SHORT);
        toast.show();
    }

                }
                return false;
            }

        });

        edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                final EditText login = (EditText) findViewById(R.id.editText2);
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE &&login.length()!=0) {
                    buttonClick1(v);


                    return handled;
                }
                return handled;
            }
        });
        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
EditText edt =(EditText) findViewById(R.id.editText3);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
edt.setText("");
                }
                return false;
            }});
    }

    @Override
    public void onBackPressed() {
         //super.onBackPressed();
        showDialog(IDD_THREE_BUTTONS);
    }
    protected Dialog onCreateDialog(int id) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        switch (id) {

            case IDD_THREE_BUTTONS:

                builder.setMessage("Вы точно хотите выйти?")
                        .setPositiveButton("Да",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {

                                        Authorization.super.onBackPressed();

                                    }
                                })

                        .setNegativeButton("Нет",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
dialog.cancel();
                                    }
                                });

                return builder.create();

            default:
                return null;
        }}


    public void buttonClick1(View view) {

        EditText login = (EditText) findViewById(R.id.editText2);
        EditText password = (EditText) findViewById(R.id.editText3);
if (login.getText().toString().equals("") || password.getText().toString().equals(""))
{
    Toast toast = Toast.makeText(getApplicationContext(),
            "Не все поля заполнены.", Toast.LENGTH_SHORT);
    toast.show();
}
        else {
        if (!(pass_from_fireb.equals(""))|| password.getText().toString().equals(pass_from_fireb)) {
            String[] io = fio_value.split(" ", 2);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Здравствуйте, " + io[1] + ".", Toast.LENGTH_SHORT);
            toast.show();
            if (box.isChecked()) {
                String login_in = login.getText().toString();
                dbSQL.updateTable(num_in, login_in);
            }
            if (moder_value.equals("yes")) {
                Intent j = new Intent(getApplicationContext(), Moder_main.class);
                j.putExtra("fios", fios);
                j.putExtra("logins", logins);
                startActivity(j);
            } else {
                Intent i = new Intent(getApplicationContext(), User_Profile.class);
                i.putExtra("fio", fio_value);
                i.putExtra("birthday", birthday_value);
                i.putExtra("address", address_value);
                i.putExtra("status", status);
                i.putExtra("abo", abo_value);
                i.putExtra("login", login.getText().toString());
                i.putExtra("history", history_value);
                startActivity(i);
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                   R.string.error_message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }}
    private void createOneButtonAlertDialog( String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Authorization.this);
        builder.setMessage(content);
        builder.setPositiveButton("OK",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        return;
                    }
                });
        builder.show();
    }

    public void getLogin(View view) {
        String but  =  getString (R.string.forgot_gata);
        createOneButtonAlertDialog(but);
    }
    public void getPassword(View view) {
        String but  =  getString (R.string.registration);
        createOneButtonAlertDialog(but);
    }
}

