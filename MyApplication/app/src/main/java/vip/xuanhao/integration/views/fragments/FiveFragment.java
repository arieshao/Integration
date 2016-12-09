package vip.xuanhao.integration.views.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.network.download.FileDownload;

/**
 * Created by Xuanhao on 2016/8/29.
 */

public class FiveFragment extends Fragment {

    @BindView(R.id.btn_download)
    Button btnDownload;

    private Context mContext;

    private OkHttpClient client;//= new OkHttpClient();

    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        this.mContext = getActivity();
        ButterKnife.bind(this, view);
        initNetConfig();
        initDialog();
        return view;
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setProgressStyle(android.R.style.Theme_Material_Light_Dialog_Alert);
    }

    private void initNetConfig() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .retryOnConnectionFailure(true)
                .build();

//        List<Interceptor> interceptors = new ArrayList<>();
//        interceptors.add(interceptor);
//        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
//        builder.setInterceptors(interceptors);
//        OkHttpFinal.getInstance().init(builder.build());

    }


    @OnClick(R.id.btn_download)
    public void onClick() {
        Toast.makeText(mContext, "download", Toast.LENGTH_SHORT).show();
        final String url = "http://dd.shouji.com.cn/app/soft/2016/20160804/sp3515015756.apk";

        final String targetFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/";
//        defultDownload(url);
        progressDialog.show();
        progressDialog.setMessage("请求连接...");

//        test();


        Observable.create(new Observable.OnSubscribe<FileDownload.FileInfo>() {
            @Override
            public void call(Subscriber<? super FileDownload.FileInfo> subscriber) {
                FileDownload download = new FileDownload(client, subscriber);
                download.download(url, targetFilePath);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onBackpressureBuffer()
                .subscribe(new Observer<FileDownload.FileInfo>() {
                    @Override
                    public void onCompleted() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.setMessage(e.toString());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onNext(FileDownload.FileInfo fileInfo) {
                        progressDialog.setMessage(fileInfo.getCurrentSize() + "/" + fileInfo.getFileSize());
                    }
                });


    }


    private void defultDownload(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    saveFile(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void rxDemo(String url) {
        Observable.just(url)
                .map(new Func1<String, Request>() {
                    @Override
                    public Request call(String s) {
                        Logger.w("first map >>>  " + s);
                        return new Request.Builder().url(s).build();
                    }
                })
                .map(new Func1<Request, Response>() {
                    @Override
                    public Response call(Request request) {
                        Logger.w("second map >>>  ");
                        Response response = null;
                        try {
                            response = client.newCall(request).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return response;
                    }
                })
                .filter(new Func1<Response, Boolean>() {
                    @Override
                    public Boolean call(Response response) {
                        return response.isSuccessful();
                    }
                })
                .flatMap(new Func1<Response, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(Response response) {
                        return Observable.just(saveFile(response));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                        if (aBoolean) {
                            Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private boolean saveFile(Response response) {
        try {
            ResponseBody body = response.body();
            long contentLength = body.contentLength();
            Logger.w(contentLength + "");
            BufferedSource source = body.source();
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.apk");
            BufferedSink sink = Okio.buffer(Okio.sink(file));
            long totalRead = 0;
            long read = 0;
            while ((read = (source.read(sink.buffer(), 2048))) != -1) {
                totalRead += read;
                int progress = (int) ((totalRead * 100) / contentLength);
                Logger.w(progress + "");
            }
            sink.writeAll(source);
            sink.flush();
            sink.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
