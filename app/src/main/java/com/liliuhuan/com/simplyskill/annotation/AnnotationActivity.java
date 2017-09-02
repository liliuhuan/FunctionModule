package com.liliuhuan.com.simplyskill.annotation;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import java.util.List;

import butterknife.BindBitmap;
import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

//注解
public class AnnotationActivity extends BaseActivity {

//    @BindView(R.id.button1)
//    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindViews({R.id.button1,R.id.button2})
    List<Button> buttons;

    @BindString(R.string.app_name)
    String butterKnifeStr;//string注解使用

    @BindDrawable(R.mipmap.ic_launcher)
    Drawable butterKnifeDrawable;//Drawable注解使用
    @BindBitmap(R.mipmap.ic_launcher)
    Bitmap butterKnifeBitmap;;//Bitmap注解使用
//    @BindArray(R.array.day_of_week)
//    String weeks[];//数组
    @BindColor(R.color.colorPrimary)
    int colorPrimary;//color注解使用
//    @BindDimen(R.dimen.activity_horizontal_margin)
//    Float spacer;


    @Override
    public int getLayoutId() {
        return R.layout.activity_annotation;
    }
    /**
     * 两个不同的button都相应onButterKnifeBtnClick事件回调
     *
     * @param button
     */
    @OnClick({R.id.button1, R.id.button2})
    public void onButterKnifeBtnClick(Button button) {
       // Log.e(TAG, "onButterKnifeBtnClick");
    }

    //注解来标记field和方法,让注入变成选择性的,如果targetView存在,则注入, 不存在,则什么事情都不做.
    @Nullable
    @BindView(R.id.button1)
    TextView button1;

    @Optional
    @OnClick(R.id.button1)
    void onMaybeMissingClicked() {
        // TODO ...
        ButterKnife.apply(button1, DISABLE);
        ButterKnife.apply(button1, ENABLED, false);
        ButterKnife.apply(button1, View.ALPHA, 0.0f);
    }

    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    /**
     * Support Annotations分类：
     * @Nullable @NullLess
     * AnimatorRes ：animator资源类型
    AnimRes：anim资源类型
    AnyRes：任意资源类型
    ArrayRes：array资源类型
    AttrRes：attr资源类型
    BoolRes：boolean资源类型
    ColorRes：color资源类型
    DimenRes：dimen资源类型。
    DrawableRes：drawable资源类型。
    FractionRes：fraction资源类型
    IdRes：id资源类型
    IntegerRes：integer资源类型
    InterpolatorRes：interpolator资源类型
    LayoutRes：layout资源类型
    MenuRes：menu资源类型
    PluralsRes：plurals资源类型
    RawRes：raw资源类型
    StringRes：string资源类型
    StyleableRes：styleable资源类型
    StyleRes：style资源类型
    TransitionRes：transition资源类型
    XmlRes：xml资源类型


    方法前绑定线程
     @UiThread UI线程
     @MainThread 主线程
     @WorkerThread 子线程
     @BinderThread  绑定线程

     Value Constraints注解：@Size, @IntRange, @FloatRange
     集合不能为空: @Size(min=1)
     字符串最大只能有23个字符: @Size(max=23)
     数组只能有2个元素: @Size(2)
     数组的大小必须是2的倍数 (例如图形API中获取位置的x/y坐标数组: @Size(multiple=2)


     Dagger2 annotation讲解

     @Module 修饰的类专门用来提供依赖

     @Provides 修饰的方法用在Module类里

     @Inject  修饰需要依赖的地方（可以是构造方法、field或者一般的方法）

     @Component 连接@Module和注入的桥梁
     1.首先定义缓存类和多任务类。并且在其构造函数上添加@Inject注解
     2.使用@Module分别定义LCacheModule、LExecutorModule类来提供相关依赖
     3.使用@Component 用来将@Inject和@Module关联起来，新建LCacheComponent类
     4.在宿主中注入想要依赖的对象
     LCacheManager.getInstance().saveCache("key","who is lcj ?");
     */
}
