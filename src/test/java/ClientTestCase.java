import com.phoenix.Client;
import com.phoenix.ClientBuilder;
import com.phoenix.Option;
import com.phoenix.Struct;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class ClientTestCase {
    @Test
    public void Rec() throws IOException {
        Integer CustomerId = 1;
        Integer ProjectId = 10000001;
        String Ak = "accessId";
        String Sk = "secretId";
        String RecUrl = "http://phoenix-api.icocofun.com/recommend/openapi/rec/";

        Client client = new ClientBuilder()
                .setCustomerId(CustomerId)
                .setProjectId(ProjectId)
                .setAk(Ak)
                .setSk(Sk)
                .setRecUrl(RecUrl)
                .build();

        Struct.user req = new Struct.user()
                .setUser_id("10001")
                .setDid("86c9c0fe3965f489")
                .setIp("127.0.0.1")
                .setChannel("HuaiWei")
                .setNetwork(1)
                .setOs("Android")
                .setModel("HUAWEI P40");

        String reqResult = client.rec(10001,req, Option.withRequestId(UUID.randomUUID().toString()));
        System.out.println(reqResult);
    }

    @Test
    public void writeItem() throws IOException {
        Integer CustomerId = 1;
        Integer ProjectId = 10000001;
        String Ak = "accessId";
        String Sk = "secretId";
        String WriteUrl = "http://phoenix-api.icocofun.com/data/openapi/write/";

        Client client = new ClientBuilder()
                .setCustomerId(CustomerId)
                .setProjectId(ProjectId)
                .setAk(Ak)
                .setSk(Sk)
                .setWriteUrl(WriteUrl)
                .build();

        // 1. 构造单个item
        Map<String, Object> data = new HashMap<>();
        data.put("x_item_id", "1");
        data.put("x_status", 1);

        // 2. 构造item数组
        List<Map<String, Object>> dataList = new ArrayList<>(Collections.singletonList(data));

        // 3. 构造请求
        String result = client.writeData("item","test",dataList);
        System.out.println(result);
    }
}
