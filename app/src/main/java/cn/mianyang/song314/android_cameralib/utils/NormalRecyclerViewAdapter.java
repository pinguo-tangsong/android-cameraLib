package cn.mianyang.song314.android_cameralib.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mianyang.song314.android_cameralib.R;
import cn.mianyang.song314.android_cameralib.settings.BaseSetting;

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    public  static  final int TYPE_CAPTURE = 0;
    public  static  final int TYPE_SWITCH = 1;
    public  static  final int TYPE_SETTING = 2;
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private final ArrayList<BaseSetting> mSettings;

    private OnItemClickListener mListener;

    public NormalRecyclerViewAdapter(Context context, ArrayList<BaseSetting> settings) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mSettings = settings;
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_setting_fuction, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, final int position) {
        holder.mTextView.setText(mSettings.get(position).name);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position, mSettings.get(position));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mSettings == null ? 0 : mSettings.size();
    }

    public class NormalTextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button)
        TextView mTextView;

        NormalTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, BaseSetting setting);
    }
}