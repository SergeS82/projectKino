package ru.anudx.project_kino

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.size
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import ru.anudx.project_kino.databinding.ScrollBasedActivityBinding

class ScrollBasedActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ScrollBasedActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        val snackbar = Snackbar.make(b.root, "Snackbar!", Snackbar.LENGTH_SHORT)
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.teal_200))
        snackbar.setAction("Action"){
            Toast.makeText(this, "Toast!", Toast.LENGTH_SHORT).show()
        }
        val bottomSheetBehavior = BottomSheetBehavior.from(b.bottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                //BottomSheetBehavior.STATE_COLLAPSED //BottomSheet раскрыт только на peekHeight
                //BottomSheetBehavior.STATE_DRAGGING //BottomSheet в движении
                //BottomSheetBehavior.STATE_EXPANDED //BottomSheet раскрыт
                //BottomSheetBehavior.STATE_HIDDEN //BottomSheet скрыт
                //BottomSheetBehavior.STATE_SETTLING //BottomSheet автоматически закрывается/раскрывается после того, как вы потянули и отпустили
                //BottomSheetBehavior.STATE_HALF_EXPANDED //BottomSheet раскрыт наполовину
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset > 0 && slideOffset < 1) {
                    b.fab2.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).alpha(1-slideOffset).start()
                    //b.fab2.scaleX = 1 - slideOffset
                    //b.fab2.scaleY = 1 - slideOffset
                }
                b.tintBack.alpha = slideOffset
            }
        })
        b.fab2.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            if (bottomSheetBehavior.peekHeight > (70*3.5).toInt())
                bottomSheetBehavior.setPeekHeight((70*3.5).toInt())
            else
                bottomSheetBehavior.setPeekHeight((70*3.5*3).toInt())

            //bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED//BottomSheet свернут и есть "язычок"
            //bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED//BottomSheet развернут
            //bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN//BottomSheet скрыт на экране
//            when (snackbar.isShown){
//                true -> snackbar.dismiss()
//                else -> snackbar.show()
//            }
        }
        //b.toolbarLayout.title = "SkillFactory !!!"
//        b.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
//            if (verticalOffset == 0){
//                b.toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.black))
//            }
//            else if (Math.abs(verticalOffset) >= appBarLayout.scrollBarSize ){
//                b.toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.purple_500))
//            }
//            b.toolbarLayout.title = verticalOffset.toString()
//        })

    }
}