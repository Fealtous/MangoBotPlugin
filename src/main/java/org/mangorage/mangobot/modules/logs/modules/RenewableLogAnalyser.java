package org.mangorage.mangobot.modules.logs.modules;

import net.dv8tion.jda.api.entities.Message;
import org.mangorage.mangobot.modules.logs.LogAnalyserModule;

import java.util.function.Supplier;

public final class RenewableLogAnalyser implements LogAnalyserModule {
    private final Supplier<LogAnalyserModule> supplier;

    public RenewableLogAnalyser(Supplier<LogAnalyserModule> logAnalyserModuleSupplier) {
        this.supplier = logAnalyserModuleSupplier;
    }

    @Override
    public void analyse(String str, Message message) {
        supplier.get().analyse(str, message);
    }
}
