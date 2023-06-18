package sueda.tarhan.gymbuddy.data

import android.view.View
import androidx.databinding.BindingAdapter

class HomeRecyclerRowBinding {

    companion object {
        @BindingAdapter("addToFavFromHome")
        @JvmStatic
        fun setAddToFavFromHome(view: View, addToFavListener: View.OnClickListener) {
            view.setOnClickListener(addToFavListener)
        }


        @BindingAdapter("onClick")
        @JvmStatic
        fun setOnClick(view: View, onClickListener: View.OnClickListener) {
            view.setOnClickListener(onClickListener)
        }
    }
}
