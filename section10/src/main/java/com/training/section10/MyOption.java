package com.training.section10;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;

public interface MyOption extends DataflowPipelineOptions {


    public void test(){
        System.out.println("hello");
    }

    public void prismTest(){
        System.out.println("prismTest");
    }

}

