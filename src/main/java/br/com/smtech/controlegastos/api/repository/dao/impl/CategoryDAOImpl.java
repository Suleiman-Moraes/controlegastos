package br.com.smtech.controlegastos.api.repository.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.smtech.controlegastos.api.dto.CategoryFilterDTO;
import br.com.smtech.controlegastos.api.dto.CategoryListDTO;
import br.com.smtech.controlegastos.api.enums.FieldOrderEnum;
import br.com.smtech.controlegastos.api.repository.dao.CategoryDAO;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@Slf4j
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private static final String SQL_QUERY_JOIN_MONTH = " LEFT JOIN month month ON month.id = item.month_id";
    private static final String SQL_QUERY_JOIN_ITEM = " LEFT JOIN item item ON category.id = item.category_id";
    private static final String SQL_QUERY_FILTER_FROM = "FROM category category" + SQL_QUERY_JOIN_ITEM
            + SQL_QUERY_JOIN_MONTH;
    private static final String SQL_QUERY_FILTER_GROUP = "GROUP BY category.id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<CategoryListDTO> findByFilter(CategoryFilterDTO filter) {
        if (filter.getProperties() == null) {
            filter.setProperties(FieldOrderEnum.ITEM_DATE_EXPECTED_CATEGORY_NAME);
            filter.setDirection(Direction.ASC);
        }
        final Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(),
                Sort.by(filter.getDirection(), filter.getProperties().getValue()));
        try {
            final List<CategoryListDTO> lista = listByFilter(filter);
            final Integer total = countByFilter(filter);
            return new PageImpl<>(lista, pageable, total);
        } catch (Exception e) {
            log.warn("findByFilter " + ExceptionUtils.getRootCauseMessage(e));
        }
        return new PageImpl<>(new LinkedList<>(), pageable, 0);
    }

    private Integer countByFilter(CategoryFilterDTO filter) {
        try {
            return Integer.valueOf(getQueryByFilter(filter, "SELECT COUNT(DISTINCT(item.id)) ", "", null)
                    .getSingleResult().toString());
        } catch (Exception e) {
            log.warn("findByFilter " + e.getMessage());
        }
        return 0;
    }

    private List<CategoryListDTO> listByFilter(CategoryFilterDTO filter) {
        try {
            StringBuilder sql = new StringBuilder("SELECT");
            sql.append(" category.id,");
            sql.append(" item.month_id AS monthId,");
            sql.append(" item.id AS itemId,");
            sql.append(" category.name,");
            sql.append(" category.classification,");
            sql.append(" category.operation,");
            sql.append(" item.amount,");
            sql.append(" item.expected_value AS expectedValue,");
            sql.append(" item.date_expected AS dateExpected");
            sql.append(" ");
            return getQueryByFilter(filter, sql.toString(),
                    SQL_QUERY_FILTER_GROUP + filter.getProperties().getOrderBy(filter.getDirection()),
                    CategoryListDTO.CATEGORY_LIST_DTO_MAPPING).setMaxResults(filter.getSize())
                            .setFirstResult(filter.getSize() * filter.getPage()).getResultList();
        } catch (Exception e) {
            log.warn("listByFilter " + e.getMessage());
        }
        return null;
    }

    private Query getQueryByFilter(CategoryFilterDTO filter, String sql, String complement, String mappingName) {
        try {
            Map<String, Object> mapa = new HashMap<>();
            StringBuilder tudo = new StringBuilder(sql);
            tudo.append(SQL_QUERY_FILTER_FROM);
            tudo.append(" WHERE 1 = 1");
            if (filter.getId() != null && filter.getId() > 0) {
                tudo.append(" AND category.id = :id");
                mapa.put("id", filter.getId());
            }
            if (filter.getActive() != null) {
                tudo.append(" AND category.active = :active");
                mapa.put("active", filter.getActive());
            }
            if (filter.getUserId() != null && filter.getUserId() > 0) {
                tudo.append(" AND month.user_id = :userId");
                mapa.put("userId", filter.getUserId());
            }
            if (filter.getMonthId() != null && filter.getMonthId() > 0) {
                tudo.append(" AND month.id ").append(filter.getTypeMonthId().getValue()).append(" :monthId");
                mapa.put("monthId", filter.getMonthId());
            }
            if (StringUtils.hasLength(filter.getName())) {
                tudo.append(" AND UPPER(category.name) LIKE :name");
                mapa.put("name", "%" + filter.getName().toUpperCase() + "%");
            }
            tudo.append(" ").append(complement);
            Query query = null;
            if (mappingName == null) {
                query = entityManager.createNativeQuery(tudo.toString());
            } else {
                query = entityManager.createNativeQuery(tudo.toString(), mappingName);
            }
            for (String key : mapa.keySet()) {
                query.setParameter(key, mapa.get(key));
            }
            return query;
        } catch (Exception e) {
            log.warn("getQueryByFilter " + e.getMessage());
            throw e;
        }
    }
}
