package com.bwei.model.kao20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.model.kao20.Bean.CarBean;
import com.bwei.model.kao20.R;

import java.util.List;

/**
 * date:2018/12/20
 * author:郝仁（Thinkpad)
 * function:
 */
public class ChildRecycleAdapter extends RecyclerView.Adapter<ChildRecycleAdapter.ViewHolder> {
    private Context mContext;
    private List<CarBean.DataBean.ListBean> mList;

    public ChildRecycleAdapter(Context context, List<CarBean.DataBean.ListBean> list) {
        mContext = context;
        mList = list;
    }

    //接口回调
    private OnchildItemClick onchildItemClick;

    public void setonchild(OnchildItemClick onchildItemClick){
        this.onchildItemClick = onchildItemClick;
    }

    public interface OnchildItemClick{
        void onCheBoxClick(View view, int i);
        void oncheAddClick(View view, int i);
        void oncheSubClick(View view, int i);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate( mContext, R.layout.adapter_child, null );
        ViewHolder holder = new ViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvGoodsName.setText( mList.get( i ).getTitle() );
        viewHolder.tvGoodsPrice.setText( mList.get( i ).getPrice() + "" );
        String image = mList.get( i ).getImages();
        String[] split = image.split( "\\|" );
        Glide.with( mContext ).load( split[0] ).into( viewHolder.adapterChildimage );
        viewHolder.tvNum.setText( mList.get( i ).getNum()+"" );
        viewHolder.adapterChildCheck.setChecked( mList.get( i ).getSelected()== 1);

        viewHolder.adapterChildCheck.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = mList.get( i ).getSelected();
                if (selected == 1){
                    mList.get( i ).setSelected(0);
                }else {
                    mList.get( i ).setSelected(1);
                }
                notifyDataSetChanged();
                onchildItemClick.onCheBoxClick(v,i);
            }
        } );
        viewHolder.tvAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = mList.get( i ).getNum();
                num++;
                mList.get( i ).setNum( num );
                notifyDataSetChanged();
                onchildItemClick.oncheAddClick(v,i);
            }
        } );
        viewHolder.tvSub.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = mList.get( i ).getNum();
                if (num>1){
                    num--;
                }else {
                    Toast.makeText(mContext, "不能在少了", Toast.LENGTH_SHORT).show();
                }

                mList.get( i ).setNum( num );
                notifyDataSetChanged();
                onchildItemClick.oncheSubClick(v,i);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox adapterChildCheck;
        private final TextView tvGoodsName, tvGoodsPrice, tvSub, tvNum, tvAdd;
        private final ImageView adapterChildimage;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            adapterChildCheck = (CheckBox) itemView.findViewById( R.id.adapter_childCheck );
            adapterChildimage = (ImageView) itemView.findViewById( R.id.adapter_childimage );
            tvGoodsName = (TextView) itemView.findViewById( R.id.tv_goodsName );
            tvGoodsPrice = (TextView) itemView.findViewById( R.id.tv_goodsPrice );
            tvSub = (TextView) itemView.findViewById( R.id.tv_sub );
            tvNum = (TextView) itemView.findViewById( R.id.tv_num );
            tvAdd = (TextView) itemView.findViewById( R.id.tv_add );
        }
    }
}
