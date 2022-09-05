package com.phoenix;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.phoenix.Util.getRandomString;
import static com.phoenix.Util.getSha256Str;

@Accessors(chain = true)
@Setter
@Getter
public class Client {
    private  Integer projectId;
    private  Integer customerId;
    private  String ak;
    private  String sk;
    private  String recUrl;
    private  String writeUrl;
    
    public String writeData(String topic ,String stage,List<Map<String, Object>> dataList,Option... opts) throws IOException {
        String url = this.getWriteUrl() + this.getProjectId() + "/" + topic+ "/" + stage;
        return apiRequest(url, Option.conv2Options(opts).getRequestId(), JSON.toJSONString(dataList));
    }

    public String rec(Integer scene, Struct.user user, Option... opts) throws IOException {
        String url = this.getRecUrl() + this.getProjectId();
        Struct.recReq param = new Struct.recReq()
                .setCustom_id(this.getCustomerId())
                .setProject_id(this.getProjectId())
                .setScene_id(scene)
                .setUser(user);
        return apiRequest(url,Option.conv2Options(opts).getRequestId(), JSON.toJSONString(param));
    }

    public String apiRequest(String url, String requestId ,String data) throws IOException {
        String timeNow = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = getRandomString(8);
        if (requestId == null) {
            requestId = getRandomString(16);
        }
        String signature = getSha256Str(this.getCustomerId() + this.getAk() + this.getSk()
                + timeNow + nonce + data);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), data);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Customer-Id", this.getCustomerId().toString())
                .addHeader("Access-Id", this.getAk())
                .addHeader("Time", timeNow)
                .addHeader("Nonce", nonce)
                .addHeader("Request-Id", requestId)
                .addHeader("Signature", signature)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
