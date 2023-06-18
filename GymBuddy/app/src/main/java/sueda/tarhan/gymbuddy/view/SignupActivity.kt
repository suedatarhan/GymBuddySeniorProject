package sueda.tarhan.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import sueda.tarhan.gymbuddy.R

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser
        if (guncelKullanici != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun kayitOl(view: View) {


        val email = findViewById<TextInputEditText>(R.id.etEmail).text.toString()
        val sifre = findViewById<TextInputEditText>(R.id.etPassword).text.toString()
        val username = findViewById<TextInputEditText>(R.id.etFullname).text.toString()

        auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener { task ->
            //asenkron
            if(task.isSuccessful) {
                //username günceller
                val guncelKullanici = auth.currentUser
                val profilGüncelleme= userProfileChangeRequest {
                    displayName= username
                }

                if (guncelKullanici != null){
                    guncelKullanici.updateProfile(profilGüncelleme).addOnCompleteListener { task ->
                        //asenkron
                        if(task.isSuccessful) {
                            Toast.makeText(applicationContext,"Hoşgeldin: ${username}" ,Toast.LENGTH_LONG).show()
                        }
                    }
                }


                //diğer aktiviteye gidelim
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }

    fun alreadyHaveAnAccount(view: View) {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
    }

}