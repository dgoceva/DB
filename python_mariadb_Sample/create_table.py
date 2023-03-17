import mariadb
import sys

try:
    con = mariadb.connect(
        user='testuser',
        password='test623',
        host='localhost',
        database='testdb',
        autocommit=True
    )
    cur = con.cursor()
    cur.execute("DROP TABLE IF EXISTS Writers")
    cur.execute("CREATE TABLE Writers(Id INT PRIMARY KEY AUTO_INCREMENT, \
                 Name VARCHAR(25))")
    cur.execute("INSERT INTO Writers(Name) VALUES('Jack London')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Honore de Balzac')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Lion Feuchtwanger')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Emile Zola')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Truman Capote')")
    print('Done.')
except mariadb.Error as e:
    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if con:
        con.close()
