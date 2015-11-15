package com.ethanaa.target.sample;


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

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        ContactMechanismUpdateNotification notification = new ContactMechanismUpdateNotification("test@gmail.com");

        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

        String json = "";
        try {
            System.out.println("WRITE:");
            json = writer.writeValueAsString(notification);
            System.out.println(json);
        } catch (Exception e) { e.printStackTrace(); }

        ObjectReader reader = mapper.readerFor(ContactMechanismUpdateNotification.class);

        try {
            System.out.println("READ:");

            ContactMechanismUpdateNotification notification1 = reader.readValue(json);

            System.out.println(notification1);

            System.out.println("\n----------------------------\n");

            L1Notification requestType = notification1.getMessageType();
            EntityType entityType = notification1.getEntityType();
            Class clazz = notification1.getEntityType().getClazz();
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


        System.out.println();

        IdentityPostRequest request = new IdentityPostRequest(new Identity("31234"));

        String json2 = "";
        try {
            System.out.println("WRITE:");
            json2 = writer.writeValueAsString(request);
            System.out.println(json2);
        } catch (Exception e) { e.printStackTrace(); }

        reader = mapper.readerFor(IdentityPostRequest.class);

        try {
            System.out.println("READ:");
            IdentityPostRequest request1 = reader.readValue(json2);
            System.out.println(request1);

            System.out.println("\n----------------------------\n");

            L1RESTRequest requestType = request1.getMessageType();
            EntityType entityType = request1.getEntityType();
            Class clazz = request1.getEntityType().getClazz();
            IdentityId idProperty = request1.getEntityIdProperty();
            String idValue = request1.getEntityIdValue();
            Identity entity = request1.getEntity();

            System.out.println("request type: " + requestType);
            System.out.println("type: " + entityType);
            System.out.println("type class: " + clazz);
            System.out.println("id property: " + idProperty);
            System.out.println("id value: " + idValue);
            System.out.println("entity: " + entity);

        } catch (Exception e) { e.printStackTrace(); }

    }
}
