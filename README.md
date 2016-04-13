## PopupMenuViewPro
实现自定义的android popupMenu View
PopopViewMenu 
首先上大图，看看效果

![](PopupMenuViewProject/sample.gif)

###使用方法：
导入model :popup_MenuView_Lib，可根据自己的需要修改popup_MenuView_Lib,再代码中
```java
PopupMenuView menuView = new PopupMenuView(this,new String[]{"微信","阿里Pay","其他"},
                new int[]{R.drawable.weixin,R.drawable.alipay,R.drawable.ic_launcher});
        menuView.showAsDropDown(ancher, -dip2px(this,PopupMenuView.WIDTH_DIP-PopupMenuView.ICON_WIDTH_DIP), 0);
        menuView.setOnMenuItemClickListener(new PopupMenuView.OnMenuItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(SampleActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
```
