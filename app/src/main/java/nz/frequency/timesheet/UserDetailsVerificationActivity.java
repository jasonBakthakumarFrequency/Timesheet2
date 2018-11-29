package nz.frequency.timesheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserDetailsVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_verification);
    }


    @Override
    public void onBackPressed() {
        Intent goHomeIntent = new Intent(Intent.ACTION_MAIN);
        goHomeIntent.addCategory(Intent.CATEGORY_HOME);
        goHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(goHomeIntent);
    }
}
