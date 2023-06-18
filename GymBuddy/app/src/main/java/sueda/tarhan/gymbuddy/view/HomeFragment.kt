package sueda.tarhan.gymbuddy.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import sueda.tarhan.gymbuddy.R
import sueda.tarhan.gymbuddy.adapters.HomeRecyclerAdapter
import sueda.tarhan.gymbuddy.databinding.FragmentHomeBinding
import sueda.tarhan.gymbuddy.model.Fav
import sueda.tarhan.gymbuddy.model.Post

//class HomeFragment : Fragment()
class HomeFragment : Fragment(R.layout.fragment_home)/*, HomeRecyclerAdapter.OnPostClickListener*/ {
    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

    var postListesi = ArrayList<Post>()
    ////////// private var likeDBRef = Firebase.firestore.collection("LikedProducts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        verileriAl()

        //homeRecyclerAdapter = HomeRecyclerAdapter(postListesi)


        /*val databaseRef = FirebaseDatabase.getInstance().reference
        val postsRef = databaseRef.child("Post")

        postsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val postId = postSnapshot.key // Retrieve the postId

                    // Use the postId as needed (e.g., assign it to the `postRef`)
                    val postRef = postId?.let { postsRef.child(it) }

                    // Continue with your logic here, such as using the postRef

                    // Example: Call a method and pass the postId
                    postId?.let {
                        doSomethingWithPostId(it)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        })*/
    }
    @SuppressLint("NotifyDataSetChanged")
    fun verileriAl(){

        database.collection("Post").orderBy("oluşturmatarihi", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            } else {
                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val documents = snapshot.documents

                        postListesi.clear()

                        for (document in documents) {
                            val kullaniciEmail = document.get("kullaniciemail") as String
                            val kullaniciKategori = document.get("kategori") as String
                            val kullaniciTarih = document.get("tarih") as String
                            val kullaniciSaat = document.get("saat") as String
                            val kullaniciKonum = document.get("konum") as String
                            val kullaniciKapasite = document.get("kapasite") as String

                            val indirilenPost =
                                Post(
                                    kullaniciEmail,
                                    kullaniciKategori,
                                    kullaniciTarih,
                                    kullaniciSaat,
                                    kullaniciKonum,
                                    kullaniciKapasite
                                ///// , id.toString()
                                )
                            postListesi.add(indirilenPost)
                        }

                        homeRecyclerAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val recyclerView: RecyclerView = binding.root.findViewById(R.id.homeRecyclerView)
        val recyclerView: RecyclerView = binding.homeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.adapter = homeRecyclerAdapter

        homeRecyclerAdapter = HomeRecyclerAdapter(postListesi)
        recyclerView.adapter = homeRecyclerAdapter
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_activity_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_activity -> {
                    findNavController().navigate(R.id.action_homeFragment_to_createActivityFragment)
                    return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
/*

    fun onClickLike(item: Post) {

        likeDBRef
            .add(Fav(item.id!! , auth.currentUser!!.uid ,item.kullaniciEmail , item.kullaniciKategori , item.kullaniciTarih , item.kullaniciSaat ,item.kullaniciKonum))
            .addOnSuccessListener {
                Toast.makeText(requireContext(),"Added to Liked Items" ,Toast.LENGTH_LONG).show()
                /////////requireActivity().toast("Added to Liked Items")
            }
            .addOnFailureListener {
                ///////requireActivity().toast("Failed to Add to Liked")
                Toast.makeText(requireContext(),"Failed to Add to Liked" ,Toast.LENGTH_LONG).show()
            }

    }
*/
    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
    /*fun sendPostToFavorites(post: Post?) {
        val action = HomeFragmentDirections.actionHomeFragmentToFavoritesFragment(post)
        findNavController().navigate(action)
    }*/

    /*fun doSomethingWithPostId(postId: String?) {
        postId?.let {
            // Do something with the postId
            // You can assign it to a variable or use it in your logic
            // For example:
            // val myPostId = postId
            // Or pass it to another method, etc.
        }
    }
    override fun onPostClick(post: Post) {
        sendPostToFavorites(post)
    }

    fun sendPostToFavorites(post: Post) {
        val action = HomeFragmentDirections.actionHomeFragmentToFavoritesFragment(post)
        findNavController().navigate(action)
    }*/
}