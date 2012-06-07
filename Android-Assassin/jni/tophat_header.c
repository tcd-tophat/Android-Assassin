#include "tophat_header.h"
#include <stdlib.h>
extern char * lol(){

		return "fuck off cunt\n";

}

extern struct header_t test(){
	
		header_t a ;
		a.ver=1;
		a.opcode=2;
		a.pstat=3;
		a.res=4;
		a.datalen=0;
		return a;
}

extern unsigned char get_ver(struct header_t head){
		return head.ver;
}


extern unsigned char get_opcode(struct header_t head){
		return head.opcode;
}

extern struct header_t string_to_header(char * data){

		struct header_t * head_ptr = (struct header_t *)data;

		if (data != NULL){

			return *head_ptr;

		}
}

