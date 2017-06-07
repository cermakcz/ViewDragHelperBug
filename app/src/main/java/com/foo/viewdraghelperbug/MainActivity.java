package com.foo.viewdraghelperbug;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.design.widget.SwipeDismissBehavior.OnDismissListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup coordinator = (ViewGroup) findViewById(R.id.coordinator);

        View button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View bottom = findViewById(R.id.bottom);
                if (bottom == null) {
                    bottom = getLayoutInflater().inflate(R.layout.part_bottom, coordinator, false);
                    final View finalBottom = bottom;

                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) bottom.getLayoutParams();
                    SwipeDismissBehavior behavior = new SwipeDismissBehavior() {
                        // Uncomment this to fix the top edge swipe which swipes away the content instead of the panel.
//                        @Override
//                        public boolean canSwipeDismissView(View child) {
//                            return child == finalBottom;
//                        }
                    };
                    behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
                    behavior.setStartAlphaSwipeDistance(0.1f);
                    behavior.setListener(new OnDismissListener() {
                        @Override
                        public void onDismiss(View view) {
                            coordinator.removeView(finalBottom);
                        }

                        @Override
                        public void onDragStateChanged(int state) {
                        }
                    });
                    params.setBehavior(behavior);

                    coordinator.addView(bottom);
                }
            }
        });

        View button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinator, "foo", Snackbar.LENGTH_INDEFINITE).show();
            }
        });

    }
}
