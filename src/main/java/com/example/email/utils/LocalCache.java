package com.example.email.utils;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SHIshixian 利用concurrentHashMap实现一个简单的本地缓存类
 * 具有线程安全性
 */

public class LocalCache {


    private static LocalCache localCache = new LocalCache();
    private static Map<Object,CacheContent> map = new ConcurrentHashMap();

    private LocalCache() {

    }

    public static LocalCache getInstance() {
        return localCache;

    }
    public void put(Object key,Object value){
        CacheContent cacheContent=new CacheContent();
        cacheContent.setValue(value);
        map.put(key,cacheContent);
    }

    public void put(Object key,Object value,int cahheMillis){
        CacheContent cacheContent=new CacheContent(value,System.currentTimeMillis()+cahheMillis);
        map.put(key,cacheContent);

    }
    public Object get(Object key){
        return map.get(key).getValue();
    }
    public boolean isValid(Object key){
        long time =System.currentTimeMillis();
        if(map.containsKey(key)&map.get(key).getEndTime()>time){
            return true;
        }
        return  false;
    }

    public void remove(Object key){
        map.remove(key);
    }
    public void clearInvalid(){
        long time =System.currentTimeMillis();
        for(Object key:map.keySet()){
            if(map.get(key).getEndTime()<time){
                map.remove(key);
            }
        }
    }


    class CacheContent {

        // 缓存对象
        private Object value;
        // 缓存结束时间
        private long endTime;

        public CacheContent() {
        }

        public CacheContent( Object value, long endTime) {

            this.value = value;
            this.endTime = endTime;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }
    }


}
