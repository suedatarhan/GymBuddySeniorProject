package sueda.tarhan.gymbuddy.data

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class FavoritesRecyclerRowBinding {
    companion object {
        @BindingAdapter("addToFavFromHome")
        @JvmStatic
        fun setAddToFavFromHome(view: View, addToFavListener: View.OnClickListener) {
            view.setOnClickListener(addToFavListener)
        }
    }
}
