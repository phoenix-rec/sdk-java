package com.phoenix;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
public class Struct {
    @Accessors(chain = true)
    @Setter
    public static class user {
        public String user_id;
        public String did;
        public String ip;
        public String channel;
        public int network;
        public String os;
        public String model;
    }

    @Accessors(chain = true)
    @Setter
    public static class recReq {
        public Integer custom_id;
        public Integer project_id;
        public Integer scene_id;
        public user user;
    }
    @Data
    public static class Options {
        private String RequestId;
    }

}
