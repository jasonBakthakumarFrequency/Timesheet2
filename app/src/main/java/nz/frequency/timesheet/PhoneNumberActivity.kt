package nz.frequency.timesheet


import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_phone_number.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.*



class PhoneNumberActivity : AppCompatActivity() {

    //Azure functions !! Request and Response the day!! Pretty Solid!!

    var client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        //set Title to Phone Number
        setTitle(R.string.phone_number)



    }

    override fun onResume() {
        super.onResume()

        nextButton.setOnClickListener {
            // nextButton.startAnimation()

            if(phoneNumberEditText.text.toString().length < 7){
                phoneNumberTextLayout.isErrorEnabled = true
                phoneNumberTextLayout.error = "Please enter a valid mobile number"
            }
            else {
                phoneNumberTextLayout.isErrorEnabled = false
                nextButton.startAnimation()
                makeNetworkCall(phoneNumberEditText.text.toString())


            }

        }

    }

    private fun makeNetworkCall(phonenumber: String) {


        doAsync {
            val phn : String = if(phonenumber.startsWith("0",true)){
                phonenumber.replaceFirst("0","+64")
            } else{
                "+64$phonenumber"
            }

            val urlBuilder = HttpUrl.parse("https://frequencytimesheetfunctions.azurewebsites.net/api/readUserData")?.newBuilder()
            urlBuilder?.addQueryParameter("code", "VyWAa62iWiPr7o3T1iKu4FDPsf6lKsVmdHb6fmA5yGX8IWDE36F3Cw==")
            urlBuilder?.addQueryParameter("phonenumber", phn)
            val url = urlBuilder?.build().toString()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            uiThread {

                val responseString = response.body()!!.string()

                when {
                    responseString == "\"This phone number does not exist in our records\"" -> {
                        //This means the phone number is not in the system
                        alert("Your mobile number is not registered in the system. Please check your mobile number again or contact your site engineer for further help") {
                            yesButton {}
                        }.show()

                        nextButton.revertAnimation { nextButton.text = "Try Again" }
                    }
                    responseString == "\"Please enter a phone number to process\"" -> {
                        alert("Please enter a phone number to process") {
                            yesButton {}
                        }.show()

                        nextButton.revertAnimation { nextButton.text = "Try Again" }
                    }
                    responseString.isNullOrBlank() || responseString.isNullOrEmpty() -> {

                        alert("Your access control has not been set up yet. Please contact your site engineer for further help") {
                            yesButton {}
                        }.show()

                        nextButton.revertAnimation { nextButton.text = "Try Again" }

                    }
                    else -> {
                        //Hey this seems like this is the right phone number
                        //val jsonArray = JSONArray(responseString)
                        longToast(responseString)
                        nextButton.doneLoadingAnimation(Color.WHITE, BitmapFactory.decodeResource(resources, R.drawable.ic_done_name))

                    }
                }



            }

            }
        }


    }



