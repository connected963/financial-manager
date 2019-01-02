package com.connected.accountservice.domain.validator.storagevalue;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModel;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.validator.common.Validator;


public class StorageValueUpdateValidator implements Validator<StorageValueInputModel> {

    public static final String STORAGE_VALUE_WITHOUT_ID = "storageValueInputModel.id.null";

    @Override
    public void validate(final StorageValueInputModel storageValueInputModel) {
        if (storageValueInputModel.getId() == null) {
            throw new BusinessException(STORAGE_VALUE_WITHOUT_ID);
        }
    }
}
