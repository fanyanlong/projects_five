package model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/*
 *Name：fanyanlong
 *Time：17:02
 *Name:day02_mvp_mode
 */

public class HomeAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
   /* Context context;
    ArrayList<HomeBean.ResultBean> list;
    private MyHolder myHolder;

    public HomeAdapter(MainActivity mainActivity, ArrayList<HomeBean.ResultBean> arrayList) {
        this.context=mainActivity;
        this.list=arrayList;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //加载布局
        //优化

        if (view==null){
            view = View.inflate(context, R.layout.one_item_layout,null);
            myHolder = new MyHolder();
            myHolder.imageView = view.findViewById(R.id.one_item_iamges);
            myHolder.one = view.findViewById(R.id.one_item_nametv);
            myHolder.two = view.findViewById(R.id.one_item_pictv);
            view.setTag(myHolder);
        }else {
            myHolder = (MyHolder) view.getTag();
        }
            myHolder.one.setText(list.get(i).getCommodityName());
           // myHolder.two.setText(list.get(i).getMasterPic());
           // new ImgAsync(list.get(i).getMasterPic(),myHolder.imageView).execute();
        //图片加载框架
        Glide.with(context).load(R.mipmap.mi)
                .placeholder(R.mipmap.bj)//占位图
                .error(R.mipmap.er)//资源加载错误图片
                .override(200,300)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//本地
                .skipMemoryCache(true)//缓存
                .into(myHolder.imageView);
        //高级 缓存
//DiskCacheStrategy.NONE： 表示不缓存任何内容。
//DiskCacheStrategy.SOURCE： 表示只缓存原始图片。
//DiskCacheStrategy.RESULT： 表示只缓存转换过后的图片（默认选项）。
//DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。




        return view;
    }
    class MyHolder{
        ImageView imageView;
        TextView one,two;
    }*/


}
