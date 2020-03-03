
package org.sample.utils;

import org.openjdk.jmh.infra.Blackhole;

public class I6 implements I {
    public void foo(int i, Blackhole blackhole) {
        blackhole.consume(i + 6);
    }
}
