package com.ethanaa.target.sample.l1message.impl;


import com.ethanaa.target.sample.l1message.L1Message;
import com.ethanaa.target.sample.l1message.type.L1RESTRequest;
import com.ethanaa.target.sample.l1message.entity.EntityInfo;
import com.ethanaa.target.sample.l1message.entity.EntityType;
import com.ethanaa.target.sample.model.contactmech.ContactMechanismId;
import com.ethanaa.target.sample.model.identity.Identity;
import com.ethanaa.target.sample.model.identity.IdentityId;

public class IdentityPostRequest extends L1Message<Identity, IdentityId, L1RESTRequest> {

    public IdentityPostRequest(Identity identity) {

        super(L1RESTRequest.POST,
                new EntityInfo<>(IdentityId.TARGET_GUID, identity.getTargetGuid()), identity);
    }

    public IdentityPostRequest() {
        super();
    }
}
