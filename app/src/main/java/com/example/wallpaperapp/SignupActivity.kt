package com.example.wallpaperapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    lateinit var btnSignup: Button
    lateinit var tvSignin: TextView

    lateinit var username: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etCheckbox:CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnSignup = findViewById(R.id.btn_signup)
        tvSignin = findViewById(R.id.signin)

        username = findViewById(R.id.username)
        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)
        etCheckbox = findViewById(R.id.checkbox);



        btnSignup.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        tvSignin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        btnSignup.setOnClickListener {
            //Recuperer les valeurs entrées
            val name = username.editableText.toString()
            val email = etEmail.editableText.toString()
            val password = etPassword.editableText.toString()
            if (validateInputs(email, password)) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(EMAIL_EXTRA, email) // on veut passer du data
                startActivity(intent)
            }
            if (!etCheckbox.isChecked()) {
                Toast.makeText(this, "Please confirm terms and conditions", Toast.LENGTH_LONG)
                    .show() //message de 3 sec
            }

            if (name.length == 0) {
                username.setError("Field should not be Empty")
            } else if (!name.matches(Regex("[a-zA-Z ]+"))) {
                username.setError("Enter Only Alphabetical Characters");
            }
        }
    }
    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            etEmail.error = getString(R.string.email_enter)
            return false
        }

        if (isEmailInvalid(email)) {
            showAlert(getString(R.string.email_invalid))
        }

        if (password.isEmpty()) {
            etPassword.error = getString(R.string.password_enter)
            return false
        }


        if (password.length < 6) {
            showAlert(getString(R.string.password_length_error))
        }

        return true
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
}