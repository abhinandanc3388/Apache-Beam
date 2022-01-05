package section3;


import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

public class MapElementsExample {


    /**
     *
     *   to convert lowercase letters to uppercase  from customer.csv
     *   we have two approach to do this 1) TypeDescriptor  2) SimpleFuntion
     *   both these are used for one to one mapping
     *   for one to zero or more mapping we use ParDo
     */
    public static void main(String[] args) {

        Pipeline p = Pipeline.create();

        PCollection<String> pCustList= p.apply(TextIO.read().from("section3/src/main/resources/Input/customer.csv"));

        //Using TypeDescriptors

        PCollection<String> pOutput=pCustList.apply(MapElements.into(TypeDescriptors.strings()).via((String obj) -> obj.toUpperCase()));

        pOutput.apply(TextIO.write().to("section3/src/main/resources/Output/cust_output.csv").withNumShards(1).withSuffix(".csv"));

        p.run();

    }
}

