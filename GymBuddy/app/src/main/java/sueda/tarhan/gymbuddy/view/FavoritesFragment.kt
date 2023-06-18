package sueda.tarhan.gymbuddy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import sueda.tarhan.gymbuddy.R
import sueda.tarhan.gymbuddy.adapters.FavoritesRecyclerAdapter
import sueda.tarhan.gymbuddy.adapters.LikedOnClickInterface
import sueda.tarhan.gymbuddy.adapters.LikedProductOnClickInterface
import sueda.tarhan.gymbuddy.databinding.FragmentFavoritesBinding
import sueda.tarhan.gymbuddy.model.Fav


class FavoritesFragment : Fragment(R.layout.fragment_favorites),
    LikedProductOnClickInterface,
    LikedOnClickInterface {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: FavoritesRecyclerAdapter
    private lateinit var likedProductList: ArrayList<Fav>


    private var likeDBRef = Firebase.firestore.collection("LikedProducts")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        likedProductList = ArrayList()
        adapter = FavoritesRecyclerAdapter(likedProductList, this, this)

        val productLayoutManager = LinearLayoutManager(requireContext())
        binding.favRecyclerView.layoutManager = productLayoutManager
        binding.favRecyclerView.adapter = adapter


        displayLikedProducts()
    }

    fun Fragment.toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun displayLikedProducts() {

        likeDBRef
            .whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (item in querySnapshot) {
                    val likedProduct = item.toObject<Fav>()
                    likedProductList.add(likedProduct)
                    adapter.notifyDataSetChanged()
                }

            }
            .addOnFailureListener {
                toast(it.localizedMessage!!)
            }
    }

    override fun onClickProduct(item: Fav) {

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
}
