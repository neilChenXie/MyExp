#!/bin/bash

jekyll build

scp -r _site/* root@172.16.1.114:/opt/apache/htdocs/Sekorm/
