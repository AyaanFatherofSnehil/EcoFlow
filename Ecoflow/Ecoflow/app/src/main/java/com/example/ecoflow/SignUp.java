package com.example.ecoflow;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLogInBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field Cannot be Empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regName.getEditText().getText().toString();
        String noWhiteSpace = "(?=\\s+$)";

        if (val.isEmpty()) {
            regName.setError("Field Cannot be Empty");
            return false;
        }

        else if (val.length() >= 15) {
            regName.setError("Username too long");
            return false;
        }

        else if (!val.matches(noWhiteSpace)) {
            regName.setError("White spaces aren't allowed");
            return false;
        }

        else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regName.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regName.setError("Field Cannot be Empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regName.setError("Invalid email address");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field Cannot be Empty");
            return false;
        }

        else if (val.length() > 10) {
            regName.setError("Invalid Phone Number");
            return false;
        }

        else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\s+$)" +
                ".{4,}" +
                "$";

        if (val.isEmpty()) {
            regName.setError("Field Cannot be Empty");
            return false;
        }

        else if (!val.matches(passwordVal)) {
            regName.setError("Please choose a strong Password");
            return false;
        }

        else {
            regName.setError(null);
            return true;
        }
    }

    public void registerUser(View view) {

        if (!validateName() | !validateUsername() | !validateEmail() | !validatePassword() | !validatePhoneNo()) {
            return;
        }

        //Get all the values in String
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);
    }
}