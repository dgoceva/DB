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
    cur.execute("UPDATE Writers SET Name=? WHERE Id=?",
                ("Guy de Maupasant", 4))

    print('Done.')
except mariadb.Error as e:
    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if con:
        con.close()
