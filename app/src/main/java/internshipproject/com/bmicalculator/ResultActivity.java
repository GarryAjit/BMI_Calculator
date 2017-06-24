package internshipproject.com.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvResult , tvUnderweight , tvNormal , tvOverweight , tvObese;
    Button btnBack , btnShare , btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = (TextView) findViewById(R.id.tvResult);
        btnBack = (Button) findViewById(R.id.btnBack);
       btnSave = (Button) findViewById(R.id.btnSave);
       btnShare = (Button) findViewById(R.id.btnShare);

        Intent i = getIntent();
        double msg = i.getDoubleExtra("bmi" , 0.0);

        if(msg<18.5)
        {
            tvResult.setText("Your BMI is " + msg + "and you are underweight");

        }
        if(18.5<=msg && msg<25)
        {
            tvResult.setText("Your BMI is " + msg + "and you are normal");
        }
        if(25<=msg && msg<30)
        {
            tvResult.setText("Your BMI is " + msg + "and you are overweight");
        }
        if (msg>=30)
        {
            tvResult.setText("Your BMI is " + msg + "and you are obese");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultActivity.this , WelcomeActivity.class);
                startActivity(i);
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String share = tvResult.getText().toString();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT , share);
                startActivity(i);
            }
        });
    }
}
