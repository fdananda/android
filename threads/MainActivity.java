package com.fdananda.threads;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonThreadPrincipal;
    private Button buttoMyThread;
    private Button buttonMyRunnable;
    private int numeroMyRunnable;
    private int numeroMyThread;
    private TextView textViewMyRunnableResultado;
    private Handler handler = new Handler();
    private boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMyRunnable = findViewById(R.id.buttonMyRunnable);
        buttonThreadPrincipal = findViewById(R.id.buttonThreadPrincipal);
        buttoMyThread = findViewById(R.id.buttonMyThread);
        textViewMyRunnableResultado = findViewById(R.id.textViewMyRunnableResultado);
    }

    //Utilizando a Thread principal - evitar
    public void startThreadPrincipal(View view){
        for (int i=0; i<=15; i++){
            Log.d("Thread", "Passos da Thread Principal:" + i);
            buttonThreadPrincipal.setText("Thread Principal: " + i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //Utilizando a Thread criada na classe MyThread
    public void startMyThread(View view){

        MyThread myThread = new MyThread();
        myThread.start();
    }
    //Utilizando a Thread criada na classe MyThead
    public void startMyRunnable(View view){

        stop = false;
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
    }

    public void stopMyRunnable(View view){
        stop = true;
        textViewMyRunnableResultado.setVisibility(View.GONE);
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {

            for (int i=0; i<=15; i++){

                if (stop)
                    return;

                numeroMyRunnable = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttonMyRunnable.setText("My Runnable: " + numeroMyRunnable);
                    }
                });

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textViewMyRunnableResultado.setVisibility(View.VISIBLE);
                            textViewMyRunnableResultado.setText("My Runnable: " + numeroMyRunnable);
                            textViewMyRunnableResultado.setGravity(1);
                        }
                    }, 5000);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textViewMyRunnableResultado.setVisibility(View.GONE);
                        }
                    }, 16000);

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public class MyThread extends Thread{

        @Override
        public void run() {
            for (int i=0; i<=15; i++){
                numeroMyThread = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttoMyThread.setText("My Thread: " + numeroMyThread);
                    }
                });
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            super.run();
        }
    }
    
}
