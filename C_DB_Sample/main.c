#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql.h>

void checkConnection()
{
    printf("MariaDB client version: %s\n\n", mysql_get_client_info());
}

void finish_with_error(MYSQL *con)
{
    fprintf(stderr, "%s\n\n", mysql_error(con));
    mysql_close(con);
    exit(EXIT_FAILURE);
}

void createDB()
{
  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "%s\n\n", mysql_error(con));
      exit(EXIT_FAILURE);
  }

  if (mysql_real_connect(con, "localhost", "root", "", // 127.0.0.1
          NULL, 0, NULL, 0) == NULL)
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "CREATE DATABASE testdb"))
  {
      finish_with_error(con);
  }

  mysql_close(con);
}

void create_populate()
{
  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "%s\n\n", mysql_error(con));
      exit(1);
  }

  if (mysql_real_connect(con, "localhost", "user12", "34klq*",
          "testdb", 0, NULL, 0) == NULL)
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "DROP TABLE IF EXISTS cars")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "CREATE TABLE cars(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), price INT)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(1,'Audi',52642)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(2,'Mercedes',57127)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(3,'Skoda',9000)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(4,'Volvo',29000)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(5,'Bentley',350000)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(6,'Citroen',21000)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(7,'Hummer',41400)")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO cars VALUES(8,'Volkswagen',21600)")) {
      finish_with_error(con);
  }

  mysql_close(con);
}

void retrieve_data()
{
  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "mysql_init() failed\n\n");
      exit(1);
  }

  if (mysql_real_connect(con, "localhost", "user12", "34klq*",
          "testdb", 0, NULL, 0) == NULL)
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "SELECT * FROM cars"))
  {
      finish_with_error(con);
  }

  MYSQL_RES *result = mysql_store_result(con);

  if (result == NULL)
  {
      finish_with_error(con);
  }

  int num_fields = mysql_num_fields(result);

  MYSQL_ROW row;

  while ((row = mysql_fetch_row(result)))
  {
      for(int i = 0; i < num_fields; i++)
      {
          printf("%s ", row[i] ? row[i] : "NULL");
      }

      printf("\n");
  }

  printf("\n");
  mysql_free_result(result);
  mysql_close(con);
}

void last_row_id()
{
  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "mysql_init() failed\n\n");
      exit(1);
  }

  if (mysql_real_connect(con, "localhost", "user12", "34klq*",
          "testdb", 0, NULL, 0) == NULL)
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "DROP TABLE IF EXISTS writers"))
  {
      finish_with_error(con);
  }

  char *sql = "CREATE TABLE writers(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))";

  if (mysql_query(con, sql))
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO writers(name) VALUES('Leo Tolstoy')"))
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO writers(name) VALUES('Jack London')"))
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "INSERT INTO writers(name) VALUES('Honore de Balzac'),('Ivan Vazov')"))
  {
      finish_with_error(con);
  }

  int id = mysql_insert_id(con);

  printf("The last inserted row id is: %d\n\n", id);

  mysql_close(con);
}
/*
void headers()
{
  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "mysql_init() failed\n\n");
      exit(1);
  }

  if (mysql_real_connect(con, "localhost", "user12", "34klq*",
          "testdb", 0, NULL, 0) == NULL)
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "SELECT * FROM cars LIMIT 3"))
  {
      finish_with_error(con);
  }

  MYSQL_RES *result = mysql_store_result(con);

  if (result == NULL)
  {
      finish_with_error(con);
  }

  int num_fields = mysql_num_fields(result);

  MYSQL_ROW row;
  MYSQL_FIELD *field;

  while ((row = mysql_fetch_row(result)))
  {
      for(int i = 0; i < num_fields; i++)
      {
          if (i == 0)
          {
             while(field = mysql_fetch_field(result))
             {
                printf("%s ", field->name);
             }

             printf("\n");
          }

          printf("%s  ", row[i] ? row[i] : "NULL");
      }
  }

  printf("\n\n");

  mysql_free_result(result);
  mysql_close(con);
}
*/

void multiple_statemens()
{
  int status = 0;

  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "mysql_init() failed\n");
      exit(EXIT_FAILURE);
  }

  if (mysql_real_connect(con, "localhost", "user12", "34klq*",
          "testdb", 0, NULL, CLIENT_MULTI_STATEMENTS) == NULL)
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "SELECT name FROM cars WHERE id=2;\
      SELECT name FROM cars WHERE id=3;SELECT name FROM cars WHERE id=6"))
  {
      finish_with_error(con);
  }

  do {
      MYSQL_RES *result = mysql_store_result(con);

      if (result == NULL)
      {
          finish_with_error(con);
      }

      MYSQL_ROW row = mysql_fetch_row(result);

      printf("%s\n", row[0]);

      mysql_free_result(result);

      status = mysql_next_result(con);

      if (status > 0) {
          finish_with_error(con);
      }

  } while(status == 0);

  mysql_close(con);
  printf("\n\n");
}

