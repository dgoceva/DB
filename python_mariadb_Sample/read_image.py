import mariadb
import sys


def write_image(fname, data):
    with open(fname, 'wb') as f:
        f.write(data)


try:
    con = mariadb.connect(
        user='testuser',
        password='test623',
        host='localhost',
        database='testdb',
    )
    cur = con.cursor()
    cur.execute("SELECT Data FROM Images WHERE Id=?",
                (1, ))

    write_image('tree-2.jpg', cur.fetchone()[0])
    print('Done.')
except mariadb.Error as e:
    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if con:
        con.close()
