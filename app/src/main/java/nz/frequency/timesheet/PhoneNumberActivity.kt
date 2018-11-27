package nz.frequency.timesheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_phone_number.*


class PhoneNumberActivity : AppCompatActivity() {

    //Azure functions !! Request and Response the day!! Pretty Solid!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        //set Title to Phone Number
        setTitle(R.string.phone_number)

        nextButton.setOnClickListener {
            nextButton.startAnimation()
        }

    }

    override fun onResume() {

        super.onResume()


    }



    override fun onBackPressed() {
        super.onBackPressed()
        //Important to just go straight to the home page and not go to the back stack.
    }



}
