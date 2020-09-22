package sbnri.consumer.android.onboarding

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.PagerAdapter
import sbnri.consumer.android.R

class OnBoardingViewPagerAdapter constructor(context: Context,mOnBoardingItem: MutableList<OnBoardingItem>)  : PagerAdapter() {

    val context : Context = context
    val mOnBoardingItem : MutableList<OnBoardingItem> = mOnBoardingItem



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen = inflater.inflate(R.layout.onboarding_info, null)

        val title = layoutScreen.findViewById<TextView>(R.id.intro_title)
        val description = layoutScreen.findViewById<TextView>(R.id.intro_description)

        //setItemOneLayoutDesign(position,title,description)
        title.setText(mOnBoardingItem.get(position).title)
        description.setText(mOnBoardingItem.get(position).subTitle)

        container.addView(layoutScreen)

        return layoutScreen
    }

    private fun setItemOneLayoutDesign(position: Int, title: TextView?, description: TextView?) {

        when(position)
        {
            0 -> {title?.textSize = context.resources.getDimension(R.dimen.font_14sp)
                val typeface = ResourcesCompat.getFont(context, R.font.medium)
                title?.setTypeface(typeface)
            description?.textSize = context.resources.getDimension(R.dimen.font_24sp)
                description?.setTypeface(description?.typeface,Typeface.BOLD)}


        }

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mOnBoardingItem.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }
}