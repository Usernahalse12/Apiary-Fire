package com.wonderful.apiary.Init;

import com.wonderful.apiary.Main;
import com.wonderful.apiary.Entity.EntityFlameScepter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Main.MOD_ID);


    //火焰权杖：投掷物实体
    public static final RegistryObject<EntityType<EntityFlameScepter>> ENTITY_FLAME_SCEPTER = ENTITY_TYPES.register("entity_flame_scepter",()->{
        return EntityType.Builder.of((EntityType<EntityFlameScepter> entityType, Level level)->{
            return new EntityFlameScepter(entityType,level);
        }, MobCategory.MISC).sized(1F,1F).build("entity_flame_scepter");
    });
}
