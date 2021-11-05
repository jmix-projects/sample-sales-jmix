package com.company.samplesales.security;

import com.company.samplesales.entity.Product;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.model.RowLevelPredicate;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.apache.commons.lang3.BooleanUtils;

@RowLevelRole(code = "test-restrictions", name = "Test Row Level Role")
public interface TestRowLevelRole {

    @PredicateRowLevelPolicy(entityClass = Product.class, actions = RowLevelPolicyAction.READ)
    static RowLevelPredicate<Product> product() {
        return entity -> BooleanUtils.isNotTrue(entity.getSpecial());
    }

}