// before using insert_image()
// change variable max_allowed_packet in my.ini to 10M or bigger and restart server
// default value of max_allowed_packet is 1M
void insert_image()
{

  FILE *fp = fopen("tree.jpg", "rb");

  if (fp == NULL)
  {
      fprintf(stderr, "cannot open image file\n");
      exit(EXIT_FAILURE);
  }

  fseek(fp, 0, SEEK_END);

  if (ferror(fp)) {

      fprintf(stderr, "fseek() failed\n");
      int r = fclose(fp);

      if (r == EOF) {
          fprintf(stderr, "cannot close file handler\n");
      }

      exit(EXIT_FAILURE);
  }

  int flen = ftell(fp);

  if (flen == -1) {

      perror("error occurred");
      int r = fclose(fp);

      if (r == EOF) {
          fprintf(stderr, "cannot close file handler\n");
      }

      exit(EXIT_FAILURE);
  }

  fseek(fp, 0, SEEK_SET);

  if (ferror(fp)) {

      fprintf(stderr, "fseek() failed\n");
      int r = fclose(fp);

      if (r == EOF) {
          fprintf(stderr, "cannot close file handler\n");
      }

      exit(EXIT_FAILURE);
  }

//  char data[flen+1];
  char *data = (char *)malloc(flen+1);
  if (data==NULL) {
      fprintf(stderr, "malloc() for data failed\n");
      int r = fclose(fp);

      if (r == EOF) {
          fprintf(stderr, "cannot close file handler\n");
      }

      exit(EXIT_FAILURE);
  }
  int size = fread(data, 1, flen, fp);

  if (ferror(fp)) {

      fprintf(stderr, "fread() failed\n");
      int r = fclose(fp);

      if (r == EOF) {
          fprintf(stderr, "cannot close file handler\n");
      }

      exit(EXIT_FAILURE);
  }

  int r = fclose(fp);

  if (r == EOF) {
      fprintf(stderr, "cannot close file handler\n");
  }

  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "mysql_init() failed\n");
      exit(EXIT_FAILURE);
  }

  if (mysql_real_connect(con, "localhost", "root", "",
          "testdb", 0, NULL, 0) == NULL)
  {
      finish_with_error(con);
  }

//  char chunk[2*size+1];
  char *chunk = (char *)malloc(2*size+1);
  if (chunk==NULL) {
      fprintf(stderr, "malloc() for chunk failed\n");
      int r = fclose(fp);

      if (r == EOF) {
          fprintf(stderr, "cannot close file handler\n");
      }

      exit(EXIT_FAILURE);
  }
  mysql_real_escape_string(con, chunk, data, size);
  free(data);

  if (mysql_query(con, "DROP TABLE IF EXISTS images")) {
      finish_with_error(con);
  }

  if (mysql_query(con, "CREATE TABLE images(id INT PRIMARY KEY, data MEDIUMBLOB)")) {
      finish_with_error(con);
  }

//  char *st = "INSERT INTO images(id, data) VALUES(1, '%s')";
//  size_t st_len = strlen(st);

  size_t st_len = strlen("INSERT INTO images(id, data) VALUES(1, \"\")");

  char query[st_len + 2*size+1];
  int len = st_len+2*size+1;
//  int len = snprintf(query, st_len + 2*size+1, st, chunk);

  strcpy(query,"INSERT INTO images(id, data) VALUES(1, \"");
  strcat(query,chunk);
  strcat(query,"\");");
  free(chunk);

  if (mysql_real_query(con, query, len))
  {
      finish_with_error(con);
  }

  mysql_close(con);
  printf("Image inserted successfully\n\n");
}

void select_image()
{
  FILE *fp = fopen("tree2.jpg", "wb");

  if (fp == NULL)
  {
      fprintf(stderr, "cannot open image file\n");
      exit(EXIT_FAILURE);
  }

  MYSQL *con = mysql_init(NULL);

  if (con == NULL)
  {
      fprintf(stderr, "mysql_init() failed\n");
      exit(EXIT_FAILURE);
  }

  if (mysql_real_connect(con, "localhost", "user12", "34klq*",
          "testdb", 0, NULL, 0) == NULL)
  {
      finish_with_error(con);
  }

  if (mysql_query(con, "SELECT data FROM images WHERE id=1"))
  {
      finish_with_error(con);
  }

  MYSQL_RES *result = mysql_store_result(con);

  if (result == NULL)
  {
      finish_with_error(con);
  }

  MYSQL_ROW row = mysql_fetch_row(result);
  unsigned long *lengths = mysql_fetch_lengths(result);

  if (lengths == NULL) {
      finish_with_error(con);
  }

  fwrite(row[0], lengths[0], 1, fp);

  if (ferror(fp))
  {
      fprintf(stderr, "fwrite() failed\n");
      mysql_free_result(result);
      mysql_close(con);

      exit(EXIT_FAILURE);
  }

  int r = fclose(fp);

  if (r == EOF) {
      fprintf(stderr, "cannot close file handler\n");
      mysql_free_result(result);
      mysql_close(con);
  } else {
      mysql_free_result(result);
      mysql_close(con);

      puts("Image is selected successfully");
  }
}

int main()
{
    checkConnection();
//    createDB();
//    create_populate();
    retrieve_data();
//    last_row_id();
//    headers();
    multiple_statemens();
//    insert_image();
    select_image();

    return EXIT_SUCCESS;
}
