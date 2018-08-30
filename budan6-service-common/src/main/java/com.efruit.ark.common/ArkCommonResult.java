package com.efruit.ark.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class ArkCommonResult {

    private static final int STATUS_OK = 800;
    private static final int STATUS_FAIL = -1;


    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ArkCommonResult build(Integer status, String msg, Object data) {
        return new ArkCommonResult(status, msg, data);
    }

    public static ArkCommonResult ok(Object data) {
        return new ArkCommonResult(data);
    }

    public static ArkCommonResult ok() {
        return new ArkCommonResult(null);
    }

    public static ArkCommonResult fail() {
        return build(STATUS_FAIL, null, null);
    }

    public static ArkCommonResult fail(String msg) {
        return build(STATUS_FAIL, msg, null);
    }

    public ArkCommonResult() {

    }

    public static ArkCommonResult build(Integer status, String msg) {
        return new ArkCommonResult(status, msg, null);
    }

    public ArkCommonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ArkCommonResult(Object data) {
        this.status = STATUS_OK;
        this.msg = "OK";
        this.data = data;
    }

    public boolean statusOK() {
        return this.status == STATUS_OK;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ArkCommonResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ArkCommonResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ArkCommonResult format(String json) {
        try {
            return MAPPER.readValue(json, ArkCommonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static ArkCommonResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
