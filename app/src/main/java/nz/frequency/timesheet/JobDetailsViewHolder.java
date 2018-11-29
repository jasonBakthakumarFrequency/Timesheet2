package nz.frequency.timesheet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class JobDetailsViewHolder extends RecyclerView.ViewHolder {

    private TextView jobNameTextView, jobDescriptionTextView;

    public JobDetailsViewHolder(View v) {
        super(v);
        jobNameTextView = (TextView) v.findViewById(R.id.textView6);
        jobDescriptionTextView = (TextView) v.findViewById(R.id.textView7);
    }

    public TextView getJobNameTextView() {
        return jobNameTextView;
    }

    public void setJobNameTextView(TextView jobNameTextView) {
        this.jobNameTextView = jobNameTextView;
    }

    public TextView getJobDescriptionTextView() {
        return jobDescriptionTextView;
    }

    public void setJobDescriptionTextView(TextView jobDescriptionTextView) {
        this.jobDescriptionTextView = jobDescriptionTextView;
    }



}
