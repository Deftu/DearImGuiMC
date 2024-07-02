# DearImGui for Minecraft
A basic library mod giving developers the ability to use ImGui within Minecraft.

---

[![Discord Badge](https://raw.githubusercontent.com/intergrav/devins-badges/v2/assets/cozy/social/discord-singular_64h.png)](https://s.deftu.dev/discord)
[![Ko-Fi Badge](https://raw.githubusercontent.com/intergrav/devins-badges/v2/assets/cozy/donate/kofi-singular_64h.png)](https://s.deftu.dev/kofi)

---

## Usage

### Setup

You need to add it as a dependency in your `build.gradle(.kts)` file.

```kotlin
repositories {
    maven("https://maven.deftu.dev/snapshots")
}

dependencies {
    modImplementation("dev.deftu:DearImGui-<MINECRAFTVERSION>-fabric:<VERSION>")
}
```
Of course, replace `<MINECRAFTVERSION>` with the version of Minecraft you are developing for, and `<VERSION>` with the version of the library you want to use.

### Rendering

To render for ImGui, you need to create your own `DearImGuiEntrypoint` and add it to your `fabric.mod.json` file,

```json
{
    "entrypoints": {
        "imgui": ["your.package.here.DearImGuiEntrypoint"]
    }
}
```

`DearImGuiEntrypoint` has two methods you can override - `createRenderer` and `render`.

```java
public class ExampleDearImGuiEntrypoint implements DearImGuiEntrypoint {
    
    @Override
    public ImGuiRenderer createRenderer() {
        return new ExampleImGuiRenderer();
    }

    @Override
    public void render() {
        // Render ImGui here
    }
    
}
```

Both ultimately achieve the same goal, but `createRenderer` is more flexible and allows you to pass in additional parameters to your renderer.

### Example Rendering

```java
public class ExampleImGuiRenderer implements ImGuiRenderer {

    @Override
    public void render() {
        ImGui.showDemoWindow();
    }

}
```

```java
public class ExampleDearImGuiEntrypoint implements DearImGuiEntrypoint {

    @Override
    public void render() {
        ImGui.showDemoWindow();
    }
    
}
```

---

[![BisectHosting](https://www.bisecthosting.com/partners/custom-banners/8fb6621b-811a-473b-9087-c8c42b50e74c.png)](https://s.deftu.dev/bisect)

---

**This project is licensed under [LGPL-3.0][lgpl]**\
**&copy; 2024 Deftu**

[lgpl]: https://www.gnu.org/licenses/lgpl-3.0.en.html
