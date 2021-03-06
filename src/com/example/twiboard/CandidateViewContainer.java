package com.example.twiboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
public class CandidateViewContainer extends LinearLayout implements OnTouchListener {
    private View mButtonLeft;
    private View mButtonRight;
    private View mButtonLeftLayout;
    private View mButtonRightLayout;
    private CandidateView mCandidates;
    
    public CandidateViewContainer(Context screen, AttributeSet attrs) {
        super(screen, attrs);
    }
    public void initViews() {
        if (mCandidates == null) {
            mButtonLeftLayout = findViewById(R.id.candidate_left_parent);
            mButtonLeft = findViewById(R.id.candidate_left);
            if (mButtonLeft != null) {
                mButtonLeft.setOnTouchListener(this);
            }
            mButtonRightLayout = findViewById(R.id.candidate_right_parent);
            mButtonRight = findViewById(R.id.candidate_right);
            if (mButtonRight != null) {
                mButtonRight.setOnTouchListener(this);
            }
            mCandidates = (CandidateView) findViewById(R.id.candidates);
        }
    }
    @Override
    public void requestLayout() {
        if (mCandidates != null) {
            int availableWidth = mCandidates.getWidth();
            int neededWidth = mCandidates.computeHorizontalScrollRange();
            int x = mCandidates.getScrollX();
            boolean leftVisible = x > 0;
            boolean rightVisible = x + availableWidth < neededWidth;
            if (mButtonLeftLayout != null) {
                mButtonLeftLayout.setVisibility(leftVisible ? VISIBLE : GONE);
            }
            if (mButtonRightLayout != null) {
                mButtonRightLayout.setVisibility(rightVisible ? VISIBLE : GONE);
            }
        }
        super.requestLayout();
    }
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v == mButtonRight) {
                mCandidates.scrollNext();
            } else if (v == mButtonLeft) {
                mCandidates.scrollPrev();
            }
        }
        return false;
    }
    
}