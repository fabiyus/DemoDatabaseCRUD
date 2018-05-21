package sg.edu.rp.c346.demodatabasecrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity{
    TextView tvID;
    EditText etContent;
    Button btnUpdate, btnDelete;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
btnUpdate = (Button) findViewById(R.id.btnUpdate);
btnDelete = (Button) findViewById(R.id.btnDelete);
tvID = (TextView) findViewById(R.id.tvID);
etContent = (EditText)findViewById(R.id.etContent);

        //initialize the variables with UI here

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etContent.setText(data.getNoteContent());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setNoteContent(etContent.getText().toString());
                dbh.updateNote(data);
                dbh.close();
                Intent o = new Intent();
                o.putExtra("type","update");
                setResult(Activity.RESULT_OK,o);
                finish();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.getId());
                dbh.close();
                Intent x = new Intent();
                x.putExtra("type","delete");
                setResult(Activity.RESULT_OK,x);
                finish();
            }
        });


    }



}
