package nz.frequency.timesheet;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nz.frequency.timesheet.util.Constants;
import nz.frequency.timesheet.util.JobDetails;
import nz.frequency.timesheet.util.UserDetails;
import nz.frequency.timesheet.util.UserDetailsRVContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsVerificationActivity extends AppCompatActivity {


    //get your settings back

    RecyclerView detailsRV;
    TextView helloUserTextView;
    Button sendVerificationButton;
    TextView verificationPromptTextView;
    public static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 5;
    public static final int MY_PERMISSIONS_REQUEST_READ_SMS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_verification);
        setTitle(R.string.user_details);
        detailsRV = (RecyclerView) findViewById(R.id.detailsRV);
        helloUserTextView = (TextView) findViewById(R.id.hello_user_text);
        sendVerificationButton = (Button) findViewById(R.id.button);
        verificationPromptTextView = (TextView) findViewById(R.id.textView5);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = this.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY_FOR_THE_APP,Context.MODE_PRIVATE);
        String responseData = preferences.getString(Constants.PHONE_NUMBER_RESPONSE_DATA, "missing");
        if (responseData != null && (!responseData.equalsIgnoreCase("missing"))) {
             //Removing the " " in the beginning and the end of the response data. Also removing escape characters to process data
             responseData = responseData.substring(1, responseData.length() - 1);
             responseData = responseData.replace("\\","");
             //Creating the new GSON Object. And getting the Object List
             Gson gson = new Gson();
             Type userListType = new TypeToken<ArrayList<UserDetails>>(){}.getType();
             List<UserDetails> userDetailsList = gson.fromJson(responseData, userListType);

             //Storing the USer ID in Shared Preferences
             SharedPreferences.Editor editor = preferences.edit();
             editor.putString(Constants.USER_ID, userDetailsList.get(0).getUserID());
             editor.putString(Constants.USER_PHONE, userDetailsList.get(0).getPhoneNumber());
             editor.apply();

             //The User Name is also updated and all.
             helloUserTextView.setText(String.format("Hello %s", userDetailsList.get(0).getUserName()));

             UserDetailsRVAdapter adapter = new UserDetailsRVAdapter(parseObtainedListInOrderAndSend(userDetailsList));
            // Attach the adapter to the Recycler View to populate items
             detailsRV.setAdapter(adapter);
            // Set layout manager to position the items
             detailsRV.setLayoutManager(new LinearLayoutManager(this));
             //Retrofit might not be the best option.


        }

        else{
            detailsRV.setVisibility(View.GONE);
            sendVerificationButton.setVisibility(View.GONE);
            verificationPromptTextView.setText(R.string.verification_fatal_failure);
            //Cheers we've done the right thing

        }



        sendVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForSMSPermission();

            }
        });


    }

    private void checkForSMSPermission() {


        if (ContextCompat.checkSelfPermission(UserDetailsVerificationActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(UserDetailsVerificationActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            //This is where we don't have the permission and show rationale before jumping the gun.

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.sms_description_dialog)
                    .setTitle(R.string.sms_title_dialog);
            // Add the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    //Check if the permission is already granted
                    if (ContextCompat.checkSelfPermission(UserDetailsVerificationActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(UserDetailsVerificationActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted
                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(UserDetailsVerificationActivity.this,
                                Manifest.permission.READ_SMS)) {

                            ActivityCompat.requestPermissions(UserDetailsVerificationActivity.this,
                                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                                    MY_PERMISSIONS_REQUEST_READ_SMS);
                        } else {
                            // No explanation needed; request the permission
                            ActivityCompat.requestPermissions(UserDetailsVerificationActivity.this,
                                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                                    MY_PERMISSIONS_REQUEST_READ_SMS);
                            // MY_PERMISSIONS_REQUEST_FINE_LOCATION is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }  //Else the permission has been granted. Do nothing! :)
                }
            });
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();

        }

        else{
            //We've got the permission already. Probably because people using an older phone (with API lower than 24). So just take it for granted and move on to the next activity
            Intent intent = new Intent(UserDetailsVerificationActivity.this, PhoneNumberVerificationActivity.class);
            startActivity(intent);
            finish();
        }


    }


    //The method that parses the obtained user list and create a much better UI experience
    private List<Object> parseObtainedListInOrderAndSend(List<UserDetails> userDetailsList) {
        //The order in which data is loaded onto theListOfObjects is quite important as that is the order in which they will be displayed. We are presorting things here.
        List<Object> theListOfObjects = new ArrayList<Object>();
        List<String> projectlist = new ArrayList<String>();
        List<String> contractorList = new ArrayList<String>();
        List<JobDetails> jobDetails = new ArrayList<JobDetails>();
        for (UserDetails userDetail: userDetailsList) {
            projectlist.add(userDetail.getProjectName());
            contractorList.add(userDetail.getContractorName());
            jobDetails.add(new JobDetails(userDetail.getJobName(),userDetail.getJobDescription()));
        }
        //Configuring the heading
        theListOfObjects.add("Project");
        //Getting the content. Order is quite important
        for (String project : projectlist){
            theListOfObjects.add(new UserDetailsRVContent(project));
        }

        //Actually commenting code makes things better
        theListOfObjects.add("Contractor");
        //Getting the content.


        for (String contractor : contractorList){
            theListOfObjects.add(new UserDetailsRVContent(contractor));
        }
        //Configuring the heading
        theListOfObjects.add("Jobs Assigned");
        //Getting the content.
        theListOfObjects.addAll(jobDetails);

        return theListOfObjects;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                // If request is cancelled, the result arrays are empty.
                Intent intent = new Intent(UserDetailsVerificationActivity.this, PhoneNumberVerificationActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }








    @Override
    public void onBackPressed() {
        Intent goHomeIntent = new Intent(Intent.ACTION_MAIN);
        goHomeIntent.addCategory(Intent.CATEGORY_HOME);
        goHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(goHomeIntent);
    }

}
