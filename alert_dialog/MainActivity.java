package com.fdananda.gitalertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.fdananda.gitalertdialog.util.DialogGeneric;

public class MainActivity extends AppCompatActivity {

    private DialogGeneric dialogGeneric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirAlertDialog(View view) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setCancelable(true);
            alertDialog.setIcon(android.R.drawable.btn_star_big_on);
            alertDialog.setTitle("Título do Alert Dialog");
            alertDialog.setMessage("Mensagem do Alert Dialog");

            alertDialog.setPositiveButton("SIM", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_sim, null);

                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }
                    });
            alertDialog.setNegativeButton("NÃO", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_nao, null);

                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }
                    });
            alertDialog.create();
            alertDialog.show();
    }

    public void abrirAlertDialogPersonalizado(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        alertDialog.setView(inflater.inflate(R.layout.alert_dialog_personalizado, null));

        alertDialog.setPositiveButton("SIM", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_sim, null);

                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                    }
                });
        alertDialog.setNegativeButton("NÃO", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_nao, null);

                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                    }
                });
        alertDialog.create();
        alertDialog.show();
    }

    public void openGenericDialog(View view){
        dialogGeneric = new DialogGeneric();
        dialogGeneric.OpenDialog(this, getResources().getDrawable(R.drawable.ic_generic),
                "Título do Alerta",
                "Texto que irá aparecer como mensagem do alerta. Pode ser multilinhas. " +
                        "O ideal é que não seja muito grande. Entendeu?!",
                "Claro :-)",
                MainActivity.class,
                "Xii... :-(",
                MainActivity.class,
                "Talvez",
                getResources().getColor(R.color.black));
    }
}
