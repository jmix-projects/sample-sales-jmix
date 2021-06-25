package com.company.samplesales.screen.dynamicattrperson;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.DynamicAttrPerson;

@UiController("sales_DynamicAttrPerson.browse")
@UiDescriptor("dynamic-attr-person-browse.xml")
@LookupComponent("dynamicAttrPersonsTable")
public class DynamicAttrPersonBrowse extends StandardLookup<DynamicAttrPerson> {
}