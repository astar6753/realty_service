package com.astar.realty.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class BrokerDTO {

    @Data
    @AllArgsConstructor
    public static class Request {
        private String id;
        private String pwd;
        private String name;
        private String phone;
        private String imageFileName;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String pwd;
        private String name;
        private String phone;
        private String imageFileName;
    }
}
