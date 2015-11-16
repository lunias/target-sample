package com.ethanaa.target.sample.l1message.impl;


import com.ethanaa.target.sample.l1message.L1Message;
import com.ethanaa.target.sample.l1message.type.L1RESTRequest;
import com.ethanaa.target.sample.l1message.entity.EntityInfo;
import com.ethanaa.target.sample.l1message.entity.EntityType;
import com.ethanaa.target.sample.model.contactmech.ContactMechanismId;
import com.ethanaa.target.sample.model.identity.Identity;
import com.ethanaa.target.sample.model.identity.IdentityId;

public class IdentityPostRequest extends L1Message<Identity, IdentityId, String, L1RESTRequest> {

    private boolean isAssociated = false;

    public IdentityPostRequest(Identity identity, boolean isAssociated) {

        super(L1RESTRequest.POST, IdentityId.TARGET_GUID, identity.getTargetGuid(), identity);

        setIsAssociated(isAssociated);
    }

    public IdentityPostRequest(Identity identity) {
        this(identity, false);
    }

    public IdentityPostRequest() {
        super();
    }

    public boolean getIsAssociated() {
        return isAssociated;
    }

    public void setIsAssociated(boolean isAssociated) {
        this.isAssociated = isAssociated;
    }

    @Override
    public String toString() {
        return "IdentityPostRequest{" +
                "isAssociated=" + isAssociated +
                "} " + super.toString();
    }
}
