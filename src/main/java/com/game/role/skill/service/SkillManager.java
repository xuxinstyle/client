package com.game.role.skill.service;

import com.game.role.skill.resource.SkillLevelResource;
import com.game.role.skill.resource.SkillResource;
import com.resource.core.StorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 23:46
 */
@Component
public class SkillManager {
    @Autowired
    private StorageManager storageManager;
    /**
     * 技能栏映射
     */
    public Map<Integer, Integer> skillBarMap;

    public SkillLevelResource getSkillLevelResource(String id){
        return storageManager.getResource(id,SkillLevelResource.class);
    }
    public SkillResource getSkillResource(int skillId){
        return storageManager.getResource(skillId,SkillResource.class);
    }

    public Map<Integer, Integer> getSkillBarMap() {
        return skillBarMap;
    }

    public void setSkillBarMap(Map<Integer, Integer> skillBarMap) {
        this.skillBarMap = skillBarMap;
    }
}
