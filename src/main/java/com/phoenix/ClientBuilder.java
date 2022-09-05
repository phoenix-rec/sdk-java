package com.phoenix;


import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Setter
@Accessors(chain = true)
public class ClientBuilder {
    private final String defaultWriteUrl = "http://phoenix-api.icocofun.com/data/openapi/write/";
    private final String defaultRecUrl = "http://phoenix-api.icocofun.com/recommend/openapi/rec/";

    public  Integer projectId;
    public  Integer customerId;
    public  String ak;
    public  String sk;
    public  String recUrl;
    public  String writeUrl;

    public Client build() {
        if (Objects.isNull(projectId)) {
            throw new IllegalArgumentException("projectId is null");
        }
        if (Objects.isNull(customerId)) {
            throw new IllegalArgumentException("customerId is null");
        }
        if (Objects.isNull(ak)) {
            throw new IllegalArgumentException("ak is null");
        }
        if (Objects.isNull(sk)) {
            throw new IllegalArgumentException("sk is null");
        }
        if (Objects.isNull(recUrl)) {
            recUrl = defaultRecUrl;
        }
        if (Objects.isNull(writeUrl)) {
            writeUrl = defaultWriteUrl;
        }

        return new Client()
                .setProjectId(projectId)
                .setCustomerId(customerId)
                .setAk(ak)
                .setSk(sk)
                .setRecUrl(recUrl)
                .setWriteUrl(writeUrl);
    }
}
