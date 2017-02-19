#!/bin/bash
ls -ali | grep -v '^[0-9]*.l.*' | sed 's/\([0-9]*\).*/\1/' | sort | uniq | grep '[0-9]\+' | wc -l
