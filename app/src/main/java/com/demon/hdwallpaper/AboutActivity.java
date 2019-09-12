package com.demon.hdwallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvPhonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.toolbar);
        tvPhonenumber = findViewById(R.id.tvphonenb);
//        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("About");
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    public void Company(View view) {
    }

    public void Email(View view) {
    }

    public void Website(View view) {
    }

    public void Contact(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + tvPhonenumber.getText().toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
