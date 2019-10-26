package com.vogella.android.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewContact extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etNumber, etWebsite, etLocation;
    ImageView ivHappy, ivNeutral, ivSad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etWebsite = findViewById(R.id.etWebsite);
        etLocation = findViewById(R.id.etLocation);

        ivHappy = findViewById(R.id.ivHappy);
        ivNeutral = findViewById(R.id.ivNeutral);
        ivSad = findViewById(R.id.ivSad);

        ivHappy.setOnClickListener(this);
        ivNeutral.setOnClickListener(this);
        ivSad.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String name = etName.getText().toString();
        String number = etNumber.getText().toString();
        String website = etWebsite.getText().toString();
        String location = etLocation.getText().toString();

        if (name.isEmpty() || number.isEmpty() || website.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("number", number);
            intent.putExtra("website", website);
            intent.putExtra("location", location);

            if (view.getId() == R.id.ivHappy) {
                intent.putExtra("mood", "happy");
            }
            else if (view.getId() == R.id.ivNeutral) {
                intent.putExtra("mood", "neutral");

            }
            else if (view.getId() == R.id.ivSad){
                intent.putExtra("mood", "sad");
            }

            setResult(RESULT_OK, intent);
            NewContact.this.finish();

        }

    }


}
