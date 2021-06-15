package com.company.samplesales.screen.newentity;

import io.jmix.core.FileRef;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.FileMultiUploadField;
import io.jmix.ui.screen.*;
import com.company.samplesales.entity.NewEntity;
import io.jmix.ui.upload.TemporaryStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

@UiController("sales_NewEntity.browse")
@UiDescriptor("new-entity-browse.xml")
@LookupComponent("newEntitiesTable")
public class NewEntityBrowse extends StandardLookup<NewEntity> {
    @Autowired
    private FileMultiUploadField fileMultiUpload;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private Notifications notifications;
    @Subscribe
    public void onInit(InitEvent event) {
        fileMultiUpload.addQueueUploadCompleteListener(queueUploadCompleteEvent -> {
            for (Map.Entry<UUID, String> entry : fileMultiUpload.getUploadsMap().entrySet()) {
                UUID fileId = entry.getKey();
                String fileName = entry.getValue();
                FileRef fileRef = temporaryStorage.putFileIntoStorage(fileId, fileName);
            }
            notifications.create()
                    .withCaption("Uploaded files: " + fileMultiUpload.getUploadsMap().values())
                    .show();
            fileMultiUpload.clearUploads();
        });
        fileMultiUpload.addFileUploadErrorListener(queueFileUploadErrorEvent ->
                notifications.create()
                        .withCaption("File upload error")
                        .show());
    }
}