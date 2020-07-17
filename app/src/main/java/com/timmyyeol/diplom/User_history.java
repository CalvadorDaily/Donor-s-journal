package com.timmyyeol.diplom;


        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.WindowManager;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;

public class User_history extends AppCompatActivity {

    Button button;
    private String user, history = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_history);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            history = extras.getString("history");
        }
        ListView listView = (ListView)findViewById(R.id.listView);
        String [] hismas = history.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, hismas);
        listView.setAdapter(adapter);
        button = (Button) findViewById(R.id.button);
    }
    @Override
    public void onBackPressed() { finish();}
}
