package com.wq.androidtest.activity.anim;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/11/24.
 */
public class CardFlipFragmentAcitivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener, View.OnClickListener {

    private Fragment front;
    private Fragment back;
    private Button flip;
    private boolean isShowingBack;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        flip = (Button) findViewById(R.id.flip);
        flip.setOnClickListener(this);
        front = new CardFrontFragment();
        back = new CardBackFragment();
        getFragmentManager().beginTransaction().add(R.id.container, front).commit();
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void onClick(View v) {
        flip();
    }

    private void flip() {

        if (isShowingBack) {
            // TODO: 15/11/24 fragment,fragment manager
            getFragmentManager().popBackStack();
            return;
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                    .replace(R.id.container, back)
                    .addToBackStack(null)
                    .commit();
        }

        isShowingBack = !isShowingBack;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // TODO: 15/11/24 refresh menu
                invalidateOptionsMenu();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public static class CardFrontFragment extends Fragment {
        public CardFrontFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }
    }

    public static class CardBackFragment extends Fragment {
        public CardBackFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }
    }
}
