
package org.sample.utils;

import org.openjdk.jmh.infra.Blackhole;

public interface I {
    void foo(int i, Blackhole blackhole);
}
