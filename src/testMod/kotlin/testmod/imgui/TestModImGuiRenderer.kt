package testmod.imgui

import dev.deftu.imgui.ImGuiRenderer
import imgui.ImGui
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.MinecraftClient

object TestModImGuiRenderer : ImGuiRenderer {

    override fun render() {
        if (!ImGui.begin("Test Mod")) {
            ImGui.end()
            return
        }

        ImGui.text("Hello, world!")
        ImGui.separator()
        ImGui.text("This is a test mod.")
        ImGui.separator()
        ImGui.text(MinecraftClient.getInstance().fpsDebugString)
        ImGui.end()
    }

}
