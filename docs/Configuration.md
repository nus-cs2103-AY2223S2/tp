---
layout: page
title: Configuration guide

---

## Configuration

**Relevant classes:**
[`Config.java`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/commons/core/Config.java),
[`UserPrefs.java`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/UserPrefs.java)

Certain properties of the application can be controlled (e.g. user preferences
file location, logging level) through the configuration file (default:
`config.json`). A default configuration file is generated the first time the
application is run. It is in JSON format and contains name-value pairs. As of
now, there are only two configuration options:

1. Log level, defaulting to `INFO`
1. Path to preferences settings, defaulting to `preferences.json`

The `preferences.json` file saves user preferences, such as the last recorded
window size and position, as well as the path to the data file. You may inspect
these files directly for more information.

