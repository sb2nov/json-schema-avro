package com.github.fge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.avro.Avro2JsonSchemaProcessor;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ConsoleProcessingReport;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.core.tree.JsonTree;
import com.github.fge.jsonschema.core.tree.SchemaTree;
import com.github.fge.jsonschema.core.tree.SimpleJsonTree;
import com.github.fge.jsonschema.core.util.ValueHolder;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            JsonNode schema = JsonLoader.fromPath(args[0]);

            JsonTree tree = new SimpleJsonTree(schema);
            ValueHolder<JsonTree> input = ValueHolder.hold(tree);
            ProcessingReport report = new ConsoleProcessingReport();

            SchemaTree outputSchema =  new Avro2JsonSchemaProcessor().process(report, input).getValue();
            JsonNode output = outputSchema.getBaseNode();
            System.out.println(output.toPrettyString());
        } catch(IOException | ProcessingException ex) {
            System.out.println("Exception:" + ex.getMessage());
        }
    }
}
