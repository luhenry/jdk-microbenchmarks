
package org.sample.utils;

import org.openjdk.jmh.infra.Blackhole;

public class I3 implements I {
    public void foo(int i, Blackhole blackhole) {
        blackhole.consume(i + 3);
    }
}
