package com.wonderful.apiary;

import com.wonderful.apiary.Init.EntityInit;
import com.wonderful.apiary.onRender.RenderEntityFlameScepter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber {

    //实体的贴图信息
    //@SubscribeEvent
    //public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        //event.registerLayerDefinition(ModelRe8Dimi.LAYER_LOCATION, ModelRe8Dimi::createBodyLayer);
    //}


    //实体渲染信息
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.ENTITY_FLAME_SCEPTER.get(), RenderEntityFlameScepter::new);
    }


    //生物属性信息
    //@SubscribeEvent
    //public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        //event.put(EntityInit.RE8DIMI.get(), EntityRe8Dimi.prepareAttributes().build());
    //}
}
