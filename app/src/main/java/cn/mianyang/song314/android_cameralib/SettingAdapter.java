package cn.mianyang.song314.android_cameralib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mianyang.song314.android_cameralib.settings.BaseSetting;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final BaseSetting mSetting;

    private OnItemClickListener mListener;

    public SettingAdapter(Context context, BaseSetting setting) {
        mLayoutInflater = LayoutInflater.from(context);
        mSetting = setting;
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
        holder.mTextView.setText(mSetting.text[position]);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position, mSetting);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mSetting == null ? 0 : mSetting.value.length;
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