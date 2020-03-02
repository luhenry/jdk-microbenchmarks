
JVM?=java
JVM_ARGS?=

target/benchmarks.jar: src/main/java/org/sample/*.java
	mvn package

run-PolymorphicDeoptBenchmark: target/benchmarks.jar
	LD_LIBRARY_PATH="/usr/lib/x86_64-linux-gnu" java -jar target/benchmarks.jar -jvm -jvm "$(JVM)" -jvmArgs "$(JVM_ARGS) -Xms40g -Xmx40g -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation -XX:CompileCommand=\"log,org/sample/*.*\" -XX:TypeProfileWidth=$(TYPE_PROFILE_WIDTH) -XX:TypeProfileMinimumReceiverPercent=0" -bm ss -f 1 org.sample.PolymorphicDeoptBenchmark.testMethod

run-TypeProfileWidthOverheadBenchmark: target/benchmarks.jar
	LD_LIBRARY_PATH="/usr/lib/x86_64-linux-gnu" java -jar target/benchmarks.jar -jvm "$(JVM)" -jvmArgs "$(JVM_ARGS) -Xms40g -Xmx40g -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation -XX:CompileCommand=\"log,org/sample/*.*\" -XX:TypeProfileWidth=$(TYPE_PROFILE_WIDTH) -XX:CompileThreshold=200000000 -XX:Tier3CompileThreshold=200000000 -XX:Tier3InvocationThreshold=200000000 -XX:Tier3BackEdgeThreshold=200000000" -bm ss -f 10 org.sample.TypeProfileWidthOverheadBenchmark.testMethod
