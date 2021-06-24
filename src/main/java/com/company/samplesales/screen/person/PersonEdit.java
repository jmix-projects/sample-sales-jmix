package com.company.samplesales.screen.person;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Person;

@UiController("sales_Person.edit")
@UiDescriptor("person-edit.xml")
@EditedEntityContainer("personDc")
public class PersonEdit extends StandardEditor<Person> {
}