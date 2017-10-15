package com.liliuhuan.com.simplyskill.menupop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.menupop.entity.DataEntity;
import com.liliuhuan.com.simplyskill.menupop.holder.SubjectHolder;

import java.util.ArrayList;
import java.util.List;

import static com.liliuhuan.com.simplyskill.R.id.subject;

/**
 *
 * 搜索菜单栏
 * Created by vonchenchen on 2016/4/5 0005.
 */
public class SelectMenuView extends LinearLayout{

    private static final int TAB_SUBJECT = 1;
    private static final int TAB_SORT = 2;
    private static final int TAB_SELECT = 3;

    private Context mContext;

    private View mSubjectView;
    private View mSortView;
    private View mSelectView;

    private View mRootView;
    
    private View mPopupWindowView;

    private RelativeLayout mMainContentLayout;
    private View mBackView;

    /** 科目 */
    private SubjectHolder mSubjectHolder;


    private OnMenuSelectDataChangedListener mOnMenuSelectDataChangedListener;

    private RelativeLayout mContentLayout;

    private TextView mSubjectText;
    private ImageView mSubjectArrowImage;
    private TextView mSortText;
    private ImageView mSortArrowImage;
    private TextView mSelectText;
    private ImageView mSelectArrowImage;

    private List<DataEntity> mGroupList;
    private List<DataEntity> mPrimaryList;
    private List<DataEntity> mJuniorList;
    private List<DataEntity> mHighList;
    private List<List<DataEntity>> mSubjectDataList;

    private int mTabRecorder = -1;

    public SelectMenuView(Context context) {
        super(context);
        this.mContext = context;
        this.mRootView = this;
        init();
    }

