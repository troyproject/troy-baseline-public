package com.troy.mongo.util;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.troy.commons.persistence.DataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Component
public class MongodbUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 插入JSON字符串
     *
     * @param table
     * @param json
     */
    public void insertJSON(String table, String json) {
        logger.info("table is :{}--json is :{}", table, json);
        mongoTemplate.insert(json, table);
    }

    /**
     * 删除指定表的JSON字符串
     *
     * @param table
     * @param key
     */
    public void deleteJSON(String table, String key) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(key)), table);
    }

    /**
     * <p>修改指定表的JSON字符串</p>
     * <p>这里进行更细微化的操作</>
     *
     * @param table
     * @param key
     * @param c
     */
    public void deleteJSON(String table, String key, String c) {
        Query query = new Query(Criteria.where("id").is(key));
        Update update = new Update().addToSet(key, mongoTemplate);
        mongoTemplate.updateFirst(query, update, table);
    }

    /**
     * 查询指定表的字符串
     *
     * @param table
     * @param key
     * @return
     */
    public List<JSONObject> getJSONById(String table, String key) {
        return mongoTemplate.find(new Query(Criteria.where("id").is(key)), JSONObject.class, table);
    }

    /**
     * 查询指定表的所有数据
     *
     * @param table
     * @return
     */
    public List<JSONObject> getJSONList(String table) {
        return mongoTemplate.find(new Query(), JSONObject.class, table);
    }
    /**
     * 根据指定条件查询指定表的所有数据
     *
     * @param table
     * @param filterMap
     * @return
     */
    public List<JSONObject> getJSONListByFilter(String table, Map<String, Object> filterMap) {
        List<Criteria> list = new ArrayList();
        filterMap.keySet().forEach(key -> {
            logger.info("key is :{}--value is:{}", key, filterMap.get(key));
            list.add(Criteria.where(key).is(filterMap.get(key)));
        });
        // 查看源码可知 andOperator 可接受一个不限长度的
        return mongoTemplate.find(new Query(new Criteria().andOperator(list.toArray(new Criteria[0]))), JSONObject.class, table);
    }

    /**
     * 查询指定表的所有数据
     *
     * @param table
     * @return
     */
    public <T extends DataEntity> List<T> getPojoList(String table, Class<T> clazz) {
        return mongoTemplate.find(new Query(), clazz, table);
    }

    /**
     * 查询指定表的字符串
     *
     * @param table
     * @param key
     * @return
     */
    public <T extends DataEntity> List<T> getPojoById(String table, String key, Class<T> clazz) {
        return mongoTemplate.find(new Query(Criteria.where("id").is(key)), clazz, table);
    }

    /**
     * 根据指定条件查询指定表的所有数据
     *
     * @param table
     * @param filterMap
     * @return
     */
    public <T extends DataEntity> List<T> getPojoListByFilter(String table, Map<String, Object> filterMap, Class<T> clazz) {
        List<Criteria> list = new ArrayList();
        filterMap.keySet().forEach(key -> {
            logger.info("key is :{}--value is:{}", key, filterMap.get(key));
            list.add(Criteria.where(key).is(filterMap.get(key)));
        });
        // 查看源码可知 andOperator 可接受一个不限长度的
        return mongoTemplate.find(new Query(new Criteria().andOperator(list.toArray(new Criteria[0]))), clazz, table);
    }

    /**
     * 查询指定表的字符串
     *
     * @param table
     * @param key
     * @return
     */
    public List<DBObject> getJSONByIdDBO(String table, String key) {
        return mongoTemplate.find(new Query(Criteria.where("id").is(key)), DBObject.class, table);
    }

    /**
     * 查询指定表的所有数据
     *
     * @param table
     * @return
     */
    public List<DBObject> getJSONListDBO(String table) {
        return mongoTemplate.find(new Query(), DBObject.class, table);
    }

    /**
     * 查询指定表的所有数据
     *
     * @param table
     * @param filterMap
     * @return
     */
    public List<DBObject> getJSONListByFilterDBO(String table, Map<String, Object> filterMap) {
        List<Criteria> list = new ArrayList();
        filterMap.keySet().forEach(key -> {
            logger.info("key is :{}--value is:{}", key, filterMap.get(key));
            list.add(Criteria.where(key).is(filterMap.get(key)));
        });
        // 查看源码可知 andOperator 可接受一个不限长度的
        return mongoTemplate.find(new Query(new Criteria().andOperator(list.toArray(new Criteria[0]))), DBObject.class, table);
    }
}
