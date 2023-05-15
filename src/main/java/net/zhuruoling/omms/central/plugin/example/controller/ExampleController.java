package net.zhuruoling.omms.central.plugin.example.controller;

import net.zhuruoling.omms.central.controller.Controller;
import net.zhuruoling.omms.central.controller.Status;
import net.zhuruoling.omms.central.controller.console.ControllerConsole;
import net.zhuruoling.omms.central.controller.console.input.PrintTarget;
import net.zhuruoling.omms.central.controller.console.output.InputSource;
import net.zhuruoling.omms.central.controller.crashreport.CrashReportStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleController extends Controller {

    String name = "test";

    boolean statusQueryable = true;
    @Override
    public boolean isStatusQueryable() {
        return true;
    }

    @Override
    public List<String> sendCommand(String s) {
        return Arrays.stream(new String[]{"command is",s,"THIS IS A TEST!"}).toList();
    }

    @Override
    public ControllerConsole startControllerConsole(InputSource inputSource, PrintTarget<?, ControllerConsole> printTarget, String id) {
        return new ExampleControllerConsole(this, id, inputSource, printTarget);
    }

    @Override
    public Status queryControllerStatus() {
        return new Status("fabric", 114514,1919810,new ArrayList<>());
    }

    @Override
    public CrashReportStorage convertCrashReport(String s) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "fabric";
    }
}
