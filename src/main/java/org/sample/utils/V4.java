
package org.sample.utils;

import org.openjdk.jmh.infra.Blackhole;

public class V4 extends V {
    public void foo(int i, Blackhole blackhole) {
        blackhole.consume(i + 4);
    }
}
