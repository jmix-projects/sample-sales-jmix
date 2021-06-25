package com.company.samplesales.entity;

import io.jmix.search.index.annotation.AutoMappedField;
import io.jmix.search.index.annotation.JmixEntitySearchIndex;

@JmixEntitySearchIndex(entity = SearchPerson.class)
public interface PersonIndexDefinition {
    @AutoMappedField(includeProperties = {"*"},
            excludeProperties = {"description"}
    )
    void orderMapping();
}
