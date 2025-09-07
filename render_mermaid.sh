#!/bin/bash

# This script is a wrapper around the mmdc command that passes in the
# necessary --no-sandbox flag via a config file. This is required to run
# mmdc in an environment where it would otherwise be blocked by sandboxing.
#
# Usage:
#   ./render_mermaid.sh <input_file.md> <output_file.md>

set -euo pipefail

INPUT_FILE=$1
OUTPUT_FILE=$2

if ! [ -f "node_modules/.bin/mmdc" ]; then
    echo "Error: mmdc not found. Please run 'npm install' first."
    exit 1
fi

./node_modules/.bin/mmdc -p puppeteer-config.json -i "$INPUT_FILE" -o "$OUTPUT_FILE"
