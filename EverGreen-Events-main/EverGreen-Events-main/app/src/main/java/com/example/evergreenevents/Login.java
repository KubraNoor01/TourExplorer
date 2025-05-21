package com.example.evergreenevents;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    Button btnLogin;
    ImageView ivGoogle;
    EditText etEmail, etPassword;
    TextView tvSignup, tvForgotPassword;
    FirebaseAuth auth;
    FirebaseUser user;
    GoogleSignInOptions option;
    GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        init();
        if (user != null) {
            startActivity(new Intent(Login.this, Dashboard.class));
            finish();
        }
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString();

                if (email.isEmpty()) {
                    etEmail.setError("Email cannot be empty");
                    return;
                }

                if (pass.isEmpty()) {
                    etPassword.setError("Password cannot be empty");
                    return;
                }
                ProgressDialog progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Verification in progress.....");
                progressDialog.show();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.dismiss();
                                startActivity(new Intent(Login.this, Dashboard.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder forgotPassword = new AlertDialog.Builder(Login.this);
                forgotPassword.setTitle("Forgot Password");
                EditText etRegEmail = new EditText(Login.this);
                etRegEmail.setHint("Enter your registered email");

                forgotPassword.setView(etRegEmail);

                forgotPassword.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String regEmail = etRegEmail.getText().toString().trim();
                        if (!TextUtils.isEmpty(regEmail)) {
                            ProgressDialog forgotDialog = new ProgressDialog(Login.this);
                            forgotDialog.setMessage("Sending password reset email...");
                            forgotDialog.show();

                            auth.sendPasswordResetEmail(regEmail)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            forgotDialog.dismiss();
                                            Toast.makeText(Login.this, "Email sent", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            forgotDialog.dismiss();
                                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });

                forgotPassword.show();

            }
        });

        // Google Sign-In click listener
        ivGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,12345);
                }
        });
    }

    private void init() {
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPass);
        ivGoogle = findViewById(R.id.ivGoogle); // Assuming this is your Google sign-in ImageView
        tvSignup = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        // Configure Google Sign-In
        option = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.Client_id)) // Replace with your web client ID
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, option);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    protected void onActivityResult(int requestCode, int ResultCode, Intent data) {
        super.onActivityResult(requestCode, ResultCode, data);
        if(requestCode == 12345)
        {
           Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

           try{
               GoogleSignInAccount account = task.getResult(ApiException.class);

               AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
               FirebaseAuth.getInstance().signInWithCredential(credential)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                   startActivity(intent);


                               }else{
                                   Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }catch (ApiException e){
               e.printStackTrace();
           }

        }
    }


   

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Signing in with Google...");
        progressDialog.show();

        FirebaseAuth.getInstance().signInWithCredential(GoogleAuthProvider.getCredential(acct.getIdToken(), null))
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        startActivity(new Intent(Login.this, Dashboard.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Google sign-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
