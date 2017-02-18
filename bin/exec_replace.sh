#!/bin/sh
cd ../report/$1/junittestcase/$1
echo "Enter report logfile path."
sed -i '' 's/ltltlt/</g' *
sed -i '' 's/gtgtgt/>/g' *
echo "Exec sed OK!"


