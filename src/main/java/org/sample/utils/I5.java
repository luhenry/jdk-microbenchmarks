
package org.sample.utils;

import org.openjdk.jmh.infra.Blackhole;

public class I5 implements I {
    public void foo(int i, Blackhole blackhole) {
        blackhole.consume(i + 5);
    }
}
