package com.company.samplesales.screen.searchperson;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.SearchPerson;

@UiController("sales_SearchPerson.edit")
@UiDescriptor("search-person-edit.xml")
@EditedEntityContainer("searchPersonDc")
public class SearchPersonEdit extends StandardEditor<SearchPerson> {
}