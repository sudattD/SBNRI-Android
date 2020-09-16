package sbnri.consumer.android.bottomsheetDialoguesFrags

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import butterknife.ButterKnife
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import sbnri.consumer.android.R
import sbnri.consumer.android.onboarding.UserEmail.UserEmailContract
import sbnri.consumer.android.splash.SplashContract

class UserEmailBottomSheetFragment : BottomSheetDialogFragment(),UserEmailContract.View
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.user_email_bottomsheet,container,false)
        ButterKnife.bind(this,view);
        return view
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialog1: DialogInterface ->
            val d = dialog1 as BottomSheetDialog
            val bottomSheet = d.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            if (bottomSheet != null) {
                BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun showToastMessage(toastMessage: String?, isErrortoast: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun accessTokenExpired() {
        TODO("Not yet implemented")
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }
}