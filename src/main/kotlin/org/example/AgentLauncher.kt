package org.example

import java.lang.instrument.Instrumentation

// must be a top-level function, args can be null
fun premain(args: String?, inst: Instrumentation) {
    println("agent: pre-main")

    inst.addTransformer(FooClassTransformer(), true)
}

fun agentmain(args: String?, inst: Instrumentation) {
    println("agent: agent-main")
}