    public SelectMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mRootView = this;
        init();
    }

    private void init(){

        mGroupList = new ArrayList<>();
        mGroupList.add(new DataEntity(1,"A"));
        mGroupList.add(new DataEntity(2,"B"));
        mGroupList.add(new DataEntity(3,"C"));
        mPrimaryList = new ArrayList<>();
        mPrimaryList.add(new DataEntity(1,"A1"));
        mPrimaryList.add(new DataEntity(1,"A2"));
        mPrimaryList.add(new DataEntity(1,"A3"));
        mJuniorList = new ArrayList<>();
        mJuniorList.add(new DataEntity(1,"B1"));
        mJuniorList.add(new DataEntity(1,"B2"));
        mJuniorList.add(new DataEntity(1,"B3"));
        mJuniorList.add(new DataEntity(1,"B4"));
        mJuniorList.add(new DataEntity(1,"B5"));
        mJuniorList.add(new DataEntity(1,"B6"));
        mJuniorList.add(new DataEntity(1,"B7"));

        mHighList = new ArrayList<>();
        mHighList.add(new DataEntity(1,"C1"));
        mHighList.add(new DataEntity(1,"C2"));
        mHighList.add(new DataEntity(1,"C3"));
        mHighList.add(new DataEntity(1,"C4"));
        mHighList.add(new DataEntity(1,"C5"));
        mHighList.add(new DataEntity(1,"C6"));
        mHighList.add(new DataEntity(1,"C7"));
        mHighList.add(new DataEntity(1,"C8"));
        mHighList.add(new DataEntity(1,"C9"));


        mSubjectDataList = new ArrayList<List<DataEntity>>();
        mSubjectDataList.add(mGroupList);
        mSubjectDataList.add(mPrimaryList);
        mSubjectDataList.add(mJuniorList);
        mSubjectDataList.add(mHighList);


        //科目
        mSubjectHolder = new SubjectHolder(mContext);
        mSubjectHolder.refreshData(mSubjectDataList, 0, 0);
        mSubjectHolder.setOnRightListViewItemSelectedListener(new SubjectHolder.OnRightListViewItemSelectedListener() {
            @Override
            public void OnRightListViewItemSelected(int leftIndex, int rightIndex, String text) {
                if(mOnMenuSelectDataChangedListener != null){
                    int grade = leftIndex;
                    int subject = getSubjectId(rightIndex);
                    mOnMenuSelectDataChangedListener.onSubjectChanged(grade+"", subject+"");
                }

                dismissPopupWindow();
                //Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_SHORT).show();
                mSubjectText.setText(text);
            }

            @Override
            public void OnRightListViewSelectedData(DataEntity dataEntity) {
                mOnMenuSelectDataChangedListener.onSubjectChangedData(dataEntity);
            }
        });

    }

    private int getSubjectId(int index){
        return index;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(mContext, R.layout.layout_search_menu, this);

        mSubjectText = (TextView) findViewById(subject);
        mSubjectArrowImage = (ImageView) findViewById(R.id.img_sub);

        mSortText = (TextView) findViewById(R.id.comprehensive_sorting);
        mSortArrowImage = (ImageView) findViewById(R.id.img_cs);

        mSelectText = (TextView) findViewById(R.id.tv_select);
        mSelectArrowImage = (ImageView) findViewById(R.id.img_sc);

        mContentLayout = (RelativeLayout) findViewById(R.id.rl_content);

        mPopupWindowView = View.inflate(mContext, R.layout.layout_search_menu_content, null);
        mMainContentLayout = (RelativeLayout) mPopupWindowView.findViewById(R.id.rl_main);
        //mBackView = mPopupWindowView.findViewById(R.id.ll_background);

        mSubjectView = findViewById(R.id.ll_subject);
        mSortView = findViewById(R.id.ll_sort);
        mSelectView = findViewById(R.id.ll_select);

        //点击 科目 弹出菜单
        mSubjectView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnMenuSelectDataChangedListener != null){
                    mOnMenuSelectDataChangedListener.onViewClicked(mSubjectView);
                }
                handleClickSubjectView();
            }
        });
        mContentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupWindow();
            }
        });
    }

    private void handleClickSubjectView(){

        mMainContentLayout.removeAllViews();
        mMainContentLayout.addView(mSubjectHolder.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popUpWindow(TAB_SUBJECT);
    }



    private void popUpWindow(int tab){
        if(mTabRecorder != -1) {
            resetTabExtend(mTabRecorder);
        }
        extendsContent();
        setTabExtend(tab);
        mTabRecorder = tab;
    }

    private void extendsContent(){
        mContentLayout.removeAllViews();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentLayout.addView(mPopupWindowView, params);
    }

    private void dismissPopupWindow(){
        mContentLayout.removeAllViews();
        setTabClose();
    }

    public void setOnMenuSelectDataChangedListener(OnMenuSelectDataChangedListener onMenuSelectDataChangedListener){
        this.mOnMenuSelectDataChangedListener = onMenuSelectDataChangedListener;
    }

    public interface OnMenuSelectDataChangedListener{

        void onSubjectChanged(String grade, String subjects);
        void onSubjectChangedData(DataEntity dataEntity);
        void onSortChanged(String sortType);

        void onSelectedChanged(String gender, String classType);

        void onViewClicked(View view);

        //筛选菜单，当点击其他处菜单收回后，需要更新当前选中项
        void onSelectedDismissed(String gender, String classType);
    }

    private void setTabExtend(int tab){
        if(tab == TAB_SUBJECT){
            mSubjectText.setTextColor(getResources().getColor(R.color.blue));
            mSubjectArrowImage.setImageResource(R.mipmap.ic_up_blue);
        }else if(tab == TAB_SORT){
            mSortText.setTextColor(getResources().getColor(R.color.blue));
            mSortArrowImage.setImageResource(R.mipmap.ic_up_blue);
        }else if(tab == TAB_SELECT){
            mSelectText.setTextColor(getResources().getColor(R.color.blue));
            mSelectArrowImage.setImageResource(R.mipmap.ic_up_blue);
        }
    }

    private void resetTabExtend(int tab){
        if(tab == TAB_SUBJECT){
            mSubjectText.setTextColor(getResources().getColor(R.color.gray));
            mSubjectArrowImage.setImageResource(R.mipmap.ic_down);
        }else if(tab == TAB_SORT){
            mSortText.setTextColor(getResources().getColor(R.color.gray));
            mSortArrowImage.setImageResource(R.mipmap.ic_down);
        }else if(tab == TAB_SELECT){
            mSelectText.setTextColor(getResources().getColor(R.color.gray));
            mSelectArrowImage.setImageResource(R.mipmap.ic_down);
        }
    }

    private void setTabClose(){

        mSubjectText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSubjectArrowImage.setImageResource(R.mipmap.ic_down);

        mSortText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSortArrowImage.setImageResource(R.mipmap.ic_down);

        mSelectText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSelectArrowImage.setImageResource(R.mipmap.ic_down);
    }



    public void clearAllInfo(){
        //清除控件内部选项
        mSubjectHolder.refreshData(mSubjectDataList, 0, -1);


        //清除菜单栏显示
        mSubjectText.setText("type1");
        mSortText.setText("type2");
    }
}