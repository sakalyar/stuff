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
	h = gmtime(&t);
	if ( h == NULL) {
		perror("gmtime");
		exit(EXIT_FAILURE);
	}
	printf("Il est : %i heure %i min %i sec\n", h->tm_hour, h->tm_min, h->tm_sec);
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

char *ascheure(const struct tm *tmptr) {
	char *value = malloc(100);
	for (int i = 0; i < 100; i++)
		value[i] = ' ';
	value[99] = '\0';
	value = ("Aujourd\'hui c\'est ");
	int len = strlen("Aujourd\'hui c\'est ");
	len += (strlen(tmptr->tm_wday) + 1);
	strcpy(value + len, itoa(tmptr->tm_mon));
	len += strlen(tmptr->tm_mon) + 1;
	strcpy(value + len, tmptr->tm_year);
	value = &("Aujourd\'hui c\'est %s %i %s %i\nIl est %i:%i:%i",
			tmptr->tm_wday, tmptr->tm_yday, tmptr->tm_mon, tmptr->tm_year,
		    tmptr->tm_hour, tmptr->tm_min, tmptr->tm_sec);
	return value;
	strcpy(value, tmptr->tm_wday);
	char *day;
	strcpy(value, "Aujourd\'hui c\'est %s %i %s %i\nIl est %i:%i:%i",
		   tmptr->tm_wday, tmptr->tm_yday, tmptr->tm_mon, tmptr->tm_year,
		//   tmptr->tm_hour, tmptr->tm_min, tmptr->tm_sec);
	return value;
	//printf("Il est : %i heure %i min %i sec\n", tmptr->tm_hour, tmptr->tm_min, tmptr->tm_sec);
	//return NULL;
}

