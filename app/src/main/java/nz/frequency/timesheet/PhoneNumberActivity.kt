package nz.frequency.timesheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class PhoneNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        //set Title to Phone Number
        setTitle(R.string.phone_number)

    }


    override fun onBackPressed() {
        super.onBackPressed()
        //Important to just go straight to the home page and not go to the back stack.
    }

}
