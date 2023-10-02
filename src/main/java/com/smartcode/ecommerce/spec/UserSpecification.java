package com.smartcode.ecommerce.spec;

import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.user.UserFilterModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserSpecification {

    public Specification<UserEntity> filterAndSearch(UserFilterModel userFilterModel) {
        Specification<UserEntity> filter = filter(userFilterModel);
        Specification<UserEntity> search = search(userFilterModel);
        return Specification.where(filter.and(search));
    }

    private Specification<UserEntity> filter(UserFilterModel userFilterModel) {
        return Specification.where((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            UserFilterModel.Filter filter = userFilterModel.getFilter();

            if(filter != null) {
                if (filter.getIsVerified() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("isVerified"), filter.getIsVerified()));
                }
                if (filter.getStartAge() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), filter.getStartAge()));
                }
                if (filter.getEndAge() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), filter.getEndAge()));
                }
            }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }

    private Specification<UserEntity> search(UserFilterModel userFilterModel) {
        return  Specification.where((root, query, criteriaBuilder) -> {

            UserFilterModel.Search search = userFilterModel.getSearch();

            List<Predicate> predicates = new ArrayList<>();

        if(search != null) {
            if (search.getText() != null) {
                Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + search.getText() + "%");
                predicates.add(nameLike);

                Predicate lastNameLike = criteriaBuilder.like(root.get("lastname"), "%" + search.getText() + "%");
                predicates.add(lastNameLike);

                Predicate emailLike = criteriaBuilder.like(root.get("email"), "%" + search.getText() + "%");
                predicates.add(emailLike);
            }
        }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
    }

}