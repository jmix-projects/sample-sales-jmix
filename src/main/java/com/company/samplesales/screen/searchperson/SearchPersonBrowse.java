package com.company.samplesales.screen.searchperson;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.SearchPerson;

@UiController("sales_SearchPerson.browse")
@UiDescriptor("search-person-browse.xml")
@LookupComponent("searchPersonsTable")
public class SearchPersonBrowse extends StandardLookup<SearchPerson> {
}