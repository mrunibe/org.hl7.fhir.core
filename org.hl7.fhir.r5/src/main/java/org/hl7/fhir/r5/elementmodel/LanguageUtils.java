package org.hl7.fhir.r5.elementmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.r5.context.ContextUtilities;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.i18n.LanguageFileProducer;
import org.hl7.fhir.utilities.i18n.LanguageFileProducer.LanguageProducerLanguageSession;
import org.hl7.fhir.utilities.i18n.LanguageFileProducer.TextUnit;
import org.hl7.fhir.utilities.i18n.LanguageFileProducer.TranslationUnit;

/**
 * in here:
 *   * generateTranslations
 *   * importFromTranslations
 *   * stripTranslations
 *   * switchLanguage
 *   
 *  in the validator
 *  
 * @author grahamegrieve
 *  generateTranslations = -langTransform export -src {src} -tgt {tgt} -dest {dest}
 *  importFromTranslations =  -langTransform import -src {src} -tgt {tgt} -dest {dest}
 */
public class LanguageUtils {

  IWorkerContext context;
  private List<String> crlist;
  
  
  public LanguageUtils(IWorkerContext context) {
    super();
    this.context = context;
  }

  public void generateTranslations(Element resource, LanguageProducerLanguageSession session) {
    translate(null, resource, session);
  }
  
  
  private void translate(Element parent, Element element, LanguageProducerLanguageSession langSession) {
    if (element.isPrimitive() && isTranslatable(element)) {
      String base = element.primitiveValue();
      if (base != null) {
        String translation = getSpecialTranslation(parent, element, langSession.getTargetLang());
        if (translation == null) {
          translation = element.getTranslation(langSession.getTargetLang());
        }
        langSession.entry(new TextUnit(pathForElement(element), base, translation));
      }
    }
    for (Element c: element.getChildren()) {
      if (!c.getName().equals("designation")) {
        translate(element, c, langSession);
      }
    }
  }
  

  private String getSpecialTranslation(Element parent, Element element, String targetLang) {
    if (parent == null) {
      return null;
    }
    if (Utilities.existsInList(pathForElement(parent), "CodeSystem.concept", "CodeSystem.concept.concept") && "CodeSystem.concept.display".equals(pathForElement(element))) {
      return getDesignationTranslation(parent, targetLang);
    }
    if (Utilities.existsInList(pathForElement(parent), "ValueSet.compose.include.concept") && "ValueSet.compose.include.concept.display".equals(pathForElement(element))) {
      return getDesignationTranslation(parent, targetLang);
    }
    if (Utilities.existsInList(pathForElement(parent), "ValueSet.expansion.contains", "ValueSet.expansion.contains.contains") && "ValueSet.expansion.contains.display".equals(pathForElement(element))) {
      return getDesignationTranslation(parent, targetLang);
    }
    return null;
  }

  private String getDesignationTranslation(Element parent, String targetLang) {
    for (Element e : parent.getChildren("designation")) {
      String lang = e.getNamedChildValue("language");
      if (langsMatch(targetLang, lang)) {
        return e.getNamedChildValue("value");
      }
    }
    return null;
  }

  private boolean isTranslatable(Element element) {    
    return element.getProperty().isTranslatable() && !Utilities.existsInList(pathForElement(element), "CanonicalResource.version");
  }

  private String pathForElement(Element element) {
    // special case support for metadata elements prior to R5:
    if (crlist == null) {
      crlist = new ContextUtilities(context).getCanonicalResourceNames();
    }
    String bp = element.getBasePath();
    if (crlist.contains(element.getProperty().getStructure().getType())) {
      String fp = bp.replace(element.getProperty().getStructure().getType()+".", "CanonicalResource.");
      if (Utilities.existsInList(fp,
         "CanonicalResource.url", "CanonicalResource.identifier", "CanonicalResource.version", "CanonicalResource.name", 
         "CanonicalResource.title", "CanonicalResource.status", "CanonicalResource.experimental", "CanonicalResource.date",
         "CanonicalResource.publisher", "CanonicalResource.contact", "CanonicalResource.description", "CanonicalResource.useContext", 
         "CanonicalResource.jurisdiction"))  {
        return fp;
      }
    }
    return bp; 
  }
  
  public int importFromTranslations(Element resource, Set<TranslationUnit> translations) {
    return importFromTranslations(null, resource, translations);
  }
  
  private int importFromTranslations(Element parent, Element element, Set<TranslationUnit> translations) {
    int t = 0;
    if (element.isPrimitive() && isTranslatable(element)) {
      String base = element.primitiveValue();
      if (base != null) {
        Set<TranslationUnit> tlist = findTranslations(pathForElement(element), base, translations);
        for (TranslationUnit translation : tlist) {
          t++;
          if (!handleAsSpecial(parent, element, translation)) {
            element.setTranslation(translation.getLanguage(), translation.getTgtText());
          }
        }
      }
    }
    for (Element c: element.getChildren()) {
      if (!c.getName().equals("designation")) {
        t = t + importFromTranslations(element, c, translations);
      }
    }
    return t;
  }

  private boolean handleAsSpecial(Element parent, Element element, TranslationUnit translation) {
    if (parent == null) {
      return false;
    }
    if (Utilities.existsInList(pathForElement(parent), "CodeSystem.concept", "CodeSystem.concept.concept") && "CodeSystem.concept.display".equals(pathForElement(element))) {
      return setDesignationTranslation(parent, translation.getLanguage(), translation.getTgtText());
    }
    if (Utilities.existsInList(pathForElement(parent), "ValueSet.compose.include.concept") && "ValueSet.compose.include.concept.display".equals(pathForElement(element))) {
      return setDesignationTranslation(parent, translation.getLanguage(), translation.getTgtText());
    }
    if (Utilities.existsInList(pathForElement(parent), "ValueSet.expansion.contains", "ValueSet.expansion.contains.contains") && "ValueSet.expansion.contains.display".equals(pathForElement(element))) {
      return setDesignationTranslation(parent, translation.getLanguage(), translation.getTgtText());
    }
    return false;
  }

  private boolean setDesignationTranslation(Element parent, String targetLang, String translation) {
    for (Element e : parent.getChildren("designation")) {
      String lang = e.getNamedChildValue("language");
      if (langsMatch(targetLang, lang)) {
        Element value = e.getNamedChild("value");
        if (value != null) {
          value.setValue(translation);
        } else {
          e.addElement("value").setValue(translation);
        }
        return true;
      }
    }
    Element d = parent.addElement("designation");
    d.addElement("language").setValue(targetLang);
    d.addElement("value").setValue(translation);
    return true;
  }

  private Set<TranslationUnit> findTranslations(String path, String src, Set<TranslationUnit> translations) {
    Set<TranslationUnit> res = new HashSet<>();
    for (TranslationUnit translation : translations) {
      if (path.equals(translation.getContext()) && src.equals(translation.getSrcText())) {
        res.add(translation);
      }
    }
    return res;
  }

  public static boolean langsMatch(String dstLang, String srcLang) {
    return dstLang == null ? false : dstLang.equals(srcLang);
  }
}