package nz.frequency.timesheet;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends OnboarderActivity  {

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 4;
    List<OnboarderPage> onboarderPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onboarderPages = new ArrayList<OnboarderPage>();

        // Create your first page
        OnboarderPage onboarderPage1 = new OnboarderPage("Welcome to the Frequency Time Sheet App", "The Frequency Time Sheet app allows you to log your time at work digitally at one place, where all your information is secure. No more manual keying in or messy papers", R.drawable.man_graphic);
        OnboarderPage onboarderPage2 = new OnboarderPage("Location based tracking", "To Clock In and Clock Out, of your job you must be on site. Also remember if you leave site, you will automatically be clocked out", R.drawable.geofencing);
        OnboarderPage onboarderPage3 = new OnboarderPage("Health and Safety", "All your health and safety documentation in one place. If have any doubts regarding your safety on site just go through our easily accessible documentation", R.drawable.mobile_storage);
        OnboarderPage onboarderPage4 = new OnboarderPage("Time Log", "Everyone is on the same page as your time log is secure and easily accessible", R.drawable.man_on_the_go);

        // Setting Background Color for each page
        onboarderPage1.setBackgroundColor(R.color.colorPrimary);
        onboarderPage2.setBackgroundColor(R.color.first_onboarder_page_color);
        onboarderPage3.setBackgroundColor(R.color.third_onboarder_page_color);
        onboarderPage4.setBackgroundColor(R.color.fourth_onboarder_page_color);


        // Add your pages to the list
        onboarderPages.add(onboarderPage1);
        onboarderPages.add(onboarderPage2);
        onboarderPages.add(onboarderPage3);
        onboarderPages.add(onboarderPage4);

        // Add pass pages to the 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages);

    }

    @Override
    public void onSkipButtonPressed() {
        // Optional: by default it skips onboarder to the end
        super.onSkipButtonPressed();
        // If the user pressES Skip, ask him for location permission
        askForLocationPermission();
    }

    @Override
    public void onFinishButtonPressed() {
        // Define your actions when the user press 'Finish' button
        //Go to the next activity and keep moving on
        //When the finish button is pressed go to the next activity!! Yay!!
        Intent intent = new Intent(this,PhoneNumberActivity.class);
        startActivity(intent);
        finish();
        //Doesn't make sense to clear out the activity back stack
    }

    @Override
    public void onPageSelected(int position) {
        //Starts at 0. And does not get called when created!! Called twice when rotated
        super.onPageSelected(position);

        if(position == 1 || position == 2){
            //After showing them the functionality of the app it is important for us to ask for location permission
            askForLocationPermission();

        }
    }

    private void askForLocationPermission() {
        //Check if the permission is already granted
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                // MY_PERMISSIONS_REQUEST_FINE_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }  //Else the permission has been granted. Do nothing! :)
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //Inverting the if condition leads to unrecognised actions

                } else {
                    // permission denied, boo! Show them the alert dialog
                    showAlertDialog();
                }
            }

        }
    }


    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }


}
