package com.fdananda.gitasynctask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBarVolume);
    }

    public void start(View view){

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(10);
    }

    //<Parâmetro de entrada, Progresso, Retorno>
    public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            seekBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {

            int progresso = integers[0];
            for (int i = 0; i<= progresso; i++){
                publishProgress(i);

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return "OK";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            seekBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            seekBar.setProgress(0);
            seekBar.setVisibility(View.INVISIBLE);
        }
    }
}
