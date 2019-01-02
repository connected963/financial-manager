package com.connected.accountservice.domain.validator.storage;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModel;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.validator.common.Validator;


public class StorageUpdateValidator implements Validator<StorageInputModel> {

    public static final String STORAGE_WITHOUT_ID = "storageInputModel.id.null";

    @Override
    public void validate(final StorageInputModel storageInputModel) {
        if (storageInputModel.getId() == null) {
            throw new BusinessException(STORAGE_WITHOUT_ID);
        }
    }
}
