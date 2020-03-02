package com.newheart.tensquare.service;

import com.newheart.tensquare.dao.LabelDao;
import com.newheart.tensquare.pojo.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author newHeart
 * @Create 2020/3/1 9:32
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }


    public Label findById(String labelId) {
        Optional<Label> optional = labelDao.findById(labelId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }


    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String labelId) {
        labelDao.deleteById(labelId);
    }


    private Specification<Label> searchLabel(Label label) {
        /**
         * @param root  代表查询的实体类.
         * @param query 封装的都是关键字查询，比如group by
         * @param criteriaBuilder  用来封装条件对象
         * @return a {@link Predicate},一个查询条件
         */
        return (Specification<Label>) (Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(label.getLabelname())) {
                Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                predicates.add(labelname);
            }
            if (StringUtils.isNotEmpty(label.getState())) {
                Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                predicates.add(state);
            }
            Predicate[] array = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(array);
        };
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(searchLabel(label));
    }

    public  Page<Label> findPage(Label label, int page, int size) {
        return labelDao.findAll(searchLabel(label), PageRequest.of(page - 1, size));
    }
}
