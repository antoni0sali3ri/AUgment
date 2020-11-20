include(":augment-core", ":augment-core-ui", ":augment-database")

if (!System.getenv().containsKey("JITPACK"))
    include(":augment-examples")

rootProject.name = "AUgment"