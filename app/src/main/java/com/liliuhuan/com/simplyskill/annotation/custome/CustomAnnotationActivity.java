package com.liliuhuan.com.simplyskill.annotation.custome;

import android.util.Log;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomAnnotationActivity extends BaseActivity {
    @BindView(R.id.tv_show)
    TextView tvShow;
    private String TAG = CustomAnnotationActivity.class.getSimpleName();
    private IReqApi iReqApi;
    StringBuilder sb;
    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_annotation;
    }

    @Override
    public void initView() {
        iReqApi = create(IReqApi.class);
        sb = new StringBuilder();
    }


    public <T> T create(final Class<T> service) {
        Class<? extends Class> aClass = service.getClass();
//        Field[] fields = aClass.getFields();
//        Method[] methods1 = aClass.getMethods();
//        if (fields!=null){
//            for (Field field:fields) {
//                ReqType type = field.getAnnotation(ReqType.class);
//               field.set();
//
//            }
//        }
//
        Method[] methods = aClass.getMethods();
        if (methods!=null){
            for (Method method:methods) {
                ReqType typeMethod = method.getAnnotation(ReqType.class);
                //method.setAccessible(true);
              //  method.invoke(aClass)
            }
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {// Annotation[]  methodAnnotations = method.getAnnotations();//拿到函数注解数组
                        ReqType reqType = method.getAnnotation(ReqType.class);
                        sb.append("IReqApi---reqType->" + (reqType.reqType() == ReqType.ReqTypeEnum.POST ? "POST" : "OTHER")+"\n");
                        Log.e(TAG, "IReqApi---reqType->" + (reqType.reqType() == ReqType.ReqTypeEnum.POST ? "POST" : "OTHER"));
                        ReqUrl reqUrl = method.getAnnotation(ReqUrl.class);
                        sb.append("IReqApi---reqUrl->" + reqUrl.reqUrl()+"\n");
                        Log.e(TAG, "IReqApi---reqUrl->" + reqUrl.reqUrl());
                        Type[] parameterTypes = method.getGenericParameterTypes();
                        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();//拿到参数注解
                        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                            Annotation[] annotations = parameterAnnotationsArray[i];
                            if (annotations != null) {
                                ReqParam reqParam = (ReqParam) annotations[0];
                                Log.e(TAG, "reqParam---reqParam->" + reqParam.value() + "==" + args[i]);
                                sb.append("reqParam---reqParam->" + reqParam.value() + "==" + args[i]+"\n");
                            }
                        }
                        //下面就可以执行相应的网络请求获取结果 返回结果
                        String result = "";//这里模拟一个结果

                        return result;
                    }
                });
    }


    @OnClick(R.id.tv_btn)
    public void onViewClicked() {
        if (iReqApi != null) iReqApi.login("zhangsan", "12345");
        if (sb != null)tvShow.setText(sb);
    }

}
