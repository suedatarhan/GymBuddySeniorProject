package sueda.tarhan.gymbuddy.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import sueda.tarhan.gymbuddy.R

class SigninActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser
        if (guncelKullanici != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun girisYap(view: View) {
        val email = findViewById<TextInputEditText>(R.id.etEmail).text.toString()
        val sifre = findViewById<TextInputEditText>(R.id.etPassword).text.toString()


        auth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                val guncelKullanici = auth.currentUser?.email.toString()
                //val guncelKullaniciId = auth.currentUser?.uid.toString()
                Toast.makeText(this,"Hoşgeldin: ${guncelKullanici}",Toast.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()}
    }

    fun dontHaveAnAccount(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}