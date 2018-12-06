package com.example.ray04.lab11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit_text);
    }

    public void onButtonClicked(View view) {
        Intent intent = new Intent(this,DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.MESSAGE,"Timing!!");
        intent.putExtra("time",String.valueOf(editText.getText()));
        startService(intent);
    }
}
