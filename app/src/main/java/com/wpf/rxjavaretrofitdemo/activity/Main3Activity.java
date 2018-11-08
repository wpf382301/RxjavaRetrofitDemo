package com.wpf.rxjavaretrofitdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.wpf.rxjavaretrofitdemo.R;
import com.wpf.rxjavaretrofitdemo.mock.Mock;
import com.wpf.rxjavaretrofitdemo.net.HttpManager;
import com.wpf.rxjavaretrofitdemo.net.MockService;
import com.wpf.rxjavaretrofitdemo.widget.EmptyRecyclerView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.main3_recycler)
    EmptyRecyclerView main3Recycler;
    @BindView(R.id.main3_tx)
    TextView main3Tx;
    private MockWebServer mockWebServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\n" +
                "  \"status\": 200,\n" +
                "  \"msg\": \"success\",\n" +
                "  \"data\": [{\n" +
                "    \"postid\": \"2017-06-06 15:26\",\n" +
                "    \"message\": \"sadasd\"" +
                "  }]\n" +
                "}"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mockWebServer.start(8080);
                    mockWebServer.url("http://127.0.0.1:8080/api/test");
                    MockService mockService = HttpManager.getInstance().getMockService();
                    Call<Mock> mockCall = mockService.getList("test");
                    mockCall.enqueue(new Callback<Mock>() {
                        @Override
                        public void onResponse(Call<Mock> call, final Response<Mock> response) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("1111", "run: " + response.body().getMsg());
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Mock> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }
}
