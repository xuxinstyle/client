package com.game.scence.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：xuxin
 * @Date: 2019/6/6 11:06
 */
@Component
public class ScenceManager {
    /**
     * 地图中存放的玩家信息
     */
    private static Map<Integer ,Map<String ,String>> accountIdsMap = new ConcurrentHashMap<>();
    /**
     * 存放地图资源信息
     */
    private static Map<Integer, String> map = new ConcurrentHashMap<>();
    public String getContext(int mapId){
        return map.get(mapId);
    }
    public void put(int mapId, String context){
        map.putIfAbsent(mapId, context);
    }
    public void addAccount(int mapId, Map<String ,String> accounts){
        accountIdsMap.put(mapId,accounts );
    }
    public Map<String ,String>getAccountIds(int mapId){
        return accountIdsMap.get(mapId);
    }

    public Map<Integer, Map<String ,String>> getAccountIdsMap() {
        return accountIdsMap;
    }

    public void setAccountIdsMap(Map<Integer, Map<String ,String>> accountIdsMap) {
        ScenceManager.accountIdsMap = accountIdsMap;
    }
}
