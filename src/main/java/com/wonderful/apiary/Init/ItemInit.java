package com.wonderful.apiary.Init;

import com.wonderful.apiary.Main;
import com.wonderful.apiary.Item.ItemFlameScepter;
import com.wonderful.apiary.Item.ItemStartButton;
import com.wonderful.apiary.Item.ItemTeaCon;
import com.wonderful.apiary.Item.ItemWorldFire;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemInit {
    //注册机
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Main.MOD_ID);

    /**
     * 构造物品
     */
    //火焰权杖
    public static RegistryObject<Item> FLAME_SCEPTER = register("flame_scepter", ItemFlameScepter::new);
    //开始按钮
    public static RegistryObject<Item> START_BUTTON = register("start_button", ItemStartButton::new);
    //TeaCon
    public static RegistryObject<Item> TEACON = register("teacon", ItemTeaCon::new);
    //世界之火
    public static RegistryObject<Item> WORLD_FIRE = register("world_fire", ItemWorldFire::new);


    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }
}
