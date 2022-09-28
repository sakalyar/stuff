#include "time.h"


// Declaration des fonctions

int i;
//(struct tm *)(* f(time_t *)); // cela nous laisse de rater fonction localetime vers f

int main(int argc, char *argv[]) {
	time_t t;
	t = time(NULL);
	
	if (t == (time_t) - 1) {
		perror("time");
	}
	struct tm *h;
	h = localtime(&t);
	char *answer = malloc(100);
	strcpy(answer, ascheure(h));
	printf("%s", answer);
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


