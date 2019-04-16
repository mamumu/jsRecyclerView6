package com.mumu.jsrecyclerview6;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TestAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_title);
        addItemType(TYPE_LEVEL_1, R.layout.item_message);
    }

    @NonNull
    @Override
    public List<MultiItemEntity> getData() {
        return super.getData();
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final TestEntity.ResultBean resultBean = (TestEntity.ResultBean) item;
                holder.setText(R.id.item_title1, resultBean.getTitle1());
                holder.setText(R.id.item_title2, resultBean.getTitle2());
                holder.addOnClickListener(R.id.item_title1);
                holder.addOnClickListener(R.id.item_title2);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (resultBean.isExpanded()) {
                            collapse(pos, false);
                            Toast.makeText(mContext, "收起：" + resultBean.getTitle1(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            expand(pos, false);
                            Toast.makeText(mContext, "展开：" + resultBean.getTitle1(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        switch (v.getId()) {
                            case R.id.item_title1:
                                Toast.makeText(mContext, ((TestEntity.ResultBean) item).getTitle1(),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item_title2:
                                Toast.makeText(mContext, ((TestEntity.ResultBean) item).getTitle2(),
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final TestEntity.ResultBean.ListBean listBean = (TestEntity.ResultBean.ListBean) item;
                holder.setText(R.id.item_message1, listBean.getMessage1());
                holder.setText(R.id.item_message2, listBean.getMessage2());
                holder.addOnClickListener(R.id.item_message1);
                holder.addOnClickListener(R.id.item_message2);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "点击了：" + listBean.getMessage1() + listBean.getMessage2(),
                                Toast.LENGTH_SHORT).show();
                        switch (view.getId()) {
                            case R.id.item_message1:
                                Toast.makeText(mContext, listBean.getMessage1(),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item_message2:
                                Toast.makeText(mContext, listBean.getMessage2(),
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });

                break;
        }
    }
}
