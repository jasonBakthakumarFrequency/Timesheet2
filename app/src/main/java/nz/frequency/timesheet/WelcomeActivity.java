package nz.frequency.timesheet;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends OnboarderActivity  {

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

        // And pass your pages to 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages);

    }

    @Override
    public void onSkipButtonPressed() {
        // Optional: by default it skips onboarder to the end
        super.onSkipButtonPressed();
        // Define your actions when the user press 'Skip' button
    }

    @Override
    public void onFinishButtonPressed() {
        // Define your actions when the user press 'Finish' button
    }

    @Override
    public void onPageSelected(int position) {
        //Starts at 0. And does not get called when created!! Called twice when rotated
        super.onPageSelected(position);
        Log.d("Page Selected","Page no:" + position);
    }
}
