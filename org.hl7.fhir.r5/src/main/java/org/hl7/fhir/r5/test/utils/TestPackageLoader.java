package org.hl7.fhir.r5.test.utils;

 import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.context.IContextResourceLoader;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.terminologies.client.TerminologyClientManager.ITerminologyClientFactory;
import org.hl7.fhir.r5.terminologies.client.TerminologyClientR5.TerminologyClientR5Factory;
import org.hl7.fhir.utilities.MarkedToMoveToAdjunctPackage;
import org.hl7.fhir.utilities.npm.NpmPackage;
import org.hl7.fhir.utilities.npm.NpmPackage.PackageResourceInformation;

@MarkedToMoveToAdjunctPackage
public class TestPackageLoader implements IContextResourceLoader {

  private List<String> types;

  public TestPackageLoader(List<String> types) {
    this.types = types;
  }

  @Override
  public Bundle loadBundle(InputStream stream, boolean isJson) throws FHIRException, IOException {
    return null;
  }

  @Override
  public Resource loadResource(InputStream stream, boolean isJson) throws FHIRException, IOException {
    return isJson ? new JsonParser().parse(stream) : new XmlParser().parse(stream);
  }

  @Override
  public List<String> getTypes() {
    return types;
  }

  @Override
  public String getResourcePath(Resource resource) {
    return resource.fhirType().toLowerCase()+"-"+resource.getId()+".html";
  }

  @Override
  public IContextResourceLoader getNewLoader(NpmPackage npm) {
    return this;
  }

  @Override
  public List<CodeSystem> getCodeSystems() {
    return new ArrayList<>();
  }

  @Override
  public void setPatchUrls(boolean value) {
    
  }

  @Override
  public String patchUrl(String url, String resourceType) {
    return url;
  }

  @Override
  public IContextResourceLoader setLoadProfiles(boolean value) {
    return this;
  }

  @Override
  public boolean wantLoad(NpmPackage pi, PackageResourceInformation pri) {
    return true;
  }

  @Override
  public ITerminologyClientFactory txFactory() {
    return new TerminologyClientR5Factory();
  }

}
