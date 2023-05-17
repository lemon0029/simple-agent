package org.example

import org.apache.bcel.Repository
import org.apache.bcel.generic.*
import java.lang.instrument.ClassFileTransformer
import java.security.ProtectionDomain

class FooClassTransformer : ClassFileTransformer {

    override fun transform(
        loader: ClassLoader,
        className: String,
        classBeingRedefined: Class<*>?,
        protectionDomain: ProtectionDomain,
        classfileBuffer: ByteArray
    ): ByteArray {
        if (className != "org/example/Foo") {
            return classfileBuffer
        }

        println("transforming class: $className")

        val clazz = Repository.lookupClass(className)
        val classGen = ClassGen(clazz)

        val method = classGen.methods.firstOrNull { it.name == "bar" } ?: return classfileBuffer

        println("transforming method: ${method.name}")

        val methodGen = MethodGen(method, className, classGen.constantPool).apply {
            instructionList = InstructionList().apply {
                append(PUSH(classGen.constantPool, "NEW_RET_VAL_BY_AGENT"))
                append(InstructionFactory.createReturn(Type.STRING))
            }

            setMaxStack()
            setMaxLocals()
        }

        println("transformed method: \n${methodGen.method.code}\n")
        classGen.replaceMethod(method, methodGen.method)

        return classGen.javaClass.bytes
    }

}