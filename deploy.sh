#!/bin/bash

if [ -z $1 ];
then
	echo "1st arg required"

else
	git add -A .
	git commit -m "$1"
	git push 

	svn cleanup .
	svn update
	svn status
	svn commit -m "$1"

	jekyll build

	scp -r _site/* root@172.16.1.114:/opt/apache/htdocs/Sekorm/
fi
