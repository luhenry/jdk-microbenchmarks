
package org.sample;

import org.openjdk.jmh.infra.Blackhole;

abstract class A {
    public abstract void foo(int i, Blackhole blackhole);
}
