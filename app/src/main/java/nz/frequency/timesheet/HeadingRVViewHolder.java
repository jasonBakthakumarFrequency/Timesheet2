package nz.frequency.timesheet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class HeadingRVViewHolder extends RecyclerView.ViewHolder {

    public TextView headingTextView;

    public HeadingRVViewHolder(View v) {
        super(v);
        headingTextView = (TextView) v.findViewById(R.id.textView4);
    }

    public TextView getHeadingTextView() {
        return headingTextView;
    }

    public void setHeadingTextView(TextView headingTextView) {
        this.headingTextView = headingTextView;
    }


}
