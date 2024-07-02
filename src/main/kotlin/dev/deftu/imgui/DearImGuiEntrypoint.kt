package dev.deftu.imgui

@JvmDefaultWithoutCompatibility
public interface DearImGuiEntrypoint {

    public fun createRenderer(): ImGuiRenderer? = null

    public fun render() {
    }

}
