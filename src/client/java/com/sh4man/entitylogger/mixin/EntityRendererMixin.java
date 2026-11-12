package com.sh4man.entitylogger.mixin;

import com.sh4man.entitylogger.EntityLoggerClient;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityRenderer.class, remap = false)
public abstract class EntityRendererMixin {
    @Inject(
            method = "createRenderState(Lnet/minecraft/world/entity/Entity;F)Lnet/minecraft/client/renderer/entity/state/EntityRenderState;",
            at = @At("RETURN"),
            remap = false
    )
    private void entitylogger$applyOutline(Entity entity, float partialTicks, CallbackInfoReturnable<EntityRenderState> cir) {
        if (EntityLoggerClient.shouldHighlight(entity)) {
            cir.getReturnValue().outlineColor = 0xFFFFFF;
        }
    }
}
