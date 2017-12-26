package com.liliuhuan.com.simplyskill.rx;

/**
 * Created by 李刘欢
 * 2017/12/26
 * 描述:操作符接口
 */

public  interface IOperate {
    /**
     * 创建性
     */
    void create();// Create (表示只发送OnNext方法)

    void just();//just (将传入的参数依次发送出来)

    void fromIterable();//fromIterable (将Iterable中的对象依次发送出去)

    void timer();//Timer (它在一个给定的延迟后发射一个特殊的值，等同于Android中Handler的postDelay( )方法)

    void interval();//Interval (创建一个按固定时间间隔发射整数序列)

    void repeat();//repeat (创建一个重复发射特定数据的Observable)

    /***
     * 线程部分
     */
    void newThread();

    void ioThread();//用于一些耗时操作，比如读写文件，数据库存取，网络交互等。

    void mainThread();

    /**
     * 转化型
     */
    void map();//Map 对Observable发射的每一项数据都用一个函数来进行转换。

    void flatMap();//将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.如果要保证顺序的话，可以使用concatMap。

    void buffer();//buffer(long count, long skip) 把发送过来的数据收集成一个集合, 然后发送一个集合给下游

    void window();//window(long count, long skip) 类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable

    void groupBy();//groupBy 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据。

    /**
     * 过滤型
     */

    void filter();//filter 最基本的过滤

    void distince();// 去除重复的Observable

    void skip();//skip 跳过前几个数据的Observable

    void take();//take 只发射前几个数组的Observable

    void last();//last 只发射最后一项（或者满足某个条件的最后一项）数据，可以指定默认值

    void debounce();// 如果两次数据的发射间隔小于指定时间，就会丢弃前一次的数据。

    /**
     * 组合型
     */
    //使用场景: 实现多个接口数据共同更新 UI
    void zip();//zip 通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。

    //使用场景一: 当请求数据的时候,如果有缓存的话先从缓存中读取,如果缓存没有的话在从网络读取
    // 使用场景二: 当请求网络数据的时候,如果网络数据有错误的话,就展示缓存的数据
    void concat();//concat 是接收若干个Observables，发射数据是有序的，不会交叉 , 如果第一个发送onNext的话就不会走第二个Observable,如果第一个发送的是onComplete那么就走第二个Observable

    void merge();//merge 将多个Observable合并为一个。不同于concat，merge不是按照添加顺序连接，而是按照时间线来连接。把多个被观察者合并到一个被观察者身上一起输出，但是可能会让合并的被观察者发射的数据交错

    //使用场景一: 来做Android表单的校验 ,比如所有的数据不为空的时候,设置提交按钮可以点击
    void combineLatest();//用来将多个Observable发射的数据组装起来然后在发射

    void startWith();//startWith增加一点同类型的数据

    /**
     * 错误处理
     */
    void retry();//retry： 当原始Observable在遇到错误时进行重试。如果重复过后还是错误,就崩溃

    void retryWhen();//retryWhen 当原始Observable在遇到错误，将错误传递给另一个Observable来决定是否要重新订阅这个Observable

    void onErrorReturn();//onErrorReturn 当原始Observable在遇到错误时发射一个特定的数据

    //使用场景: 当请求数据的时候,假如token无效的话,也就是http返回码是401的时候,可以用这个重新请求token接口之后再请求数据
    void onErrorResumeNext();// 当遇到错误的时候,会返回一个新的Observable

    /**
     * 辅助型
     */
    void doOnNext();//doOnNext 让订阅者在接收到数据做一些事情。假如我们在获取到数据之前想先保存一下它

    void doAfterNext();//doAfterNext 顾名思义 跟doOnNext相反 实在收到数据之后的操作

    /**
     * 条件
     */
    void all();//all 把发射过来的所有数据,看看每一个是否满足条件,之后传递过去一个boolean值

    void contans();//contains 判断发射过来的数据是否包含某个数据,只要有一个就返回true

    void isEmpty();//isEmpty 判断是否有数据发送过来,如果有就是false 如果只发射了OnComplete就会返回true

    void takeUntil();// 发射的数据满足某个条件之后的就会终止发送;
}
