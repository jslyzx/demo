//-------------------------------------------------------------------------
// Copyright (c) 2000-2016 Digital. All Rights Reserved.
//
// This software is the confidential and proprietary information of
// Digital
//
// Original author: zhaojin
//
//-------------------------------------------------------------------------
// APACHE
//-------------------------------------------------------------------------
package com.yph.authcommon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtil
{
    private static final SerializerFeature[] features = {
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.DisableCircularReferenceDetect // 禁止以引用形式输出
    };

    /**
     * 获取Json格式化数据
     * 
     * @param object
     * @return
     */
    public static String toJsonString(Object object)
    {
        return JSON.toJSONString(object, features);
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    public static <T> T getObject4JsonString(String jsonString, Class<T> clazz)
    {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * JSON字符串转换成java对象
     * 
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T toObject(String jsonString, TypeReference<T> clazz)
    {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 转换JSON集合
     * 
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> List<T> toArray(String jsonString, Class<T> clazz)
    {
        return JSON.parseArray(jsonString, clazz);
    }

    /**
     * 从json HASH表达式中获取一个map，改map支持嵌套功能
     * 
     * @param jsonString
     * @return
     */
    public static Map<String, Object> getMap4Json(String jsonString)
    {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Iterator<String> keyIter = jsonObject.keySet().iterator();
        String key;
        Object value;
        Map<String, Object> valueMap = new HashMap<String, Object>();

        while (keyIter.hasNext())
        {
            key = keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    /**
     * 从json数组中得到相应java数组
     * 
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString)
    {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        return jsonArray.toArray();
    }

    /**
     * 将一个对象转换为JSON字符串
     * 
     * @param obj
     * @return
     */
    public static String getObjectToJsonObject(Object obj)
    {
        return JSON.toJSON(obj).toString();
    }

    /**
     * 从json数组中解析出java字符串数组
     * 
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString)
    {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        String[] stringArray = new String[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++)
        {
            stringArray[i] = jsonArray.get(i).toString();
        }

        return stringArray;
    }
}
