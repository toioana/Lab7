package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mySpeech;
    private Button speakButton;
    private Button listenButton;
    private EditText myInputText;

    private TextToSpeech ttobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listenButton = (Button)findViewById(R.id.listen);
        myInputText = (EditText)findViewById(R.id.editText);
        mySpeech = (TextView)findViewById(R.id.text);
        speakButton = (Button)findViewById(R.id.speak);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechInput();
            }
        });
        ttobj=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    ttobj.setLanguage(Locale.UK);
                }
            }

        });

        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = myInputText.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }

    private void speechInput()
    {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-En");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText
            mySpeech.setText(spokenText);

        }
    }
}
