package net.zhuruoling.omms.central.plugin.example.controller;

import net.zhuruoling.omms.central.controller.Controller;
import net.zhuruoling.omms.central.controller.console.ControllerConsole;
import net.zhuruoling.omms.central.controller.console.input.PrintTarget;
import net.zhuruoling.omms.central.controller.console.output.InputSource;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.locks.LockSupport;

public class ExampleControllerConsole extends Thread implements ControllerConsole {

    ExampleController exampleController;
    String consoleId;
    InputSource inputSource;
    PrintTarget<?, ControllerConsole> printTarget;
    Logger logger = LoggerFactory.getLogger("ExampleControllerConsole");

    public ExampleControllerConsole(ExampleController exampleController, String consoleId, InputSource inputSource, PrintTarget<?, ControllerConsole> printTarget) {
        super("ExampleControllerConsole");
        this.exampleController = exampleController;
        this.consoleId = consoleId;
        this.inputSource = inputSource;
        this.printTarget = printTarget;
    }

    @Override
    public Controller getController() {
        return exampleController;
    }

    @Override
    public String getConsoleId() {
        return consoleId;
    }

    /**
     * write your custom console logic here;
     * here is a simple echo console.
     */
    @Override
    public void run() {
        printTarget.println("Type :q to exit console.",this);
        String line = inputSource.getLine();
        try {
            while (line != null && !line.equals(":q")) {
                if (!line.isEmpty()){
                    logger.debug(line);
                    printTarget.println("input: %s".formatted(line), this);
                }
                sleep(50);
                line = inputSource.getLine();
            }
        }catch (InterruptedException ignored){}
        printTarget.println("Exiting.", this);
    }

    @Override
    public InputSource getInputSource() {
        return inputSource;
    }

    @Override
    public void close() {
        printTarget.println("Bye!",this);
        this.interrupt();
    }
}
