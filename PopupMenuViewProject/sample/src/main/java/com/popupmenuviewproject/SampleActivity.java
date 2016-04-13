package com.popupmenuviewproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.view.PopupMenuView;

public class SampleActivity extends AppCompatActivity {
    private ImageButton ancher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ancher = (ImageButton) findViewById(R.id.menu_button);
    }

    public void onClickMenuBtn(View view){
        PopupMenuView menuView = new PopupMenuView(this,new String[]{"微信","阿里Pay","其他"},
                new int[]{R.drawable.weixin,R.drawable.alipay,R.drawable.ic_launcher});
        menuView.showAsDropDown(ancher, -dip2px(this,PopupMenuView.WIDTH_DIP-PopupMenuView.ICON_WIDTH_DIP), 0);
        menuView.setOnMenuItemClickListener(new PopupMenuView.OnMenuItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(SampleActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // dip转换为px
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
