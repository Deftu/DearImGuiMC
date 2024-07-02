plugins {
    id("dev.deftu.gradle.multiversion-root")
}

preprocess {
    val fabric_1_21 = createNode("1.21-fabric", 1_21_00, "yarn")
    val fabric_1_20_06 = createNode("1.20.6-fabric", 1_20_06, "yarn")
    val fabric_1_20_04 = createNode("1.20.4-fabric", 1_20_04, "yarn")
    val fabric_1_20_02 = createNode("1.20.2-fabric", 1_20_02, "yarn")
    val fabric_1_20_01 = createNode("1.20.1-fabric", 1_20_01, "yarn")
    val fabric_1_19_04 = createNode("1.19.4-fabric", 1_19_04, "yarn")
    val fabric_1_19_02 = createNode("1.19.2-fabric", 1_19_02, "yarn")
    val fabric_1_18_02 = createNode("1.18.2-fabric", 1_18_02, "yarn")
    val fabric_1_17_01 = createNode("1.17.1-fabric", 1_17_01, "yarn")
    val fabric_1_16_05 = createNode("1.16.5-fabric", 1_16_05, "yarn")

    fabric_1_21.link(fabric_1_20_06)
    fabric_1_20_06.link(fabric_1_20_04)
    fabric_1_20_04.link(fabric_1_20_02)
    fabric_1_20_02.link(fabric_1_20_01)
    fabric_1_20_01.link(fabric_1_19_04)
    fabric_1_19_04.link(fabric_1_19_02)
    fabric_1_19_02.link(fabric_1_18_02)
    fabric_1_18_02.link(fabric_1_17_01)
    fabric_1_17_01.link(fabric_1_16_05)
}
