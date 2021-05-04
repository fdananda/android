package com.fdananda.gitcrudsharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAtributo1, editTextAtributo2,  editTextAtributo3;
    private TextView textViewAtributo1, textViewAtributo2, textViewAtributo3;
    private Button buttonGravarEdicao;
    private CrudPreferencias crudPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crudPreferencias = new CrudPreferencias(getApplicationContext());

        editTextAtributo1 = findViewById(R.id.editTextAtributo1);
        editTextAtributo2 = findViewById(R.id.editTextAtributo2);
        editTextAtributo3 = findViewById(R.id.editTextAtributo3);

        textViewAtributo1 = findViewById(R.id.textViewAtributo1);
        textViewAtributo2 = findViewById(R.id.textViewAtributo2);
        textViewAtributo3 = findViewById(R.id.textViewAtributo3);

        buttonGravarEdicao      = findViewById(R.id.buttonEditar);
    }

    public void salvar(View view) {

        String atributo1, atributo2, atributo3;

        atributo1 = editTextAtributo1.getText().toString();
        atributo2 = editTextAtributo2.getText().toString();
        atributo3 = editTextAtributo3.getText().toString();

        if(atributo1.isEmpty() || atributo1 == ""){
            Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
        }else{
            if(atributo2.isEmpty() || atributo1 == ""){
                Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
            }else{
                if(atributo3.isEmpty() || atributo1 == ""){
                    Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                }else{

                    crudPreferencias.create(atributo1, atributo2, atributo3);

                    editTextAtributo1.setText("");
                    editTextAtributo2.setText("");
                    editTextAtributo3.setText("");

                    Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();

                    buttonGravarEdicao.setVisibility(View.GONE);

                    listar(view);
                }
            }
        }
    }

    public void listar(View view){

            String atributo1, atributo2, atributo3;

            atributo1 = crudPreferencias.read().getAtributo1();
            atributo2 = crudPreferencias.read().getAtributo2();
            atributo3 = crudPreferencias.read().getAtributo3();

            textViewAtributo1.setText(atributo1);
            textViewAtributo2.setText(atributo2);
            textViewAtributo3.setText(atributo3);

        buttonGravarEdicao.setVisibility(View.GONE);
    }


    public void editar(View view) {

            String atributo1, atributo2, atributo3;

            atributo1 = crudPreferencias.read().getAtributo1();
            atributo2 = crudPreferencias.read().getAtributo2();
            atributo3 = crudPreferencias.read().getAtributo3();

            editTextAtributo1.setText(atributo1);
            editTextAtributo2.setText(atributo2);
            editTextAtributo3.setText(atributo3);

            buttonGravarEdicao.setVisibility(View.VISIBLE);
    }

    public void gravarEdicao(View view) {

        String atributoalterado1, atributoalterado2, atributoalterado3;

        atributoalterado1 = editTextAtributo1.getText().toString();
        atributoalterado2 = editTextAtributo2.getText().toString();
        atributoalterado3 = editTextAtributo3.getText().toString();

        if(atributoalterado1.isEmpty() || atributoalterado1 == ""){
            Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
        }else{
            if(atributoalterado2.isEmpty() || atributoalterado2 == ""){
                Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
            }else{
                if(atributoalterado3.isEmpty() || atributoalterado3 == ""){
                    Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                }else{

                    crudPreferencias.update(atributoalterado1, atributoalterado2, atributoalterado3);

                    editTextAtributo1.setText("");
                    editTextAtributo2.setText("");
                    editTextAtributo3.setText("");

                    Toast.makeText(this, "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();

                    buttonGravarEdicao.setVisibility(View.GONE);

                    listar(view);
                }
            }
        }
    }

    public void excluir(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);
        alertDialog.setIcon(android.R.drawable.ic_menu_delete);
        alertDialog.setTitle("Excluir dados");
        alertDialog.setMessage("Ao confirmar todos os atributos serão excluídos. Deseja continuar?");

        alertDialog.setPositiveButton("SIM", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        crudPreferencias.delete();

                        editTextAtributo1.setText("");
                        editTextAtributo2.setText("");
                        editTextAtributo3.setText("");

                        Toast.makeText(getApplicationContext(), "Dados excluídos com sucesso!", Toast.LENGTH_SHORT).show();

                        listar(view);
                    }
                });
        alertDialog.setNegativeButton("NÃO", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        alertDialog.create();
        alertDialog.show();
    }
}
