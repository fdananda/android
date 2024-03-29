package com.fdananda.gitfirebaserealtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAtributo1, editTextAtributo2, editTextAtributo3, editTextPesquisar;
    private TextView textViewDadosUsuarioLogado;
    private ListView listViewAtributos;
    private Atributo atributoSelecionado;
    DatabaseReference atributosRef = Configuracao.getDatabase();
    private ValueEventListener valueEventListenerAtributo;
    private Button buttonRecuperar;
    ArrayList<Atributo> atributos = new ArrayList<Atributo>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAtributo1 = findViewById(R.id.editTextAtributo1);
        editTextAtributo2 = findViewById(R.id.editTextAtributo2);
        editTextAtributo3 = findViewById(R.id.editTextAtributo3);
        editTextPesquisar = findViewById(R.id.editTextPesquisar);
        buttonRecuperar   = findViewById(R.id.buttonLerDados);

        Bundle dadosRecebidos = getIntent().getExtras();

        textViewDadosUsuarioLogado = findViewById(R.id.textViewidUsuarioLogado);
        textViewDadosUsuarioLogado.setText("E-mail logado: " + dadosRecebidos.getString("email"));
    }

    public void salvar(View view){

        String atributo1 = editTextAtributo1.getText().toString();
        String atributo2 = editTextAtributo2.getText().toString();
        String atributo3 = editTextAtributo3.getText().toString();

        if(atributo1.isEmpty() || atributo1 == ""){
            Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
        }else{
            if(atributo2.isEmpty() || atributo1 == ""){
                Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
            }else{
                if(atributo3.isEmpty() || atributo1 == ""){
                    Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                }else{

                    Atributo atributo = new Atributo();
                    atributo.setAtributo1(atributo1);
                    atributo.setAtributo2(atributo2);
                    atributo.setAtributo3(atributo3);

                    atributo.salvar();

                    editTextAtributo1.setText("");
                    editTextAtributo2.setText("");
                    editTextAtributo3.setText("");

                    Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void ler(View view){

        listViewAtributos  = findViewById(R.id.listViewRecuperar);

        valueEventListenerAtributo = atributosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                atributos.clear();

                for(DataSnapshot dados: snapshot.getChildren() ){

                    Atributo atributo = dados.getValue(Atributo.class);
                    atributo.setIdentificador(dados.getKey());
                    atributos.add(atributo);
                }

                adapter = new AdapterCustomizado(getApplicationContext(), atributos);
                listViewAtributos.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                listViewAtributos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(parent.getContext());
                        alertDialog.setCancelable(true);
                        alertDialog.setIcon(R.drawable.ic_aviso);
                        alertDialog.setTitle("Escolha uma opção");
                        alertDialog.setMessage("Você deseja EDITAR ou EXCLUIR?");

                        alertDialog.setPositiveButton("Excluir", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        atributoSelecionado = new Atributo();
                                        atributoSelecionado = (Atributo) parent.getAdapter().getItem(position);

                                        atributoSelecionado.excluir();
                                        adapter.notifyDataSetChanged();

                                        Toast.makeText(MainActivity.this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        alertDialog.setNegativeButton("Editar", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        atributoSelecionado = new Atributo();
                                        atributoSelecionado = (Atributo) parent.getAdapter().getItem(position);

                                        DatabaseReference atributosRefSelecionado = Configuracao.getDatabase().child(atributoSelecionado.getIdentificador());

                                        Intent intent = new Intent(parent.getContext(), EditarActivity.class);
                                        intent.putExtra("identificador", atributoSelecionado.getIdentificador());
                                        intent.putExtra("atributo1", atributoSelecionado.getAtributo1());
                                        intent.putExtra("atributo2", atributoSelecionado.getAtributo2());
                                        intent.putExtra("atributo3", atributoSelecionado.getAtributo3());
                                        startActivity(intent);

                                    }
                                });
                        alertDialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.create();
                        alertDialog.show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void pesquisar(View view){

        String termoBusca = editTextPesquisar.getText().toString().toLowerCase();

        List<Atributo> listaAtributosPesquisa = new ArrayList<>();

        for (Atributo atributo : atributos){

            String atributo1 = atributo.getAtributo1().toLowerCase();
            String atributo2 = atributo.getAtributo2().toLowerCase();
            String atributo3 = atributo.getAtributo3().toLowerCase();

            if (atributo1.contains(termoBusca) || atributo2.contains(termoBusca) || atributo3.contains(termoBusca)){
                listaAtributosPesquisa.add(atributo);
            }
        }
        adapter = new AdapterCustomizado(getApplicationContext(), (ArrayList<Atributo>) listaAtributosPesquisa);
        listViewAtributos.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        ler(buttonRecuperar);
        super.onResume();
    }

    @Override
    protected void onStop() {
        atributosRef.removeEventListener(valueEventListenerAtributo);
        super.onStop();
    }
}
