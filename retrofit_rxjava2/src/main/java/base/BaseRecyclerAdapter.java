package base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zd.retrofit_rxjava2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 *@describe(描述)：BaseRecyclerAdapter
 *@data（日期）: 2019/10/18
 *@time（时间）: 8:36
 *@author（作者）: fanyanlong
 **/

public abstract class  BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {
    public static Context context;
    private List<T> list = new ArrayList<>();

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseRecyclerAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, getLayoutId(), null);
        BaseRecyclerAdapter.BaseViewHolder baseViewHolder = new BaseRecyclerAdapter.BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerAdapter.BaseViewHolder baseViewHolder, int i) {
        createPosition(i);
        bindViewData(baseViewHolder, list.get(i));
        bindViewDataPosition(baseViewHolder, list.get(i), i);
    }

    public void bindViewDataPosition(BaseViewHolder baseViewHolder, T t, int i) {
    }

    public void createPosition(int i) {

    }


    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        private SparseArray<View> sparseArray = new SparseArray<>();


        //用于获取控件的方法
        public View get(int id) {
            View view = sparseArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                sparseArray.put(id, view);
            }
            return view;
        }

        //给TextView 赋值
        public void setText(int id, String content) {
            TextView textView = (TextView) get(id);
            textView.setText(content);
        }

        public void setPic(int id, String url) {
            //这里写加载图片的逻辑
            //Glide  Fresco  pissca
            Glide.with(context).load(url).into((ImageView) get(id));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //传递数据
    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    //获取数据
    public List<T> getList() {
        return list;
    }

    //子类向父类传递的layout
    public abstract int getLayoutId();

    //子类初始化数据
    public abstract void bindViewData(BaseRecyclerAdapter.BaseViewHolder baseViewHolder, T t);
}