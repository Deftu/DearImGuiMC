package dev.deftu.imgui

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.entrypoint.EntrypointContainer
import org.jetbrains.annotations.ApiStatus

public object DearImGuiClient : ClientModInitializer {

    private val entrypoints = mutableListOf<DearImGuiEntrypoint>()
    private val renderers = mutableListOf<ImGuiRenderer>()

    @ApiStatus.Internal
    override fun onInitializeClient() {
        entrypoints.addAll(FabricLoader.getInstance().getEntrypointContainers("imgui", DearImGuiEntrypoint::class.java).map(EntrypointContainer<DearImGuiEntrypoint>::getEntrypoint))

        for (entrypoint in entrypoints) {
            entrypoint.createRenderer()?.let(renderers::add)
        }
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun render() {
        for (renderer in renderers) {
            renderer.render()
        }

        for (entrypoint in entrypoints) {
            entrypoint.render()
        }
    }

}
