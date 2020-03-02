
package org.sample;

import org.openjdk.jmh.infra.Blackhole;

class A8 extends A {
    public void foo(int i, Blackhole blackhole) {
        blackhole.consume(i + 8);
    }
}
