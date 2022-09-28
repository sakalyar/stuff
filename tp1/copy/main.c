#include "time.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Declaration des fonctions

// ???
//#include "time.h"

void affiche_heure_gmt(void) {
	time_t t;
	struct tm *h;
	
	if ((t = time(NULL)) == NULL) {
		perror("time");
		exit(EXIT_FAILURE);		
	}
	// f ~ gmtime;
	h = gmtime(&t);
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


int i;
//(struct tm *)(* f(time_t *)); // cela nous laisse de rater fonction localetime vers f

int main(int argc, char *argv[]) {
	if (argc < 2) {
		fprintf(stderr, "Nombre d'arguments incorrecte");
		fprintf(stderr, "Usage %s -gmt|loc\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	if (strcmp(argv[1], "-gmt") == 0) {
		affiche_heure_gmt();
	} else {
		affiche_heure_locale();
	}
}


