package sueda.tarhan.gymbuddy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sueda.tarhan.gymbuddy.model.Post
import sueda.tarhan.gymbuddy.databinding.HomeRecyclerRowBinding
/*interface OnPostClickListener {
    fun onPostClick(post: Post)
}*/
class HomeRecyclerAdapter(
    private val postList: ArrayList<Post>
                          //,private val onPostClickListener: OnPostClickListener
    //,homeFragment: HomeFragment
) :
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(private val binding: HomeRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val kullaniciEmail = binding.homeRecyclerRowKullaniciEmail
        val homeKategori = binding.homeKategori
        val homeTarih = binding.homeTarih
        val homeSaat = binding.homeSaat
        val homeKonum = binding.homeKonum
        val homeCapacity = binding.homeCapacity

        /*init {
            // Öğelerin tıklanma olayını ele almak için onClickListener ayarlanır
            binding.homeFav.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedPost =getItem(position)
                    // Tıklanan öğeyle ilgili işlemler burada yapılır
                    addToFavFromHome(clickedPost)
                    sendPostToFavorites(clickedPost)
                    onPostClickListener.onPostClick(clickedPost)
                }
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeRecyclerRowBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val post = getItem(position)

        holder.kullaniciEmail.text = post.kullaniciEmail
        holder.homeKategori.text = post.kullaniciKategori
        holder.homeTarih.text = post.kullaniciTarih
        holder.homeSaat.text = post.kullaniciSaat
        holder.homeKonum.text = post.kullaniciKonum
        holder.homeCapacity.text = post.kullaniciKapasite


    }
    private fun getItem(position: Int): Post {
        return postList[position]
    }

    private fun addToFavFromHome(post: Post) {}
        // Implement your addToFavFromHome functionality here
       // val favoritesList = getFavoritesFromDatabase()

        /*// Post'un zaten favorilere eklenmiş olup olmadığını kontrol edin
        if (favoritesList.contains(post)) {
            // Post zaten favorilere eklenmişse, favorilerden kaldırın
            favoritesList.remove(post)
        } else {
            // Post favorilere eklenmemişse, favorilere ekleyin
            favoritesList.add(post)
        }

        // Güncellenmiş favori listesini veritabanına kaydedin
        saveFavoritesToDatabase(favoritesList)
    }*/
}
//ileri seviye 4-18