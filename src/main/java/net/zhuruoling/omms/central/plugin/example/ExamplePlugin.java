package net.zhuruoling.omms.central.plugin.example;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.zhuruoling.omms.central.command.CommandSourceStack;
import net.zhuruoling.omms.central.plugin.PluginMain;
import net.zhuruoling.omms.central.plugin.callback.CommandRegistrationCallback;
import net.zhuruoling.omms.central.plugin.callback.ControllerLoadCallback;
import net.zhuruoling.omms.central.plugin.callback.WhitelistLoadCallback;
import net.zhuruoling.omms.central.plugin.example.controller.ExampleController;
import net.zhuruoling.omms.central.plugin.example.whitelist.ExampleWhitelist;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ExamplePlugin extends PluginMain {

    Logger logger = LoggerFactory.getLogger("ExamplePlugin");

    @Override
    public void onInitialize() {
        ControllerLoadCallback.INSTANCE.register(controllerManager -> {
            controllerManager.addController(new ExampleController());
        });

        CommandRegistrationCallback.INSTANCE.register(commandManager -> {
            commandManager.getCommandDispatcher()
                    .register(LiteralArgumentBuilder.<CommandSourceStack>literal("example")
                            .executes(commandContext -> {
                                commandContext.getSource().sendFeedback("Hello from ExamplePlugin!");
                                commandContext.getSource().sendError("This is a error message using CommandSource.sendError() !");
                                return 0;
                            })
                    );
        });
        WhitelistLoadCallback.INSTANCE.register(whitelistManager -> {
            whitelistManager.addWhitelist(new ExampleWhitelist(net.zhuruoling.omms.central.util.Util.randomStringGen(16)));
        });
        logger.info("Hello World!");
    }
}