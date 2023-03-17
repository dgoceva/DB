import mariadb
import sys

try:
    conn = mariadb.connect(
        user='testuser',
        password='test623',
        host='localhost',
        database='testdb'
    )
    curr = conn.cursor()

    curr.execute('SELECT VERSION()')

    (ver,) = curr
    print(ver)

except mariadb.Error as e:
    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if conn:
        conn.close()
