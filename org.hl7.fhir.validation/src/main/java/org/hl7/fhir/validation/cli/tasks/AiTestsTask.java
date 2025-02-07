package org.hl7.fhir.validation.cli.tasks;

import java.io.IOException;
import java.io.PrintStream;

import org.hl7.fhir.utilities.SystemExitManager;
import org.hl7.fhir.utilities.TimeTracker;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JsonException;
import org.hl7.fhir.utilities.json.model.JsonObject;
import org.hl7.fhir.utilities.json.parser.JsonParser;
import org.hl7.fhir.validation.ai.AITests;
import org.hl7.fhir.validation.cli.model.CliContext;
import org.hl7.fhir.validation.cli.utils.Params;
import org.hl7.fhir.validation.special.TxTester;

public class AiTestsTask extends StandaloneTask{
  @Override
  public String getName() {
    return "aiTests";
  }

  @Override
  public String getDisplayName() {
    return "AI Tests";
  }

  @Override
  public boolean isHidden() {
    return true;
  }

  @Override
  public boolean shouldExecuteTask(CliContext cliContext, String[] args) {
    return Params.hasParam(args, Params.AI_TESTS);
  }

  @Override
  public void printHelp(PrintStream out) {

  }

  @Override
  public void executeTask(CliContext cliContext, String[] args, TimeTracker tt, TimeTracker.Session tts) throws Exception {
    String source = Params.getParam(args, Params.SOURCE);
    String config = Params.getParam(args, Params.CONFIG);
    boolean runTests = Params.hasParam(args, Params.RUN_TESTS);
    
    AITests ai = new AITests();
    ai.execute(source, config, runTests);
    SystemExitManager.finish();
  }

}
