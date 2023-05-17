package org.example

import java.lang.instrument.Instrumentation

class AgentLauncher {

    fun premain(args: String, inst: Instrumentation) {
        println("agent: pre-main")
    }

    fun agentmain(args: String, inst: Instrumentation) {
        println("agent: agent-main")
    }
}