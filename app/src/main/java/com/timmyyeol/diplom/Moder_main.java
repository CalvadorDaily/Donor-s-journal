package com.timmyyeol.diplom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.text.Editable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Moder_main extends AppCompatActivity {
    private static final String TAG = "MyApp";
    Animation animation = null;
    String logins = "", fios = "", fios2 = "", logins2 = "", man = "";
    public String fio_value = "", birthday_value = "", address_value = "", logins_value = "", abo_value = "", history_value = "", moder_value = "", fios_value = "", status = "", pass_from_fireb = "";
    SimpleAdapter adapter;
    private final int IDD_THREE_BUTTON = 0;
    String newStatus;
    AlertDialog.Builder ad;
    Context context;
    String fiosn, loginsn, fiosn2, loginsn2, fios3, logins3;
    int keyt, keyt2;

    private final int IDD_THREE_BUTTONS = 0;
    private final int IDD_status = 1;
    private final int IDD_history = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moder_main);
        final ListView listView = (ListView) findViewById(R.id.listView);
        String[] fiomas;
        String[] loginsmas;
        keyt = 0;
        keyt2 = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fios = extras.getString("fios");
            logins = extras.getString("logins");
            fiosn = extras.getString("fiosn");
            loginsn = extras.getString("loginsn");
            keyt = extras.getInt("keyt");
            loginsn2 = extras.getString("loginsn2");
            keyt2 = extras.getInt("keyt2");
            fiosn2 = extras.getString("fiosn2");
        }
        if (keyt == 0 && keyt2 == 0) {
            fiomas = fios.split(",");
            loginsmas = logins.split(",");
            logins3 = logins;
            fios3 = fios;
        } else if (keyt == 1 && keyt2 == 0) {
            fiomas = fiosn.split(",");
            loginsmas = loginsn.split(",");
            logins3 = loginsn;
            fios3 = fiosn;
        } else {
            fiomas = fiosn2.split(",");
            loginsmas = loginsn2.split(",");
            logins3 = loginsn2;
            fios3 = fiosn2;
        }
        ArrayList<Map<String, String>> myArrList = new ArrayList<Map<String, String>>(
                fiomas.length);
        Map<String, String> map;
        for (int i = 0; i < fiomas.length; i++) {
            map = new HashMap<String, String>();
            map.put("fi", fiomas[i]);
            map.put("log", loginsmas[i]);
            myArrList.add(map);
        }
        adapter = new SimpleAdapter(this, myArrList, android.R.layout.simple_list_item_2,
                new String[]{"fi", "log"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Map<String, String> map = (Map<String, String>) adapter.getItem(position);
                man = (String) map.get("log");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference get_password = database.getReference("users/" + man + "/password");
                get_password.addValueEventListener(new ValueEventListener() {                    //пароль
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        pass_from_fireb = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такого пароля.", databaseError.toException());
                    }
                });

                DatabaseReference get_fio = database.getReference("users/" + man + "/fio");
                get_fio.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        fio_value = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такого ФИО.", databaseError.toException());
                    }
                });

                DatabaseReference get_birthday = database.getReference("users/" + man + "/birthday");
                get_birthday.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        birthday_value = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такого дня рождения.", databaseError.toException());
                    }
                });

                DatabaseReference get_address = database.getReference("users/" + man + "/address");
                get_address.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        address_value = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такого адреса.", databaseError.toException());
                    }
                });

                DatabaseReference get_abo = database.getReference("users/" + man + "/abo");
                get_abo.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        abo_value = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такой группы крови.", databaseError.toException());
                    }
                });

                DatabaseReference get_history = database.getReference("users/" + man + "/history");
                get_history.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        history_value = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такой истории.", databaseError.toException());
                    }
                });

                DatabaseReference get_fios = database.getReference("search/fios");
                get_fios.addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        fios_value = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет таких ФИО.", databaseError.toException());
                    }
                });

                DatabaseReference get_logins = database.getReference("search/logins");
                get_logins.addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        logins_value = value;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет таких логинок.", databaseError.toException());
                    }
                });

                DatabaseReference get_moder = database.getReference("users/" + man + "/moder");
                get_moder.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        moder_value = value;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такого модер права.", databaseError.toException());
                    }
                });
                DatabaseReference get_status = database.getReference("users/" + man + "/status");

                get_status.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        status = value;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Нет такого статуса.", databaseError.toException());
                    }
                });
                showDialog(IDD_THREE_BUTTONS);
            }
        });
        logins2 = "";
        fios2 = "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_moder, menu);
        return true;
    }


    public void onExitModer(MenuItem item) {
        finish();
        Intent i = new Intent(getApplicationContext(), Authorization.class);
        startActivity(i);
        Toast toast = Toast.makeText(getApplicationContext(),
                R.string.exitComp, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void addButton(View view) {
        if(fios3.equals("")||logins3.equals(""))
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Подключение установлено. Нажмите ещё раз.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
        Intent i = new Intent(getApplicationContext(), AddActivity.class);
        i.putExtra("fios", fios3);                                                                    //для добавления в search брать в активити адд
        i.putExtra("logins", logins3);                                                                 //брать при нажатии на едитекст в активити add
        startActivity(i);
    }}

    @Override
    protected Dialog onCreateDialog(int id) {
        final Intent i = new Intent(getApplicationContext(), ModerUserChange.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id) {
            case IDD_THREE_BUTTONS:
                builder.setMessage("Выберите раздел:")
                        .setPositiveButton("История посещений",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        if (history_value.equals(""))
                                        {
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    "Ошибка подключения. Нажмите ещё раз.", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                        else {
                                            showDialog(IDD_history);
                                            dialog.cancel();
                                        }}
                                })
                        .setNeutralButton("Статус анализов",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
if (status.equals(""))
{
    Toast toast = Toast.makeText(getApplicationContext(),
            "Ошибка подключения. Нажмите ещё раз.", Toast.LENGTH_SHORT);
    toast.show();
}
 else {
    showDialog(IDD_status);
    dialog.cancel(); }}
                                })
                        .setNegativeButton("Профиль",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
if (fio_value.equals("") || fios3.equals("")|| logins3.equals("")||birthday_value.equals("")|| address_value.equals("")||
        abo_value.equals("")|| pass_from_fireb.equals("")||moder_value.equals("")|| history_value.equals("")||status.equals(""))
{
    Toast toast = Toast.makeText(getApplicationContext(),
            "Ошибка подключения. Нажмите ещё раз.", Toast.LENGTH_SHORT);
    toast.show();
}
else {
   final Intent i = new Intent(getApplicationContext(), ModerUserChange.class);
    i.putExtra("login", man);
    i.putExtra("fio", fio_value);
    i.putExtra("fios", fios3);
    i.putExtra("logins", logins3);
    i.putExtra("birthday", birthday_value);
    i.putExtra("address", address_value);
    i.putExtra("abo", abo_value);
    i.putExtra("password", pass_from_fireb);
    i.putExtra("moder", moder_value);
    i.putExtra("history", history_value);
    i.putExtra("status", status);
    startActivity(i);
    birthday_value="";
    moder_value="";
    fio_value="";
    pass_from_fireb="";
    abo_value="";
}}});
                return builder.create();
            case IDD_status:
                final String[] mStatus = {"Отсутствует", "Положительные результаты", "Отрицательные результаты", "Ожидание результатов"};
                builder.setSingleChoiceItems(mStatus, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item >= 0) {
                            newStatus = mStatus[item];
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users/" + man + "/status");
                            myRef.setValue(newStatus);
                            dialog.cancel();
                        }}});
                return builder.create();

            case IDD_history:
                View linearlayout = getLayoutInflater().inflate(R.layout.dialog, null);
                builder.setView(linearlayout);
                builder.setTitle("Дата(ДД.ММ.ГГГГ):");
                final EditText edt = (EditText) linearlayout.findViewById(R.id.edthistory);
                builder.setPositiveButton("Готово",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (edt.getText().toString().length() > 0) {
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("users/" + man + "/history");
                                    myRef.setValue(history_value + "," + edt.getText().toString());
                                    dialog.cancel();
                                }
                                else
                                {
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Заполните поле.", Toast.LENGTH_SHORT);
                                    toast.show();
                                }}
                        })
                        .setNeutralButton("Открыть историю",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        if (history_value.equals(""))
                                        {
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    "Ошибка подключения. Нажмите ещё раз.", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                        else {
                                            Intent i = new Intent(getApplicationContext(), User_history.class);
                                            i.putExtra("history", history_value);
                                            startActivity(i);
                                            dialog.cancel(); }}
                                });
                return builder.create();
            default:
                return null;
        }}

    @Override
    public void onBackPressed() {}
}

