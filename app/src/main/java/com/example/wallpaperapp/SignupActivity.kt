package com.example.wallpaperapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_signup.setOnClickListener {
            val name = et_Username.editableText.toString()
            val email = et_Email.editableText.toString()
            val password = et_Password.editableText.toString()
            if (validateInputs(email, password)) {
                saveUserToFirebase(email, password)
            }

        }
        signin.setOnClickListener {
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            et_Email.error = getString(R.string.empty_mail)
            return false
        }
        if (password.isEmpty()) {
            et_Password.error = getString(R.string.empty_password)
            return false
        }
        if (isEmailInvalid(email)) {
            showAlert(getString(R.string.invalid_mail))
            return false
        }
        if (!checkPassword(password)) {
            showAlert(getString(R.string.invalid_password))
            return false
        }
        return true
    }

    fun checkPassword(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[!&{}¿?.<>~()^@+*/=;:#%\$])" +
                    ".{8,}" +
                    "$"
        )
        return passwordREGEX.matcher(password).matches()
    }

    private fun isEmailInvalid(email: String): Boolean {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showAlert(message: String) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(R.string.AlertTitle))
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(getString(R.string.AlertOKButton)) { dialog, i -> dialog.dismiss() }
        alertDialog.create().show()
    }

    private fun saveCredentialsToSharedPrefs(email: String, password: String) {
        val preference: SharedPreferences = getSharedPreferences(PACKAGE_NAMEE, Context.MODE_PRIVATE)
        preference.edit().putString(EMAILL, email).apply()
        preference.edit().putString(PASSWORDD, password).apply()
    }

    private fun saveUserToFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    goToLoginActivity()
                }
            }.addOnFailureListener {
                showAlert("failed To register")
            }
    }
}