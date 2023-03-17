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

    curr.execute('SELECT * FROM writers LIMIT 2')

    desc = curr.description
    print(desc[0][0], desc[1][0])

    for (id, name) in curr:
        print(id, name)

except mariadb.Error as e:
    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if conn:
        conn.close()
