
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

public class PolymorphicVirtualCallBenchmark {

    public final static int N = 100_000_000;

    @State(Scope.Benchmark)
    public static class PolymorphicVirtualCallBenchmarkState {
        public V[] objs = new V[N];

        @Setup
        public void setup() throws Exception {
            for (int i = 0; i < objs.length; ++i) {
                switch (i % 8) {
                case 0: objs[i] = new V1(); break;
                case 1: objs[i] = new V2(); break;
                case 2: objs[i] = new V3(); break;
                case 3: objs[i] = new V4(); break;
                case 4: objs[i] = new V5(); break;
                case 5: objs[i] = new V6(); break;
                case 6: objs[i] = new V7(); break;
                case 7: objs[i] = new V8(); break;
                }
            }
        }
    }

    @Benchmark @OperationsPerInvocation(N)
    public void run(PolymorphicVirtualCallBenchmarkState state, Blackhole blackhole) {
        V[] objs = state.objs;
        for (int i = 0; i < objs.length; ++i) {
            objs[i].foo(i, blackhole);
        }
    }

}
