package com.ethanaa.target.sample;


import com.ethanaa.target.sample.l1message.L1Message;
import com.ethanaa.target.sample.l1message.entity.EntityType;
import com.ethanaa.target.sample.l1message.impl.ContactMechanismUpdateNotification;
import com.ethanaa.target.sample.l1message.type.L1Notification;
import com.ethanaa.target.sample.l1message.type.L1RESTRequest;
import com.ethanaa.target.sample.model.contactmech.ContactMechanism;
import com.ethanaa.target.sample.model.contactmech.ContactMechanismId;
import com.ethanaa.target.sample.model.identity.Identity;
import com.ethanaa.target.sample.l1message.impl.IdentityPostRequest;
import com.ethanaa.target.sample.model.identity.IdentityId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Main {

    public static void main(String[] args) {

        ContactMechanismUpdateNotification notification = new ContactMechanismUpdateNotification(
                ContactMechanismId.CONTACT_MECHANISM_VALUE, "test@gmail.com");

        String json = "";
        try {

            System.out.println("WRITE:");
            json = notification.toJson();
            System.out.println(json);

        } catch (Exception e) { e.printStackTrace(); }

        try {
            System.out.println("READ:");

            ContactMechanismUpdateNotification notification1 =
                    L1Message.fromJson(json, ContactMechanismUpdateNotification.class);

            System.out.println(notification1);

            System.out.println("\n----------------------------\n");

            L1Notification requestType = notification1.getMessageType();
            EntityType entityType = notification1.getEntityType();
            Class clazz = notification1.getEntityClass();
            ContactMechanismId idProperty = notification1.getEntityIdProperty();
            String idValue = notification1.getEntityIdValue();
            ContactMechanism entity = notification1.getEntity();

            System.out.println("request type: " + requestType);
            System.out.println("type: " + entityType);
            System.out.println("type class: " + clazz);
            System.out.println("id property: " + idProperty);
            System.out.println("id value: " + idValue);
            System.out.println("entity: " + entity);

        } catch (Exception e) { e.printStackTrace(); }


        System.out.println("\n================================\n");

        IdentityPostRequest request = new IdentityPostRequest(new Identity("31234"));

        String json2 = "";
        try {

            System.out.println("WRITE:");
            json2 = request.toJson();
            System.out.println(json2);

        } catch (Exception e) { e.printStackTrace(); }

        try {
            System.out.println("READ:");

            IdentityPostRequest request1 =
                    L1Message.fromJson(json2, IdentityPostRequest.class);

            System.out.println(request1);

            System.out.println("\n----------------------------\n");

            L1RESTRequest requestType = request1.getMessageType();
            EntityType entityType = request1.getEntityType();
            Class clazz = request1.getEntityClass();
            IdentityId idProperty = request1.getEntityIdProperty();
            String idValue = request1.getEntityIdValue();
            Identity entity = request1.getEntity();
            boolean isAssociated = request1.getIsAssociated();

            System.out.println("request type: " + requestType);
            System.out.println("type: " + entityType);
            System.out.println("type class: " + clazz);
            System.out.println("id property: " + idProperty);
            System.out.println("id value: " + idValue);
            System.out.println("entity: " + entity);
            System.out.println("associated: " + isAssociated);

        } catch (Exception e) { e.printStackTrace(); }

    }
}
