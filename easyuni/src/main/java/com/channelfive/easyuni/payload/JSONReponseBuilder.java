package com.channelfive.easyuni.payload;

import java.util.HashMap;
import java.util.Map;

public class JSONReponseBuilder {

    public static Map<String, Object> build(Boolean success, String msg, Object data) {
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("success", success);
        jsonData.put("message", msg);
        jsonData.put("data", data);
        return jsonData;
    }
    
}
