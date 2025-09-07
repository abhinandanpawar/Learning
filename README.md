# Java Learning Resources

This repository contains a collection of notes and tutorials for learning the Java programming language. Whether you are a beginner or an experienced developer, you will find something useful here.

## Contents

*   [**Java Notes**](./java-notes/README.md): A comprehensive guide to the Java language, from the basics to advanced topics. This is a great resource for developers who want to deepen their understanding of Java.

*   [**Java for Children**](./java-for-children/Dramatis_Personae.md): A fun, story-based introduction to the world of Java. This is a great place to start if you are new to programming.

*   [**Java Interview Prep**](./Java_Interview_Prep.md): A collection of notes and questions to help you prepare for a Java programming interview.

## A Note on Diagrams

Some of the notes in this repository use Mermaid to render diagrams. If you are not seeing diagrams, you may need to use a markdown viewer or browser extension that supports Mermaid.

### Rendering Diagrams Locally

To render the diagrams locally, you can use the `render_mermaid.sh` script. This script uses the `@mermaid-js/mermaid-cli` tool to convert the Mermaid diagrams in a Markdown file into SVGs and embeds them in a new Markdown file.

First, make sure you have the dependencies installed:

```bash
npm install
```

Then, run the script with the input and output file paths:

```bash
./render_mermaid.sh <input_file.md> <output_file.md>
```

For example:

```bash
./render_mermaid.sh java-notes/12-System-Design-with-Java/README.md java-notes/12-System-Design-with-Java/README_with_diagrams.md
```

This will create a new file, `README_with_diagrams.md`, with the Mermaid diagrams rendered as SVGs.
