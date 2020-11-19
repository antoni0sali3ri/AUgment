include(":augment-core", ":augment-core-ui")

if (!System.getenv().containsKey("JITPACK"))
    include(":augment-examples")

rootProject.name = "AUgment"