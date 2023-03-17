import mariadb
import sys

try:
    con = mariadb.connect(
        user='testuser',
        password='test623',
        host='localhost',
        database='testdb',
    )
    cur = con.cursor()
    cur.execute("DELETE FROM Writers")
    cur.execute("INSERT INTO Writers(Name) VALUES('Jack London')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Honore de Balzac')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Lion Feuchtwanger')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Emile Zola')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Truman Capote')")
    cur.execute("INSERT INTO Writers(Name) VALUES('Terry Pratchett')")
    con.commit()
    print('Done.')
except mariadb.Error as e:
    if con:
        con.rollback()

    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if con:
        con.close()
