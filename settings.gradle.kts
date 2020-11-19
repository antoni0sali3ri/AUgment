include(":core", ":core-ui")

if (!System.getenv().containsKey("JITPACK"))
    include(":examples")

rootProject.name = "AUgment"