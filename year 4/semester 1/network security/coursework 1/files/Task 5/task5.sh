#!/usr/bin/env bash


Contents=$(cat words\(3\).txt)
array=( $Contents );

size=${#array[@]};
x=0;

echo $size;

while [ $x != $size ]
do
#echo ${array[$x]};
let "x=x+1";
done

x=0;

checksum1=$(cat plain.txt | md5sum)
echo $checksum1;

while [ $x -lt $size ]
do

	echo "${array[$x]}";


	openssl enc -aes-128-cbc -d -in cipher-28.txt -out original_task5.txt -k "${array[$x]}"

	checksum2=$(cat original_task5.txt | md5sum)

	echo $checksum2;

	if [ "$checksum1" == "$checksum2" ]
	then
		echo "correct output";
		echo "key is ${array[$x]}";
		echo "key $x out of $size";
		break;
	else
		echo "fail";
	fi

	let "x=x+1";
	echo "key $x out of $size";

done

