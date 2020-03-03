package com.tensquare.spit.service;

import com.mongodb.client.result.UpdateResult;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Author newHeart
 * @Create 2020/3/3 10:08
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有
     * @return
     */
    public List<Spit> findAll() {
//        spitDao.findAll();
        return mongoTemplate.findAll(Spit.class);
    }

    /**
     * 根据id查询
     * @param spitId
     * @return
     */
    public Spit findById(String spitId) {
//        spitDao.findById(spitId).get();
        return mongoTemplate.findById(spitId,Spit.class,"spit");
    }

    /**
     * 新增
     * @param spit
     */
    public void save(Spit spit) {
        spit.set_id(String.valueOf(idWorker.nextId()));
        spit.setPublishtime(new Date());        // 发布日期
        spit.setVisits(0);      // 浏览量
        spit.setShare(0);       // 分享数
        spit.setThumbup(0);     // 点赞数
        spit.setComment(0);     // 回复数
        spit.setState("1");     // 状态
//        spitDao.save(spit);
//        如果有父节点，那么父节点评论加1
        if (StringUtils.isNotEmpty(spit.getParentid())){
            Query query = new Query(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        mongoTemplate.save(spit,"spit");
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String spitId) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(spitId)),"spit");
//        spitDao.deleteById(spitId);
    }

    public Page<Spit>  findByParentId(String parentid, int page, int size) {
        Page<Spit> spitPage = spitDao.findByParentid(parentid, PageRequest.of(page - 1, size));
        return spitPage;
    }

    /**
     * 点赞
     * 用户不能重复点赞
     * @param spitId
     */
    public Result thumbup(String spitId) {
        String userid="newheart_666";
        Object o = redisTemplate.opsForValue().get(userid + "_" + spitId);
        if (o!=null){
            return new Result(StatusCode.ERROR, false, "用户已点赞");
        }
//        点赞自增
        redisTemplate.opsForValue().set(userid + "_" + spitId,"点赞");
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup",1);
        UpdateResult spit = mongoTemplate.updateFirst(query, update, "spit");
        return new Result(StatusCode.OK, true, "点赞成功");
    }
}
