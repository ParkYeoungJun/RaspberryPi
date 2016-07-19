/*
 * fsr_test.c
 *
 *  Created on: 2016. 7. 19.
 *      Author: Park
 */

#include<stdio.h>
#include<wiringPi.h>

#define FSR 1

int main(void)
{
	if(wiringPiSetup() == -1)
		return 1;

	pinMode(FSR, INPUT);

	for(;;)
	{
		if(digitalRead(FSR) == 0)
		{
			printf("pressed\n");

			delay(1000);
		}
	}
}
