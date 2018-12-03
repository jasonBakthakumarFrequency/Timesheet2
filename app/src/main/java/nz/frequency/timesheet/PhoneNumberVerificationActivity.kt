package nz.frequency.timesheet

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_phone_number_verification.*
import nz.frequency.timesheet.util.Constants



class PhoneNumberVerificationActivity : AppCompatActivity() {


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
            //Shifting over from Twilio to Sinch because of easier integration. Quite useful and also better integration.

        }
        else{

            //Using Sinch from Now

        }


    }




    override fun onBackPressed() {

        //Makes them go back to the home page
        val mainActivity = Intent(Intent.ACTION_MAIN)
        mainActivity.addCategory(Intent.CATEGORY_HOME)
        mainActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(mainActivity)




    }



}
