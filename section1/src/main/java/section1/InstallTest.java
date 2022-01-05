package section1;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.values.PCollection;

public class InstallTest {

    public static void main(String[] args) {

        Pipeline pipeline=Pipeline.create();
        PCollection<String> outputList = pipeline.apply(TextIO.read().from("section1/src/main/resources/CSV/input.csv"));
        outputList.apply(TextIO.write().to("section1/src/main/resources/CSV/output.csv").withNumShards(1).withSuffix(".csv"));

        pipeline.run();

        //System.out.println("hello");
    }
}

