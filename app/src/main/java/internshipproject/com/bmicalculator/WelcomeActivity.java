package internshipproject.com.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    TextView tvWelcome , tvHeight , tvFeet , tvInch;
    Spinner spnFeet , spnInch;
    EditText etWeight;
    Button btnCalculate , btnHistory;
    SharedPreferences sp1;
     String f ,i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        spnFeet = (Spinner) findViewById(R.id.spnFeet);
        spnInch = (Spinner) findViewById(R.id.spnInch);
        etWeight = (EditText) findViewById(R.id.etWeight);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnHistory = (Button) findViewById(R.id.btnHistory);

        sp1 = getSharedPreferences("B1" , MODE_PRIVATE);
        String name = sp1.getString("name" , "");
        tvWelcome.setText("Hi" + name);

        ArrayList<String> feet = new ArrayList<String>();
        feet.add("1");feet.add("2");feet.add("3");feet.add("4");feet.add("5");feet.add("6");
        feet.add("7");feet.add("8");feet.add("9");feet.add("10");
        ArrayAdapter<String> feetAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , feet);
        spnFeet.setAdapter(feetAdapter);

        ArrayList<String> inch = new ArrayList<String>();
        inch.add("1");inch.add("2");inch.add("3");inch.add("4");inch.add("5");inch.add("6");inch.add("7");
        inch.add("8");inch.add("9");inch.add("10");inch.add("11");
        ArrayAdapter<String> inchAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , inch);
        spnInch.setAdapter(inchAdapter);



        spnFeet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String f = adapterView.getItemAtPosition(i).toString();


            }
        });

        spnInch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int j, long l) {
                String i = adapterView.getItemAtPosition(j).toString();

            }
        });

        String weight = etWeight.getText().toString();
        double wt = Double.parseDouble(weight);
        double ft = Double.parseDouble(f);
        double in = Double.parseDouble(i);
        double metres = (ft*0.3048) + (in*0.0254);
        final double bmi = (wt)/(Math.sqrt(metres));

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(WelcomeActivity.this , ResultActivity.class);
                i.putExtra("bmi" , bmi);
                startActivity(i);

            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close the application");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
    }
}


















