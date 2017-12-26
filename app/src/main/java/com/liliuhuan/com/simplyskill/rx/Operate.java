package com.liliuhuan.com.simplyskill.rx;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 李刘欢
 * 2017/12/26
 * 描述:操作符实现类
 */

public class Operate extends InterOperate {

    private static final String TAG = Operate.class.getSimpleName();

    @Override
    public void create() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG + ":create==", integer + "");
            }
        });
    }

    @Override
    public void just() {
        Observable.just(1, 2, 3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG + ":just==", integer + "");
            }
        });
    }

    @Override
    public void fromIterable() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList.add("" + i + i);
        }

        Observable.fromIterable(arrayList).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG + ":fromIterable==", s);
            }
        });
    }

    @Override
    public void timer() {
        final long startTime = System.currentTimeMillis();
        Observable.timer(1000, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                long endTime = System.currentTimeMillis();
                Log.e(TAG + ":timer==时间差", (endTime - startTime) + "ms");
            }
        });
    }

    @Override
    public void interval() {
        Observable.interval(1000, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG ,":interval=="+aLong + "");

            }
        });
    }

    @Override
    public void repeat() {
        Observable.just(1).repeat(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG + ":repeat==", integer + "");
            }
        });
    }

    @Override
    public void newThread() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.i(TAG, "发布的线程是:" + Thread.currentThread().getName());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "订阅的线程是:" + Thread.currentThread().getName());
                    }
                });
    }

    @Override
    public void ioThread() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.i(TAG, "发布的线程是:" + Thread.currentThread().getName());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "订阅的线程是:" + Thread.currentThread().getName());
                    }
                });
    }

    @Override
    public void mainThread() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.i(TAG, "发布的线程是:" + Thread.currentThread().getName());
            }
        })
                .subscribeOn(Schedulers.io())//以上内容线程
                .observeOn(AndroidSchedulers.mainThread())//以下内容线程
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "订阅的线程是:" + Thread.currentThread().getName());
                    }
                });
    }

    @Override
    public void map() {
        Observable.just(1, 2, 3, 4).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer * 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG + ":map==", integer + "");
            }
        });
    }

    @Override
    public void flatMap() {
        Observable.just(1, 2, 3, 4).flatMap(new Function<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> apply(Integer integer) throws Exception {
                ArrayList<Integer> integers = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    integers.add(i);
                }
                return Observable.fromIterable(integers);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                Log.e(TAG + ":flatMap==", s + "");
            }
        });
        /* 0 1 2 0 1 2 0 1 2 0 1 2
        * */
    }

    @Override
    public void buffer() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).buffer(4, 2).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                Log.e(TAG, "buffer:" + integers);
            }
        });
    }

    @Override
    public void window() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .window(2, 3)
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {
                        Log.e(TAG, "window:" + integerObservable);
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e(TAG, "window:" + integer);
                            }
                        });
                    }
                });
    }

    @Override
    public void groupBy() {
        Observable.just(1, 2, 3, 4, 5).groupBy(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer % 2 == 0 ? "偶数" : "奇数";
            }
        }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "groupby:" + stringIntegerGroupedObservable.getKey() + "----" + integer);
                    }
                });
            }
        });
        //奇数----1 偶数-----2
    }


    @Override
    public void filter() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "filter--大于2的数据有:" + integer);
                    }
                });
    }

    @Override
    public void distince() {
        Observable.just(1, 1, 3, 3, 5, 6)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "distinct--去除重复的数据:" + integer);
                    }
                });
        /**1356**/
    }

    @Override
    public void skip() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "skip--跳过的数据:" + integer);
                    }
                });

        //3456
    }

    @Override
    public void take() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .take(2)
                //          .take(1000, TimeUnit.MICROSECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "take--发射的前两个数据:" + integer);
                    }
                });
        //12
    }

    @Override
    public void last() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .last(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "last--最后的数据:" + integer);
                    }
                });
        //6
    }

    @Override
    public void debounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                Thread.sleep(2000);
                emitter.onNext(6);
                emitter.onNext(7);
                emitter.onComplete();
            }
        }).debounce(1000, TimeUnit.MICROSECONDS).subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "debounce--:" + integer);
            }
        });
        //57
    }


    @Override
    public void zip() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
        Observable<Integer> observable2 = Observable.just(1, 2, 3);
        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2 + "";
            }
        }).subscribe(new Consumer<String>() {

            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "zip合并后的数据是:" + s);
            }
        });
        //2 4 6
    }

    @Override
    public void concat() {
        Observable<Integer> observable3 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (true) {
                    //假如有缓存就走这里
                    e.onNext(1);
                } else {
                    //假如没有缓存就走这里
                    e.onComplete();
                }
            }
        });
        Observable<Integer> observable4 = Observable.just(1, 2, 3);
        Observable.concat(observable3, observable4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "concat的数据是:" + integer);
            }
        });
        //true 1 false 1 2 3
    }

    @Override
    public void merge() {
        Observable<Integer> observable3 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (true) {
                    e.onNext(1);
                } else {
                    e.onComplete();
                }
            }
        });
        Observable<Integer> observable4 = Observable.just(1, 2, 3);
        Observable.concat(observable3, observable4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "concat的数据是:" + integer);
            }
        });
        //4 1 2 3 5 6
    }

    @Override
    public void combineLatest() {
        Observable<Integer> observable7 = Observable.just(1, 2, 3);
        Observable<Integer> observable8 = Observable.just(4, 5, 6, 7);
        Observable.combineLatest(observable7, observable8, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                //integer 这个就是observable7中最后发的一个元素
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "combineLatest的数据是:" + integer);
            }
        });
        //7 8 9 10
    }

    @Override
    public void startWith() {
        Observable.just(1, 2, 3).startWith(0).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "startWith添加后的数据是:" + integer);
            }
        });
        //0 1 2 3
    }

    @Override
    public void retry() {
        Observable.just(1, "2", 3)
                .cast(Integer.class)
                .retry(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "retry重试数据" + integer);
                    }
                });

    }

    @Override
    public void retryWhen() {
        Observable.just(1, "2", 3)
                .cast(Integer.class)
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return Observable.just(4, 5);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "retryWhen重试数据" + integer);
                    }
                });
        //1  1
    }

    @Override
    public void onErrorReturn() {
        Observable.just(1, "2", 3)
                .cast(Integer.class)
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        return 0;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "onErrorReturn 出现错误之后的默认数据" + integer);
            }
        });
        // 1 0
    }

    @Override
    public void onErrorResumeNext() {
        Observable.just(1, "2", 3)
                .cast(Integer.class)
                .onErrorResumeNext(new Function<Throwable, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Throwable throwable) throws Exception {

                        return Observable.just(1);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "onErrorResumeNext 出现错误之后的默认数据" + integer);
            }
        });

    }


    @Override
    public void doOnNext() {
        Observable.just(1, 2, 3).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "doOnNext接受之前:" + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "doOnNext接受到的数据:" + integer);
            }
        });

    }

    @Override
    public void doAfterNext() {
        Observable.just(1, 2, 3).doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "doAfterNext接受之后:" + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "doAfterNext接受到的数据:" + integer);
            }
        });
    }

    @Override
    public void all() {
        Observable.just(1, 2, 3).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 2;
            }
        }).subscribe(new BiConsumer<Boolean, Throwable>() {
            @Override
            public void accept(Boolean aBoolean, Throwable throwable) throws Exception {
                Log.e(TAG, "ALL===" + aBoolean);
            }
        });
    }

    @Override
    public void contans() {
        Observable.just(1, 2, 3, 4).contains(2).subscribe(new BiConsumer<Boolean, Throwable>() {
            @Override
            public void accept(Boolean aBoolean, Throwable throwable) throws Exception {
                Log.e(TAG, "contains===" + aBoolean);
            }
        });
    }

    @Override
    public void isEmpty() {
        Observable.just(1, 2, 3).isEmpty().subscribe(new BiConsumer<Boolean, Throwable>() {
            @Override
            public void accept(Boolean aBoolean, Throwable throwable) throws Exception {
                Log.e(TAG, "contains===" + aBoolean);
            }
        });
    }

    @Override
    public void takeUntil() {
        Observable.just(1, 2, 3).takeUntil(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "takeUntil -->:" + integer);
            }
        });
    }
    @Override
    public void typeCreate() {
        create();
        just();
        fromIterable();
        timer();
        interval();
        repeat();
    }
    @Override
    public void typeThread() {
        newThread();
        ioThread();
        mainThread();
    }
    @Override
    public void typeTrans() {
        map();
        flatMap();
        buffer();
        window();
        groupBy();
    }

    @Override public void typeFilter() {
        filter();
        distince();
        skip();
        take();
        last();
        debounce();
    }
    @Override
    public void typeGroup() {
        zip();
        concat();
        merge();
        combineLatest();
        startWith();
    }
    @Override
    public void typeError() {
        retry();
        retryWhen();
        onErrorReturn();
        onErrorResumeNext();
    }
    @Override
    public void typeAssist() {
        doOnNext();
        doAfterNext();
    }
    @Override
    public void typeCondaction() {
        all();
        contans();
        isEmpty();
        takeUntil();
    }
}
