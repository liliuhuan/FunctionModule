package com.liliuhuan.a50aftercharacter.android5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.widget.TextView;

import com.liliuhuan.a50aftercharacter.BaseActivity;
import com.liliuhuan.a50aftercharacter.R;

public class PaletteActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_palette;
    }

    @Override
    protected void handleToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("Palette");
        //toolbarHelper.getToolbar().setNavigationIcon(null);
    }

    @Override
    protected void initView() {
        final TextView textView = (TextView) findViewById(R.id.text_view);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        //同步获取，需要在子线程中使用
        Palette palette = Palette.from(bitmap).generate();
        //异步获取，可以在主线程中使用
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
              //  textView.setBackgroundColor(vibrantSwatch.getRgb());
                textView.setTextColor(vibrantSwatch.getRgb());
            }
        });
        /*
        vibrant      -  有活力的颜色
        lightVibrant -  有活力的亮色
        darkVibrant  -  有活力的暗色
        muted        -  柔和暗淡的颜色
        lightMuted   -  柔和的亮色
        darkMuted    -  柔和的暗色
         */

        //我们可以直接使用palette获取指定颜色：
      //  palette.getLightMutedColor(5000);

        //一般也可以先获取采样对象Swatch,从Swatch中获取我们需要的颜色:
        //获取有活力颜色的采样对象
      //  Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

        //swatch.getPopulation(): the amount of pixels which this swatch represents.
        //swatch.getRgb(): the RGB value of this color.
        //swatch.getHsl(): the HSL value of this color，即色相，饱和度，明度.
        //swatch.getBodyTextColor(): the RGB value of a text color which can be displayed on top of this color.
        //swatch.getTitleTextColor(): the RGB value of a text color which can be displayed on top of this color
        //一般会将getRgb设置给控件背景色，getBodyTextColor()设置给文字颜色
//        textView.setBackgroundColor(vibrantSwatch.getRgb());
//        textView.setTextColor(vibrantSwatch.getBodyTextColor());
    }
}