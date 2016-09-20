package vip.xuanhao.integration;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.schedulers.Timestamped;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_01() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }

    @Test
    public void test_02() {
        Observable.just(1, 2, 3, 4)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    @Test
    public void test_03() {
        Integer[] integers = new Integer[]{1, 2, 3, 4};
        Observable.from(integers)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    @Test
    public void test_03b() {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Observable.from(integers)
                .subscribe(new Action1<Integer>() {
                               @Override
                               public void call(Integer integer) {
                                   System.out.println(integer.toString());
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out.println("Error encountered: " + throwable.getMessage());
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                System.out.println("Sequence complete");
                            }
                        }
                );
    }

    @Test
    public void test_05() {
        Observable
                .interval(2000, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                               @Override
                               public void call(Long aLong) {
                                   System.out.println(aLong.toString());
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out.println("Error encountered: " + throwable.getMessage());
                            }
                        },
                        new Action0() {

                            @Override
                            public void call() {
                                System.out.println("Sequence complete");
                            }
                        });
    }

    @Test
    public void test_06() {
        Observable.range(10, 5)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer.toString());
                    }
                });
    }

    @Test
    public void test_07() {

        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(List<Integer> integers) {
                        System.out.println("-----------------");
                        return Observable.from(integers);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer.toString());
                    }
                });

    }

    @Test
    public void test_08() {
        Observable.just(1, 2, 3, 4, 5)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                });
    }

    @Test
    public void test_09() {

        Observable.just(1, 2, 3, 4)
                .startWith(0)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer.toString());
                    }
                });
    }

    @Test
    public void test_10() {
        Observable.just(1, 2, 3)
                .timestamp().subscribe(new Action1<Timestamped<Integer>>() {
            @Override
            public void call(Timestamped<Integer> integerTimestamped) {
                System.out.println(integerTimestamped.toString());
            }
        });
    }

    @Test
    public void test_11() {

        Proxy.newProxyInstance(TestDemo.class.getClassLoader(), new Class<?>[]{TestDemo.class}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Class<?> declaringClass = method.getDeclaringClass();
                System.out.println(declaringClass.getSimpleName());
                return null;
            }
        });
    }


    public interface TestDemo {
        void test();

    }

    @Test
    public void test_12() {

        final Observable<String> fromDb = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        String s = queryFormDb();
                        if (!s.equals("")) {
//                            System.out.println("fromDb  onNext");
                            subscriber.onNext(s);
                        }
                        subscriber.onCompleted();
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
//                        System.out.println("fromDb  map call");
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        return s.equals("");
//                    }
//                });
        final Observable<String> fromNet = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        String s = requestFromNet();
                        if (!s.equals("")) {
//                            System.out.println("fromNet  onNext");
                            subscriber.onNext(s);
                        }
                        subscriber.onCompleted();
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
//                        System.out.println("fromNet  map call");
                        return s;
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (!s.equals("")) {
//                            System.out.println("fromNet doOnNext call");
                            System.out.println( s +"存入数据库");
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());;

        Observable.concat(fromDb, fromNet)
                .subscribe(new Action1<String>() {
                               @Override
                               public void call(String s) {
                                   System.out.println(s);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out.println(throwable.toString());
                            }
                        });
    }


    public String queryFormDb() {

        return "来自数据库的数据";
    }

    public String requestFromNet() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "来自网络的数据";
    }


    @Test
    public void test_13() {

        Observable.just("hello")
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

    }

    @Test
    public void test14() {
        Random random = new Random();
        boolean b = random.nextBoolean();
        System.out.println(b);


        Observable<String> fromDB = Observable.just(b)
                .flatMap(new Func1<Boolean, Observable<String>>() {
                    @Override
                    public Observable<String> call(Boolean aBoolean) {
                        if (!aBoolean) {
                            return Observable.empty();
                        }
                        return Observable.just("来自数据库的数据");
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnCompleted");
                    }
                });


        Observable<String> fromNet = Observable.just("来自网络的数据")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return Observable.just(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        Observable.concat(fromDB, fromNet).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext  " + s);
            }
        });
    }

}