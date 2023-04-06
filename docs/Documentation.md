---
layout: page
title: Documentation guide

---

## Documentation

**Setting up and maintaining the project website:**

* We use [**Jekyll**](https://jekyllrb.com/) to manage documentation.
* The `docs/` folder is used for documentation.
* To learn how set it up and maintain the project website, follow the guide
  [_[se-edu/guides] **Using Jekyll for project
  documentation**_](https://se-education.org/guides/tutorials/jekyll.html).
* Note these points when adapting the documentation to a different
  project/product:
  * The 'Site-wide settings' section of the page linked above has information
    on how to update site-wide elements such as the top navigation bar.

<div markdown="span" class="alert alert-success">
:bulb: In addition to updating content files, you might have to update the
config files `docs\_config.yml` and `docs\_sass\minima\_base.scss` (which
contains a reference to `Mycelium` that comes into play when converting
documentation pages to PDF format).
</div>

**Style guidance:**

* Follow the [**_Google developer documentation style
  guide_**](https://developers.google.com/style)
* Also relevant is the [_[se-edu/guides] **Markdown coding
  standard**_](https://se-education.org/guides/conventions/markdown.html)

**Diagrams:**

We use both [draw.io](https://app.diagrams.net/) and
[PlantUML](https://plantuml.com/) as diagramming tools. The former is a drag
and drop editor, while the latter defines UML diagrams through plain text
files.

* The `docs/images` directory contains ready-for-use pictures in PNG format.
  This may include UML diagrams and screenshots.
* The `docs/diagrams` directory contains `.puml` files (for PlantUML) and
  `.xml` or `.drawio` files (for draw.io) which allow editing and regenerating
  of diagrams.
* All `.puml` files are placed within subdirectories of the `docs/diagrams`
  directory based on which component they are most closely aligned with. The
  same directory structure is used in the `docs/images` directory.
