package com.example.administrator.doublelistlinkagedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 右侧列表
 *
 * @author zhangqin
 * @date 2017/7/20
 */
public class ClassifyDetailAdapter extends RecyclerView.Adapter<ClassifyDetailAdapter.ClassifyHolder> {

    private List<SortBean> list;
    private Context mContext;
    private RvListener listener;
    private LayoutInflater mInflater;
    private TypedArray typedArray;

    public ClassifyDetailAdapter(Context mContext, List<SortBean> list, RvListener listener) {
        this.list = list;
        this.mContext = mContext;
        this.listener = listener;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ClassifyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(viewType == 0 ? R.layout.item_title : R.layout.item_classify_detail, parent, false);
        return new ClassifyHolder(view, viewType, listener);
    }

    @Override
    public void onBindViewHolder(ClassifyHolder holder, int position) {
        holder.bindHolder(list.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ClassifyHolder extends RecyclerView.ViewHolder {
        private TextView tvCity;
        private ImageView avatar;
        private TextView tvTitle;
        private RvListener mListener;

        public ClassifyHolder(View itemView, int type, RvListener listener) {
            super(itemView);
            switch (type) {
                case 0:
                    tvTitle = itemView.findViewById(R.id.tv_title);
                    break;
                case 1:
                    tvCity = itemView.findViewById(R.id.tvCity);
                    avatar = itemView.findViewById(R.id.ivAvatar);
                    break;
                default:
                    break;
            }

            this.mListener = listener;
            itemView.setOnClickListener(v -> mListener.onItemClick(v.getId(), getAdapterPosition()));
        }

        public void bindHolder(SortBean sortBean, int position) {
            int itemViewType = ClassifyDetailAdapter.this.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    tvTitle.setText(sortBean.getCountry());
                    break;
                case 1:
                    if (position > 0 && position < 6) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.philippines);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 1)).into(avatar);
                    }
                    if (position > 6 && position < 12) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.unitedKingdom);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 7)).into(avatar);
                    }
                    if (position > 12 && position < 18) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.australia);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 13)).into(avatar);
                    }
                    if (position > 18 && position < 24) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.unitedStates);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 19)).into(avatar);
                    }
                    if (position > 24 && position < 30) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.canada);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 25)).into(avatar);
                    }
                    if (position > 30 && position < 36) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.netherlands);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 31)).into(avatar);
                    }
                    if (position > 36 && position < 42) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.italy);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 37)).into(avatar);
                    }
                    if (position > 42 && position < 48) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.venezuela);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 43)).into(avatar);
                    }
                    if (position > 48 && position < 54) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.russia);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 49)).into(avatar);
                    }
                    if (position > 54 && position < 60) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.colombia);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 55)).into(avatar);
                    }
                    if (position > 60 && position < 66) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.argentina);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 61)).into(avatar);
                    }
                    if (position > 66 && position < 72) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.denmark);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 67)).into(avatar);
                    }
                    if (position > 72 && position < 78) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.ukraine);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 73)).into(avatar);
                    }
                    if (position > 78 && position < 84) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.brazil);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 79)).into(avatar);
                    }
                    if (position > 84 && position < 90) {
                        typedArray = mContext.getResources().obtainTypedArray(R.array.sweden);
                        Glide.with(mContext).load(typedArray.getDrawable(position - 85)).into(avatar);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
