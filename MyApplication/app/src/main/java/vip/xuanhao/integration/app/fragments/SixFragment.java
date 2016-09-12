package vip.xuanhao.integration.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import vip.xuanhao.integration.R;

/**
 * Created by Xuanhao on 2016/8/8.
 */

public class SixFragment extends Fragment {


    @BindView(R.id.jz_cardnum)
    EditText jzCardnum;
    @BindView(R.id.jz_name)
    EditText jzName;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.cardid)
    EditText cardid;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.f_data1)
    TextView fData1;
    @BindView(R.id.f_am1)
    RadioButton fAm1;
    @BindView(R.id.f_pm1)
    RadioButton fPm1;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.lv_message)
    ListView lvMessage;


    private OkHttpClient client;

    private Subscription subscription;

    private List<String> logInfo = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.six_fragment, container, false);
        ButterKnife.bind(this, contentView);
        initView();
        return contentView;
    }

    private void initView() {
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,logInfo);
        lvMessage.setAdapter(adapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @OnClick(R.id.btn_start)
    public void onClick() {
        final OkHttpClient client = new OkHttpClient();
        subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                RequestBody body = new FormBody.Builder()
                        .add("login_name", "13175174701")
                        .add("password", "zxcdsaw")
                        .build();
                Request request = new Request.Builder()
                        .url("http://113.128.194.227:8000/yongkang/user.htm?action=login")
                        .header("User-Agent", "Mozilla/4.7 [en] (Win98; I)")
                        .post(body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        subscriber.onNext(response.header("Set-Cookie"));
                        subscriber.onNext(response.body().string());

                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Logger.w(s);
                        logInfo.add(s);
                        adapter.notifyDataSetChanged();
                        lvMessage.setSelection(0);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.w(throwable.toString());
                    }
                });


    }


    private void initNetConfig() {
        client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("login_name", "13175174701")
                .add("password", "zxcdsaw")
                .build();
        Request request = new Request.Builder()
                .url("http://113.128.194.227:8000/yongkang/user.htm?action=login")
                .header("User-Agent", "Mozilla/4.7 [en] (Win98; I)")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.w(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String responseCookie = response.header("Set-Cookie");
                    String json = response.body().string();
                    Logger.w(responseCookie);
                    Logger.w(json);
                    JSONObject obj = null;
                    String loginState = "";
                    try {
                        obj = new JSONObject(json);
                        loginState = obj.getString("R");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (loginState.equals("200")) {
                        Logger.w("登录成功! Cookie信息:" + responseCookie);
                        doGet(responseCookie);
                    } else if (loginState.equals("646")) {
                    } else if (loginState.equals("-1")) {
                    } else {
                    }
                }
            }
        });
    }

    private void doGet(final String responseCookie) {
        String url = "http://113.128.194.227:8000/yongkang/siteorder.htm?action%20=%20doctorOrderDetail&"
                + "dept_id=" + "2102"
                + "&doct_id=" + "001415"
                + "&date=" + "2016-08-11"
                + "&flag=" + "1" + "";

        Request request = new Request.Builder()
                .header("Cookie", responseCookie)
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();

                    Logger.w(string);
                    if (string.contains("有卡预约")) {
                        innerCardReservation(responseCookie);
                    }
                }
            }
        });
    }


    private void innerCardReservation(final String responseCookie) {
        String url = "http://113.128.194.227:8000/yongkang/siteorder.htm?action%20=%20orderFunc&" +
                "date=" + "2016-08-11"
                + "&time=&flag=" + "1"
                + "&type=0&imgUrl=http://qfsyy.zwjk.com:8000/doctorExpert/20151116/1447663819318.png";

        Request request = new Request.Builder()
                .header("Cookie", responseCookie)
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();

                    Logger.w(string);
                    if (string.contains("确认预约")) {

                        String time = "07:00~07:30";//默认时间
                        submitUserInfo(responseCookie, time);
//                        innerCardReservation(responseCookie);
                    } else {

                    }
                }
            }
        });
    }


    private void submitUserInfo(String responseCookie, String time) {
        String saveurl = "http://113.128.194.227:8000/yongkang/siteorder.htm?action%20=%20save";
        RequestBody body = new FormBody.Builder()
                .add("date", "13175174701")
                .add("time", "zxcdsaw")
                .add("name", "zxcdsaw")
                .add("id_card", "zxcdsaw")
                .add("phone", "zxcdsaw")
                .add("type", "zxcdsaw")
                .add("card", "zxcdsaw")
                .add("discrib", "zxcdsaw")
                .build();
        Request request = new Request.Builder()
                .url(saveurl)
                .header("Cookie", responseCookie)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String string = response.body().string();
                    try {
                        JSONObject info = new JSONObject(string);
                        String state = info.getString("R");

                        if (state.equals("200")) {
                            Logger.w("预约成功");
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
