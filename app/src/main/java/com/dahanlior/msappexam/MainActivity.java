package com.dahanlior.msappexam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.dahanlior.msappexam.utils.BaseFragment;
import com.dahanlior.msappexam.utils.FragmentVisualizer;
import com.dahanlior.msappexam.view.movie.create.AddMovieFragment;
import com.dahanlior.msappexam.view.movie.list.MovieListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentVisualizer {

    private static final int REQUEST_PERMISSION = 1;
    private MainFragmentVisualizer mFragmentVisualizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFragmentVisualizer = new MainFragmentVisualizer();


        if (getSupportFragmentManager().findFragmentById(R.id.container) == null) {
            showFragment(MovieListFragment.newInstance(), false, false);
        }


        FloatingActionButton fab = findViewById(R.id.addMovieFab);
        fab.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION
                );
            }

        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showFragment(AddMovieFragment.newInstance(), true, false);
            }else {
                Toast.makeText(MainActivity.this, "Grant permission to add new movie", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showFragment(BaseFragment frag, boolean toBackStack, boolean clearAll) {
        mFragmentVisualizer.showFragment(frag, toBackStack, clearAll);

    }

    @Override
    public void removeAllFragments() {
        mFragmentVisualizer.removeAllFragments();

    }

    class MainFragmentVisualizer implements FragmentVisualizer {

        @Override
        public void showFragment(BaseFragment frag, boolean toBackStack, boolean clearAll) {
            final FragmentManager fm = getSupportFragmentManager();

            final BaseFragment currentFragment = (BaseFragment) fm.findFragmentById(R.id.container);
            if (currentFragment != null && frag.getManagerTag().equals(currentFragment.getManagerTag())) { // if current visible fragment is same as requested
                return;
            }

            if (clearAll) {
                clearBackStack();
            }

            FragmentTransaction ft = fm.beginTransaction();
            if (fm.getBackStackEntryCount() > 0) {
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            }
            ft.replace(R.id.container, frag, frag.getManagerTag());
            if (toBackStack) {
                ft.addToBackStack(frag.getManagerTag());
            }

            ft.commit();

        }

        @Override
        public void removeAllFragments() {
            clearBackStack();
        }


        void clearBackStack() {
            final FragmentManager fm = getSupportFragmentManager();

            //noinspection StatementWithEmptyBody
            while (fm.popBackStackImmediate(R.id.container, FragmentManager.POP_BACK_STACK_INCLUSIVE)) {
            }

            if (fm.findFragmentById(R.id.container) != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(Objects.requireNonNull(fm.findFragmentById(R.id.container)))
                        .commitNow();
            }
        }

    }
}
