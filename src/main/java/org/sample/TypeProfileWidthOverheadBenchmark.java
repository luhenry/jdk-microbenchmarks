
package org.sample;

import java.lang.Exception;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class TypeProfileWidthOverheadBenchmark {

    public final static int N = 100_000_000;

    @State(Scope.Benchmark)
    public static class TypeProfileWidthOverheadBenchmarkState {
        public A[] objs = new A[N];

        @Setup
        public void setup() throws Exception {
            for (int i = 0; i < objs.length; ++i) {
                switch (i % 8) {
                case 0: objs[i] = new A1(); break;
                case 1: objs[i] = new A2(); break;
                case 2: objs[i] = new A3(); break;
                case 3: objs[i] = new A4(); break;
                case 4: objs[i] = new A5(); break;
                case 5: objs[i] = new A6(); break;
                case 6: objs[i] = new A7(); break;
                case 7: objs[i] = new A8(); break;
                }
            }
        }
    }

    @Benchmark @OperationsPerInvocation(N)
    public void run(TypeProfileWidthOverheadBenchmarkState state, Blackhole blackhole) {
        A[] objs = state.objs;
        for (int i = 0; i < objs.length; ++i) {
            objs[i].foo(i, blackhole);
        }
    }

}
