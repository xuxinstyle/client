package com.socket.core;

import com.game.util.JsonUtils;
import com.socket.dispatcher.action.IActionDispatcher;
import com.socket.dispatcher.config.RegistSerializerMessage;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

import java.util.Map;


/**
 * @Author：xuxin
 * @Date: 2019/4/29 14:44
 */
public class TSession {
    private static final Logger logger = Logger.getLogger(TSession.class);
    // 里面放在线的玩家账号信息 <信息标识，玩家信息>

    private String accountId;
    private String passward;
    private long playerId;
    private final long createTime = System.currentTimeMillis();
    private final Channel channel;
    private final String ip;
    private String inetIp;
    private final String port;
    /**
     * 地图id
     */
    private int mapId;
    /**
     * 场景id
     */
    private int sceneId;
    private final IActionDispatcher actionDispatcher;
    public TSession(Channel channel){
        this(channel, null);
    }
    //FIXME:项目中有一个IParse参数，次数没了解其用处，暂时不加
    public TSession(Channel channel, IActionDispatcher actionDispatcher){
        this.channel = channel;
        this.actionDispatcher = actionDispatcher;
        if(channel != null){
            this.ip = channel.remoteAddress().toString();
        }else{
            this.ip = "127.0.0.1:8888";
        }
        String[] adds = ip.split(":");
        this.inetIp = adds[0].substring(1);//FIXME:这里不太理解为什么
        this.port = adds[1];
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void sendPacket(Object res) {
        try{
            if(res==null){
                MyPack pack = new MyPack();
                channel.writeAndFlush(pack);
                return;
            }
            int opIndex = 0;
            for (Map.Entry<Integer, Class<?>> entry :RegistSerializerMessage.ID_CLASS_MAP.entrySet()) {
                if(entry.getValue().equals(res.getClass())){
                    opIndex = entry.getKey();
                    break;
                }
            }
            MyPack pack = new MyPack();
            pack.setpId(opIndex);
            pack.setPacket(JsonUtils.object2Bytes(res));
            channel.writeAndFlush(pack);
        }catch (Exception e){
            String msg = String.format("encode %s error.",res != null ? res.getClass().getSimpleName():"null");
            logger.error(msg,e);
        }
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public void esc(){
        channel.close();
    }

    public Channel getChannel() {
        return channel;
    }

    public String getIp() {
        return ip;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }
}
