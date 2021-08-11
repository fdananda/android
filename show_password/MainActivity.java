package com.fdananda.gitshowpassword;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editText_Password;
    private ImageView imageView_show;
    private boolean isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_Password = findViewById(R.id.editText_password);
        imageView_show    = findViewById(R.id.imageView);
        imageView_show.setImageResource(R.drawable.ic_view);
        isVisible = false;

        imageView_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_Password.getText().toString().equals("")){
                    if (!isVisible){
                        isVisible = true;
                        imageView_show.setImageResource(R.drawable.ic_hide);
                        editText_Password.setTransformationMethod(null);
                    }else {
                        isVisible = false;
                        imageView_show.setImageResource(R.drawable.ic_view);
                        editText_Password.setTransformationMethod(new PasswordTransformationMethod());
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Campo SENHA está vazio!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
