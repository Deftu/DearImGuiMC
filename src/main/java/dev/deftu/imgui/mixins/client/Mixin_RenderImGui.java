package dev.deftu.imgui.mixins.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.deftu.imgui.DearImGuiClient;
import dev.deftu.imgui.ImGuiLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class Mixin_RenderImGui {

    @Inject(method = "flipFrame", at = @At("HEAD"), remap = false)
    private static void imgui$renderImGui(long handle, CallbackInfo ci) {
        ImGuiLogic.startFrame();
        DearImGuiClient.render();
        ImGuiLogic.endFrame();
    }

}
