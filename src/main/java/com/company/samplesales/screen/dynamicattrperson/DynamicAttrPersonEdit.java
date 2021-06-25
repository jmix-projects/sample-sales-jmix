package com.company.samplesales.screen.dynamicattrperson;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.DynamicAttrPerson;

@UiController("sales_DynamicAttrPerson.edit")
@UiDescriptor("dynamic-attr-person-edit.xml")
@EditedEntityContainer("dynamicAttrPersonDc")
public class DynamicAttrPersonEdit extends StandardEditor<DynamicAttrPerson> {
}