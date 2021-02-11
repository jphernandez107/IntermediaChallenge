package com.jphernandez.intermediachallenge.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.jphernandez.intermediachallenge.R

class FirebaseUIActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_ui)

        auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null) {
            startNextActivity()     //Signed In -> nos vamos al activity siguiente
        } else {
            createSignInIntent()
        }
    }

    private fun createSignInIntent() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build()
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.Theme_IntermediaChallenge)
                .build(),
            RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                startNextActivity() // Iniciamos el activity siguiente porque estamos logueados
            } else {
                if (response == null) {
                Snackbar.make(findViewById(R.id.activity_firebase_ui), "Inicio de sesión cancelado, vuelva a intentarlo", Snackbar.LENGTH_LONG)
                    return
                }

                if (response.error?.errorCode == ErrorCodes.NO_NETWORK) {
                    Snackbar.make(findViewById(R.id.activity_firebase_ui), "Sin conexion a internet", Snackbar.LENGTH_LONG)
                    return
                }
                Snackbar.make(findViewById(R.id.activity_firebase_ui), "Ocurrio un error, por favor vuelva a intentarlo", Snackbar.LENGTH_LONG)
                Log.e("FirebaseUIActivity", "Sign-in error: ", response.error)
            }
        }
    }

    private fun startNextActivity(){
        startActivity(Intent(this, MainActivity::class.java)) // Iniciamos el activity siguiente porque estamos logueados
        finish()
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }
}