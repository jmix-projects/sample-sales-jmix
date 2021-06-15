package com.company.samplesales.screen.newentity;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.NewEntity;

@UiController("sales_NewEntity.edit")
@UiDescriptor("new-entity-edit.xml")
@EditedEntityContainer("newEntityDc")
public class NewEntityEdit extends StandardEditor<NewEntity> {
}