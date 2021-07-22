package com.company.samplesales.screen.entitywithfile;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.EntityWithFile;

@UiController("sales_EntityWithFile.edit")
@UiDescriptor("entity-with-file-edit.xml")
@EditedEntityContainer("entityWithFileDc")
public class EntityWithFileEdit extends StandardEditor<EntityWithFile> {
}