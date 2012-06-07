#ifndef TOPHAT_TEST
#define TOPHAT_TEST
struct header_t{
	unsigned char ver;
	unsigned char opcode;
	unsigned char pstat;
	unsigned char res;
	unsigned int datalen;
};
typedef struct header_t header_t;

extern header_t;

unsigned char get_ver(struct header_t head);

unsigned char get_opcode(struct header_t head);

unsigned char get_pstat(struct header_t head);

unsigned char get_response_code(struct header_t head);

unsigned int get_datalen(struct header_t head);

extern header_t test();
extern struct header_t string_to_header(char * data);
#endif
