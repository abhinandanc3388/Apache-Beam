package section3;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.View;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionView;

import java.util.Map;

public class SideInputExample {

    public static void main(String[] args) {
        //left side value ctrl+alt+v
        Pipeline p = Pipeline.create();

        PCollection<KV<String, String>> pReturn = p.apply(TextIO.read().from("section3/src/main/resources/Input/return.csv")).apply(ParDo.of(new DoFn<String, KV<String, String>>() {
            @ProcessElement
            public void process(ProcessContext c) {
                String[] arr = c.element().split(",");
                c.output(KV.of(arr[0], arr[1]));
            }
        }));

        PCollectionView<Map<String, String>> pMap = pReturn.apply(View.asMap());

        PCollection<String> pCustList = p.apply(TextIO.read().from("section3/src/main/resources/Input/cust_order.csv"));

        pCustList.apply(ParDo.of(new DoFn<String, Void>() {

            @ProcessElement
            public void process(ProcessContext p) {

                Map<String, String> pSideInputView = p.sideInput(pMap);
                String[] arr = p.element().split(",");
                String custName = pSideInputView.get(arr[0]);
                if (custName == null) {
                    System.out.println(p.element());
                }
            }

        }).withSideInputs(pMap));

    }
}
