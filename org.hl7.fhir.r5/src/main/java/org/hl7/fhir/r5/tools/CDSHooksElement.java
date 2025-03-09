package org.hl7.fhir.r5.tools;


/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, \
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this \
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, \
     this list of conditions and the following disclaimer in the documentation \
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND \
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED \
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. \
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, \
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT \
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR \
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, \
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) \
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE \
  POSSIBILITY OF SUCH DAMAGE.
  */

// Generated on Thu, Mar 23, 2023 19:59+1100 for FHIR v5.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hl7.fhir.r5.tools.Enumerations.*;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.model.*;
import org.hl7.fhir.instance.model.api.ICompositeType;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.DatatypeDef;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;

/**
 * This structure is defined to allow the FHIR Validator to validate a CDSHooks Services Element with Extensions. TODO: This content will be moved to the CDS Hooks specification in the future
 */
@DatatypeDef(name="CDSHooksElement")
public abstract class CDSHooksElement extends LogicalBase implements ICompositeType {

    /**
     * A JSON object that has properties where the names are defined by the extension definitions
     */
    @Child(name = "extension", type = {CDSHooksExtensions.class}, order=0, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Object that has Named Extension Properties", formalDefinition="A JSON object that has properties where the names are defined by the extension definitions" )
    protected CDSHooksExtensions extension;

    private static final long serialVersionUID = 362241525L;

  /**
   * Constructor
   */
    public CDSHooksElement() {
      super();
    }

    /**
     * @return {@link #extension} (A JSON object that has properties where the names are defined by the extension definitions)
     */
    public CDSHooksExtensions getExtension() { 
      if (this.extension == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create CDSHooksElement.extension");
        else if (Configuration.doAutoCreate())
          this.extension = new CDSHooksExtensions(); // cc
      return this.extension;
    }

    public boolean hasExtension() { 
      return this.extension != null && !this.extension.isEmpty();
    }

    /**
     * @param value {@link #extension} (A JSON object that has properties where the names are defined by the extension definitions)
     */
    public CDSHooksElement setExtension(CDSHooksExtensions value) { 
      this.extension = value;
      return this;
    }

      protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("extension", "http://hl7.org/fhir/tools/StructureDefinition/CDSHooksExtensions", "A JSON object that has properties where the names are defined by the extension definitions", 0, 1, extension));
      }

      @Override
      public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
        switch (_hash) {
        case -612557761: /*extension*/  return new Property("extension", "http://hl7.org/fhir/tools/StructureDefinition/CDSHooksExtensions", "A JSON object that has properties where the names are defined by the extension definitions", 0, 1, extension);
        default: return super.getNamedProperty(_hash, _name, _checkValid);
        }

      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -612557761: /*extension*/ return this.extension == null ? new Base[0] : new Base[] {this.extension}; // CDSHooksExtensions
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -612557761: // extension
          this.extension = (CDSHooksExtensions) value; // CDSHooksExtensions
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("extension")) {
          this.extension = (CDSHooksExtensions) value; // CDSHooksExtensions
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -612557761:  return getExtension();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -612557761: /*extension*/ return new String[] {"http://hl7.org/fhir/tools/StructureDefinition/CDSHooksExtensions"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("extension")) {
          this.extension = new CDSHooksExtensions();
          return this.extension;
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "CDSHooksElement";

  }

      public abstract CDSHooksElement copy();

      public void copyValues(CDSHooksElement dst) {
        super.copyValues(dst);
        dst.extension = extension == null ? null : extension.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof CDSHooksElement))
          return false;
        CDSHooksElement o = (CDSHooksElement) other_;
        return compareDeep(extension, o.extension, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof CDSHooksElement))
          return false;
        CDSHooksElement o = (CDSHooksElement) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(extension);
      }


}

