package com.ethanaa.target.sample.l1message.impl;


import com.ethanaa.target.sample.l1message.L1Message;
import com.ethanaa.target.sample.l1message.type.L1Notification;
import com.ethanaa.target.sample.l1message.entity.EntityInfo;
import com.ethanaa.target.sample.l1message.entity.EntityType;
import com.ethanaa.target.sample.model.contactmech.ContactMechanism;
import com.ethanaa.target.sample.model.contactmech.ContactMechanismId;
import com.ethanaa.target.sample.model.identity.Identity;

public class ContactMechanismUpdateNotification extends L1Message<ContactMechanism, ContactMechanismId, String, L1Notification> {

    public ContactMechanismUpdateNotification(ContactMechanismId contactMechanismId, String contactMechanismValue) {

        super(L1Notification.UPDATED,
                new EntityInfo<>(contactMechanismId, contactMechanismValue));
    }

    public ContactMechanismUpdateNotification() {
        super();
    }
}
