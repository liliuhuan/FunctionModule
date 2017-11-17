package com.example.customer.myapplication.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.customer.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王瑞铭 on 2017/11/16.
 * 九宫格手势锁,可以设置错误次数，九宫格之间的间距，已经保存的密码
 * 动态帮你计算是解锁还是设置密码锁，可以设置最少连接的点数
 */

public class Lock9View extends ViewGroup {

    /**
     * 每行显示九宫格的个数
     */
    private static final int NODE_OF_NUMBER_ON_LINE = 3;
    /**
     * 记录高亮的宫格
     */
    private List<NodeView> higtNodeList = new ArrayList<>();

    /**
     * 九宫格之间的间隔
     */
    private int nodePadding;
    /**
     * 九宫格之间连线的颜色
     */
    private int nodeLineColor;
    /**
     * 九宫格之间连线的宽度
     */
    private int nodeLineWidth;
    /**
     * 解锁支持的最大错误次数
     */
    private int errorNum;
    /**
     * 当前密码.如果值是空的，那么默认是还没有设置密码，接下来的操作是设置密码的操作
     */
    private String lock = "";
    /**
     * 当前绘制的密码,用来和已有密码做比较，判断是否解锁成功，或者是设置成功
     */
    private String currentLock = "";
    /**
     * 标识是设置密码，还是解锁密码,默认是设置密码
     */
    private boolean isSetLock = true;
    /**
     * 设置至少要连接几个点
     */
    private int minNum;
    /**
     * 九宫格的半径
     */
    private int nodeRadius;

    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    private Drawable nodeNormalDrawable;

    private Drawable nodeHightDrawable;
    /**
     * 当前触摸的宫格
     */
    private NodeView currentNode;

    private OnLockFinishListener mListener;

    private Context context;

    private int erroNumber;//设置密码绘制次数
    //密码绘制的回调
    public interface OnLockFinishListener {
        void onSuccess(boolean isSetLock, String password);

        void onShort(int remainder);

        void onFailue(boolean isSetLock, String password);
    }

    public void setOnLockFinishListener(OnLockFinishListener listener) {
        this.mListener = listener;
    }

    public Lock9View(Context context) {
        this(context, null);
    }

