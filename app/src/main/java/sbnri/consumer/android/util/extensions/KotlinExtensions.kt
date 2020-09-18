package sbnri.consumer.android.util.extensions

import android.graphics.PorterDuff
import android.widget.Button
import sbnri.consumer.android.R


fun Button.disable()
{
    getBackground().setColorFilter(resources.getColor(R.color.semitransparent), PorterDuff.Mode.MULTIPLY)
    alpha = 0.5f
    setClickable(false)
}


fun Button.enable()
{
        alpha = 1f
        getBackground().setColorFilter(resources.getColor(R.color.dodgerBlue), PorterDuff.Mode.MULTIPLY)
        setClickable(true)
    }

fun Any.isNullTrue(obj:Any?):Boolean
{
    return obj == null
}