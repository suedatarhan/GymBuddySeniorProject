package sueda.tarhan.gymbuddy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sueda.tarhan.gymbuddy.R
import sueda.tarhan.gymbuddy.adapters.SearchAdapter
import sueda.tarhan.gymbuddy.databinding.FragmentSearchBinding
import sueda.tarhan.gymbuddy.model.searchData


class SearchFragment : Fragment() {
    private var mList = ArrayList<searchData>()
    private lateinit var adapter: SearchAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDataToList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
/*home fragmenttan ekledim yukarıdaki tek satır yerine
        _binding = SearchHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view}*/


    }
    //home fragmenttan adapter bağlama işlemi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.searchRecyclerView)
        //searchView = view.findViewById(R.id.searchView)
        //val recyclerView: RecyclerView = binding.root.findViewById(R.id.homeRecyclerView)
        /*bu part1 val recyclerView: RecyclerView = binding.searchRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())*/
        //recyclerView.adapter = homeRecyclerAdapter

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = SearchAdapter(mList)
        recyclerView.adapter = adapter
    }

    private fun addDataToList() {
        mList.add(searchData("Sueda@gmail.com","Volleyball","21/06/2023"))
        mList.add(searchData("Feyza@gmail.com","Volleyball","21/06/2023"))
        mList.add(searchData("Sueda@gmail.com","Basketball","21/06/2023"))

    }
}