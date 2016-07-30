package cn.mianyang.song314.android_cameralib;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

/**
 * time: 7/15/16
 * description:
 *
 * @author tangsong
 */
public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("BaseFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("BaseFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d("BaseFragment");

        ImageView imageView = new ImageView(inflater.getContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        return imageView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Logger.d("BaseFragment");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        Logger.d("BaseFragment");
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        Logger.d("BaseFragment");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logger.d("BaseFragment");
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        Logger.d("BaseFragment");
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Logger.d("BaseFragment");
        super.onDetach();
    }

    @Override
    public void onResume() {
        Logger.d("BaseFragment");
        super.onResume();
    }

    @Override
    public void onPause() {
        Logger.d("BaseFragment");
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Logger.d("BaseFragment");
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onStop() {
        Logger.d("BaseFragment");
        super.onStop();
    }
}
