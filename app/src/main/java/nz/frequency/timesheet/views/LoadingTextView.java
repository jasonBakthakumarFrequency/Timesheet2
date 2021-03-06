package nz.frequency.timesheet.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import nz.frequency.timesheet.R;

import java.util.Arrays;
import java.util.Collections;

public class LoadingTextView extends android.support.v7.widget.AppCompatTextView {


    private static final int TIMEOUT = 2000;
    private Animation fadeIn, fadeOut;
    private Handler handler;
    private String[] quotes;
    private int position = 0;
    private boolean isRunning = false;
    private String postFix = "";
    private String preFix = "";
    private int duration = TIMEOUT;

    public LoadingTextView(Context context) {
        this(context, null);
    }

    public LoadingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextLoader,
                0, 0);

        try {
            preFix = a.getString(R.styleable.TextLoader_funny_prefix) == null ?
                    "" : a.getString(R.styleable.TextLoader_funny_prefix);
            postFix = a.getString(R.styleable.TextLoader_funny_postfix) == null ?
                    "" : a.getString(R.styleable.TextLoader_funny_postfix);
            duration = a.getInteger(R.styleable.TextLoader_funny_animation_duration, TIMEOUT);
        } finally {
            a.recycle();
        }
    }

    private void init() {
        fadeIn = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out);
        isRunning = false;
        handler = new Handler();
        quotes = getResources().getStringArray(R.array.loading_text);

        Collections.shuffle(Arrays.asList(quotes));

        StringBuilder sb = new StringBuilder();
        sb.append(preFix);
        sb.append(quotes[++position]);
        sb.append(postFix);

        setText(sb.toString());
    }


    public void start() {
        if (isRunning) return;

        isRunning = true;
        startAnimation();
    }

    public void stop() {
        stopAnimation();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void setVisibility(int visibility) {
        if (View.VISIBLE != visibility && isRunning) {
            stop();
        }
        super.setVisibility(visibility);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (isRunning) {
            stop();
        }
        super.onDetachedFromWindow();
    }

    private void startAnimation() {
        StringBuilder sb = new StringBuilder();
        sb.append(preFix);
        sb.append(quotes[position]);
        sb.append(postFix);

        setText(sb.toString());

        startAnimation(fadeIn);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimation(fadeOut);
                if (getAnimation() != null)
                    getAnimation().setAnimationListener(new LoadingTextAnimationAdapter() {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            position = position == quotes.length - 1 ? 0 : position + 1;
                            startAnimation();
                        }
                    });
            }
        }, duration);
    }

    private void stopAnimation() {
        handler.removeCallbacksAndMessages(null);
        if (getAnimation() != null) getAnimation().cancel();
    }



}
