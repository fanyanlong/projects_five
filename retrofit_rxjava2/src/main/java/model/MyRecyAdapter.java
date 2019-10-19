package model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/*
 *Name：fanyanlong
 *Time：11:40
 *Name:day02_mvp_mode
 */
public class MyRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
   /* private final  int  ONETYPE=1;
    private final  int  TWOTYPE=2;
    private final  int  XBANNERTYPE=3;
    Context context;
    ArrayList<HomeBean.ResultBean> list;
    private View inflate;
    private int itemViewType;
    private MyOneViewHolder myOneViewHolder;
    List<XbannerBean.ResultBean> xbannerResult;
    private List<String> xbannertitle= new ArrayList<>();
    private List<XbannerBean.ResultBean> listxbanner;


    public MyRecyAdapter(MainActivity mainActivity, ArrayList<HomeBean.ResultBean> arrayList, List<XbannerBean.ResultBean> xbannerResult) {
        this.context=mainActivity;
        this.list=arrayList;
        this.xbannerResult=xbannerResult;

    }

    *//*
     * 初始视图
     * *//*
    @NonNull
    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==XBANNERTYPE){
            inflate = LayoutInflater.from(context).inflate(R.layout.xbanner_layout, parent,false);
            return new MyXbannerViewHolder(inflate);
        } else  if (viewType==ONETYPE){
             inflate = LayoutInflater.from(context).inflate(R.layout.one_item_layout, parent,false);
             return new MyOneViewHolder(inflate);
         }else if (viewType==TWOTYPE){
             inflate = LayoutInflater.from(context).inflate(R.layout.two_item_layout, parent,false);
             return new MyTwoViewHolder(inflate);

         }
         return  null;
    }



    *//*
    * 设置条目类型
    * 1：重新 getItemViewType
    * 2：定义条目类型数量
    * 3；判断条目
    * 4；定义viewhodel
    * 5；onCreateViewHolder 初始化不同条目对应得布局
    * 6；onBindViewHolder 1.绑定不同条目数据 （先获取条目类型getItemViewType(position)）
    * *//*
    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return XBANNERTYPE;
        }else if(position==position%2){
            return ONETYPE;
        }else {
            return TWOTYPE;
        }
    }
    *//*
     * 绑定初始化数据
     * *//*
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        itemViewType = getItemViewType(position);
        switch (itemViewType){
            case ONETYPE:
                if (holder instanceof MyOneViewHolder){
                    Glide.with(context)
                            .load(list.get(position).getMasterPic())
                            .skipMemoryCache(true)
                            .override(100,100)
                            .error(R.mipmap.er)
                            .placeholder(R.mipmap.bj)
                            .into(((MyOneViewHolder) holder).oneimageView);
                    ((MyOneViewHolder) holder).onelinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onItemClickListener.onItemClick(view,position);
                        }
                    });
                    ((MyOneViewHolder) holder).onetextViewone.setText(list.get(position).getCommodityName());
                    ((MyOneViewHolder) holder).onetextViewtwo.setText(list.get(position).getPrice());
                }
                break;
            case TWOTYPE:
                if (holder instanceof MyTwoViewHolder){
                    Glide.with(context)
                            .load(list.get(position).getMasterPic())
                            .skipMemoryCache(true)
                            .override(100,100)
                            .error(R.mipmap.er)
                            .placeholder(R.mipmap.bj)
                            .into(((MyTwoViewHolder) holder).twoimageView);
                    ((MyTwoViewHolder) holder).twotextViewone.setText(list.get(position).getCommodityName());
                    ((MyTwoViewHolder) holder).twotextViewtwo.setText(list.get(position).getPrice());
                    ((MyTwoViewHolder) holder).twolinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onItemClickListener.onItemClick(view,position);
                        }
                    });

                }
                break;
                case XBANNERTYPE:
                    Log.d("img", "onBindViewHolder:=== "+xbannerResult.get(0).getTitle());
                    if (holder instanceof MyXbannerViewHolder){
                        ((MyXbannerViewHolder) holder).xBanner.setData(xbannerResult,null);
                        Log.d("img", "Holder:=== "+xbannerResult.get(position).getImageUrl());
                        ((MyXbannerViewHolder) holder).xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, View view, int position) {
                                Glide.with(context).load(xbannerResult.get(position).getImageUrl()).into((ImageView) view);
                            }
                        });
                        ((MyXbannerViewHolder) holder).xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {

                            private String jumpUrl;
                            private Intent intent;

                            @Override
                            public void onItemClick(XBanner banner, int position) {
                                intent = new Intent(context, DilesActivity.class);
                                intent.putExtra("pathurl",xbannerResult.get(position).getJumpUrl());
                                context.startActivity(intent);
                            }
                        });
                    }
                    break;
        }



      *//*  holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemClickListener.onItemLongClick(view,position);
                return false;
            }
        });*//*

    }
    @NonNull
    *//*
    * 统计条目数量
    * *//*
    @Override
    public int getItemCount() {
         if (XBANNERTYPE==3){
             return xbannerResult.size();
         }
         return list.size();

    }
    class   MyOneViewHolder extends  RecyclerView.ViewHolder{
        private final ImageView oneimageView;
        private final TextView onetextViewone,onetextViewtwo;
        private final LinearLayout onelinearLayout;


        public MyOneViewHolder(@NonNull View itemView) {
            super(itemView);
            onelinearLayout = itemView.findViewById(R.id.one_item_home_linear);
            oneimageView = itemView.findViewById(R.id.one_item_iamges);
            onetextViewone = itemView.findViewById(R.id.one_item_nametv);
            onetextViewtwo = itemView.findViewById(R.id.one_item_pictv);
        }
    }

    class   MyTwoViewHolder extends  RecyclerView.ViewHolder{
        private final ImageView twoimageView;
        private final TextView twotextViewone,twotextViewtwo;
        private final LinearLayout twolinearLayout;
        public MyTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            twolinearLayout = itemView.findViewById(R.id.two_item_home_linear);
            twoimageView = itemView.findViewById(R.id.two_item_iamges);
            twotextViewone = itemView.findViewById(R.id.two_item_nametv);
            twotextViewtwo = itemView.findViewById(R.id.two_item_pictv);
        }
    }

    class   MyXbannerViewHolder extends  RecyclerView.ViewHolder{
        private final XBanner xBanner;
        public MyXbannerViewHolder(@NonNull View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.main_xbanner);
        }
    }
    //点击事件
    *//*
    * 接口回调实现
    * 1.定义接口
    * 2.实现接口
    * 3.回调接口
    * *//*
     public  OnItemClickListener onItemClickListener;
       public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
           this.onItemClickListener=onItemClickListener;
       }
      //定义接口
     public   interface  OnItemClickListener {
         //点击
         public void onItemClick(View view, int position);
         //长按
         public void onItemLongClick(View view, int position);
     }
*/

}
