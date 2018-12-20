package com.bwei.model.kao20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.model.kao20.Bean.CarBean;
import com.bwei.model.kao20.R;

import java.time.chrono.MinguoChronology;
import java.util.List;

/**
 * date:2018/12/20
 * author:郝仁（Thinkpad)
 * function:
 */
public class CarRecyclerAdapter extends RecyclerView.Adapter<CarRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<CarBean.DataBean> mList;
    private ChildRecycleAdapter mChildAdapter;

    public CarRecyclerAdapter(Context context, List<CarBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    //接口回调
    private onCarItemClick mOnCarItemClick;

    public void setOnCar(onCarItemClick mOnCarItemClick) {
        this.mOnCarItemClick = mOnCarItemClick;
    }

    public interface onCarItemClick {
        void onCheBoxClick(View view, int i);

        void oncheAddClick(View view, int i);

        void oncheSubClick(View view, int i);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate( mContext, R.layout.adapter_fu, null );
        ViewHolder holder = new ViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.adapterNeir.setText( mList.get( i ).getSellerName() );
        int isChacked = mList.get( i ).getIsChacked();
        if (isChacked == 1) {
            viewHolder.adapterBox.setChecked( true );
        } else {
            viewHolder.adapterBox.setChecked( false );
        }

        //显示布局管理器
        LinearLayoutManager manager = new LinearLayoutManager( mContext );
        manager.setOrientation( LinearLayoutManager.VERTICAL );
        viewHolder.fuRecycler.setLayoutManager( manager );
        //适配器
        List<CarBean.DataBean.ListBean> list = mList.get( i ).getList();
        mChildAdapter = new ChildRecycleAdapter( mContext, list );
        viewHolder.fuRecycler.setAdapter( mChildAdapter );

        mChildAdapter.setonchild( new ChildRecycleAdapter.OnchildItemClick() {
            @Override
            public void onCheBoxClick(View view, int i) {
                mOnCarItemClick.onCheBoxClick( view, i );
            }

            @Override
            public void oncheAddClick(View view, int i) {
                mOnCarItemClick.oncheAddClick( view, i );
            }

            @Override
            public void oncheSubClick(View view, int i) {
                mOnCarItemClick.oncheSubClick( view, i );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox adapterBox;
        private final TextView adapterNeir;
        private final RecyclerView fuRecycler;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            adapterBox = (CheckBox) itemView.findViewById( R.id.adapter_box );
            adapterNeir = (TextView) itemView.findViewById( R.id.adapter_neir );
            fuRecycler = (RecyclerView) itemView.findViewById( R.id.fu_recycler );
        }
    }
}
