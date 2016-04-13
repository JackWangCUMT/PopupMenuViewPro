package com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang shuailing on 2016/4/13.
 * email bitxiaozhang@163.com
 */
public class PopupMenuView extends PopupWindow {

    public static final int WIDTH_DIP = 150;
    public static final int ICON_WIDTH_DIP = 36;
    private OnMenuItemClickListener onMenuItemClickListener;

    /*
     * if has contained tabIcons,tabIcons's length must equal tabTitles's length
     */
    public PopupMenuView(Activity activity ,@NonNull String[] tabTitles,int[] tabIcons) {
        super(activity);

        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_view_layout,null);
        ListView listView = (ListView) view.findViewById(R.id.popview_list);
        listView.setAdapter(new MenuAdapter(buildMemuData(tabTitles, tabIcons), activity));
        this.setContentView(view);
        this.setWidth(dip2px(activity, WIDTH_DIP));
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenuView.this.dismiss();
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onClickItem((int) l);
                }
            }
        });

    }

    private List<MenuItem> buildMemuData(@NonNull String[] tabTitles,int[] tabIcons) {
        List<MenuItem> list = new ArrayList<>();
        if (tabTitles != null && tabTitles.length > 0) {
            MenuItem itemMenu;
            if (tabIcons != null && tabIcons.length == tabTitles.length) {
                for (int i = 0; i < tabTitles.length; i++) {
                    itemMenu = new MenuItem();
                    itemMenu.title = tabTitles[i];
                    itemMenu.iconId = tabIcons[i];
                    list.add(itemMenu);
                }
            } else if (tabIcons == null) {
                for (int i = 0; i < tabTitles.length; i++) {
                    itemMenu = new MenuItem();
                    itemMenu.title = tabTitles[i];
                    list.add(itemMenu);
                }
            }

        }
        return list;
    }
    // dip转换为px
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private class MenuItem{
        protected int iconId;
        protected String title;
    }

    private class MenuAdapter extends BaseAdapter{

        private List<MenuItem> list;
        private Context mCotext;

        public MenuAdapter(List<MenuItem> list, Context mCotext) {
            this.list = list;
            this.mCotext = mCotext;
        }

        @Override
        public int getCount() {
            return list==null?0:list.size();
        }

        @Override
        public MenuItem getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null){
                view = LayoutInflater.from(mCotext).inflate(R.layout.pop_menu_item_layout,null);
                viewHolder = new ViewHolder();
                viewHolder.titleView = (TextView) view.findViewById(R.id.title);
                viewHolder.iconView = (ImageView) view.findViewById(R.id.icon);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            MenuItem model = list.get(i);
            viewHolder.iconView.setImageResource(model.iconId);

            if (model.iconId == 0){
                viewHolder.iconView.setVisibility(View.GONE);
            }else{
                viewHolder.titleView.setText(model.title);
            }

            return view;
        }

        protected class ViewHolder{
            ImageView iconView;
            TextView titleView;
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public interface OnMenuItemClickListener{
        void onClickItem(int position);
    }
}
