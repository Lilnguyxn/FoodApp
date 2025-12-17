package com.example.food36.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;

import com.example.food36.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ✅ ViewBinding đúng (chỉ setContentView 1 lần)
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.txtUser.getText().toString().trim();
                String password = binding.txtPassword.getText().toString().trim();

                if (password.length() < 6) {
                    Toast.makeText(SignUpActivity.this,
                            "Mật khẩu phải có ít nhất 6 ký tự",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // ✅ Firebase SignUp ĐÚNG
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this,
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.i(TAG, "Dang ki thanh cong");
                                            Toast.makeText(SignUpActivity.this,
                                                    "Đăng ký thành công",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignUpActivity.this,
                                                    "Lỗi: " + task.getException().getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
            }
        });
    }
}
