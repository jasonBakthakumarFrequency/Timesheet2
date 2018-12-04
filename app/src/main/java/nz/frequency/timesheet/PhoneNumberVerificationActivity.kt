package nz.frequency.timesheet

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sinch.verification.*
import kotlinx.android.synthetic.main.activity_phone_number_verification.*
import nz.frequency.timesheet.util.Constants
import com.sinch.verification.VerificationListener
import com.sinch.verification.SinchVerification
import com.sinch.verification.Verification

class PhoneNumberVerificationActivity : AppCompatActivity() {

    lateinit var verification : Verification


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number_verification)
        setTitle(R.string.verification)
        val preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_KEY_FOR_THE_APP, Context.MODE_PRIVATE)
        val phoneNumber = preferences.getString(Constants.USER_PHONE, "missing")
        if( phoneNumber.isNullOrEmpty() && phoneNumber.isNullOrBlank() && phoneNumber.equals("missing",true)){
            //The phone number is not present a fatal error has occurred.
            progressBar.visibility = View.INVISIBLE
            loadingTextView.setText(R.string.fatal_error)
        }
        else{
            //This Sinch thing is working. And it is working pretty well. Cheers.. Just go ahead and build this thing. Its all about running scripts I guess.
            val config = SinchVerification.config().applicationKey(Constants.SINCH_APPLICATION_KEY).context(this).build()
            val listener = CustomVerificationListener()
            verification = SinchVerification.createSmsVerification(config, "+642040782348", listener)
            verification.initiate()

        }
    }


    override fun onBackPressed() {

        //Makes them go back to the home page
        val mainActivity = Intent(Intent.ACTION_MAIN)
        mainActivity.addCategory(Intent.CATEGORY_HOME)
        mainActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(mainActivity)

    }


    class CustomVerificationListener : VerificationListener {


        override fun onInitiated(p0: InitiationResult?) {
            //This holds the initiation result
            Log.d("Zapak", "Inside onInitiated: " + p0.toString())

        }

        override fun onVerificationFallback() {
            //This is for verification fallback.
            Log.d("Zapak", "Inside on Verification CallBack")
        }

        override fun onInitiationFailed(e: Exception) {
            when (e) {
                is InvalidInputException -> {
                    // Incorrect number provided
                    //Change the format of the number and send again.
                    //This  must work. If not I'm getting sick of this
                    Log.d("Zapak", "Inside on Invalid Input Exception: " + e.message)


                }
                is ServiceErrorException -> {
                    // Sinch service error
                    //This is quite bad. Please resend the verification message. Also enable retry button.
                    Log.d("Zapak", "Inside on Service Error Exception: " + e.message)


                }
                else -> {
                    // Other system error, such as UnknownHostException in case of network error
                    //Re initiate the verification and just go with it.
                    Log.d("Zapak", "Inside Just a bad exception: " + e.message)

                }
            }
        }

        override fun onVerified() {
            // Verification successful
            // Just write that it is verified in the system and also create a better feedback!
            Log.d("Zapak", "Apparently this is already verified")

        }

        override fun onVerificationFailed(e: Exception) {
            when (e) {
                is InvalidInputException -> {
                    // Incorrect number or code provided
                    //Again do the same thing that you did in the initiation failed one
                    //Got to have that Super Bass.
                    //It appears that we are not going to have to build this one out.
                    Log.d("Zapak", "Inside on Invalid Input Exception: " + e.message)


                }
                is CodeInterceptionException -> {
                    // Intercepting the verification code automatically failed, input the code manually with verify()
                    //Show the verification box along with the button
                    //That is not the right information to be working on.
                    Log.d("Zapak", "Inside Code Intercept Exception: " + e.message)

                }
                is IncorrectCodeException -> {
                    // The verification code provided was incorrect
                    //  Show that the code provided is wrong. Just plain wrong
                    Log.d("Zapak", "Inside Incoorect code Exception: " + e.message)

                }
                is ServiceErrorException -> {
                    // Sinch service error
                    //This is a service error. Change the text to show that
                    Log.d("Zapak", "Inside Service Error Exception: " + e.message)

                }
                else -> {
                    // Other system error, such as UnknownHostException in case of network error
                    // This is a network error. Please show that and also update the text view.
                    Log.d("Zapak", "This is a network error " + e.message)

                }
            }
        }

    }

}
