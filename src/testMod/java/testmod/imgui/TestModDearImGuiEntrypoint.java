package testmod.imgui;

import dev.deftu.imgui.DearImGuiEntrypoint;
import dev.deftu.imgui.ImGuiRenderer;

public class TestModDearImGuiEntrypoint implements DearImGuiEntrypoint {

    public ImGuiRenderer createRenderer() {
        return TestModImGuiRenderer.INSTANCE;
    }

}
