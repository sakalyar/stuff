// ???
#include "time.h"

void affiche_heure_gmt(void) {
	time_t t;
	struct tm *h;
	
	if ((t = time(NULL)) == NULL) {
		perror("time");
		exit(EXIT_FAILURE);		
	}
	// f ~ gmtime;
	h = f(&t);
	if ( h == NULL) {
		perror("gmtime");
		exit(EXIT_FAILURE);
	}
	printf("Il est : %i heure %i min %i sec", h->tm_hour, h->tm_min, h->tm_sec);
	exit(EXIT_SUCCESS);
	
	exit(EXIT_FAILURE);
}

void affiche_heure_locale(void) {
	time_t t;
	t = time(NULL);
	
	if (t == (time_t) - 1) {
		perror("time");
	}
	struct tm *h;
	h = localtime(&t);
	if (h == NULL) {
		perror("localtime");
		exit(EXIT_FAILURE);
	}
	printf("Il est : %i heure %i min %i sec\n", h->tm_hour, h->tm_min, h->tm_sec);
	exit(EXIT_SUCCESS);
	
	exit(EXIT_FAILURE);
}
