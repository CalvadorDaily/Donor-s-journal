package com.timmyyeol.diplom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.Toast;


public class User_Profile extends AppCompatActivity {


        String fio = "", birthday = "", address = "", abo = "", history = "", status = "";
        private static final String TAG = "MyApp";
    int len = 0;
    String [] historymas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
setTitle("Профиль");
        Bundle extras = getIntent().getExtras();
        TextView fio_text = (TextView) findViewById(R.id.edtfio);
        TextView birthday_text = (TextView) findViewById(R.id.edtdate);
        TextView address_text = (TextView) findViewById(R.id.edtaddress);
        TextView abo_text = (TextView) findViewById(R.id.edtlogin);
        TextView status_text = (TextView) findViewById(R.id.edtstatus);
        TextView history_text = (TextView) findViewById(R.id.textView11);

        if (extras != null) {
            fio = extras.getString("fio");
            birthday = extras.getString("birthday");
            address = extras.getString("address");
            abo = extras.getString("abo");
            history = extras.getString("history");
            status = extras.getString("status");
        }

        historymas = history.split(",");
        len = historymas.length-1;
        fio_text.setText(fio);
        birthday_text.setText(birthday);
        address_text.setText(address);
        abo_text.setText(abo);
        status_text.setText(status);
        history_text.setText(String.valueOf(len));
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true; }



    private void createOneButtonAlertDialog( String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(User_Profile.this);
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

        public void historyButton(View view) {
            Intent i = new Intent(getApplicationContext(), User_history.class);
            i.putExtra("history", history);
            startActivity(i);
        }

        public void onInstructions(MenuItem item) {
            String but  =
                            "Cтарайтесь регулярно и сбалансировано питаться, включайте в свой рацион мясо, рыбу, творог, фаcоль, чечевицу.\n\n" +
                            "Воздержитесь от употребления алкоголя за 48 часов до процедуры.\n\n" +
                            "Не курите за 2 часа до процедуры, а также 2-3 часа после плазмосдачи. Ядовитое действие попавшего в кровь никотина опасно для пациентов, особенно новорождённых, а также для вашего здоровья.\n\n" +
                            "Обязательно выспитесь! Плазму нужно сдавать будучи здоровым и отдохнувшим.\n\n" +
                            "До сдачи плазмы избегайте употребления лекарств. Не принимайте лекарства, содержащие аспирин и анальгетики в течение 5 дней до процедуры.\n\n" +
                            "Накануне исключите из рациона жирное, жареное, острое, копчёное, молочные продукты, яйца, масло. Рекомендуется - сладкий чай, варенье, хлеб, сухари, сушки, отварные крупы, макароны на воде без масла, соки, морсы, компоты, минеральная вода, овощи, фрукты (крмое бананов).\n\n" +
                            "Самое подходящее время для сдачи плазмы 2-3 часа после еды. Соблюдение этих требований позволит качетственно произвести донацию плазмы.\n\n" +
                            "Вы - донор, значит, Вы здоровы. Пусть донорство даст вам ощущение гордости и радости за спасённые вами жизни.\n\n" +
                            "После сдачи плазмы не спешите сразу вставать, рекомендуем Вам отдохнуть (хотя бы 10 минут), выпить чай или кофе (это поможет восполнить потерю жидкости в организме). Употребляйте больше жидкости и в последующие дни после донации.\n\n" +
                            "Наложенную медсестрой стерильную повязку не снимайте и не мочите в течение 4-6 часов.\n\n" +
                            "Если после сдачи плазмы возникнет покраснение вокруг вены, ухудшится самочувствие или вы почувствуете слабость - обратитесь к персоналу плазмоцентра, который сможет быстро и квалифицированнно оказать вам необходимую помощь.";
            createOneButtonAlertDialog(but); }

        public void onContacts(MenuItem item) {
            String but  =
                    "Казанский Плазмоцентр:\n" +
                            "ул. Островского, 69, Казань, 420107\n" +
                    "тел. 8(843)292-13-93"+"\n\n" +
                    "фГБУ \"РосПлазма\":\n" +
                    "телефон: (8332) 36-80-00\n" +
                            "e-mail: center@rosplasma.ru\n" +
                    "факс: (8332) 36-80-01\n" +
                    "телефон: (8332) 36-79-92\n" +
                            "e-mail: personal@rosplasma.ru\n" +
                   "e-mail: sov@rosplasma.ru\n";
            createOneButtonAlertDialog(but);  }

        public void onExit(MenuItem item) {
            finish();
            Intent i = new Intent(getApplicationContext(), Authorization.class);
            startActivity(i);
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.exitComp, Toast.LENGTH_SHORT);
            toast.show();                  }

    @Override
    public void onBackPressed() {}
    }




