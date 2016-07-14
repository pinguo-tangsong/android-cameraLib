package cn.mianyang.song314.android_cameralib;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mianyang.song314.android_cameralib.hal.ICamera;
import cn.mianyang.song314.android_cameralib.hal.LibCamera;
import cn.mianyang.song314.android_cameralib.settings.BaseSetting;
import cn.mianyang.song314.android_cameralib.settings.SettingFactory;
import cn.mianyang.song314.android_cameralib.utils.NormalRecyclerViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CameraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment implements NormalRecyclerViewAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ICamera mCamera;
    private CameraPreview mPreview;
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;

    //    @BindView(R.id.button_switch)
//    Button mBtnSwitch;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.recycler_view2)
    RecyclerView mRecyclerView2;
    private NormalRecyclerViewAdapter mAdapter;
    private Activity mActivity;


    public CameraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CameraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CameraFragment newInstance(String param1, String param2) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ButterKnife.bind(this, view);

        // Create an instance of Camera
        mCamera = new LibCamera(mCameraId);
        mCamera.open();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        initSettingAdapter();

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(mActivity);
        mPreview.setCamera(mCamera, mCameraId);
        ViewGroup preview = (ViewGroup) view.findViewById(R.id.camera_preview);
        preview.addView(mPreview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        super.onViewCreated(view, savedInstanceState);
    }

    private void initSettingAdapter() {
        mAdapter = new NormalRecyclerViewAdapter(mActivity, SettingFactory.build(mCamera.getParameters()));
        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position, final BaseSetting setting) {
        if (setting.value == null) {

            if (SettingFactory.CAPTURE.equals(setting.name)) {
                mCamera.autoFocus();
            }

            if (SettingFactory.SWAP_CAM.equals(setting.name)) {
                mCamera.stopPreview();
                mCamera.release();

                final int cameraId = mCameraId == Camera.CameraInfo.CAMERA_FACING_BACK ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK;
                mCameraId = cameraId;
                mCamera = new LibCamera(mCameraId);
                mCamera.open();
                initSettingAdapter();

                mPreview.setCamera(mCamera, cameraId);
                mPreview.surfaceCreated(mPreview.getHolder());
            }

        } else {
            SettingAdapter sa = new SettingAdapter(mActivity, setting);
            mRecyclerView2.setAdapter(sa);
            sa.setListener(new SettingAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, BaseSetting setting) {
                    setting.set(mCamera.getParameters(), setting.value[position]);
                    mCamera.setParameters(mCamera.getParameters());
                }
            });


        }

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
