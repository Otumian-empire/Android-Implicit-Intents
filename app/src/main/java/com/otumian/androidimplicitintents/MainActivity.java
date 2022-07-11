package com.otumian.androidimplicitintents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String URL_TAG = "URL_TAG", LOC_TAG = "LOC_TAG", TEXT_TAG = "TEXT_TAG";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private EditText etUrl, etLocation, etText;
    // private Button btnUrl, btnLocation, btnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = findViewById(R.id.et_url);
        etLocation = findViewById(R.id.et_location);
        etText = findViewById(R.id.et_text);

        if (savedInstanceState != null) {
            etUrl.setText(savedInstanceState.get(URL_TAG).toString());
            etLocation.setText(savedInstanceState.get(LOC_TAG).toString());
            etText.setText(savedInstanceState.get(TEXT_TAG).toString());
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(URL_TAG, etUrl.getText().toString());
        outState.putString(LOC_TAG, etLocation.getText().toString());
        outState.putString(TEXT_TAG, etText.getText().toString());
    }

    public void OpenWebsite(View view) {
        String url = etUrl.getText().toString().trim();

        if (url.length() > 1) {
            Uri uri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Log.d(LOG_TAG, "Can't handle this!");
                String text = "No package manager found, make sure you entered an actual url";
                Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, "Please enter URL", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void OpenLocation(View view) {
    }

    public void shareText(View view) {
    }
}