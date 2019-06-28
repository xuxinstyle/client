package com.game.scence.service;

import com.game.scence.model.PlayerPosition;
import com.game.scence.resource.MapResource;
import com.resource.core.StorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
    private static Map<Integer ,Map<String ,PlayerPosition>> accountIdsMap = new ConcurrentHashMap<>();
    /**
     * 存放地图资源信息
     */
    private static Map<Integer, int[][]> map = new ConcurrentHashMap<>();
    public void init(){
        Collection<MapResource> resourceAll =(Collection<MapResource>) StorageManager.getResourceAll(MapResource.class);
        for(MapResource resource:resourceAll){
            map.put(resource.getId(),resource.getMapcontext());
        }

    }
    public int[][] getContext(int mapId){
        return map.get(mapId);
    }

    public void put(int mapId, int[][] context){
        map.putIfAbsent(mapId, context);
    }

    public void setAccountIdsMap(int mapId,String accountId,PlayerPosition position){
        Map<String ,PlayerPosition> map = accountIdsMap.get(mapId);
        if(map==null){
            map = new ConcurrentHashMap<>();
        }

        map.put(accountId, position);
        accountIdsMap.put(mapId,map);
    }
    public void setAccountIdsMap(int mapId,String accountId,int x,int y){
        Map<String, PlayerPosition> map = accountIdsMap.get(mapId);
        map.put(accountId,PlayerPosition.valueOf(x,y));
        accountIdsMap.put(mapId,map);
    }
    private PlayerPosition parse(String strPosition) {
        String[] split = strPosition.split(",");
        if(split.length<2){
            return null;
        }
        PlayerPosition playerPosition = PlayerPosition.valueOf(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
        return playerPosition;
    }

    public Map<String ,PlayerPosition> getPostionMap(int mapId){
        return accountIdsMap.get(mapId);
    }

}
