package sg.edu.rp.c346.id22028460.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    EditText enterText;
    EditText enterDate;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        enterDate = findViewById(R.id.EnterDate);
        enterText = findViewById(R.id.EnterText);
        lv = findViewById(R.id.lv);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);
                String newTask = enterText.getText().toString();
                String newDate = enterDate.getText().toString();

                db.insertTask(newTask, newDate);

                ArrayList<String> data = db.getTaskContent();
                ArrayList<Task> alTasks = db.getTasks();
                db.close();


                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
                ArrayAdapter<Task> aaTasks = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1, alTasks);
                lv.setAdapter(aaTasks);
            }
        });
    }

}
