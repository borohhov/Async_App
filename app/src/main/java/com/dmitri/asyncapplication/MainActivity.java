package com.dmitri.asyncapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int iterations = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_helloWorld);
        Button button = findViewById(R.id.button_hello);
        Button longOpButton = findViewById(R.id.button_long_operation);
        Button asyncOpButton = findViewById(R.id.button_async_operation);
        final EditText inputEditText = findViewById(R.id.et_iterations);
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    iterations = 0;
                } else {
                    iterations = Integer.parseInt(inputEditText.getText().toString());
                }
            }
        });
        longOpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(String.format("Iterations: %d\nExecution time: %dms", iterations, runLongOperation(iterations)));

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("HELLO WORLD!!!");
            }
        });
        asyncOpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncOperation operation = new AsyncOperation();
                operation.execute(iterations);
            }
        });
    }

    class AsyncOperation extends AsyncTask<Integer, Void, Long> {
        private int iterations = 0;

        @Override
        protected Long doInBackground(Integer... numbers) {
            iterations = numbers[0];
            return runLongOperation(iterations);
        }

        @Override
        protected void onPostExecute(Long executionTime) {
            super.onPostExecute(executionTime);
            textView.setText(String.format("Iterations: %d\nExecution time: %dms", iterations, executionTime));
        }
    }

    public long runLongOperation(int iterations) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            ZeroFinder.findZeroesByString(11001241241L);
        }
        return (System.currentTimeMillis() - startTime);
    }
}
