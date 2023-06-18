/*package sueda.tarhan.gymbuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


import sueda.tarhan.gymbuddy.R
import sueda.tarhan.gymbuddy.adapters.FavoritesRecyclerAdapter
//import sueda.tarhan.gymbuddy.adapters.LikedOnClickInterface
import sueda.tarhan.gymbuddy.model.Fav


class MainActivity : AppCompatActivity(), FavoritesRecyclerAdapter.LikedOnClickInterface {
    private lateinit var likedProductList: ArrayList<Fav>
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: FavoritesRecyclerAdapter
    private var likeDBRef = Firebase.firestore.collection("LikedProducts")

    //lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setupWithNavController(navController)
        //recyclerView = findViewById(R.id.activitiesRecyclerView)


    }
    fun AppCompatActivity.toast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClickLike(item: Fav) {
        likeDBRef
            .whereEqualTo("uid", auth.currentUser!!.uid)
            .whereEqualTo("pid", item.pid)
            .get()
            .addOnSuccessListener { querySnapshot ->

                for (item in querySnapshot) {
                    likeDBRef.document(item.id).delete()
                    likedProductList.remove(item.toObject<Fav>())
                    adapter.notifyDataSetChanged()
                    toast("Removed From the Liked Items")
                }

            }
            .addOnFailureListener {
                toast("Failed To Remove From Liked Items")
            }
    }

}*/
package sueda.tarhan.gymbuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


import sueda.tarhan.gymbuddy.R


class MainActivity : AppCompatActivity() {

    //lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setupWithNavController(navController)
        //recyclerView = findViewById(R.id.activitiesRecyclerView)


    }

}