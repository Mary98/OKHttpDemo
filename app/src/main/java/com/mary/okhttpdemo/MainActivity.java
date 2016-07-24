package com.mary.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient mOKHttpClient = new OkHttpClient();
    private String url = "http://v.juhe.cn/boxoffice/rank.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doGet();



    }

    /**
     *
     */
    private void doGet() {
        final TextView tvTip = (TextView) this.findViewById(R.id.id_tv_Tip);
        final Request request = new Request.Builder()
                .url(url)
                //.header("key", "8d068ee7b9729d6c831862068beaeb6a")
                //.header("area", "CN")
                .build();
        Call call = mOKHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final StringBuilder sb = new StringBuilder();
                sb.append("response.body().string() = " + response.body().string() + "; ");
                sb.append("Call = " + call.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTip.setText(sb.toString());
                    }
                });
                Log.e("Mary", sb.toString());
            }
        });
    }

    /**
     *
     */
    private void doPost() {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{\"username\":\"lisi\",\"nickname\":\"李四\"}");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = mOKHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final StringBuilder sb = new StringBuilder();
                sb.append("response.body().string() = " + response.body().string() + "; ");
                sb.append("Call = " + call.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // tvTip.setText(sb.toString());
                    }
                });
                Log.e("Mary", sb.toString());
            }
        });
    }
}
