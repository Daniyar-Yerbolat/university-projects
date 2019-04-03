#!/usr/bin/env bash


Contents=$(cat words\(3\).txt)
array=( $Contents );

size=${#array[@]};
x=0;

echo $size;

while [ $x -lt $size ]
do
#echo ${array[$x]};
let "x=x+1";
done

x=0;

checksum1=$(cat cipher-29.txt | md5sum)
echo $checksum1;

while [ $x -lt $size ]
do

	echo "${array[$x]}";


	openssl enc -aes-128-cbc -e -nosalt -in plain.txt -out original_task5_b.txt -k "${array[$x]}"

	#wait

	checksum2=$(cat original_task5_b.txt | md5sum)

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

