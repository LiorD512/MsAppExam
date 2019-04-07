package com.dahanlior.msappexam.utils;


public interface FragmentVisualizer {

    void showFragment(BaseFragment frag, boolean toBackStack, boolean clearAll);

    void removeAllFragments();
}
