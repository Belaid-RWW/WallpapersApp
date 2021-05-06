package com.example.wallpaperapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_photos.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {

            val email = et_Email.editableText.toString()
            val password = et_Password.editableText.toString()

            if (validateInputs(email, password)) {
                firebaseLogin(email, password)

                val intent2 = Intent(this,PhotosActivity::class.java)
                intent2.putExtra(EMAILL, email)
            }
        }
        sign_up.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    private fun goToPhotosActivity(email: String) {
        val intent = Intent(this, PhotosActivity::class.java)
        intent.putExtra(EMAILL, email)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        et_Email.text = null
        et_Password.text = null
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            et_Email.error = getString(R.string.email_enter)
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

    private fun isEmailInvalid(email: String): Boolean {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //message d'alerte
    private fun showAlert(message: String){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(R.string.AlertTitle))
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(getString(R.string.AlertOKButton)) { dialog, i-> dialog.dismiss()}
        alertDialog.create().show()
    }
    fun checkPassword(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[!&{}¿?.<>~()^@+*/=;:#%\$])" +    //at least 1 special character
                    ".{8,}" +               //at least 8 characters
                    "$"
        )
        return passwordREGEX.matcher(password).matches()
    }

    private fun getCredentialsFromSharedPrefs(): Pair<String?, String?> {
        val preference: SharedPreferences = getSharedPreferences(PACKAGE_NAMEE, Context.MODE_PRIVATE)
        val email = preference.getString(EMAILL, null)
        val password = preference.getString(PASSWORDD, null)
        return Pair(email, password)
    }


    private fun firebaseLogin(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                goToPhotosActivity(email)
            }
            .addOnFailureListener {
                showAlert(getString(R.string.verify_credentials))
            }
    }

}