package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author newHeart
 * @Create 2020/3/2 16:39
 */
@Service
public class GatheringService {

    @Autowired
    private GatheringDao gatheringDao;

    @Cacheable(value = "Gathering",key = "#id")
    public Gathering findById(String id) {
        Optional<Gathering> optional = gatheringDao.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            return null;
        }
    }
    @CacheEvict(value = "Gathering",key = "#gathering.id")
    public void update(Gathering gathering) {
        gatheringDao.save(gathering);
    }


    @CacheEvict(value = "Gathering",key = "#id")
    public void deleteById(String id) {
        gatheringDao.deleteById(id);
    }
}
