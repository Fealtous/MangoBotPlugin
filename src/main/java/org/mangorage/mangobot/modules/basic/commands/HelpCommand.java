/*
 * Copyright (c) 2023. MangoRage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.mangorage.mangobot.modules.basic.commands;

import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.NotNull;
import org.mangorage.mangobotapi.core.commands.Arguments;
import org.mangorage.mangobotapi.core.commands.CommandResult;
import org.mangorage.mangobotapi.core.commands.IBasicCommand;
import org.mangorage.mangobotapi.core.plugin.api.CorePlugin;

public class HelpCommand implements IBasicCommand {

    private final CorePlugin corePlugin;

    public HelpCommand(CorePlugin corePlugin) {
        this.corePlugin = corePlugin;
    }


    @NotNull
    @Override
    public CommandResult execute(Message message, Arguments args) {
        var settings = corePlugin.getMessageSettings();
        String cmd = args.get(0);
        if (cmd == null) {
            settings.apply(message.reply(usage())).queue();
            return CommandResult.PASS;
        }

        var command = corePlugin.getCommandRegistry().getCommand(cmd);
        if (command == null) {
            settings.apply(message.reply("Command not found!\nUsage: " + usage())).queue();
            return CommandResult.PASS;
        }

        settings.apply(message.reply(command.usage())).queue();

        return CommandResult.PASS;
    }

    @Override
    public String commandId() {
        return "help";
    }

    @Override
    public String usage() {
        return "!help <command/alias>";
    }
}
