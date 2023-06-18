package sueda.tarhan.gymbuddy.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sueda.tarhan.gymbuddy.databinding.HomeRecyclerRowBinding
import sueda.tarhan.gymbuddy.model.Fav

class FavoritesRecyclerAdapter(private val list: ArrayList<Fav>,
                               private val productClickInterface: LikedProductOnClickInterface,
                               private val likeClickInterface: LikedOnClickInterface

): RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesViewHolder>() {
    inner class FavoritesViewHolder(val binding: HomeRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(HomeRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.homeKategori.text = "${currentItem.kullaniciKategori} "
        holder.binding.homeTarih.text = "₹${currentItem.kullaniciTarih}"
        holder.binding.homeSaat.text = "₹${currentItem.kullaniciSaat}"
        holder.binding.homeKonum.text = "₹${currentItem.kullaniciKonum}"
        holder.binding.homeRecyclerRowKullaniciEmail.text = "₹${currentItem.kullaniciEmail}"
        holder.binding.homeFav.backgroundTintList = ColorStateList.valueOf(Color.RED)


        holder.itemView.setOnClickListener {
            productClickInterface.onClickProduct(list[position])
        }

        holder.binding.homeFav.setOnClickListener {
            likeClickInterface.onClickLike(currentItem)
            holder.binding.homeFav.backgroundTintList = ColorStateList.valueOf(Color.WHITE)

            likeClickInterface.onClickLike(currentItem)
        }
    }
}
interface LikedProductOnClickInterface {
    fun onClickProduct(item: Fav)
}

interface LikedOnClickInterface{
    fun onClickLike(item: Fav)
}
