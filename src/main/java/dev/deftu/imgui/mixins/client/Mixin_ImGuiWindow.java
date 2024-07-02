package dev.deftu.imgui.mixins.client;

import dev.deftu.imgui.ImGuiLogic;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class Mixin_ImGuiWindow {

    @Shadow @Final private long handle;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void imgui$onCreate(
            WindowEventHandler eventHandler,
            MonitorTracker monitorTracker,
            WindowSettings windowSettings,
            String videoMode,
            String title,
            CallbackInfo ci
    ) {
        ImGuiLogic.initialize(this.handle);
    }

}
