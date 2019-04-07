package com.dahanlior.msappexam.utils;

import android.app.Activity;
import android.view.MenuItem;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public abstract class BaseFragment extends Fragment implements FragmentVisualizer{

    public static final String TAG = BaseFragment.class.getSimpleName();


    public abstract String getManagerTag();

    public BaseFragment() {
        setHasOptionsMenu(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Objects.requireNonNull(getActivity()).onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected FragmentManager getSupportFragmentManager() {
        final FragmentActivity activity = getActivity();
        if(activity != null) {
            return activity.getSupportFragmentManager();
        }

        return null;
    }


    /**
     *
     * @param frag  Instance of Fragment to be shown.
     * @param toBackStack if this fragment should be added to back-stack.
     * @param clearAll If previous content of entry point should be removed (cleared is back-stack and current content).
     */
    @Override
    public void showFragment(BaseFragment frag, boolean toBackStack, boolean clearAll) {
        final Activity act = getActivity();
        if(act instanceof FragmentVisualizer) {
            ((FragmentVisualizer) act).showFragment(frag, toBackStack, clearAll);
        }
    }

    /**
     * Same as calling {@link BaseFragment#showFragment(BaseFragment, boolean, boolean)} with clearALL set to FALSE.
     * @param frag  Instance of Fragment to be shown.
     * @param toBackStack   if this fragment should be added to back-stack.
     */
    public void showFragment(BaseFragment frag, boolean toBackStack) {
        showFragment(frag, toBackStack, false);
    }

    @Override
    public void removeAllFragments() {
        final Activity act = getActivity();
        if(act instanceof FragmentVisualizer) {
            ((FragmentVisualizer) act).removeAllFragments();
        }
    }
}