    public Lock9View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Lock9View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Lock9View);
        nodePadding = (int) a.getDimension(R.styleable.Lock9View_nodePadding, 40);
        nodeLineColor = a.getColor(R.styleable.Lock9View_nodeLineColor, Color.parseColor("#000000"));
        nodeLineWidth = (int) a.getDimension(R.styleable.Lock9View_nodeLineWidth, 10);
        minNum = a.getInteger(R.styleable.Lock9View_minPoint, 4);
        errorNum = a.getInteger(R.styleable.Lock9View_errorNum, 5);
        nodeNormalDrawable = a.getDrawable(R.styleable.Lock9View_normalDrawable);
        nodeHightDrawable = a.getDrawable(R.styleable.Lock9View_hightDrawable);
        a.recycle();
        if (nodeNormalDrawable == null)
            nodeNormalDrawable = getResources().getDrawable(R.drawable.chushi);
        if (nodeHightDrawable == null)
            nodeHightDrawable = getResources().getDrawable(R.drawable.huizhi);
        removeAllViews();
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(nodeLineWidth);
        paint.setColor(nodeLineColor);
        paint.setAntiAlias(true);

        DisplayMetrics dm = getResources().getDisplayMetrics(); // bitmap的宽度是屏幕宽度，足够使用
        bitmap = Bitmap.createBitmap(dm.widthPixels, dm.widthPixels, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        for (int i = 0; i < 9; i++) {
            NodeView nodeView = new NodeView(context, i + 1);
            addView(nodeView);
        }
        if (lock.isEmpty()) {
            isSetLock = true;
        } else {
            isSetLock = false;
        }
        // 清除FLAG，否则 onDraw() 不会调用，原因是 ViewGroup 默认透明背景不需要调用 onDraw()
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        //父控件的宽度
        int width = getMeasuredWidth();
        nodeRadius = (width - nodePadding * (NODE_OF_NUMBER_ON_LINE + 1)) / 3 / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!changed) {
            return;
        }
        int cCount = getChildCount();
        if (cCount == 0)
            return;
        for (int i = 0; i < cCount; i++) {
            //每个宫格的大小
            int nodeWidth = nodeRadius * 2;
            NodeView nodeView = (NodeView) getChildAt(i);
            //行
            int raw = i / 3;
            //列
            int column = i % 3;
            int left = column * nodeWidth + nodePadding * (column + 1);
            int right = nodeWidth + left;
            int top = raw * nodeWidth + nodePadding * (raw + 1);
            int bottom = nodeWidth + top;
            nodeView.layout(left, top, right, bottom);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (errorNum < 1 || (!lock.isEmpty() && lock.equals(currentLock))) {
            //超过错误次数之后就不能继续绘制
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //按下和滑动
                //获取正在触摸的是哪个宫格
                NodeView touchNode = getTouchNode(event.getX(), event.getY());
                //如果触摸的超出宫格之外，则不做处理

                //判断这个过程中时候是否触摸到了宫格
                if (touchNode == null && currentNode == null) {
                    //还没有触摸到宫格
                    return true;
                }
                //清空已经绘制的线，并绘制之前已经触摸过的宫格之间的线
                drawTouchedLine();
                if (touchNode != null && currentNode == null) {
                    //首次触摸到宫格，
                    addTouchNodeToList(touchNode);
                } else if (touchNode == null && currentNode != null) {
                    //此时从一个宫格滑动到了宫格之外，此时需要画线
                    if (!isTouchOutside(event.getX(), event.getY()))
                        canvas.drawLine(currentNode.getCenterX(), currentNode.getCenterY(), event.getX(), event.getY(), paint);
                } else if (touchNode != null && currentNode != null) {
                    //从一个宫格滑动到了另一个宫格
                    canvas.drawLine(currentNode.getCenterX(), currentNode.getCenterY(), touchNode.getCenterX(), touchNode.getCenterY(), paint);
                    addTouchNodeToList(touchNode);
                }
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                //离开屏幕
                //清除高亮
                for (NodeView nodeView : higtNodeList) {
                    nodeView.setNormal(true);
                }
                //清空之前的绘制内容
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                //绘制结束的处理
                onTouchFinish();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void onTouchFinish() {
        if (higtNodeList.isEmpty()) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        for (NodeView nodeView : higtNodeList) {
            sb.append(nodeView.getNumber());
        }
        currentNode = null;
        higtNodeList.clear();
        if (isSetLock) {
            //设置密码，那么需要判断是否是首次绘制密码
            if (lock.isEmpty()) {
                //是首次绘制密码，只需要记录当前密码
                lock = sb.toString();
                if (lock.length() < minNum) {
                    lock = "";
                    //密码太短
                    onShort();
                }
            } else {
                //不是首次绘制密码，那么需要和之前的密码做比较
                currentLock = sb.toString();
                lockCallBack();
            }
        } else {
            //密码已经设置完成，本次是解锁
            currentLock = sb.toString();
            lockCallBack();
        }
    }

    private void lockCallBack() {
        if (lock.equals(currentLock)) {
            //两次密码绘制一样，密码设置成功，返回设置的密码
            onSuccess();
        } else {
            onError();
        }
    }

    private void onError() {
        errorNum--;
        if (isSetLock) {
            //重新初始化
            Toast.makeText(context, "密码绘制两次不一制,请重新绘制", Toast.LENGTH_LONG).show();
            errorNum++;
            lock="";
//            if (errorNum > 0)
//                Toast.makeText(context, "密码绘制错误,还可以设置" + errorNum + "次", Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(context, "密码绘制错误,本次设置密码失败，已锁定", Toast.LENGTH_LONG).show();
        } else {
            if (errorNum > 0)
                Toast.makeText(context, "密码解锁错误,还可以解锁" + errorNum + "次", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "密码绘制错误,本次解锁失败，已锁定", Toast.LENGTH_LONG).show();
        }
        if (mListener != null) {
            mListener.onFailue(isSetLock, currentLock);
        }
    }

    private void onSuccess() {
        if (isSetLock)
            Toast.makeText(context, "密码设置成功！！！", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "解锁成功！！！", Toast.LENGTH_LONG).show();
        if (mListener != null)
            mListener.onSuccess(isSetLock, currentLock);
    }

    private void onShort() {
        errorNum--;
        Toast.makeText(context, "密码太短了,请重新设置，还可以设置" + errorNum + "次", Toast.LENGTH_LONG).show();
        if (mListener != null)
            mListener.onShort(errorNum);
    }

    /**
     * 手势滑动过程中是否滑动到了九个宫格之外
     *
     * @return
     */
    private boolean isTouchOutside(float x, float y) {
        int cCount = getChildCount();
        if (cCount == 0)
            return true;
        int left = nodePadding;
        int right = nodePadding * 3 + nodeRadius * 2 * 3;
        if (x >= left && x <= right && y >= left && y <= right) {
            return false;
        }
        return true;
    }

    private void addTouchNodeToList(NodeView touchNode) {
        currentNode = touchNode;
        currentNode.setNormal(false);
        if (!higtNodeList.contains(currentNode))
            higtNodeList.add(currentNode);
    }

    private void drawTouchedLine() {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        if (higtNodeList.size() >= 2) {
            //至少两个宫格才绘制线
            for (int i = 0; i < higtNodeList.size(); i++) {
                if (i + 1 < higtNodeList.size()) {
                    NodeView first = higtNodeList.get(i);
                    NodeView second = higtNodeList.get(i + 1);
                    canvas.drawLine(first.getCenterX(), first.getCenterY(), second.getCenterX(), second.getCenterY(), paint);
                }
            }
        }
    }

    /**
     * 获取当前正在触摸的宫格
     *
     * @return
     */
    private NodeView getTouchNode(float x, float y) {
        int index = -1;
        for (int i = 0; i < getChildCount(); i++) {
            NodeView nodeView = (NodeView) getChildAt(i);
            if (x > nodeView.getCenterX() - nodeRadius && x < nodeView.getCenterX() + nodeRadius && y > nodeView.getCenterY() - nodeRadius && y < nodeView.getCenterY() + nodeRadius) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Log.e("======index=====", "=====" + index);
            return null;
        }
        return (NodeView) getChildAt(index);
    }

    public void setErrorNum(int num) {
        this.errorNum = num;
    }

    /**
     * 设置已有密码
     *
     * @param lock
     */
    public void setLock(String lock) {
        this.lock = lock;
        this.isSetLock = false;
    }

    //九宫格中的每个宫格
    private class NodeView extends View {
        //记录对应的中心点坐标，半径，编号，当前状态（默认or高亮）

        private float centerX;

        private float centerY;

        private int number;

        private boolean isNormal;

        public NodeView(Context context) {
            super(context);
            isNormal = true;

            setBackground();
        }

        public NodeView(Context context, int num) {
            this(context);
            this.number = num;
        }

        //设置九宫格的背景色
        private void setBackground() {
            if (isNormal) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    setBackground(nodeNormalDrawable);
                } else {
                    setBackgroundResource(R.drawable.chushi);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    setBackground(nodeHightDrawable);
                } else {
                    setBackgroundResource(R.drawable.huizhi);
                }
            }
        }

        public float getCenterX() {
            centerX = (getRight() + getLeft()) / 2;
            return centerX;
        }

        public float getCenterY() {
            centerY = (getBottom() + getTop()) / 2;
            return centerY;
        }

        public int getNumber() {
            return number;
        }

        public boolean isNormal() {
            return isNormal;
        }

        public void setNormal(boolean normal) {
            isNormal = normal;
            setBackground();
        }
    }

}
