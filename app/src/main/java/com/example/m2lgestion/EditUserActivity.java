package com.example.m2lgestion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.m2lgestion.databinding.ActivityEditUserBinding;
import com.example.m2lgestion.entity.User;

public class EditUserActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityEditUserBinding binding;
    private EditText editTextNom, editTextPrenom , editTextEmail;
    private Button buttonSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editTextNom = findViewById(R.id.editTextEditNom);
        editTextPrenom = findViewById(R.id.editTextEditPrenom);
        editTextEmail = findViewById(R.id.editTextEditEmail);
        buttonSaveChanges = findViewById(R.id.buttonUpdate);

        // Récupérez l'ID de l'utilisateur ou l'objet User depuis l'Intent
        // Exemple avec un ID :
        User user = (User) getIntent().getSerializableExtra("user_id");
        editTextEmail.setText(user.getEmail());
        editTextNom.setText(user.getNom());}


    }