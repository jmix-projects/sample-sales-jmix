package com.company.samplesales.screen.person;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Person;

@UiController("sales_Person.browse")
@UiDescriptor("person-browse.xml")
@LookupComponent("personsTable")
public class PersonBrowse extends StandardLookup<Person> {
}