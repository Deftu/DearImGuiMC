package testmod.imgui;

import dev.deftu.imgui.DearImGuiEntrypoint;
import dev.deftu.imgui.ImGuiRenderer;
import net.fabricmc.loader.api.FabricLoader;

public class TestModDearImGuiEntrypoint implements DearImGuiEntrypoint {

    public ImGuiRenderer createRenderer() {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? TestModImGuiRenderer.INSTANCE : null;
    }

}
