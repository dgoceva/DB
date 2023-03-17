import mariadb
import sys


def read_image(fname):
    with open(fname, 'rb') as f:
        return f.read()


try:
    con = mariadb.connect(
        user='testuser',
        password='test623',
        host='localhost',
        database='testdb',
        autocommit=True
    )
    cur = con.cursor()
    cur.execute("INSERT INTO Images(Id,Data) VALUES(?,?)",
                (1, read_image('tree.jpg')))

    print('Done.')
except mariadb.Error as e:
    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if con:
        con.close()
