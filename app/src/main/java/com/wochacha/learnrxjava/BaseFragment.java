// (c)2016 Flipboard Inc, All Rights Reserved.

package com.wochacha.learnrxjava;

import android.support.v4.app.Fragment;

import rx.Subscription;

public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;
//
//    @OnClick(R.id.tipBt)
//    void tip() {
//        new AlertDialog.Builder(getActivity())
//                .setTitle(getTitleRes())
//                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
//                .show();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected abstract int getDialogRes();

    protected abstract int getTitleRes();
}
