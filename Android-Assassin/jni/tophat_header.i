%module p_header

%inline %{
#include "tophat_header.h"
extern char * lol();
extern struct header_t test();
extern unsigned char get_ver(struct header_t head);
extern unsigned char get_opcode(struct header_t head);
extern struct header_t string_to_header(char * data);
%}

