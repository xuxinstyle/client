package com.game.scence.service;

import com.game.scence.model.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static Map<Integer ,Map<String ,Position>> accountIdsMap = new ConcurrentHashMap<>();
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

    public void setAccountIdsMap(int mapId,String accountId,String strPosition){
        Map<String ,Position> map = accountIdsMap.get(mapId);
        if(map==null){
            map = new ConcurrentHashMap<>();
        }
        Position position=parse(strPosition);
        if(position==null){
            return;
        }
        map.put(accountId,position);
        accountIdsMap.put(mapId,map);
    }
    public void setAccountIdsMap(int mapId,String accountId,int x,int y){
        Map<String, Position> map = accountIdsMap.get(mapId);
        map.put(accountId,new Position(x,y));
        accountIdsMap.put(mapId,map);
    }
    private Position parse(String strPosition) {
        String[] split = strPosition.split(",");
        if(split.length<2){
            return null;
        }
        Position position = new Position(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
        return position;
    }

    public Map<String ,Position> getPostionMap(int mapId){
        return accountIdsMap.get(mapId);
    }

}
