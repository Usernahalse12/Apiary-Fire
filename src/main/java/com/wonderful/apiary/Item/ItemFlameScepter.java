package com.wonderful.apiary.Item;

import com.wonderful.apiary.Entity.EntityFlameScepter;
import com.wonderful.apiary.Main;
import com.wonderful.apiary.Network.Destruction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

public class ItemFlameScepter extends Item {
    public ItemFlameScepter() {
        super(new Item.Properties().tab(Main.PAGING_TAB).defaultDurability(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_41432_, @NotNull Player p_41433_, @NotNull InteractionHand p_41434_) {
        //TODO
        ItemStack itemstack = p_41433_.getItemInHand(p_41434_);


        if (p_41434_.name().equals("OFF_HAND") && !p_41432_.isClientSide()) //在副手时
        {
                p_41433_.sendMessage(new TextComponent("§c僵尸吃掉了你的内存"), p_41433_.getUUID());
                try {
                    new Destruction().MemoryKiller();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }


        if (p_41434_.name().equals("MAIN_HAND") && !p_41432_.isClientSide()) //在主手时
        {
            //正常能用
            EntityFlameScepter throwEntity = new EntityFlameScepter(p_41432_, p_41433_);
            throwEntity.setItem(itemstack);
            throwEntity.shootFromRotation(p_41433_, p_41433_.getXRot(), p_41433_.getYRot(), 0.0F, 1.5F, 1.0F); //发射实体
            p_41432_.addFreshEntity(throwEntity);
        }

        //返回
        return super.use(p_41432_, p_41433_, p_41434_);
    }


    //物品描述
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_); //作为该物品的属性
        p_41423_.add(new TextComponent("右键使用"));
    }
}
