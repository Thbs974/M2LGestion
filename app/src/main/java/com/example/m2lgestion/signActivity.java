package com.example.m2lgestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.m2lgestion.dao.UserDao;
import com.example.m2lgestion.entity.User;

public class signActivity extends AppCompatActivity {

    private EditText  emailEditText ;
    private EditText passwordEditText ;
    private Button loginButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        emailEditText  = findViewById(R.id.email) ;
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button) ;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

    private void login() {
        String email = emailEditText.getText().toString().trim(); // Utilisez trim() pour supprimer les espaces avant et après.
        String password = passwordEditText.getText().toString().trim();

// Vérifier si les champs ne sont pas vides
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs.",
                    Toast.LENGTH_SHORT).show();
            return; // Arrêtez la méthode ici si les champs sont vides
        }
        User user = UserDao.findUserByEmailAndPassword(email, password);

        if (user != null) {
            // L'utilisateur existe, redirection vers une autre activité
            Intent intent = new Intent(signActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            // Termine MainActivity pour empêcher le retour à l';écran de connexion
        } else {
            Toast.makeText(this, "Informations d'identification invalides.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}