package com.wonderful.apiary.Server.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import com.wonderful.apiary.Main;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

public class ModCommands {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final int LEVEL_ALL = 0;
    public static final int LEVEL_MODERATORS = 1;
    public static final int LEVEL_GAMEMASTERS = 2;
    public static final int LEVEL_ADMINS = 3;
    public static final int LEVEL_OWNERS = 4;
    //private final CommandDispatcher<CommandSourceStack> dispatcher = new CommandDispatcher<>();


    private final CommandDispatcher<CommandSourceStack> dispatcher = new CommandDispatcher<>();


    public ModCommands(Commands.CommandSelection p_82093_) {
        WorldCommand.register(dispatcher);
    }
}
