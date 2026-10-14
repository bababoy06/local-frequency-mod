package dev.localfrequency.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public final class LocalFrequencyClient implements ClientModInitializer {
    public static final String MOD_ID = "local_frequency";

    private static KeyMapping openRadioKey;

    @Override
    public void onInitializeClient() {
        openRadioKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.local_frequency.open_radio",
                GLFW.GLFW_KEY_R,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openRadioKey.consumeClick()) {
                if (client.screen instanceof RadioReceiverScreen) {
                    client.setScreen(null);
                } else if (client.player != null) {
                    client.setScreen(new RadioReceiverScreen());
                }
            }
        });
    }
}
