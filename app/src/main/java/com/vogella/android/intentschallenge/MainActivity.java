package com.vogella.android.intentschallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name="", number="", website="", location="", mood="";

    Button btnNewContact;
    ImageView ivMood, ivCall, ivWebsite, ivLocation;

    final int NEW_CONTACT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMood = findViewById(R.id.ivMood);
        ivCall = findViewById(R.id.ivCall);
        ivWebsite = findViewById(R.id.ivWebsite);
        ivLocation = findViewById(R.id.ivLocation);


        // hide all image views at start
        ivMood.setVisibility(View.GONE);
        ivCall.setVisibility(View.GONE);
        ivWebsite.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);

        btnNewContact = findViewById(R.id.btnNewContact);

        btnNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.vogella.android.intentschallenge.NewContact.class);
                startActivityForResult(intent, NEW_CONTACT);
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        ivWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + website));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + location));
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CONTACT) {

            if (resultCode == RESULT_OK) {
                ivCall.setVisibility(View.VISIBLE);
                ivWebsite.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);
                ivMood.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                website = data.getStringExtra("website");
                location = data.getStringExtra("location");
                mood = data.getStringExtra("mood");

                if (mood == "happy") {
                    ivMood.setImageResource(R.drawable.happy);
                }
                else if (mood == "sad") {
                    ivMood.setImageResource(R.drawable.sad);
                }
                else if (mood == "neutral"){
                    ivMood.setImageResource(R.drawable.neutral);
                }



            }
            
            // cancelled result
            else {
                Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
