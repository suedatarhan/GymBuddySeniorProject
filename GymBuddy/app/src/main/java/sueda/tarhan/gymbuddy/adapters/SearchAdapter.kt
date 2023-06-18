package sueda.tarhan.gymbuddy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sueda.tarhan.gymbuddy.R
import sueda.tarhan.gymbuddy.model.searchData

class SearchAdapter(var mList: List<searchData>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Email: TextView = itemView.findViewById(R.id.search_recycler_row_kullanici_email)
        val Kategori: TextView = itemView.findViewById(R.id.search_kategori)
        val Tarih: TextView = itemView.findViewById(R.id.search_tarih)
        /*val Kategori = binding.homeKategori
        val Tarih = binding.homeTarih
        val Saat = binding.homeSaat
        val Konum = binding.homeKonum
        val Capacity = binding.homeCapacity*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        /*val inflater = LayoutInflater.from(parent.context)
        val binding = HomeRecyclerRowBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)*/
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_recycler_row , parent , false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.Email.text = mList[position].kullaniciEmail
        holder.Kategori.text = mList[position].kullaniciKategori
        holder.Tarih.text = mList[position].kullaniciTarih
        /*holder.Saat.text = mList[position].kullaniciSaat
        holder.Konum.text = mList[position].kullaniciKonum
        holder.Capacity.text = mList[position].kullaniciKapasite*/
    }
}