package net.zhuruoling.omms.central.plugin.example.controller;

import net.zhuruoling.omms.central.controller.Controller;
import net.zhuruoling.omms.central.controller.console.ControllerConsole;
import net.zhuruoling.omms.central.controller.console.input.PrintTarget;
import net.zhuruoling.omms.central.controller.console.output.InputSource;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

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
     * write your custom console logic here
     */
    @Override
    public void run() {
        logger.info("Type :q to exit console.");
        String line = inputSource.getLine();
        while (line != null && !line.equals(":q")){
            printTarget.println("input: %s".formatted( line), this);
            line = inputSource.getLine();
        }
        logger.info("Exiting.");
    }

    @Override
    public InputSource getInputSource() {
        return inputSource;
    }

    @Override
    public void close() {
        System.out.println("Bye!");
    }
}
