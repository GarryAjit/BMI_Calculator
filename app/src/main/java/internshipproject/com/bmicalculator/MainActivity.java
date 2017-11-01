package internshipproject.com.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import internshipproject.com.bmicalculator.R;

public class MainActivity extends AppCompatActivity {

    EditText etName , etAge , etEmail;
    Button btnSave;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSave = (Button) findViewById(R.id.btnSave);

        sp1 = getSharedPreferences("B1" , MODE_PRIVATE);

        final String email = etEmail.getText().toString();
        String name = sp1.getString("name" , "");
        if( ! name.equals(""))
        {
            Intent i = new Intent(MainActivity.this , WelcomeActivity.class);
            startActivity(i);
            finish();
        }

        else
        {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = etName.getText().toString();

                    if(name.length()==0)
                    {
                        etName.setError("Please enter name");
                        etName.requestFocus();
                        return;
                    }

                    String age = etAge.getText().toString();
                    if(age.length()==0)
                    {
                        etAge.setError("Please enter age");
                        etAge.requestFocus();
                        return;
                    }

                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    {
                        etEmail.setError("please enter valid email");
                        etEmail.requestFocus();
                        return;
                    }

                    SharedPreferences.Editor editor = sp1.edit();
                    editor.putString("name" , name);
                    editor.putString("age" , age);
                    editor.putString("email" , email);
                    editor.commit();

                    Intent i = new Intent(MainActivity.this , WelcomeActivity.class);
                    startActivity(i);
                    finish();

                }
            });
        }
    }
}
