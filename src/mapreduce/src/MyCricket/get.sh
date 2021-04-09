#!/bin/bash

mkdir MyCricket
if [[ $? -eq 0 ]]; then

    cd MyCricket || exit
    files=(
        "CricMapper.java"
        "CricReducer.java"
        "MyCric.java"
        "input_cric.txt"
    )
    for i in "${files[@]}"; do
        wget "https://raw.githubusercontent.com/shivanshu-semwal/hadoop/master/src/mapreduce/src/MyCricket/$i"
    done
fi
