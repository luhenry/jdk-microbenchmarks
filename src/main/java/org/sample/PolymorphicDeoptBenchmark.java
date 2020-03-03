
package org.sample;

import org.sample.utils.*;

import java.lang.Exception;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class PolymorphicDeoptBenchmark {

    public final static int N = 100_000_000;

    @State(Scope.Benchmark)
    public static class PolymorphicDeoptBenchmarkState {
        public V[] objs = new V[N];

        @Setup
        public void setup() throws Exception {
            int cutoff1 = (int)(objs.length * .90);
            int cutoff2 = (int)(objs.length * .95);
            for (int i = 0; i < cutoff1; ++i) {
                switch (i % 2) {
                case 0: objs[i] = new V1(); break;
                case 1: objs[i] = new V2(); break;
                }
            }
            for (int i = cutoff1; i < cutoff2; ++i) {
                switch (i % 4) {
                case 0: objs[i] = new V1(); break;
                case 1: objs[i] = new V2(); break;
                case 2:
                case 3: objs[i] = new V3(); break;
                }
            }
            for (int i = cutoff2; i < objs.length; ++i) {
                switch (i % 4) {
                case 0:
                case 1: objs[i] = new V3(); break;
                case 2:
                case 3: objs[i] = new V4(); break;
                }
            }
        }
    }

    @Benchmark @OperationsPerInvocation(N)
    public void run(PolymorphicDeoptBenchmarkState state, Blackhole blackhole) {
        V[] objs = state.objs;
        for (int i = 0; i < objs.length; ++i) {
            objs[i].foo(i, blackhole);
        }
    }

}
