package nz.frequency.timesheet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val info = packageManager.getPackageInfo(
                "nz.frequency.timesheet",
                android.content.pm.PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = java.security.MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                android.util.Log.d(
                    "KeyHash", "KeyHash:" + android.util.Base64.encodeToString(
                        md.digest(),
                        android.util.Base64.DEFAULT
                    )
                )

            }
        } catch (e: android.content.pm.PackageManager.NameNotFoundException) {

        } catch (e: java.security.NoSuchAlgorithmException) {

        }


        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()



    }
}