package dev.deftu.imgui

import imgui.ImGui
import imgui.flag.ImGuiCol
import imgui.flag.ImGuiConfigFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import imgui.internal.ImGuiContext
import org.jetbrains.annotations.ApiStatus
import org.lwjgl.glfw.GLFW

@ApiStatus.Internal
public object ImGuiLogic {

    @JvmStatic
    @ApiStatus.Internal
    public var isInitialized: Boolean = false
        private set

    public lateinit var imguiContext: ImGuiContext
        private set

    public lateinit var imguiGl3: ImGuiImplGl3
        private set

    public lateinit var imguiGlfw: ImGuiImplGlfw
        private set

    private var isStarted = false

    @JvmStatic
    @get:ApiStatus.Internal
    public val isMouseHidden: Boolean
        get() = isInitialized && ImGui.getIO().wantCaptureMouse

    @JvmStatic
    @ApiStatus.Internal
    public fun initialize(handle: Long) {
        if (isInitialized) {
            return
        }

        imguiGlfw = ImGuiImplGlfw()
        imguiGl3 = ImGuiImplGl3()

        imGuiInitialize()
        imguiGlfw.init(handle, true)
        imguiGl3.init()

        isInitialized = true
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun mouseFocus() {
        if (!isInitialized) {
            return
        }

        ImGui.setWindowFocus(null)
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun mouseClick(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!isInitialized || !ImGui.getIO().wantCaptureMouse) {
            return false
        }

        ImGui.getIO().setMousePos(mouseX.toFloat(), mouseY.toFloat())
        ImGui.getIO().setMouseDown(button, true)
        return true
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun mouseRelease(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!isInitialized || !ImGui.getIO().wantCaptureMouse) {
            return false
        }

        ImGui.getIO().setMousePos(mouseX.toFloat(), mouseY.toFloat())
        ImGui.getIO().setMouseDown(button, false)
        return true
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun mouseScroll(xOff: Double, yOff: Double): Boolean {
        if (!isInitialized || !ImGui.getIO().wantCaptureMouse) {
            return false
        }

        ImGui.getIO().mouseWheelH = xOff.toFloat()
        ImGui.getIO().mouseWheel = yOff.toFloat()
        return true
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun keyPress(keyCode: Int, mods: Int): Boolean {
        if (!isInitialized || !ImGui.getIO().wantCaptureKeyboard) {
            return false
        }

        ImGui.getIO().keyCtrl = mods and GLFW.GLFW_MOD_CONTROL != 0
        ImGui.getIO().keyAlt = mods and GLFW.GLFW_MOD_ALT != 0
        ImGui.getIO().keyShift = mods and GLFW.GLFW_MOD_SHIFT != 0
        ImGui.getIO().keySuper = mods and GLFW.GLFW_MOD_SUPER != 0
        ImGui.getIO().setKeysDown(keyCode, true)
        return true
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun keyRelease(keyCode: Int, mods: Int): Boolean {
        if (!isInitialized || !ImGui.getIO().wantCaptureKeyboard) {
            return false
        }

        ImGui.getIO().keyCtrl = mods and GLFW.GLFW_MOD_CONTROL != 0
        ImGui.getIO().keyAlt = mods and GLFW.GLFW_MOD_ALT != 0
        ImGui.getIO().keyShift = mods and GLFW.GLFW_MOD_SHIFT != 0
        ImGui.getIO().keySuper = mods and GLFW.GLFW_MOD_SUPER != 0
        ImGui.getIO().setKeysDown(keyCode, false)
        return true
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun destroy() {
        if (!isInitialized) {
            return
        }

        imguiGl3.dispose()
        imguiGlfw.dispose()
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun startFrame() {
        imguiGlfw.newFrame()
        ImGui.newFrame()

        this.isStarted = true
    }

    @JvmStatic
    @ApiStatus.Internal
    public fun endFrame() {
        if (!this.isStarted) {
            return
        }

        ImGui.render()
        imguiGl3.renderDrawData(ImGui.getDrawData())

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            val backupHandle = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupHandle);
        }
    }

    private fun imGuiInitialize() {
        imguiContext = ImGui.createContext()

        val io = ImGui.getIO()
        io.iniFilename = null
        io.configViewportsNoTaskBarIcon = true;
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard)
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable)
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable)
        io.configViewportsNoTaskBarIcon = true
        io.fonts.addFontDefault()

        if (io.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            val style = ImGui.getStyle()
            style.windowRounding = 0.0f
            style.setColor(ImGuiCol.WindowBg, ImGui.getColorU32(ImGuiCol.WindowBg, 1f))
        }
    }

}
