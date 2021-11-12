package com.company.samplesales.security;

import com.company.samplesales.entity.Product;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.apache.commons.lang3.BooleanUtils;

import java.util.function.Predicate;

@RowLevelRole(code = TestRowLevelRole.CODE, name = "Test Row Level Role")
public interface TestRowLevelRole {
    public static final String CODE = "test-restrictions";

    @PredicateRowLevelPolicy(entityClass = Product.class, actions = RowLevelPolicyAction.READ)
    default Predicate<Product> product() {
        return entity -> BooleanUtils.isNotTrue(entity.getSpecial());
    }
}
