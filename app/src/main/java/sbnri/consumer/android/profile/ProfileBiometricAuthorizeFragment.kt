package sbnri.consumer.android.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import butterknife.BindView
import butterknife.OnClick
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.base.activity.BaseFragment
import sbnri.consumer.android.base.contract.BaseView
import java.util.concurrent.Executor

class ProfileBiometricAuthorizeFragment : BaseFragment()
{

    @BindView(R.id.btnAuthorize)
    lateinit var btnAuthorize : Button

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    companion object
    {
        fun newInstance(): ProfileBiometricAuthorizeFragment? {
            val fragment = ProfileBiometricAuthorizeFragment()
            val args = Bundle()
            //args.putString(Constants.FLOW, flow);
            fragment.arguments = args
            return fragment
        }

    }

    override fun onResume() {
        super.onResume()
        (getActivity() as ProfileCompletionActivity?)!!.setToolBarTitle("Set your Fingerprint")
    }

    override fun callDependencyInjector(injectorComponent: DependencyInjectorComponent?) {
        injectorComponent?.injectDependencies(this)
    }

    override fun onCreateKnifeView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_fingerprint_info, container, false)
    }

    override fun getBaseView(): BaseView? {
       return null
    }


    @OnClick(R.id.btnAuthorize)
    fun authorizeClick()
    {
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int,
                                                       errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(context,
                                "Authentication error: $errString", Toast.LENGTH_SHORT)
                                .show()
                    }

                    override fun onAuthenticationSucceeded(
                            result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        Toast.makeText(context,
                                "Authentication succeeded!", Toast.LENGTH_SHORT)
                                .show()
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(context, "Authentication failed",
                                Toast.LENGTH_SHORT)
                                .show()
                    }
                })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.

            biometricPrompt.authenticate(promptInfo)

    }

}