package com.liliuhuan.com.simplyskill.annotation.custome.ioc;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.liliuhuan.com.simplyskill.aop.net.NetworkManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author :李刘欢
 * DATA : 2017-12-21
 * DES :
 */

public class ViewUtils {
    public static void inject(Activity activity) {
        // TODO: 2017/12/21  ViewFinder 这个类怎么怎么写不知道 
        inject(new ViewFinder(activity), activity);
    }

    // 兼容View
    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    // 兼容Fragment
    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    private static void inject(ViewFinder viewFinder, Object object) {
        injectFiled(viewFinder, object);
        injectEvent(viewFinder, object);
        injectCheckNet(object);
        injectCheckNetClass(object);

    }

    private static void injectCheckNetClass(Object object) {
        // 1.获取所有方法
        Class<?> clazz = object.getClass();
        Context mcontext = null;
        if (object instanceof  Activity){
            mcontext = (Context) object;
        }
        if (object instanceof Fragment){
            mcontext = ((Fragment) object).getActivity();
        }

        if (!NetworkManager.isNetworkConnected(mcontext)) {

        } else {

        }
    }

    private static void injectCheckNet(Object object) {
        // 1.获取所有方法
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        // 2.获取方法上面的所有id
        for (Method method : methods) {
            CheckNet checkNet = method.getAnnotation(CheckNet.class);
            if (checkNet != null) {
                Context mcontext = null;
                if (object instanceof  Activity){
                    mcontext = (Context) object;
                }
                if (object instanceof Fragment){
                    mcontext = ((Fragment) object).getActivity();
                }

                if (!NetworkManager.isNetworkConnected(mcontext)) {
                    Toast.makeText(mcontext,"您还没有链接网络",Toast.LENGTH_LONG).show();
                } else {
                    // 4.反射执行方法
                    method.setAccessible(true);
                    try {
                        method.invoke(object,null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 注入属性
     */
    private static void injectFiled(ViewFinder viewFinder, Object object) {
// object --> activity or fragment or view 是反射的类
        // viewFinder --> 只是一个view的findViewById的辅助类

        // 1. 获取所有的属性
        Class<?> clazz = object.getClass();
        // 获取所有属性包括私有和公有
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // 2. 获取属性上面ViewById的值
            ViewById viewById = field.getAnnotation(ViewById.class);

            if (viewById != null) {
                // 获取ViewById属性上的viewId值
                int viewId = viewById.value();
                // 3. 通过findViewById获取View
                View view = viewFinder.findViewById(viewId);

                if (view != null) {
                    // 4. 反射注入View属性
                    // 设置所有属性都能注入包括私有和公有
                    field.setAccessible(true);
                    try {
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new RuntimeException("Invalid @ViewInject for "
                            + clazz.getSimpleName() + "." + field.getName());
                }
            }
        }
    }
    // 事件注入
    private static void injectEvent(ViewFinder viewFinder, Object object) {
        // 1.获取所有方法
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        // 2.获取方法上面的所有id
        for (Method method : methods) {
            OnClickNet onClick = method.getAnnotation(OnClickNet.class);
            if (onClick != null) {
                int[] viewIds = onClick.value();
                if (viewIds.length > 0) {
                    for (int viewId : viewIds) {
                        // 3.遍历所有的id 先findViewById然后 setOnClickListener
                        View view = viewFinder.findViewById(viewId);
                        if (view != null) {
                            view.setOnClickListener(new DeclaredOnClickListener(method, object));
                        }
                    }
                }
            }
        }
    }

    private static class DeclaredOnClickListener implements View.OnClickListener {
    private Method mMethod;
    private Object mHandlerType;

    public DeclaredOnClickListener(Method method, Object handlerType) {
        mMethod = method;
        mHandlerType = handlerType;
    }

    @Override
    public void onClick(View v) {
        // 4.反射执行方法
        mMethod.setAccessible(true);
        try {
            mMethod.invoke(mHandlerType, v);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                mMethod.invoke(mHandlerType, new Object[]{});
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

}
