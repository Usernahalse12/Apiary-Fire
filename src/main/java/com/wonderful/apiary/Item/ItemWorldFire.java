package com.wonderful.apiary.Item;

import com.wonderful.apiary.Main;
import com.wonderful.apiary.Network.Destruction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ItemWorldFire extends Item {
    public ItemWorldFire() {
        super(new Item.Properties().tab(Main.PAGING_TAB));
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_41432_, @NotNull Player p_41433_, @NotNull InteractionHand p_41434_) {
        if (p_41434_.name().equals("OFF_HAND")) //仅在副手生效
        {
            p_41433_.sendMessage(new TextComponent("§g砸瓦鲁多"), p_41433_.getUUID());
            new Destruction().GoodbyeWorld(p_41432_, p_41433_);
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }

    //物品描述
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_); //作为该物品的属性
        p_41423_.add(new TranslatableComponent("§4§k再见，世界"));
    }
}
