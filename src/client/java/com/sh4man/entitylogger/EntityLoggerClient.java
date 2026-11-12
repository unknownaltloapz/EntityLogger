package com.sh4man.entitylogger;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;

public final class EntityLoggerClient implements ClientModInitializer {
    private static final String MOD_ID = "entitylogger";
    private static boolean enabled;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath(MOD_ID, "main")
        );

        KeyMapping toggleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.entitylogger.toggle",
                InputConstants.Type.KEYBOARD,
                InputConstants.KEY_P,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.consumeClick()) {
                enabled = !enabled;

                if (client.player != null) {
                    if (enabled) {
                        client.player.sendOverlayMessage(Component.literal(
                                "ENTITYLOGGER ENABLED - " + countTargets(client) + " TARGETS"
                        ));
                    } else {
                        client.player.sendOverlayMessage(Component.literal("ENTITYLOGGER DISABLED"));
                    }
                }
            }
        });
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static boolean shouldHighlight(Entity entity) {
        return enabled && (entity instanceof FallingBlockEntity || entity instanceof Display.BlockDisplay);
    }

    private static int countTargets(Minecraft client) {
        if (client.level == null) {
            return 0;
        }

        int count = 0;
        for (Entity entity : client.level.entitiesForRendering()) {
            if (shouldHighlight(entity)) {
                count++;
            }
        }
        return count;
    }
}
