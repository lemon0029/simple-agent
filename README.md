# Simple Agent

## premain
![image-20230518124859994 PM](https://yec-dev.oss-cn-guangzhou.aliyuncs.com/image-20230518124859994%20PM.png)

## agentmain

![image-2023090871846983 PM](https://yec-dev.oss-cn-guangzhou.aliyuncs.com/image-2023090871846983%20PM.png)



```java
import com.sun.tools.attach.VirtualMachine;

public class SimpleLauncher {
    public static void main(String... args) throws Exception {
        if (args.length < 2) return;

        var options = args.length >= 3 ? args[2] : null;
        attachAgent(args[0], args[1], options);
    }

    private static void 
    attachAgent(String targetJvmPid, String agentJarPath, String options) throws Exception {
        var virtualMachine = VirtualMachine.attach(targetJvmPid);
        virtualMachine.loadAgent(agentJarPath, options);
        virtualMachine.detach();
    }
}
```

