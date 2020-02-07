package org.hl7.fhir.convertors.conv30_40;

import org.hl7.fhir.convertors.VersionConvertor_30_40;
import org.hl7.fhir.exceptions.FHIRException;
import java.util.Collections;

public class Endpoint30_40 {

    public static org.hl7.fhir.r4.model.Endpoint convertEndpoint(org.hl7.fhir.dstu3.model.Endpoint src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r4.model.Endpoint tgt = new org.hl7.fhir.r4.model.Endpoint();
        VersionConvertor_30_40.copyDomainResource(src, tgt);
        if (src.hasIdentifier()) {
            for (org.hl7.fhir.dstu3.model.Identifier t : src.getIdentifier()) tgt.addIdentifier(VersionConvertor_30_40.convertIdentifier(t));
        }
        if (src.hasStatus())
            tgt.setStatus(convertEndpointStatus(src.getStatus()));
        if (src.hasConnectionType())
            tgt.setConnectionType(VersionConvertor_30_40.convertCoding(src.getConnectionType()));
        if (src.hasNameElement())
            tgt.setNameElement((org.hl7.fhir.r4.model.StringType) VersionConvertor_30_40.convertType(src.getNameElement()));
        if (src.hasManagingOrganization())
            tgt.setManagingOrganization(VersionConvertor_30_40.convertReference(src.getManagingOrganization()));
        if (src.hasContact()) {
            for (org.hl7.fhir.dstu3.model.ContactPoint t : src.getContact()) tgt.addContact(VersionConvertor_30_40.convertContactPoint(t));
        }
        if (src.hasPeriod())
            tgt.setPeriod(VersionConvertor_30_40.convertPeriod(src.getPeriod()));
        if (src.hasPayloadType()) {
            for (org.hl7.fhir.dstu3.model.CodeableConcept t : src.getPayloadType()) tgt.addPayloadType(VersionConvertor_30_40.convertCodeableConcept(t));
        }
        if (src.hasPayloadMimeType()) {
            for (org.hl7.fhir.dstu3.model.CodeType t : src.getPayloadMimeType()) tgt.addPayloadMimeType(t.getValue());
        }
        if (src.hasAddressElement())
            tgt.setAddressElement((org.hl7.fhir.r4.model.UrlType) VersionConvertor_30_40.convertType(src.getAddressElement()));
        if (src.hasHeader()) {
            for (org.hl7.fhir.dstu3.model.StringType t : src.getHeader()) tgt.addHeader(t.getValue());
        }
        return tgt;
    }

    public static org.hl7.fhir.dstu3.model.Endpoint convertEndpoint(org.hl7.fhir.r4.model.Endpoint src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.Endpoint tgt = new org.hl7.fhir.dstu3.model.Endpoint();
        VersionConvertor_30_40.copyDomainResource(src, tgt);
        if (src.hasIdentifier()) {
            for (org.hl7.fhir.r4.model.Identifier t : src.getIdentifier()) tgt.addIdentifier(VersionConvertor_30_40.convertIdentifier(t));
        }
        if (src.hasStatus())
            tgt.setStatus(convertEndpointStatus(src.getStatus()));
        if (src.hasConnectionType())
            tgt.setConnectionType(VersionConvertor_30_40.convertCoding(src.getConnectionType()));
        if (src.hasNameElement())
            tgt.setNameElement((org.hl7.fhir.dstu3.model.StringType) VersionConvertor_30_40.convertType(src.getNameElement()));
        if (src.hasManagingOrganization())
            tgt.setManagingOrganization(VersionConvertor_30_40.convertReference(src.getManagingOrganization()));
        if (src.hasContact()) {
            for (org.hl7.fhir.r4.model.ContactPoint t : src.getContact()) tgt.addContact(VersionConvertor_30_40.convertContactPoint(t));
        }
        if (src.hasPeriod())
            tgt.setPeriod(VersionConvertor_30_40.convertPeriod(src.getPeriod()));
        if (src.hasPayloadType()) {
            for (org.hl7.fhir.r4.model.CodeableConcept t : src.getPayloadType()) tgt.addPayloadType(VersionConvertor_30_40.convertCodeableConcept(t));
        }
        if (src.hasPayloadMimeType()) {
            for (org.hl7.fhir.r4.model.CodeType t : src.getPayloadMimeType()) tgt.addPayloadMimeType(t.getValue());
        }
        if (src.hasAddressElement())
            tgt.setAddressElement((org.hl7.fhir.dstu3.model.UriType) VersionConvertor_30_40.convertType(src.getAddressElement()));
        if (src.hasHeader()) {
            for (org.hl7.fhir.r4.model.StringType t : src.getHeader()) tgt.addHeader(t.getValue());
        }
        return tgt;
    }

    static public org.hl7.fhir.r4.model.Endpoint.EndpointStatus convertEndpointStatus(org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case ACTIVE:
                return org.hl7.fhir.r4.model.Endpoint.EndpointStatus.ACTIVE;
            case SUSPENDED:
                return org.hl7.fhir.r4.model.Endpoint.EndpointStatus.SUSPENDED;
            case ERROR:
                return org.hl7.fhir.r4.model.Endpoint.EndpointStatus.ERROR;
            case OFF:
                return org.hl7.fhir.r4.model.Endpoint.EndpointStatus.OFF;
            case ENTEREDINERROR:
                return org.hl7.fhir.r4.model.Endpoint.EndpointStatus.ENTEREDINERROR;
            case TEST:
                return org.hl7.fhir.r4.model.Endpoint.EndpointStatus.TEST;
            default:
                return org.hl7.fhir.r4.model.Endpoint.EndpointStatus.NULL;
        }
    }

    static public org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus convertEndpointStatus(org.hl7.fhir.r4.model.Endpoint.EndpointStatus src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case ACTIVE:
                return org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus.ACTIVE;
            case SUSPENDED:
                return org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus.SUSPENDED;
            case ERROR:
                return org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus.ERROR;
            case OFF:
                return org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus.OFF;
            case ENTEREDINERROR:
                return org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus.ENTEREDINERROR;
            case TEST:
                return org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus.TEST;
            default:
                return org.hl7.fhir.dstu3.model.Endpoint.EndpointStatus.NULL;
        }
    }
}
