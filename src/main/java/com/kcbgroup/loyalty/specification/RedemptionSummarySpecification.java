package com.kcbgroup.loyalty.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.kcbgroup.loyalty.model.RedemptionSummary;

public class RedemptionSummarySpecification implements Specification<RedemptionSummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7639693844715441051L;
	private SearchCriteria criteria;

	public RedemptionSummarySpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<RedemptionSummary> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase(":") && 
        		root.get(criteria.getKey()).getJavaType() == String.class) {
            return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
		
		return null;
	}

}
