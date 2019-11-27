package com.unipu.hr.GoPlay;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class NavigacijaFragment extends BottomSheetDialogFragment {

    public static NavigacijaFragment newInstance() {

        Bundle args = new Bundle();

        NavigacijaFragment fragment = new NavigacijaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            if (slideOffset > 0.5) {
                closeButton.setVisibility(View.VISIBLE);
            } else {
                closeButton.setVisibility(View.GONE);
            }
        }
    };

    private ImageView closeButton;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.navigacijski_drawer, null);
        dialog.setContentView(contentView);

        NavigationView navigationView = contentView.findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Home activity = (Home) getActivity();
                switch (item.getItemId()) {
                    case R.id.nav_nogomet:
                        activity.data("Nogomet");
                        getFragmentManager().beginTransaction().remove(NavigacijaFragment.this).commit();
                        break;
                    case R.id.nav_tenis:
                        activity.data("Tenis");
                        getFragmentManager().beginTransaction().remove(NavigacijaFragment.this).commit();
                        break;
                    case R.id.nav_online_igranje:
                        activity.data("Online_igranje");
                        getFragmentManager().beginTransaction().remove(NavigacijaFragment.this).commit();
                        break;
                    case R.id.nav_odbojka:
                        activity.data("Odbojka");
                        getFragmentManager().beginTransaction().remove(NavigacijaFragment.this).commit();
                        break;
                    case R.id.nav_rukomet:
                        activity.data("Rukomet");
                        getFragmentManager().beginTransaction().remove(NavigacijaFragment.this).commit();
                        break;
                        case R.id.nav_trcanje:
                        activity.data("Trcanje");
                        getFragmentManager().beginTransaction().remove(NavigacijaFragment.this).commit();
                        break;
                }
                return false;
            }
        });
        closeButton = contentView.findViewById(R.id.close_image_view);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }



}
