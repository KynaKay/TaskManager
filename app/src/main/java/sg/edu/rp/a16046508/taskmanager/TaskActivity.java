package sg.edu.rp.a16046508.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    TextView tvName, tvDesc;
    EditText etName, etDesc;
    Button btnAdd, btnCancel;
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tvName = (TextView) findViewById(R.id.tvName);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        etName = (EditText) findViewById(R.id.etName);
        etDesc = (EditText) findViewById(R.id.etDesc);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Task) i.getSerializableExtra("data");

        tvName.setText("Name: " + data.getID());
        etName.setText(data.getTaskName());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DBHelper dbh = new DBHelper(TaskActivity.this);
//                data.setTaskName(tvName.getText().toString());
//                dbh.updateNote(data);
//                dbh.close();

                Intent i = new Intent();
                i.putExtra("type", "update");

                setResult(RESULT_OK, i); // back to the result page
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DBHelper dbh = new DBHelper(TaskActivity.this);
//                dbh.deleteNote(data.getID());
//                dbh.close();

                Intent i = new Intent();
                i.putExtra("type", "cancel");

                setResult(RESULT_CANCELED, i); // back to the result page
                finish();
            }
        });

    }
}
