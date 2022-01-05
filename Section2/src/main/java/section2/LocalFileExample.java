package section2;

import java.util.ArrayList;
import java.util.List;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.TypeDescriptors;


/***
instead of hardcoding input and output file we pass it through command line  like below

 did some changes in pom.xml as well

 we have PipelineOptions interface with the help of it we create one more interface extending it
 and write getter and setter to accept input through command line

go to target folder in current project and run below command

java -cp .\Section2-1.0-SNAPSHOT-jar-with-dependencies.jar section2.LocalFileExample --inputFile="I:\MS\saga-choreography-examp
le-main\Section2\src\main\resources\input.csv" --outputFile="I:\MS\saga-choreography-example-main\Section2\src\main\resources\output.csv" --extn=".csv"

 ****/
public class LocalFileExample {

    public static void main(String[] args) {

        Myoptions options=PipelineOptionsFactory.fromArgs(args).withValidation().as(Myoptions.class);


        Pipeline pipeline = Pipeline.create(options);

        PCollection<String> output= pipeline.apply(TextIO.read().from(options.getInputFile()));

        output.apply(TextIO.write().to(options.getOutputFile()).withNumShards(1).withSuffix(options.getExtn()));

        pipeline.run();

    }
}
