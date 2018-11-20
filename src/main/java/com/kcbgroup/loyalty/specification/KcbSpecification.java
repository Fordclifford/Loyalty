package com.kcbgroup.loyalty.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.kcbgroup.loyalty.model.Kcb;

public class KcbSpecification implements Specification<Kcb> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2818098882942558480L;
	private SearchCriteria criteria;

	public KcbSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Kcb> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && 
        		root.get(criteria.getKey()).getJavaType() == String.class) {
            return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
        else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
	}

}