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
import org.springframework.stereotype.Repository;

import br.com.smtech.controlegastos.api.dto.MonthDTO;
import br.com.smtech.controlegastos.api.enums.FieldOrderEnum;
import br.com.smtech.controlegastos.api.repository.dao.MonthDAO;
import br.com.smtech.controlegastos.library.dto.FilterBasicDto;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@Slf4j
@Repository
public class MonthDAOImpl implements MonthDAO {

    private static final String SQL_QUERY_FILTER_FROM = "FROM month month";
    private static final String SQL_QUERY_FILTER_GROUP = "GROUP BY month.id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<MonthDTO> findByFilter(FilterBasicDto filter) {
        filter.setProperties(filter.getProperties() != null ? filter.getProperties() : FieldOrderEnum.MONTH_YEAR_MONTH);
        final Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(),
                Sort.by(filter.getDirection(), filter.getProperties().getValue()));
        try {
            final List<MonthDTO> lista = listByFilter(filter);
            final Integer total = countByFilter(filter);
            return new PageImpl<>(lista, pageable, total);
        } catch (Exception e) {
            log.warn("findByFilter " + ExceptionUtils.getRootCauseMessage(e));
        }
        return new PageImpl<>(new LinkedList<>(), pageable, 0);
    }

    private Integer countByFilter(FilterBasicDto filter) {
        try {
            return Integer.valueOf(getQueryByFilter(filter, "SELECT COUNT(DISTINCT(month.id)) ", "", null)
                    .getSingleResult().toString());
        } catch (Exception e) {
            log.warn("findByFilter " + e.getMessage());
        }
        return 0;
    }

    private List<MonthDTO> listByFilter(FilterBasicDto filter) {
        try {
            StringBuilder sql = new StringBuilder("SELECT");
            sql.append(" month.id,");
            sql.append(" month.month,");
            sql.append(" month.year,");
            sql.append(" month.amount_debt AS amountDebt,");
            sql.append(" month.amount_credit AS amountCredit,");
            sql.append(" month.expected_value_debt AS expectedValueDebt,");
            sql.append(" month.expected_value_credit AS expectedValueCredit");
            sql.append(" ");
            return getQueryByFilter(filter, sql.toString(),
                    SQL_QUERY_FILTER_GROUP + filter.getProperties().getOrderBy(filter.getDirection()),
                    MonthDTO.MONTH_DTO_MAPPING).setMaxResults(filter.getSize())
                            .setFirstResult(filter.getSize() * filter.getPage()).getResultList();
        } catch (Exception e) {
            log.warn("listByFilter " + e.getMessage());
        }
        return null;
    }

    private Query getQueryByFilter(FilterBasicDto filter, String sql, String complement, String mappingName) {
        try {
            Map<String, Object> mapa = new HashMap<>();
            StringBuilder tudo = new StringBuilder(sql);
            tudo.append(SQL_QUERY_FILTER_FROM);
            tudo.append(" WHERE 1 = 1");
            if (filter.getUserId() != null && filter.getUserId() > 0) {
                tudo.append(" AND month.user_id = :userId");
                mapa.put("userId", filter.getUserId());
            }
            // if (StringUtils.hasLength(filter.getName())) {
            // tudo.append(" AND UPPER(month.name) LIKE :name");
            // mapa.put("name", "%" + filter.getName().toUpperCase() + "%");
            // }
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
