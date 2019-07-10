package com.game.scence.visible.service;

import com.game.scence.npc.resource.NpcResource;
import com.game.scence.visible.model.Position;
import com.game.scence.visible.resource.MapResource;
import com.resource.core.StorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：xuxin
 * @Date: 2019/6/6 11:06
 */
@Component
public class ScenceManager {

    @Autowired
    private StorageManager storageManager;
    /**
     * 地图中存放的玩家信息
     */
    private Map<Integer ,Map<String ,Position>> accountIdsMap = new ConcurrentHashMap<>();
    /**
     * 存放地图资源信息
     */
    private Map<Integer, int[][]> map = new ConcurrentHashMap<>();
    /**
     * <mapId, npcIds>
     */
    private Map<Integer,List<Integer>> mapNpcIds = new ConcurrentHashMap<>();

    public void init(){
        Collection<MapResource> resourceAll =(Collection<MapResource>) storageManager.getResourceAll(MapResource.class);
        for(MapResource resource:resourceAll){
            map.put(resource.getId(),resource.getMapcontext());
        }
        Collection<NpcResource> npcResources = (Collection<NpcResource>)storageManager.getResourceAll(NpcResource.class);
        for(NpcResource resource:npcResources) {
            List<Integer> npcIds = mapNpcIds.get(resource.getMapId());
            if(npcIds==null){
                npcIds = new ArrayList<>();
                mapNpcIds.put(resource.getMapId(),npcIds);
            }
            npcIds.add(resource.getId());
        }
    }

    public List<Integer> getNpcIds(int mapId){
        return mapNpcIds.get(mapId);
    }

    public Map<Integer, Map<String, Position>> getAccountIdsMap() {
        return accountIdsMap;
    }

    public void setAccountIdsMap(Map<Integer, Map<String, Position>> accountIdsMap) {
        this.accountIdsMap = accountIdsMap;
    }

    public Map<Integer, int[][]> getMap() {
        return map;
    }

    public void setMap(Map<Integer, int[][]> map) {
        this.map = map;
    }

    public Map<Integer, List<Integer>> getMapNpcIds() {
        return mapNpcIds;
    }

    public void setMapNpcIds(Map<Integer, List<Integer>> mapNpcIds) {
        this.mapNpcIds = mapNpcIds;
    }

    public int[][] getContext(int mapId){
        return map.get(mapId);
    }

    public void put(int mapId, int[][] context){
        map.putIfAbsent(mapId, context);
    }

    public void setAccountIdsMap(int mapId,String accountId){
        Map<String ,Position> map = accountIdsMap.get(mapId);
        if(map==null){
            map = new ConcurrentHashMap<>();
        }
        MapResource resource = storageManager.getResource(mapId, MapResource.class);
        Position position = Position.valueOf(resource.getInitX(), resource.getInitY());
        map.put(accountId,position);
        accountIdsMap.put(mapId,map);
    }
    public void setAccountIdsMap(int mapId,String accountId,int x,int y){
        Map<String, Position> map = accountIdsMap.get(mapId);
        map.put(accountId,Position.valueOf(x,y));
        accountIdsMap.put(mapId,map);
    }
    private Position parse(String strPosition) {
        String[] split = strPosition.split(",");
        if(split.length<2){
            return null;
        }
        Position position = Position.valueOf(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
        return position;
    }

    public Map<String ,Position> getPostionMap(int mapId){
        return accountIdsMap.get(mapId);
    }

}
