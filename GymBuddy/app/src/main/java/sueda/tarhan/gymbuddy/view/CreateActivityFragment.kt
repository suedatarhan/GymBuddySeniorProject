package sueda.tarhan.gymbuddy.view

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import sueda.tarhan.gymbuddy.R
import sueda.tarhan.gymbuddy.databinding.FragmentCreateActivityBinding




class CreateActivityFragment : Fragment() {
    private var _binding: FragmentCreateActivityBinding? = null
    private val binding get() = _binding!!
    private lateinit var storage : FirebaseStorage
    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
    }

        override fun onResume() {
        super.onResume()
        val category= resources.getStringArray(R.array.category)
        val arrayAdapter= ArrayAdapter(requireContext(), R.layout.dropdown_item,category)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateActivityBinding.inflate(inflater, container, false)

        binding.createActivityButton.setOnClickListener {
            createNewActivity()
            navigateToHomeFragment()
        }

        binding.closeCreateActivityButton.setOnClickListener {
            navigateToHomeFragment()
        }

        return binding.root
    }

    private fun createNewActivity() {

        val guncelKullaniciEmaili = auth.currentUser!!.email.toString()
        val kullaniciKategori = binding.autoCompleteTextView.text.toString()
        val kullaniciTarih = binding.createDate.text.toString()
        val kullaniciSaat = binding.createTime.text.toString()
        val kullaniciKonum = binding.createLocation.text.toString()
        val kullaniciKapasite = binding.createCapacity.text.toString()
        val tarih = Timestamp.now()

        if (kullaniciKapasite.isNotBlank()&&kullaniciKategori.isNotBlank()&&kullaniciKonum.isNotBlank()&&kullaniciTarih.isNotBlank()&&kullaniciSaat.isNotBlank()){
            val postHashMap = hashMapOf<String, Any>()
            postHashMap.put("kullaniciemail",guncelKullaniciEmaili)
            postHashMap.put("kategori",kullaniciKategori)
            postHashMap.put("tarih",kullaniciTarih)
            postHashMap.put("saat",kullaniciSaat)
            postHashMap.put("konum",kullaniciKonum)
            postHashMap.put("kapasite",kullaniciKapasite)
            postHashMap.put("oluşturmatarihi",tarih)

            database.collection("Post").add(postHashMap).addOnCompleteListener { task ->

            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }else {
            // Gerekli değerler girilmediğinde yapılacak işlem
            Toast.makeText(requireContext(),  "Please fill in all fields.", Toast.LENGTH_LONG).show()
        }
    }


    private fun navigateToHomeFragment() {
        val navController = findNavController()
        navController.popBackStack(R.id.homeFragment, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}